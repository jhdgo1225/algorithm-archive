SELECT
    ID,
    CASE
        WHEN RN <= 0.25 THEN "CRITICAL"
        WHEN RN <= 0.5 THEN "HIGH"
        WHEN RN <= 0.75 THEN "MEDIUM"
        ELSE "LOW"
    END AS COLONY_NAME
FROM (
    SELECT ID,
        PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS RN
    FROM ECOLI_DATA
) AS CUSTOM_TB
ORDER BY ID;

# ANOTHER SOLVE METHOD(It's bad thing)
-- SELECT
--     ID,
--     IF(
--         ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () / 4), 
--         "CRITICAL",
--         IF (
--             ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () * 2 / 4), 
--             "HIGH",
--             IF (
--                 ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () * 3 / 4), 
--                 "MEDIUM",
--                 "LOW"
--             )
--         )
--     ) AS COLONY_NAME
-- FROM ECOLI_DATA
-- ORDER BY ID ASC;