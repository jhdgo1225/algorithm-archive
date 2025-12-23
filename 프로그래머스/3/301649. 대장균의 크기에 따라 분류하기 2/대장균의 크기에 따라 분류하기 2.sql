SELECT
    ID,
    IF(
        ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () / 4), 
        "CRITICAL",
        IF (
            ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () * 2 / 4), 
            "HIGH",
            IF (
                ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) <= (COUNT(*) OVER () * 3 / 4), 
                "MEDIUM",
                "LOW"
            )
        )
    ) AS COLONY_NAME
FROM ECOLI_DATA
ORDER BY ID ASC;