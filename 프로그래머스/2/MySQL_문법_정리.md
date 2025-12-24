# 레벨 2 - MySQL 문법 종합 정리

## 목차

1. [기본 SELECT 문](#1-기본-select-문)
2. [WHERE 조건절](#2-where-조건절)
3. [정렬 (ORDER BY)](#3-정렬-order-by)
4. [집계 함수](#4-집계-함수)
5. [날짜/시간 함수](#5-날짜시간-함수)
6. [문자열 함수](#6-문자열-함수)
7. [NULL 처리](#7-null-처리)
8. [조건 처리 (CASE)](#8-조건-처리-case)
9. [비트 연산](#9-비트-연산)
10. [LIMIT](#10-limit)
11. [JOIN](#11-join)
12. [GROUP BY & HAVING](#12-group-by--having)
13. [서브쿼리](#13-서브쿼리)
14. [윈도우 함수](#14-윈도우-함수)
15. [고급 문자열 함수](#15-고급-문자열-함수)
16. [산술 연산](#16-산술-연산)

---

## 1. 기본 SELECT 문

### SELECT \* (모든 컬럼 조회)

```sql
SELECT *
FROM FOOD_PRODUCT
WHERE PRICE=(SELECT MAX(PRICE) FROM FOOD_PRODUCT);
```

### SELECT 특정 컬럼

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE ANIMAL_TYPE='Dog';
```

---

## 2. WHERE 조건절

### 기본 비교 연산자

```sql
-- 등호/부등호
WHERE ANIMAL_TYPE='Dog'
WHERE RARITY="LEGEND"
WHERE LOCATION2="수원"

-- 범위 조건
WHERE APNT_YMD >= '2022-05-01' AND APNT_YMD <= '2022-05-31'
```

### BETWEEN

```sql
-- 시간 범위 지정
WHERE HOUR(DATETIME) BETWEEN 9 AND 19
```

### IN 연산자

```sql
-- 여러 값 중 하나와 일치
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
WHERE tb2.FISH_NAME IN ('BASS', 'SNAPPER')
WHERE NAME IN ("Python", "C#")
```

### LIKE 패턴 매칭

```sql
-- 대소문자 구분 없이 검색
WHERE ANIMAL_TYPE='Dog' AND LOWER(NAME) LIKE '%el%'

-- 여러 패턴
WHERE SEX_UPON_INTAKE LIKE "Neutered%" OR SEX_UPON_INTAKE LIKE "Spayed%"

-- 문자열 포함
WHERE OPTIONS LIKE '%통풍시트%' OR OPTIONS LIKE '%열선시트%' OR OPTIONS LIKE '%가죽시트%'
```

---

## 3. 정렬 (ORDER BY)

### 단일/다중 컬럼 정렬

```sql
-- 기본 정렬
ORDER BY HOUR(DATETIME)
ORDER BY CREATED_DATE ASC, MCDP_CD ASC

-- 다중 조건 정렬
ORDER BY FISH_COUNT DESC
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC
ORDER BY YEAR, YEAR_DEV
```

### FIELD를 이용한 커스텀 정렬

```sql
-- 지정된 순서로 정렬
ORDER BY FIELD(ANIMAL_TYPE, "Cat", "Dog")
```

---

## 4. 집계 함수

### COUNT

```sql
-- 기본 카운트
SELECT COUNT(*) AS count
FROM ANIMAL_INS;

-- 중복 제거 카운트
SELECT COUNT(DISTINCT NAME) AS count
WHERE NAME IS NOT NULL;

-- GROUP BY와 함께 사용
SELECT COUNT(*) AS FISH_COUNT, TB2.FISH_NAME AS FISH_NAME
FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
GROUP BY TB2.FISH_NAME;
```

**관련 문제:**

- [59406. 동물 수 구하기](../2/59406.%20동물%20수%20구하기)
- [59408. 중복 제거하기](../2/59408.%20중복%20제거하기) - COUNT(DISTINCT)
- [59040. 고양이와 개는 몇 마리 있을까](../2/59040.%20고양이와%20개는%20몇%20마리%20있을까)
- [59041. 동명 동물 수 찾기](../2/59041.%20동명%20동물%20수%20찾기)
- [59412. 입양 시각 구하기(1)](../2/59412.%20입양%20시각%20구하기（1）)
- [131529. 카테고리 별 상품 개수 구하기](../2/131529.%20카테고리%20별%20상품%20개수%20구하기)
- [131530. 가격대 별 상품 개수 구하기](../2/131530.%20가격대%20별%20상품%20개수%20구하기)
- [132202. 진료과별 총 예약 횟수 출력하기](../2/132202.%20진료과별%20총%20예약%20횟수%20출력하기)
- [293257. 물고기 종류 별 잡은 수 구하기](../2/293257.%20물고기%20종류%20별%20잡은%20수%20구하기)
- [293260. 월별 잡은 물고기 수 구하기](../2/293260.%20월별%20잡은%20물고기%20수%20구하기)
- [298518. 특정 물고기를 잡은 총 수 구하기](../2/298518.%20특정%20물고기를%20잡은%20총%20수%20구하기)
- [299308. 분기별 분화된 대장균의 개체 수 구하기](../2/299308.%20분기별%20분화된%20대장균의%20개체%20수%20구하기)

### SUM

```sql
-- 합계 계산
SELECT SUM(PRICE) AS TOTAL_PRICE
WHERE RARITY="LEGEND";

-- 집계 함수와 조인
SELECT SUM(TB2.SCORE) AS SCORE
FROM HR_EMPLOYEES AS TB1 NATURAL JOIN HR_GRADE AS TB2
GROUP BY TB1.EMP_NO;

-- 계산식과 함께
SELECT TB1.PRODUCT_CODE, SUM(TB2.SALES_AMOUNT) * TB1.PRICE AS SALES
FROM PRODUCT AS TB1 NATURAL JOIN OFFLINE_SALE AS TB2
GROUP BY TB1.PRODUCT_CODE;
```

**관련 문제:**

- [273709. 조건에 맞는 아이템들의 가격의 총합 구하기](../2/273709.%20조건에%20맞는%20아이템들의%20가격의%20총합%20구하기)
- [131533. 상품 별 오프라인 매출 구하기](../2/131533.%20상품%20별%20오프라인%20매출%20구하기)
- [133026. 성분으로 구분한 아이스크림 총 주문량](../2/133026.%20성분으로%20구분한%20아이스크림%20총%20주문량)
- [284527. 조건에 맞는 사원 정보 조회하기](../2/284527.%20조건에%20맞는%20사원%20정보%20조회하기)
- [284531. 노선별 평균 역 사이 거리 조회하기](../2/284531.%20노선별%20평균%20역%20사이%20거리%20조회하기)

### MIN / MAX

```sql
-- 최솟값
SELECT MIN(DATETIME)
FROM ANIMAL_INS;

-- 서브쿼리로 최댓값 활용
WHERE PRICE=(SELECT MAX(PRICE) FROM FOOD_PRODUCT);
```

**관련 문제:**

- [59038. 최솟값 구하기](../2/59038.%20최솟값%20구하기) - MIN()
- [131115. 가격이 제일 비싼 식품의 정보 출력하기](../2/131115.%20가격이%20제일%20비싼%20식품의%20정보%20출력하기) - MAX() in subquery
- [299310. 연도별 대장균 크기의 편차 구하기](../2/299310.%20연도별%20대장균%20크기의%20편차%20구하기) - MAX() with window function

### AVG (평균)

```sql
-- 평균과 반올림
SELECT ROUND(AVG(DATEDIFF(END_DATE, START_DATE) + 1), 1) AS AVERAGE_DURATION
GROUP BY CAR_ID HAVING AVERAGE_DURATION >= 7;

-- 여러 평균 계산
SELECT YEAR(YM) AS YEAR,
    ROUND(AVG(PM_VAL1), 2) AS PM10,
    ROUND(AVG(PM_VAL2), 2) AS "PM2.5"
GROUP BY YEAR(YM);
```

**관련 문제:**

- [157342. 자동차 평균 대여 기간 구하기](../2/157342.%20자동차%20평균%20대여%20기간%20구하기)
- [284530. 연도 별 평균 미세먼지 농도 조회하기](../2/284530.%20연도%20별%20평균%20미세먼지%20농도%20조회하기)
- [284531. 노선별 평균 역 사이 거리 조회하기](../2/284531.%20노선별%20평균%20역%20사이%20거리%20조회하기)

---

## 5. 날짜/시간 함수

### YEAR / MONTH / HOUR

```sql
-- 연도 추출
WHERE YEAR(BOARD.CREATED_DATE)=2022
WHERE TB2.YEAR=2022

-- 월 추출
WHERE MONTH(DATE_OF_BIRTH)=3

-- 시간 추출
SELECT HOUR(DATETIME) AS HOUR
WHERE HOUR(DATETIME) BETWEEN 9 AND 19
```

### DATE_FORMAT

```sql
-- 날짜 형식 변경
DATE_FORMAT(DATE_OF_BIRTH, "%Y-%m-%d") AS DATE_OF_BIRTH
DATE_FORMAT(TB1.PUBLISHED_DATE, "%Y-%m-%d") AS PUBLISHED_DATE
DATE_FORMAT(DATETIME, "%Y-%m-%d") AS '날짜'
```

### DATEDIFF

```sql
-- 날짜 차이 계산 (+1은 당일 포함)
ROUND(AVG(DATEDIFF(END_DATE, START_DATE) + 1), 1) AS AVERAGE_DURATION
```

### QUARTER

```sql
-- 분기 추출
SELECT CONCAT(QUARTER(DIFFERENTIATION_DATE), 'Q') AS QUARTER,
    COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER;
```

---

## 6. 문자열 함수

### LOWER

```sql
-- 소문자 변환
WHERE ANIMAL_TYPE='Dog' AND LOWER(NAME) LIKE '%el%'
```

### CONCAT

```sql
-- 문자열 연결
CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE
CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
CONCAT(QUARTER(DIFFERENTIATION_DATE), 'Q') AS QUARTER
```

---

## 7. NULL 처리

### IS NULL / IS NOT NULL

```sql
-- NULL 체크
WHERE NAME IS NOT NULL
WHERE TLNO IS NOT NULL
WHERE TB2.PARENT_ITEM_ID IS NULL
```

### IFNULL

```sql
-- NULL 대체값 지정
IFNULL(NAME, 'No name') AS NAME
```

---

## 8. 조건 처리 (CASE)

### CASE WHEN 기본

```sql
-- 단순 조건
CASE
    WHEN SEX_UPON_INTAKE LIKE "Neutered%" OR SEX_UPON_INTAKE LIKE "Spayed%"
        THEN 'O'
    ELSE 'X'
END AS '중성화'
```

### 다중 조건 CASE

```sql
-- 여러 조건 분기
CASE
    WHEN STATUS='SALE' THEN '판매중'
    WHEN STATUS='RESERVED' THEN '예약중'
    ELSE '거래완료'
END AS STATUS
```

---

## 9. 비트 연산

### 비트 AND (&)

```sql
-- 단순 비트 연산
WHERE SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE NAME IN ("Python", "C#"))

-- 복잡한 비트 연산
WHERE CHILD.GENOTYPE & PARENT.GENOTYPE = PARENT.GENOTYPE
```

- 특정 스킬/형질을 가지고 있는지 비트 단위로 확인

**관련 문제:**

- [276034. 조건에 맞는 개발자 찾기](../2/276034.%20조건에%20맞는%20개발자%20찾기)
- [301647. 부모의 형질을 모두 가지는 대장균 찾기](../2/301647.%20부모의%20형질을%20모두%20가지는%20대장균%20찾기)

---

## 10. LIMIT

### 상위 N개 제한

```sql
-- 최고 점수 1명만 조회
ORDER BY SCORE DESC
LIMIT 1;
```

---

## 11. JOIN

### NATURAL JOIN

```sql
-- 공통 컬럼 자동 조인
FROM FISH_INFO AS TB1 NATURAL JOIN FISH_NAME_INFO AS TB2
FROM FIRST_HALF AS TB1 NATURAL JOIN ICECREAM_INFO AS TB2
FROM ITEM_INFO AS TB1 NATURAL JOIN ITEM_TREE AS TB2
FROM BOOK AS TB1 NATURAL JOIN AUTHOR AS TB2
FROM PRODUCT AS TB1 NATURAL JOIN OFFLINE_SALE AS TB2
FROM HR_EMPLOYEES AS TB1 NATURAL JOIN HR_GRADE AS TB2
```

**관련 문제:**

- [131533. 상품 별 오프라인 매출 구하기](../2/131533.%20상품%20별%20오프라인%20매출%20구하기)
- [133026. 성분으로 구분한 아이스크림 총 주문량](../2/133026.%20성분으로%20구분한%20아이스크림%20총%20주문량)
- [144854. 조건에 맞는 도서와 저자 리스트 출력하기](../2/144854.%20조건에%20맞는%20도서와%20저자%20리스트%20출력하기)
- [273710. ROOT 아이템 구하기](../2/273710.%20ROOT%20아이템%20구하기)
- [273711. 업그레이드 된 아이템 구하기](../2/273711.%20업그레이드%20된%20아이템%20구하기)
- [284527. 조건에 맞는 사원 정보 조회하기](../2/284527.%20조건에%20맞는%20사원%20정보%20조회하기)
- [293257. 물고기 종류 별 잡은 수 구하기](../2/293257.%20물고기%20종류%20별%20잡은%20수%20구하기)
- [298518. 특정 물고기를 잡은 총 수 구하기](../2/298518.%20특정%20물고기를%20잡은%20총%20수%20구하기)

### 명시적 조인 (WHERE)

```sql
-- 조인 조건 명시
FROM ECOLI_DATA AS CHILD, ECOLI_DATA AS PARENT
WHERE PARENT.ID = CHILD.PARENT_ID
```

### 서브쿼리와 IN을 사용한 조인

```sql
WHERE TB2.PARENT_ITEM_ID IN (SELECT ITEM_ID FROM ITEM_INFO WHERE RARITY="RARE")
```

---

## 12. GROUP BY & HAVING

### 단일 컬럼 그룹화

```sql
-- 기본 그룹화
GROUP BY ANIMAL_TYPE
GROUP BY MCDP_CD
GROUP BY TB2.FISH_NAME
```

### 다중 컬럼 그룹화

```sql
-- 여러 컬럼으로 그룹화
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) >= 2
```

### HAVING 절

```sql
-- 그룹화 후 필터링
GROUP BY CAR_ID
HAVING AVERAGE_DURATION >= 7

-- 별칭 사용 가능
HAVING COUNT(*) >= 2
```

---

## 13. 서브쿼리

### WHERE 절의 서브쿼리

```sql
-- 최댓값 찾기
WHERE PRICE=(SELECT MAX(PRICE) FROM FOOD_PRODUCT);

-- IN과 함께 사용
WHERE TB2.PARENT_ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY="RARE"
)

-- 비트 연산과 함께
WHERE SKILL_CODE & (
    SELECT SUM(CODE)
    FROM SKILLCODES
    WHERE NAME IN ("Python", "C#")
)
```

---

## 14. 윈도우 함수

### OVER 절

```sql
-- PARTITION BY로 그룹별 최댓값
SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR,
    MAX(SIZE_OF_COLONY) OVER (PARTITION BY YEAR(DIFFERENTIATION_DATE)) - SIZE_OF_COLONY AS YEAR_DEV,
    ID
FROM ECOLI_DATA
ORDER BY YEAR, YEAR_DEV;
```

- **PARTITION BY**: 데이터를 그룹으로 나누어 윈도우 함수 적용
- **OVER()**: 윈도우 함수 적용 범위 지정

**관련 문제:**

- [299310. 연도별 대장균 크기의 편차 구하기](../2/299310.%20연도별%20대장균%20크기의%20편차%20구하기)

---

## 15. 고급 문자열 함수

### SUBSTRING

```sql
-- 문자열 일부 추출
SELECT SUBSTRING(PRODUCT_CODE, 1, 2) AS CATEGORY,
    COUNT(PRODUCT_CODE) AS PRODUCTS
FROM PRODUCT
GROUP BY CATEGORY;
```

- `SUBSTRING(문자열, 시작위치, 길이)`

**관련 문제:**

- [131529. 카테고리 별 상품 개수 구하기](../2/131529.%20카테고리%20별%20상품%20개수%20구하기)

---

## 16. 산술 연산

### FLOOR

```sql
-- 내림 연산 (가격대 구간 만들기)
SELECT FLOOR(PRICE / 10000) * 10000 AS PRICE_GROUP,
    COUNT(PRODUCT_ID) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP;
```

- 특정 구간으로 데이터를 그룹화할 때 유용

**관련 문제:**

- [131530. 가격대 별 상품 개수 구하기](../2/131530.%20가격대%20별%20상품%20개수%20구하기)

---

## 사용 예시

### 복합 쿼리 예시 1: 윈도우 함수 + 날짜 함수

```sql
SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR,
    MAX(SIZE_OF_COLONY) OVER (PARTITION BY YEAR(DIFFERENTIATION_DATE)) - SIZE_OF_COLONY AS YEAR_DEV,
    ID
FROM ECOLI_DATA
ORDER BY YEAR, YEAR_DEV;
```

### 복합 쿼리 예시 2: 서브쿼리 + JOIN + 비트 연산

```sql
SELECT TB1.ITEM_ID, TB1.ITEM_NAME, TB1.RARITY
FROM ITEM_INFO AS TB1 NATURAL JOIN ITEM_TREE AS TB2
WHERE TB2.PARENT_ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY="RARE"
)
ORDER BY TB1.ITEM_ID DESC;
```

### 복합 쿼리 예시 3: 다중 집계 함수 + CONCAT

```sql
SELECT ROUTE,
    CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE,
    CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY SUM(D_BETWEEN_DIST) DESC;
```

### 복합 쿼리 예시 4: FLOOR를 이용한 구간 그룹화

```sql
SELECT FLOOR(PRICE / 10000) * 10000 AS PRICE_GROUP,
    COUNT(PRODUCT_ID) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP;
```

### 복합 쿼리 예시 5: SUBSTRING으로 카테고리 추출

```sql
SELECT SUBSTRING(PRODUCT_CODE, 1, 2) AS CATEGORY,
    COUNT(PRODUCT_CODE) AS PRODUCTS
FROM PRODUCT
GROUP BY CATEGORY
ORDER BY CATEGORY;
```

### 복합 쿼리 예시 6: QUARTER 함수

```sql
SELECT CONCAT(QUARTER(DIFFERENTIATION_DATE), 'Q') AS QUARTER,
    COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER;
```

---

## 문법 빈도 분석

### 가장 많이 사용된 문법 TOP 10

1. **SELECT**: 모든 쿼리에서 사용
2. **FROM**: 모든 쿼리에서 사용
3. **ORDER BY**: 대부분의 쿼리에서 정렬
4. **WHERE**: 조건 필터링
5. **GROUP BY**: 그룹 집계에 필수
6. **집계 함수 (COUNT, SUM, AVG, MAX, MIN)**: 데이터 분석 핵심
7. **NATURAL JOIN**: 테이블 조인에 광범위하게 사용
8. **날짜 함수 (YEAR, MONTH, DATE_FORMAT)**: 날짜 처리
9. **ROUND**: 소수점 처리
10. **CONCAT**: 문자열 결합

### 레벨 1과의 차이점

- **윈도우 함수 (OVER, PARTITION BY)**: 그룹 내 집계 계산
- **SUBSTRING**: 문자열 일부 추출
- **FLOOR**: 구간 그룹화
- **QUARTER**: 분기 추출
- **더 복잡한 서브쿼리**: IN, EXISTS와 함께 다양하게 활용
- **FIELD**: 커스텀 정렬
- **더 복잡한 비트 연산**: 여러 조건 조합

### 난이도별 문법

- **기초**: SELECT, WHERE, ORDER BY, COUNT, SUM, AVG
- **중급**: GROUP BY, HAVING, JOIN, DATE_FORMAT, CONCAT, SUBSTRING
- **고급**: 윈도우 함수 (OVER, PARTITION BY), 복잡한 서브쿼리, 비트 연산, FLOOR, QUARTER
