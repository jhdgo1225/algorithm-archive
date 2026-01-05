SELECT
    CASE
        WHEN TB1.SKILL_CODE & (
                SELECT SUM(CODE)
                FROM SKILLCODES
                WHERE CATEGORY='Front End'
            ) > 0 THEN (
                CASE
                    WHEN TB1.SKILL_CODE & (
                            SELECT CODE
                            FROM SKILLCODES
                            WHERE NAME='Python'
                        ) > 0 THEN 'A'
                    WHEN TB1.SKILL_CODE & (
                            SELECT CODE
                            FROM SKILLCODES
                            WHERE NAME='C#'
                        ) > 0 THEN 'B'
                    ELSE 'C'
                END
            )
        WHEN TB1.SKILL_CODE & (
                SELECT CODE
                FROM SKILLCODES
                WHERE NAME='C#'
            ) > 0 THEN 'B'
    END AS GRADE,
    TB1.ID,
    TB1.EMAIL
FROM DEVELOPERS AS TB1
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID;