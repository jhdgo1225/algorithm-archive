WITH CTE AS (SELECT 'Front End' AS NAME, SUM(CODE) AS CODE
FROM SKILLCODES
WHERE CATEGORY='Front End'
UNION
SELECT NAME, CODE
FROM SKILLCODES
WHERE NAME IN ('Python', 'C#'))
SELECT 
    CASE
        WHEN SKILL_CODE & (
            SELECT CODE
            FROM CTE
            WHERE NAME='Front End'
        ) > 0 THEN (
            CASE
                WHEN SKILL_CODE & (
                    SELECT CODE
                    FROM CTE
                    WHERE NAME='Python'
                ) > 0 THEN 'A'
                WHEN SKILL_CODE & (
                    SELECT CODE
                    FROM CTE
                    WHERE NAME='C#'
                ) THEN 'B'
                ELSE 'C'
            END
        )
        WHEN SKILL_CODE & (
            SELECT CODE
            FROM CTE
            WHERE NAME='C#'
        ) THEN 'B'
    END AS GRADE,
    ID,
    EMAIL
FROM DEVELOPERS
HAVING GRADE IS NOT NULL
ORDER BY GRADE ASC, ID ASC;