SELECT CONCAT('/home/grep/src/',
              CONCAT(TB2.BOARD_ID,
                     CONCAT('/',
                            CONCAT(TB2.FILE_ID,
                                   CONCAT(TB2.FILE_NAME, TB2.FILE_EXT)
                                  )
                           )
                    )
             ) AS FILE_PATH
FROM USED_GOODS_BOARD AS TB1 NATURAL JOIN USED_GOODS_FILE AS TB2
WHERE TB1.BOARD_ID=TB2.BOARD_ID
    AND TB1.VIEWS=(
        SELECT MAX(VIEWS)
        FROM USED_GOODS_BOARD
    )
ORDER BY TB2.FILE_ID DESC;