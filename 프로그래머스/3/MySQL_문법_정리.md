# 레벨 3 - MySQL 문법 종합 정리

## 목차

1. [기본 SELECT 문](#1-기본-select-문)
2. [JOIN](#2-join)
3. [서브쿼리](#3-서브쿼리)
4. [윈도우 함수](#4-윈도우-함수)
5. [조건 처리 (CASE, IF)](#5-조건-처리-case-if)
6. [집계 함수](#6-집계-함수)
7. [정렬 (ORDER BY)](#7-정렬-order-by)
8. [NOT IN](#8-not-in)
9. [GROUP BY & HAVING](#9-group-by--having)
10. [날짜 함수](#10-날짜-함수)
11. [문자열 함수](#11-문자열-함수)
12. [기타 함수](#12-기타-함수)

---

## 1. 기본 SELECT 문

### SELECT 특정 컬럼

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION='Sick';
```

### SELECT with 별칭

```sql
SELECT ID, NAME, HOST_ID
FROM PLACES;
```

---

## 2. JOIN

### LEFT JOIN

```sql
-- 왼쪽 테이블 기준 조인 (오른쪽에 매칭되지 않아도 왼쪽은 모두 출력)
SELECT TB1.NAME, TB1.DATETIME
FROM ANIMAL_INS AS TB1 LEFT JOIN ANIMAL_OUTS AS TB2
    ON TB1.ANIMAL_ID=TB2.ANIMAL_ID
WHERE TB2.ANIMAL_ID IS NULL
ORDER BY TB1.DATETIME
LIMIT 3;
```

- **LEFT JOIN**: 왼쪽 테이블의 모든 레코드를 포함
- 오른쪽 테이블에 매칭되지 않는 경우 NULL로 표시
- `WHERE TB2.ANIMAL_ID IS NULL`: 오른쪽에 없는 데이터만 필터링

**관련 문제:**

- [59044. 오랜 기간 보호한 동물（1）](./59044.%20오랜%20기간%20보호한%20동물（1）)
- [299305. 대장균들의 자식의 수 구하기](./299305.%20대장균들의%20자식의%20수%20구하기)

### RIGHT JOIN

```sql
-- 오른쪽 테이블 기준 조인 (왼쪽에 매칭되지 않아도 오른쪽은 모두 출력)
SELECT TB2.ANIMAL_ID, TB2.NAME
FROM ANIMAL_INS AS TB1 RIGHT JOIN ANIMAL_OUTS AS TB2
ON TB1.ANIMAL_ID=TB2.ANIMAL_ID
WHERE TB1.ANIMAL_ID IS NULL;
```

- **RIGHT JOIN**: 오른쪽 테이블의 모든 레코드를 포함
- 왼쪽 테이블에 매칭되지 않는 경우 NULL로 표시
- `WHERE TB1.ANIMAL_ID IS NULL`: 왼쪽에 없는 데이터만 필터링

**관련 문제:**

- [59042. 없어진 기록 찾기](./59042.%20없어진%20기록%20찾기)

### INNER JOIN

```sql
-- 두 테이블에 모두 존재하는 데이터만 조회
SELECT TB1.ANIMAL_ID, TB1.NAME
FROM ANIMAL_INS AS TB1 INNER JOIN ANIMAL_OUTS AS TB2
WHERE TB1.ANIMAL_ID=TB2.ANIMAL_ID
ORDER BY DATEDIFF(TB2.DATETIME, TB1.DATETIME) DESC
LIMIT 2;
```

**관련 문제:**

- [59411. 오랜 기간 보호한 동물（2）](./59411.%20오랜%20기간%20보호한%20동물（2）)

### NATURAL JOIN

```sql
-- 같은 이름의 컬럼을 자동으로 조인
SELECT TB1.CATEGORY, SUM(TB2.SALES) AS TOTAL_SALES
FROM BOOK AS TB1 NATURAL JOIN BOOK_SALES AS TB2
WHERE TB1.BOOK_ID=TB2.BOOK_ID
    AND TB2.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY TB1.CATEGORY
ORDER BY TB1.CATEGORY;
```

- **NATURAL JOIN**: 두 테이블의 같은 이름의 컬럼을 자동으로 조인
- ON 절 없이 사용 가능

**관련 문제:**

- [144855. 카테고리 별 도서 판매량 집계하기](./144855.%20카테고리%20별%20도서%20판매량%20집계하기)
- [164668. 조건에 맞는 사용자와 총 거래금액 조회하기](./164668.%20조건에%20맞는%20사용자와%20총%20거래금액%20조회하기)
- [164670. 조건에 맞는 사용자 정보 조회하기](./164670.%20조건에%20맞는%20사용자%20정보%20조회하기)
- [157341. 대여 기록이 존재하는 자동차 리스트 구하기](./157341.%20대여%20기록이%20존재하는%20자동차%20리스트%20구하기)
- [284529. 부서별 평균 연봉 조회하기](./284529.%20부서별%20평균%20연봉%20조회하기)

### 명시적 조인 (WHERE)

```sql
-- 두 테이블을 WHERE로 조인
SELECT TB1.ANIMAL_ID, TB1.NAME
FROM ANIMAL_INS AS TB1, ANIMAL_OUTS AS TB2
WHERE TB1.ANIMAL_ID=TB2.ANIMAL_ID
  AND TB1.DATETIME > TB2.DATETIME
ORDER BY TB1.DATETIME;
```

- 동일한 테이블 간 비교도 가능

**관련 문제:**

- [59043. 있었는데요 없었습니다](./59043.%20있었는데요%20없었습니다)

### 자기 자신과의 조인 (Self Join)

```sql
-- 같은 테이블을 서로 조인
SELECT TB1.ID, COUNT(TB2.ID) AS CHILD_COUNT
FROM ECOLI_DATA AS TB1 LEFT JOIN ECOLI_DATA AS TB2 ON TB1.ID=TB2.PARENT_ID
GROUP BY TB1.ID;
```

- **Self Join**: 같은 테이블을 두 번 사용하여 조인
- 부모-자식 관계 등을 찾을 때 유용

**관련 문제:**

- [299305. 대장균들의 자식의 수 구하기](./299305.%20대장균들의%20자식의%20수%20구하기)

---

## 3. 서브쿼리

### IN 서브쿼리

```sql
-- 서브쿼리로 조건 필터링
SELECT ID, NAME, HOST_ID
FROM PLACES
WHERE HOST_ID IN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(HOST_ID) >= 2
)
ORDER BY ID;
```

**관련 문제:**

- [77487. 헤비 유저가 소유한 장소](./77487.%20헤비%20유저가%20소유한%20장소)
- [151139. 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기](./151139.%20대여%20횟수가%20많은%20자동차들의%20월별%20대여%20횟수%20구하기)

### NOT IN 서브쿼리

```sql
-- 서브쿼리에 없는 데이터 찾기 (아이템)
SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID NOT IN (
    SELECT DISTINCT PARENT_ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IS NOT NULL
)
ORDER BY ITEM_ID DESC;
```

```sql
-- 서브쿼리에 없는 데이터 찾기 (동물 - RIGHT JOIN 대체)
SELECT ANIMAL_ID, NAME
FROM ANIMAL_OUTS
WHERE ANIMAL_ID NOT IN (
    SELECT ANIMAL_ID
    FROM ANIMAL_INS
);
```

- **NOT IN**: 서브쿼리 결과에 포함되지 않는 데이터만 선택
- **DISTINCT**: 중복 제거
- 서브쿼리에서 NULL 처리 주의 필요
- **RIGHT/LEFT JOIN의 대안**으로 사용 가능

**관련 문제:**

- [273712. 업그레이드 할 수 없는 아이템 구하기](./273712.%20업그레이드%20할%20수%20없는%20아이템%20구하기)
- [59042. 없어진 기록 찾기](./59042.%20없어진%20기록%20찾기) - 대체 방법

### 튜플 서브쿼리 (다중 컬럼 비교)

```sql
-- 여러 컬럼을 동시에 비교 (식당)
SELECT FOOD_TYPE,
    REST_ID,
    REST_NAME,
    FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (
    SELECT FOOD_TYPE, MAX(FAVORITES)
    FROM REST_INFO
    GROUP BY FOOD_TYPE
)
ORDER BY FOOD_TYPE DESC;
```

```sql
-- 여러 컬럼을 동시에 비교 (물고기)
SELECT
    TB1.ID,
    TB2.FISH_NAME,
    TB1.LENGTH
FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
WHERE TB1.FISH_TYPE=TB2.FISH_TYPE
    AND (TB2.FISH_NAME, TB1.LENGTH) IN (
        SELECT FISH_NAME, MAX(LENGTH)
        FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
        WHERE TB1.FISH_TYPE=TB2.FISH_TYPE
        GROUP BY TB2.FISH_NAME
    )
ORDER BY TB1.ID;
```

- **(col1, col2)**: 여러 컬럼을 동시에 비교
- 각 그룹별 최댓값을 가진 행 찾기
- 윈도우 함수의 대안으로 사용 가능

**관련 문제:**

- [131123. 즐겨찾기가 가장 많은 식당 정보 출력하기](./131123.%20즐겨찾기가%20가장%20많은%20식당%20정보%20출력하기)
- [293261. 물고기 종류 별 대어 찾기](./293261.%20물고기%20종류%20별%20대어%20찾기)

### 스칼라 서브쿼리 (단일 값)

```sql
-- 서브쿼리로 단일 값 반환
SELECT CONCAT('/home/grep/src/', ...) AS FILE_PATH
FROM USED_GOODS_BOARD AS TB1 NATURAL JOIN USED_GOODS_FILE AS TB2
WHERE TB1.BOARD_ID=TB2.BOARD_ID
    AND TB1.VIEWS=(
        SELECT MAX(VIEWS)
        FROM USED_GOODS_BOARD
    )
ORDER BY TB2.FILE_ID DESC;
```

- **스칼라 서브쿼리**: WHERE 절에서 단일 값과 비교

**관련 문제:**

- [164671. 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기](./164671.%20조회수가%20가장%20많은%20중고거래%20게시판의%20첨부파일%20조회하기)

---

## 4. 윈도우 함수

### PERCENT_RANK()

```sql
-- 백분위 순위 계산 (0.0 ~ 1.0)
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
```

- **PERCENT_RANK()**: 0.0 ~ 1.0 사이의 백분위 순위 반환
- 사분위수 계산에 최적화된 함수
- ROW_NUMBER()보다 직관적이고 효율적

**관련 문제:**

- [301649. 대장균의 크기에 따라 분류하기 2](./301649.%20대장균의%20크기에%20따라%20분류하기%202)

### ROW_NUMBER()

```sql
-- 순위 부여
ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC)
```

**관련 문제:**

- [301649. 대장균의 크기에 따라 분류하기 2](./301649.%20대장균의%20크기에%20따라%20분류하기%202) - 대체 방법

### ROW_NUMBER() with PARTITION BY

```sql
-- 그룹별 순위 부여 (대체 방법)
SELECT
    ID,
    FISH_NAME,
    LENGTH
FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY FISH_TYPE ORDER BY LENGTH DESC) AS RN FROM FISH_INFO) AS TB1
JOIN FISH_NAME_INFO AS TB2
    ON TB1.FISH_TYPE=TB2.FISH_TYPE
WHERE RN=1
ORDER BY ID;
```

- **PARTITION BY**: 그룹별로 순위를 매김
- **ORDER BY**: 정렬 기준
- **RN=1**: 각 그룹의 1등만 선택
- **튜플 서브쿼리로도 해결 가능**

**관련 문제:**

- [293261. 물고기 종류 별 대어 찾기](./293261.%20물고기%20종류%20별%20대어%20찾기) - 대체 방법
- [131123. 즐겨찾기가 가장 많은 식당 정보 출력하기](./131123.%20즐겨찾기가%20가장%20많은%20식당%20정보%20출력하기) - 대체 방법

### COUNT() OVER()

```sql
-- 전체 행 수 계산
COUNT(*) OVER ()
```

**관련 문제:**

- [301649. 대장균의 크기에 따라 분류하기 2](./301649.%20대장균의%20크기에%20따라%20분류하기%202)

### 복합 윈도우 함수 예시 (PERCENT_RANK)

```sql
-- 추천: PERCENT_RANK를 사용한 사분위수 계산
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
```

### 복합 윈도우 함수 예시 (ROW_NUMBER 대체 방법)

```sql
-- 대체 방법: ROW_NUMBER와 COUNT OVER 사용
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
```

**윈도우 함수 활용 포인트:**

- `PERCENT_RANK() OVER (ORDER BY ...)`: 백분위 순위 계산 (0.0 ~ 1.0)
- `ROW_NUMBER() OVER (ORDER BY ...)`: 정렬 순서대로 번호 부여
- `COUNT(*) OVER ()`: 전체 행 개수 반환 (파티션 없음)
- 사분위수(Quartile) 계산: 전체의 1/4, 2/4, 3/4 구간으로 분류
- **PERCENT_RANK가 더 직관적이고 효율적**

---

## 5. 조건 처리 (CASE, IF)

### CASE 문

```sql
-- 여러 조건 분기
SELECT ID,
    CASE
        WHEN SIZE_OF_COLONY <= 100 THEN 'LOW'
        WHEN SIZE_OF_COLONY <= 1000 THEN 'MEDIUM'
        ELSE 'HIGH'
    END AS SIZE
FROM ECOLI_DATA;
```

- **CASE WHEN ... THEN ... ELSE ... END**: 복잡한 조건 분기
- **가독성이 좋음**

**관련 문제:**

- [299307. 대장균의 크기에 따라 분류하기 1](./299307.%20대장균의%20크기에%20따라%20분류하기%201)
- [157340. 자동차 대여 기록에서 대여중 ／ 대여 가능 여부 구분하기](./157340.%20자동차%20대여%20기록에서%20대여중%20／%20대여%20가능%20여부%20구분하기)

### CASE with 날짜 비교

```sql
-- 날짜 조건에 따른 분류
SELECT ORDER_ID,
    PRODUCT_ID,
    DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE,
    CASE
        WHEN OUT_DATE IS NULL THEN '출고미정'
        WHEN DATEDIFF('2022-05-01', OUT_DATE) >= 0 THEN '출고완료'
        ELSE '출고대기'
    END AS 출고여부
FROM FOOD_ORDER
ORDER BY ORDER_ID;
```

**관련 문제:**

- [131113. 조건별로 분류하여 주문상태 출력하기](./131113.%20조건별로%20분류하여%20주문상태%20출력하기)

### CASE with MAX() 조건

```sql
-- MAX 결과를 이용한 조건 분기
SELECT
    CAR_ID,
    CASE
        WHEN MAX(START_DATE<=DATE('2022-10-16') AND END_DATE>=DATE('2022-10-16'))=1 THEN '대여중'
        ELSE '대여 가능'
    END AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;
```

- **MAX()와 조건식**: 해당 조건이 하나라도 참이면 1 반환
- GROUP BY와 함께 사용하여 그룹별 조건 판단

**관련 문제:**

- [157340. 자동차 대여 기록에서 대여중 ／ 대여 가능 여부 구분하기](./157340.%20자동차%20대여%20기록에서%20대여중%20／%20대여%20가능%20여부%20구분하기)

### 중첩 IF 문

```sql
-- 여러 조건 분기
IF(
    조건1,
    "결과1",
    IF(
        조건2,
        "결과2",
        IF(
            조건3,
            "결과3",
            "기본값"
        )
    )
)
```

**관련 문제:**

- [301649. 대장균의 크기에 따라 분류하기 2](./301649.%20대장균의%20크기에%20따라%20분류하기%202)

### 사분위수 분류 예시

```sql
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
```

- 상위 25%: CRITICAL
- 상위 26-50%: HIGH
- 상위 51-75%: MEDIUM
- 하위 25%: LOW

---

## 6. 집계 함수

### COUNT()

```sql
-- 행 개수 세기
SELECT COUNT(ID) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
GROUP BY FISH_TYPE HAVING AVG(COALESCE(LENGTH, 10)) >= 33
ORDER BY FISH_TYPE;
```

**관련 문제:**

- [298519. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기](./298519.%20특정%20조건을%20만족하는%20물고기별%20수와%20최대%20길이%20구하기)
- [299305. 대장균들의 자식의 수 구하기](./299305.%20대장균들의%20자식의%20수%20구하기)

### COUNT() with GROUP BY & HAVING

```sql
-- 그룹별 카운트 후 필터링
SELECT HOST_ID
FROM PLACES
GROUP BY HOST_ID
HAVING COUNT(HOST_ID) >= 2
```

**관련 문제:**

- [77487. 헤비 유저가 소유한 장소](./77487.%20헤비%20유저가%20소유한%20장소)
- [164670. 조건에 맞는 사용자 정보 조회하기](./164670.%20조건에%20맞는%20사용자%20정보%20조회하기)

### SUM()

```sql
-- 합계 계산
SELECT TB1.CATEGORY, SUM(TB2.SALES) AS TOTAL_SALES
FROM BOOK AS TB1 NATURAL JOIN BOOK_SALES AS TB2
WHERE TB1.BOOK_ID=TB2.BOOK_ID
    AND TB2.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY TB1.CATEGORY
ORDER BY TB1.CATEGORY;
```

**관련 문제:**

- [144855. 카테고리 별 도서 판매량 집계하기](./144855.%20카테고리%20별%20도서%20판매량%20집계하기)
- [164668. 조건에 맞는 사용자와 총 거래금액 조회하기](./164668.%20조건에%20맞는%20사용자와%20총%20거래금액%20조회하기)

### AVG() & ROUND()

```sql
-- 평균 계산 후 반올림
SELECT TB1.DEPT_ID, TB1.DEPT_NAME_EN, ROUND(AVG(TB2.SAL)) AS AVG_SAL
FROM HR_DEPARTMENT AS TB1 NATURAL JOIN HR_EMPLOYEES AS TB2
WHERE TB1.DEPT_ID=TB2.DEPT_ID
GROUP BY TB1.DEPT_ID
ORDER BY AVG_SAL DESC;
```

- **AVG()**: 평균 계산
- **ROUND()**: 반올림

**관련 문제:**

- [284529. 부서별 평균 연봉 조회하기](./284529.%20부서별%20평균%20연봉%20조회하기)

### MAX()

```sql
-- 최댓값 찾기
SELECT FOOD_TYPE, MAX(FAVORITES)
FROM REST_INFO
GROUP BY FOOD_TYPE
```

**관련 문제:**

- [131123. 즐겨찾기가 가장 많은 식당 정보 출력하기](./131123.%20즐겨찾기가%20가장%20많은%20식당%20정보%20출력하기)
- [164671. 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기](./164671.%20조회수가%20가장%20많은%20중고거래%20게시판의%20첨부파일%20조회하기)
- [298519. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기](./298519.%20특정%20조건을%20만족하는%20물고기별%20수와%20최대%20길이%20구하기)

### DISTINCT

```sql
-- 중복 제거
SELECT DISTINCT TB1.CAR_ID
FROM CAR_RENTAL_COMPANY_CAR AS TB1
    NATURAL JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS TB2
WHERE TB1.CAR_ID=TB2.CAR_ID
    AND TB1.CAR_TYPE='세단'
    AND MONTH(TB2.START_DATE)=10
ORDER BY TB1.CAR_ID DESC;
```

**관련 문제:**

- [157341. 대여 기록이 존재하는 자동차 리스트 구하기](./157341.%20대여%20기록이%20존재하는%20자동차%20리스트%20구하기)
- [273712. 업그레이드 할 수 없는 아이템 구하기](./273712.%20업그레이드%20할%20수%20없는%20아이템%20구하기)

---

## 7. 정렬 (ORDER BY)

### 단일 컬럼 정렬

```sql
-- 오름차순
ORDER BY ID
ORDER BY ID ASC

-- 내림차순
ORDER BY ITEM_ID DESC
```

### 다중 컬럼 정렬

```sql
-- 여러 기준으로 정렬
ORDER BY TB1.DATETIME
ORDER BY YEAR, YEAR_DEV
```

---

## 8. NOT IN

### NOT IN 활용

```sql
-- 특정 목록에 없는 데이터 찾기
WHERE ITEM_ID NOT IN (
    SELECT DISTINCT PARENT_ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IS NOT NULL
)
```

- 업그레이드할 수 없는 아이템 찾기
- NULL 값이 있으면 의도치 않은 결과가 나올 수 있으므로 주의

**관련 문제:**

- [273712. 업그레이드 할 수 없는 아이템 구하기](../3/273712.%20업그레이드%20할%20수%20없는%20아이템%20구하기)

---

## 9. GROUP BY & HAVING

### GROUP BY

```sql
-- 그룹화
GROUP BY HOST_ID
```

**관련 문제:**

- [77487. 헤비 유저가 소유한 장소](./77487.%20헤비%20유저가%20소유한%20장소)

### HAVING

```sql
-- 그룹화 후 조건 필터링
HAVING COUNT(HOST_ID) >= 2
```

**관련 문제:**

- [77487. 헤비 유저가 소유한 장소](./77487.%20헤비%20유저가%20소유한%20장소)

### GROUP BY with 여러 컬럼

```sql
-- 여러 컬럼으로 그룹화
SELECT MONTH(START_DATE) AS MONTH,
    CAR_ID,
    COUNT(CAR_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (...)
GROUP BY MONTH(START_DATE), CAR_ID
ORDER BY MONTH(START_DATE), CAR_ID DESC;
```

**관련 문제:**

- [151139. 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기](./151139.%20대여%20횟수가%20많은%20자동차들의%20월별%20대여%20횟수%20구하기)

### GROUP BY with HAVING AVG

```sql
-- 평균값으로 필터링
SELECT COUNT(ID) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
GROUP BY FISH_TYPE HAVING AVG(COALESCE(LENGTH, 10)) >= 33
ORDER BY FISH_TYPE;
```

**관련 문제:**

- [298519. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기](./298519.%20특정%20조건을%20만족하는%20물고기별%20수와%20최대%20길이%20구하기)

---

## 10. 날짜 함수

### DATE_FORMAT()

```sql
-- 날짜 형식 변환
DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE
```

**관련 문제:**

- [131113. 조건별로 분류하여 주문상태 출력하기](./131113.%20조건별로%20분류하여%20주문상태%20출력하기)

### DATEDIFF()

```sql
-- 두 날짜 간의 차이 계산 (일 단위)
SELECT TB1.ANIMAL_ID, TB1.NAME
FROM ANIMAL_INS AS TB1 INNER JOIN ANIMAL_OUTS AS TB2
WHERE TB1.ANIMAL_ID=TB2.ANIMAL_ID
ORDER BY DATEDIFF(TB2.DATETIME, TB1.DATETIME) DESC
LIMIT 2;
```

**관련 문제:**

- [59411. 오랜 기간 보호한 동물（2）](./59411.%20오랜%20기간%20보호한%20동물（2）)
- [131113. 조건별로 분류하여 주문상태 출력하기](./131113.%20조건별로%20분류하여%20주문상태%20출력하기)

### YEAR()

```sql
-- 날짜에서 연도 추출
WHERE YEAR(START_DATE)=2022
```

**관련 문제:**

- [151139. 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기](./151139.%20대여%20횟수가%20많은%20자동차들의%20월별%20대여%20횟수%20구하기)

### MONTH()

```sql
-- 날짜에서 월 추출
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(CAR_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE MONTH(START_DATE) BETWEEN 8 AND 10
```

**관련 문제:**

- [151139. 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기](./151139.%20대여%20횟수가%20많은%20자동차들의%20월별%20대여%20횟수%20구하기)
- [157341. 대여 기록이 존재하는 자동차 리스트 구하기](./157341.%20대여%20기록이%20존재하는%20자동차%20리스트%20구하기)

### DATE()

```sql
-- 날짜 문자열을 날짜 타입으로 변환
DATE('2022-10-16')
```

**관련 문제:**

- [157340. 자동차 대여 기록에서 대여중 ／ 대여 가능 여부 구분하기](./157340.%20자동차%20대여%20기록에서%20대여중%20／%20대여%20가능%20여부%20구분하기)

### BETWEEN (날짜 범위)

```sql
-- 날짜 범위 조건
WHERE TB2.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
```

**관련 문제:**

- [144855. 카테고리 별 도서 판매량 집계하기](./144855.%20카테고리%20별%20도서%20판매량%20집계하기)

---

## 11. 문자열 함수

### CONCAT()

```sql
-- 문자열 연결
CONCAT(TB1.CITY, ' ', CONCAT(STREET_ADDRESS1, ' ', STREET_ADDRESS2)) AS 전체주소
```

**관련 문제:**

- [164670. 조건에 맞는 사용자 정보 조회하기](./164670.%20조건에%20맞는%20사용자%20정보%20조회하기)
- [164671. 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기](./164671.%20조회수가%20가장%20많은%20중고거래%20게시판의%20첨부파일%20조회하기)

### 복잡한 CONCAT() 예시

```sql
-- 여러 컬럼을 연결하여 파일 경로 생성
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
```

**관련 문제:**

- [164671. 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기](./164671.%20조회수가%20가장%20많은%20중고거래%20게시판의%20첨부파일%20조회하기)

### SUBSTRING()

```sql
-- 문자열 일부 추출 (전화번호 포맷팅)
CONCAT(SUBSTRING(TB1.TLNO, 1, 3),
       CONCAT('-',
              CONCAT(SUBSTRING(TB1.TLNO, 4, 4),
                     CONCAT('-',
                            SUBSTRING(TB1.TLNO, 8, 4)
                           )
                    )
             )
      ) AS 전화번호
```

- **SUBSTRING(문자열, 시작위치, 길이)**: 문자열의 일부를 추출

**관련 문제:**

- [164670. 조건에 맞는 사용자 정보 조회하기](./164670.%20조건에%20맞는%20사용자%20정보%20조회하기)

---

## 12. 기타 함수

### LIMIT

```sql
-- 결과 개수 제한
SELECT TB1.NAME, TB1.DATETIME
FROM ANIMAL_INS AS TB1 LEFT JOIN ANIMAL_OUTS AS TB2
    ON TB1.ANIMAL_ID=TB2.ANIMAL_ID
WHERE TB2.ANIMAL_ID IS NULL
ORDER BY TB1.DATETIME
LIMIT 3;
```

**관련 문제:**

- [59044. 오랜 기간 보호한 동물（1）](./59044.%20오랜%20기간%20보호한%20동물（1）)
- [59411. 오랜 기간 보호한 동물（2）](./59411.%20오랜%20기간%20보호한%20동물（2）)

### COALESCE()

```sql
-- NULL 값 처리
HAVING AVG(COALESCE(LENGTH, 10)) >= 33
```

- **COALESCE(값, 기본값)**: NULL인 경우 기본값 반환

**관련 문제:**

- [298519. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기](./298519.%20특정%20조건을%20만족하는%20물고기별%20수와%20최대%20길이%20구하기)

### IS NULL / IS NOT NULL

```sql
-- NULL 체크
WHERE TB1.ANIMAL_ID IS NULL
WHERE PARENT_ITEM_ID IS NOT NULL
WHERE OUT_DATE IS NULL
```

**관련 문제:**

- [59042. 없어진 기록 찾기](./59042.%20없어진%20기록%20찾기)
- [59044. 오랜 기간 보호한 동물（1）](./59044.%20오랜%20기간%20보호한%20동물（1）)
- [273712. 업그레이드 할 수 없는 아이템 구하기](./273712.%20업그레이드%20할%20수%20없는%20아이템%20구하기)
- [131113. 조건별로 분류하여 주문상태 출력하기](./131113.%20조건별로%20분류하여%20주문상태%20출력하기)

---

## 사용 예시

### 복합 쿼리 예시 1: RIGHT JOIN으로 없는 데이터 찾기

```sql
-- 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물
SELECT TB2.ANIMAL_ID, TB2.NAME
FROM ANIMAL_INS AS TB1 RIGHT JOIN ANIMAL_OUTS AS TB2
ON TB1.ANIMAL_ID=TB2.ANIMAL_ID
WHERE TB1.ANIMAL_ID IS NULL;
```

### 복합 쿼리 예시 2: 명시적 조인으로 시간 비교

```sql
-- 보호 시작일보다 입양일이 빠른 동물
SELECT TB1.ANIMAL_ID, TB1.NAME
FROM ANIMAL_INS AS TB1, ANIMAL_OUTS AS TB2
WHERE TB1.ANIMAL_ID=TB2.ANIMAL_ID
  AND TB1.DATETIME > TB2.DATETIME
ORDER BY TB1.DATETIME;
```

### 복합 쿼리 예시 3: 서브쿼리로 헤비 유저 찾기

```sql
-- 공간을 둘 이상 등록한 사람
SELECT ID, NAME, HOST_ID
FROM PLACES
WHERE HOST_ID IN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(HOST_ID) >= 2
)
ORDER BY ID;
```

### 복합 쿼리 예시 4: NOT IN으로 업그레이드 불가 아이템 찾기

```sql
-- 부모 아이템이 되지 않는 아이템 (업그레이드할 수 없음)
SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID NOT IN (
    SELECT DISTINCT PARENT_ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IS NOT NULL
)
ORDER BY ITEM_ID DESC;
```

### 복합 쿼리 예시 5: 윈도우 함수로 사분위수 분류

```sql
-- 대장균 크기에 따라 4개 구간으로 분류 (PERCENT_RANK 사용 - 추천)
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
```

```sql
-- 대체 방법: ROW_NUMBER와 COUNT OVER 사용
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
```

### 복합 쿼리 예시 6: Self Join으로 자식 개수 구하기

```sql
-- 각 대장균의 자식 개수 구하기
SELECT TB1.ID, COUNT(TB2.ID) AS CHILD_COUNT
FROM ECOLI_DATA AS TB1 LEFT JOIN ECOLI_DATA AS TB2 ON TB1.ID=TB2.PARENT_ID
GROUP BY TB1.ID;
```

### 복합 쿼리 예시 7: 그룹별 최댓값 찾기 (튜플 서브쿼리 - 추천)

```sql
-- 각 물고기 종류별로 가장 큰 물고기 찾기
SELECT
    TB1.ID,
    TB2.FISH_NAME,
    TB1.LENGTH
FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
WHERE TB1.FISH_TYPE=TB2.FISH_TYPE
    AND (TB2.FISH_NAME, TB1.LENGTH) IN (
        SELECT FISH_NAME, MAX(LENGTH)
        FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
        WHERE TB1.FISH_TYPE=TB2.FISH_TYPE
        GROUP BY TB2.FISH_NAME
    )
ORDER BY TB1.ID;
```

```sql
-- 대체 방법: PARTITION BY로 그룹별 1등 찾기
SELECT
    ID,
    FISH_NAME,
    LENGTH
FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY FISH_TYPE ORDER BY LENGTH DESC) AS RN FROM FISH_INFO) AS TB1
JOIN FISH_NAME_INFO AS TB2
    ON TB1.FISH_TYPE=TB2.FISH_TYPE
WHERE RN=1
ORDER BY ID;
```

### 복합 쿼리 예시 8: 튜플 서브쿼리로 그룹별 최댓값 찾기 (추천)

```sql
-- 각 음식 종류별로 즐겨찾기가 가장 많은 식당
SELECT FOOD_TYPE,
    REST_ID,
    REST_NAME,
    FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (
    SELECT FOOD_TYPE, MAX(FAVORITES)
    FROM REST_INFO
    GROUP BY FOOD_TYPE
)
ORDER BY FOOD_TYPE DESC;
```

```sql
-- 대체 방법: PARTITION BY로 그룹별 1등 찾기
SELECT REST_INFO_WITH_RANK.FOOD_TYPE,
    REST_INFO_WITH_RANK.REST_ID,
    REST_INFO_WITH_RANK.REST_NAME,
    REST_INFO_WITH_RANK.FAVORITES
FROM (
    SELECT FOOD_TYPE,
        REST_ID,
        REST_NAME,
        FAVORITES,
        ROW_NUMBER() OVER (PARTITION BY FOOD_TYPE ORDER BY FAVORITES DESC) AS RN
    FROM REST_INFO
) AS REST_INFO_WITH_RANK
WHERE RN=1
ORDER BY FOOD_TYPE DESC;
```

---

## 문법 빈도 분석

### 가장 많이 사용된 문법 TOP 15

1. **SELECT**: 모든 쿼리에서 사용
2. **FROM**: 모든 쿼리에서 사용
3. **WHERE**: 조건 필터링 (21개 중 19개 문제)
4. **ORDER BY**: 정렬 (21개 중 19개 문제)
5. **GROUP BY**: 그룹 집계 (21개 중 12개 문제)
6. **JOIN**: 테이블 관계 처리 (21개 중 14개 문제)
7. **서브쿼리 (IN, NOT IN)**: 복잡한 조건 처리 (21개 중 6개 문제)
8. **집계 함수 (COUNT, SUM, AVG, MAX)**: 데이터 집계 (21개 중 11개 문제)
9. **HAVING**: 그룹 필터링 (21개 중 7개 문제)
10. **CASE/IF**: 조건 분기 (21개 중 5개 문제)
11. **날짜 함수**: YEAR, MONTH, DATE_FORMAT, DATEDIFF (21개 중 7개 문제)
12. **문자열 함수**: CONCAT, SUBSTRING (21개 중 2개 문제)
13. **윈도우 함수**: PERCENT_RANK, ROW_NUMBER, PARTITION BY (21개 중 2개 문제 + 대체 방법 2개)
14. **DISTINCT**: 중복 제거 (21개 중 2개 문제)
15. **LIMIT**: 결과 개수 제한 (21개 중 2개 문제)

### JOIN 종류별 빈도

- **NATURAL JOIN**: 5개 문제
- **LEFT JOIN**: 2개 문제
- **INNER JOIN**: 1개 문제
- **RIGHT JOIN**: 1개 문제
- **Self Join**: 1개 문제
- **명시적 조인 (WHERE)**: 1개 문제

### 집계 함수 사용 빈도

- **COUNT()**: 7개 문제
- **SUM()**: 2개 문제
- **AVG()**: 2개 문제
- **MAX()**: 4개 문제
- **ROUND()**: 1개 문제

### 날짜 함수 사용 빈도

- **YEAR()**: 1개 문제
- **MONTH()**: 2개 문제
- **DATE()**: 1개 문제
- **DATE_FORMAT()**: 1개 문제
- **DATEDIFF()**: 2개 문제
- **BETWEEN**: 2개 문제

### 레벨 2와의 차이점

- **Self Join**: 같은 테이블을 두 번 조인 (부모-자식 관계)
- **NATURAL JOIN**: 자동으로 같은 이름의 컬럼 조인
- **PERCENT_RANK()**: 백분위 순위 계산 (0.0 ~ 1.0)
- **PARTITION BY**: 그룹별 윈도우 함수 적용
- **RIGHT JOIN**: 외부 조인으로 누락된 데이터 찾기
- **NOT IN**: 제외 조건 처리 (외부 조인의 대안)
- **중첩 IF**: 여러 단계 조건 분기
- **ROW_NUMBER() 윈도우 함수**: 순위 기반 분류
- **COUNT(\*) OVER()**: 전체 행 수를 각 행에서 참조
- **사분위수 계산**: 데이터를 4개 구간으로 분류
- **튜플 서브쿼리**: 여러 컬럼을 동시에 비교 (PARTITION BY의 대안)
- **복잡한 문자열 조작**: CONCAT, SUBSTRING 중첩 사용

### 난이도별 문법

- **기초**: SELECT, WHERE, ORDER BY, JOIN, GROUP BY
- **중급**: 서브쿼리 (IN), HAVING, DISTINCT, 날짜 함수, LIMIT
- **고급**: RIGHT/LEFT JOIN, Self Join, NATURAL JOIN, NOT IN, 윈도우 함수 (ROW_NUMBER, PARTITION BY, COUNT OVER), 중첩 IF, CASE, 튜플 서브쿼리, COALESCE

### 레벨 3 핵심 개념

1. **외부 조인 (RIGHT/LEFT JOIN)**: 한쪽 테이블에 없는 데이터를 찾을 때
2. **Self Join**: 같은 테이블을 조인하여 계층 구조 탐색
3. **NATURAL JOIN**: 자동으로 컬럼 매칭
4. **윈도우 함수**: 전체 데이터 대비 현재 행의 위치/비율 계산
5. **PERCENT_RANK()**: 백분위 순위 계산 (사분위수에 최적)
6. **PARTITION BY**: 그룹별로 순위 매기기
7. **NOT IN**: 특정 집합에 속하지 않는 데이터 필터링 (외부 조인의 대안)
8. **튜플 서브쿼리**: 여러 컬럼을 동시에 비교하여 복잡한 조건 처리 (PARTITION BY의 대안)
9. **CASE vs IF**: CASE는 가독성, IF는 간결함
10. **사분위수**: 데이터를 비율로 나누어 등급 부여
11. **복잡한 문자열 조작**: 여러 문자열 함수를 중첩하여 사용
12. **다양한 해결 방법**: 같은 문제를 여러 방식으로 해결 가능 (윈도우 함수 vs 튜플 서브쿼리 vs 외부 조인)

---

## 실무 활용 팁

### 1. RIGHT JOIN vs LEFT JOIN

```sql
-- RIGHT JOIN: 오른쪽 테이블 기준
FROM A RIGHT JOIN B ON A.id = B.id

-- 같은 의미 (LEFT JOIN)
FROM B LEFT JOIN A ON A.id = B.id
```

### 2. NATURAL JOIN 주의사항

```sql
-- NATURAL JOIN은 편리하지만, 컬럼명이 같으면 자동으로 조인되므로 주의
-- 명시적으로 조인 조건을 작성하는 것이 더 안전

-- NATURAL JOIN
FROM A NATURAL JOIN B

-- 명시적 조인 (권장)
FROM A JOIN B ON A.id = B.id
```

### 3. NOT IN 사용 시 주의사항

```sql
-- NULL이 포함되면 의도치 않은 결과
WHERE ITEM_ID NOT IN (SELECT PARENT_ITEM_ID FROM ITEM_TREE)  -- 잘못된 예

-- NULL 제외 필요
WHERE ITEM_ID NOT IN (
    SELECT PARENT_ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IS NOT NULL  -- 올바른 예
)
```

### 4. 윈도우 함수 활용

```sql
-- 백분위 순위 계산 (사분위수에 최적)
PERCENT_RANK() OVER (ORDER BY col)  -- 0.0 ~ 1.0

-- 전체 대비 비율 계산 (대체 방법)
ROW_NUMBER() OVER (ORDER BY col) / COUNT(*) OVER ()

-- 그룹별 순위
ROW_NUMBER() OVER (PARTITION BY group_col ORDER BY value_col)

-- 그룹별 1등만 선택
WHERE ROW_NUMBER() OVER (PARTITION BY group_col ORDER BY value_col DESC) = 1
```

### 5. IF vs CASE

```sql
-- IF: 간결하지만 중첩 시 복잡
IF(조건, 참, 거짓)

-- CASE: 가독성 좋음
CASE
    WHEN 조건1 THEN 결과1
    WHEN 조건2 THEN 결과2
    ELSE 기본값
END
```

### 6. 튜플 서브쿼리 활용

```sql
-- 여러 컬럼을 동시에 비교 (각 그룹별 최댓값 행 찾기)
-- PARTITION BY의 대안으로 더 직관적
WHERE (col1, col2) IN (
    SELECT col1, MAX(col2)
    FROM table
    GROUP BY col1
)
```

### 7. Self Join 활용

```sql
-- 부모-자식 관계 찾기
SELECT parent.id, COUNT(child.id) AS child_count
FROM table AS parent
LEFT JOIN table AS child ON parent.id = child.parent_id
GROUP BY parent.id
```

### 8. COALESCE로 NULL 처리

```sql
-- NULL을 기본값으로 대체
AVG(COALESCE(length, 10))  -- NULL이면 10으로 대체
```

### 9. 날짜 함수 조합

```sql
-- 특정 연도, 특정 월 범위 필터링
WHERE YEAR(date_col) = 2022 AND MONTH(date_col) BETWEEN 8 AND 10
```

### 10. 복잡한 문자열 조작

```sql
-- 전화번호 포맷팅: 01012345678 → 010-1234-5678
CONCAT(
    SUBSTRING(phone, 1, 3), '-',
    SUBSTRING(phone, 4, 4), '-',
    SUBSTRING(phone, 8, 4)
) AS formatted_phone
```

---

## 전체 문제 문법 요약표

| 문제   | JOIN      | 서브쿼리 | 윈도우함수          | CASE/IF | 집계함수   | 날짜함수              | 문자열함수        | 기타                       |
| ------ | --------- | -------- | ------------------- | ------- | ---------- | --------------------- | ----------------- | -------------------------- |
| 59042  | RIGHT     | -        | -                   | -       | -          | -                     | -                 | IS NULL                    |
| 59043  | 명시적    | -        | -                   | -       | -          | -                     | -                 | -                          |
| 59044  | LEFT      | -        | -                   | -       | -          | -                     | -                 | IS NULL, LIMIT             |
| 59411  | INNER     | -        | -                   | -       | -          | DATEDIFF              | -                 | LIMIT                      |
| 77487  | -         | IN       | -                   | -       | COUNT      | -                     | -                 | GROUP BY, HAVING           |
| 131113 | -         | -        | -                   | CASE    | -          | DATE_FORMAT, DATEDIFF | -                 | IS NULL                    |
| 131123 | -         | 튜플     | -                   | -       | MAX        | -                     | -                 | GROUP BY                   |
| 144855 | NATURAL   | -        | -                   | -       | SUM        | BETWEEN               | -                 | GROUP BY                   |
| 151139 | -         | IN       | -                   | -       | COUNT      | YEAR, MONTH           | -                 | GROUP BY                   |
| 157340 | -         | -        | -                   | CASE    | MAX        | DATE                  | -                 | GROUP BY                   |
| 157341 | NATURAL   | -        | -                   | -       | -          | MONTH                 | -                 | DISTINCT                   |
| 164668 | NATURAL   | -        | -                   | -       | SUM        | -                     | -                 | GROUP BY, HAVING           |
| 164670 | NATURAL   | -        | -                   | -       | COUNT      | -                     | CONCAT, SUBSTRING | GROUP BY, HAVING           |
| 164671 | NATURAL   | 스칼라   | -                   | -       | MAX        | -                     | CONCAT            | -                          |
| 273712 | -         | NOT IN   | -                   | -       | -          | -                     | -                 | IS NOT NULL, DISTINCT      |
| 284529 | NATURAL   | -        | -                   | -       | AVG, ROUND | -                     | -                 | GROUP BY                   |
| 293261 | NATURAL   | 튜플     | PARTITION BY (대체) | -       | MAX        | -                     | -                 | -                          |
| 298519 | -         | -        | -                   | -       | COUNT, MAX | -                     | -                 | GROUP BY, HAVING, COALESCE |
| 299305 | Self Join | -        | -                   | -       | COUNT      | -                     | -                 | GROUP BY                   |
| 299307 | -         | -        | -                   | CASE    | -          | -                     | -                 | -                          |
| 301649 | -         | -        | PERCENT_RANK        | CASE    | -          | -                     | -                 | 서브쿼리(FROM)             |

---

## 난이도별 추천 학습 순서

### 1단계: 기본 JOIN & 집계

1. [299305. 대장균들의 자식의 수 구하기](./299305.%20대장균들의%20자식의%20수%20구하기) - Self Join 기초
2. [284529. 부서별 평균 연봉 조회하기](./284529.%20부서별%20평균%20연봉%20조회하기) - NATURAL JOIN & AVG
3. [144855. 카테고리 별 도서 판매량 집계하기](./144855.%20카테고리%20별%20도서%20판매량%20집계하기) - NATURAL JOIN & SUM

### 2단계: 외부 조인 & 서브쿼리

4. [59042. 없어진 기록 찾기](./59042.%20없어진%20기록%20찾기) - RIGHT JOIN
5. [59044. 오랜 기간 보호한 동물（1）](./59044.%20오랜%20기간%20보호한%20동물（1）) - LEFT JOIN
6. [77487. 헤비 유저가 소유한 장소](./77487.%20헤비%20유저가%20소유한%20장소) - IN 서브쿼리
7. [273712. 업그레이드 할 수 없는 아이템 구하기](./273712.%20업그레이드%20할%20수%20없는%20아이템%20구하기) - NOT IN 서브쿼리

### 3단계: 조건 처리 & 날짜 함수

8. [299307. 대장균의 크기에 따라 분류하기 1](./299307.%20대장균의%20크기에%20따라%20분류하기%201) - CASE
9. [131113. 조건별로 분류하여 주문상태 출력하기](./131113.%20조건별로%20분류하여%20주문상태%20출력하기) - CASE & 날짜함수
10. [59411. 오랜 기간 보호한 동물（2）](./59411.%20오랜%20기간%20보호한%20동물（2）) - DATEDIFF

### 4단계: 고급 서브쿼리 & 문자열

11. [131123. 즐겨찾기가 가장 많은 식당 정보 출력하기](./131123.%20즐겨찾기가%20가장%20많은%20식당%20정보%20출력하기) - 튜플 서브쿼리
12. [164671. 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기](./164671.%20조회수가%20가장%20많은%20중고거래%20게시판의%20첨부파일%20조회하기) - 스칼라 서브쿼리 & CONCAT
13. [164670. 조건에 맞는 사용자 정보 조회하기](./164670.%20조건에%20맞는%20사용자%20정보%20조회하기) - 문자열 함수

### 5단계: 윈도우 함수 (최고난이도)

14. [293261. 물고기 종류 별 대어 찾기](./293261.%20물고기%20종류%20별%20대어%20찾기) - PARTITION BY
15. [301649. 대장균의 크기에 따라 분류하기 2](./301649.%20대장균의%20크기에%20따라%20분류하기%202) - 윈도우 함수 & 중첩 IF

### 6단계: 종합 응용

16. [151139. 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기](./151139.%20대여%20횟수가%20많은%20자동차들의%20월별%20대여%20횟수%20구하기) - 서브쿼리 & 날짜함수
17. [157340. 자동차 대여 기록에서 대여중 ／ 대여 가능 여부 구분하기](./157340.%20자동차%20대여%20기록에서%20대여중%20／%20대여%20가능%20여부%20구분하기) - CASE & MAX
18. [298519. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기](./298519.%20특정%20조건을%20만족하는%20물고기별%20수와%20최대%20길이%20구하기) - COALESCE & HAVING
19. [157341. 대여 기록이 존재하는 자동차 리스트 구하기](./157341.%20대여%20기록이%20존재하는%20자동차%20리스트%20구하기) - DISTINCT & NATURAL JOIN
20. [164668. 조건에 맞는 사용자와 총 거래금액 조회하기](./164668.%20조건에%20맞는%20사용자와%20총%20거래금액%20조회하기) - 종합
21. [59043. 있었는데요 없었습니다](./59043.%20있었는데요%20없었습니다) - 명시적 조인
