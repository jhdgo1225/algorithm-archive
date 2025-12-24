# 레벨 1 - MySQL 문법 종합 정리

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

---

## 1. 기본 SELECT 문

### SELECT \* (모든 컬럼 조회)

```sql
SELECT *
FROM ANIMAL_INS;
```

- 테이블의 모든 컬럼을 조회

### SELECT 특정 컬럼

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS;
```

- 원하는 컬럼만 선택하여 조회

---

## 2. WHERE 조건절

### 기본 비교 연산자

```sql
-- 같음
WHERE INTAKE_CONDITION='Sick'

-- 다름
WHERE INTAKE_CONDITION!='Aged'

-- 비교 연산
WHERE AGE <= 12
WHERE AGE >= 20 AND AGE < 30
```

### IN 연산자

```sql
WHERE MCDP_CD IN ('CS', 'GS')
```

- 여러 값 중 하나와 일치하는지 확인

### OR 연산자

```sql
WHERE SKILL_1="Python" OR SKILL_2="Python" OR SKILL_3="PYTHON"
```

- 여러 조건 중 하나라도 참이면 참

### LIKE 패턴 매칭

```sql
-- 시작하는 문자열
WHERE ADDRESS LIKE '강원도%'
WHERE ADDRESS LIKE '경기도%'

-- 포함하는 문자열
WHERE OPTIONS LIKE '%네비게이션%'
```

- `%`: 0개 이상의 문자를 대체
- `_`: 정확히 1개의 문자를 대체

---

## 3. 정렬 (ORDER BY)

### 단일 컬럼 정렬

```sql
-- 오름차순 (ASC는 생략 가능)
ORDER BY ANIMAL_ID
ORDER BY ANIMAL_ID ASC

-- 내림차순
ORDER BY ANIMAL_ID DESC
ORDER BY TOTAL_ORDER DESC
```

### 다중 컬럼 정렬

```sql
-- 여러 기준으로 정렬
ORDER BY NAME, DATETIME DESC
ORDER BY AGE DESC, PT_NAME
ORDER BY HIRE_YMD DESC, DR_NAME ASC
ORDER BY CREATED_DATE ASC, BOARD.TITLE ASC
```

- 첫 번째 기준으로 정렬한 후, 같은 값이 있으면 두 번째 기준으로 정렬

---

## 4. 집계 함수

### COUNT

```sql
-- 전체 행 개수
SELECT COUNT(*) AS USERS

-- NULL이 아닌 행 개수
SELECT COUNT(*) AS count
```

### MAX / MIN

```sql
-- 최댓값
SELECT MAX(PRICE) AS MAX_PRICE
SELECT MAX(DATETIME) AS '시간'
SELECT MAX(LENGTH)

-- 최솟값
SELECT MIN(DATETIME)
```

### AVG (평균)

```sql
-- 단순 평균
SELECT ROUND(AVG(DAILY_FEE)) AS AVERAGE_FEE

-- 조건부 평균 (IF 함수 사용)
SELECT ROUND(AVG(IF(LENGTH > 10, LENGTH, 10)), 2) AS AVERAGE_LENGTH
```

### ROUND (반올림)

```sql
-- 소수점 자리 지정
ROUND(AVG(DAILY_FEE))        -- 정수로 반올림
ROUND(AVG(...), 1)            -- 소수점 첫째자리
ROUND(AVG(...), 2)            -- 소수점 둘째자리
```

---

## 5. 날짜/시간 함수

### YEAR / MONTH

```sql
-- 연도 추출
WHERE YEAR(START_DATE)=2022
WHERE YEAR(PUBLISHED_DATE)=2021
WHERE YEAR(JOINED)=2021
WHERE YEAR(TIME)=2021

-- 월 추출
WHERE MONTH(START_DATE)=9
WHERE MONTH(BOARD.CREATED_DATE)=10
WHERE MONTH(DATE_OF_BIRTH)=3
```

### DATE_FORMAT

```sql
-- 날짜 형식 변경
DATE_FORMAT(START_DATE, "%Y-%m-%d") AS START_DATE
DATE_FORMAT(END_DATE, "%Y-%m-%d") AS END_DATE
DATE_FORMAT(PUBLISHED_DATE, "%Y-%m-%d") AS PUBLISHED_DATE
DATE_FORMAT(HIRE_YMD, "%Y-%m-%d") AS HIRE_YMD
DATE_FORMAT(CREATED_DATE, "%Y-%m-%d") AS CREATED_DATE
```

- `%Y`: 4자리 연도
- `%m`: 2자리 월
- `%d`: 2자리 일

### DATEDIFF

```sql
-- 날짜 차이 계산 (일 단위)
DATEDIFF(END_DATE, START_DATE)

-- 조건문과 함께 사용
CASE
    WHEN DATEDIFF(END_DATE, START_DATE) >= 29
    THEN '장기 대여'
    ELSE '단기 대여'
END AS RENT_TYPE
```

---

## 6. 문자열 함수

### CONCAT

```sql
-- 문자열 연결
SELECT CONCAT(MAX(LENGTH), 'cm') AS MAX_LENGTH
```

---

## 7. NULL 처리

### IS NULL / IS NOT NULL

```sql
-- NULL인 경우
WHERE NAME IS NULL
WHERE AGE IS NULL
WHERE LENGTH IS NULL

-- NULL이 아닌 경우
WHERE NAME IS NOT NULL
WHERE TLNO IS NOT NULL
```

### IFNULL

```sql
-- NULL일 경우 기본값 설정
IFNULL(FREEZER_YN, 'N') AS FREEZER_YN
IFNULL(TLNO, "NONE") AS TLNO
```

---

## 8. 조건 처리 (CASE)

### CASE WHEN 구문

```sql
-- 단순 조건
CASE
    WHEN DATEDIFF(END_DATE, START_DATE) >= 29
    THEN '장기 대여'
    ELSE '단기 대여'
END AS RENT_TYPE

-- 다중 조건
CASE
    WHEN SEX_UPON_INTAKE LIKE "Neutered%" OR SEX_UPON_INTAKE LIKE "Spayed%"
        THEN 'O'
    ELSE 'X'
END AS '중성화'
```

---

## 9. 비트 연산

### 비트 AND (&)

```sql
-- 비트 연산으로 형질 확인
WHERE GENOTYPE & 2 = 0 AND (GENOTYPE & 4 OR GENOTYPE & 1)
```

- `&`: 비트 AND 연산자
- 특정 비트가 설정되어 있는지 확인할 때 사용

---

## 10. LIMIT

### 상위 N개 조회

```sql
-- 상위 1개
ORDER BY DATETIME
LIMIT 1

-- 상위 10개
ORDER BY LENGTH DESC, ID
LIMIT 10
```

---

## 11. JOIN

### NATURAL JOIN

```sql
-- 공통 컬럼으로 자동 조인
FROM FIRST_HALF AS TB1 NATURAL JOIN ICECREAM_INFO AS TB2
```

### 명시적 JOIN (컬럼 나열)

```sql
-- WHERE 절로 조인 조건 지정
FROM USED_GOODS_REPLY as REPL, USED_GOODS_BOARD as BOARD
WHERE REPL.BOARD_ID=BOARD.BOARD_ID
```

---

## 12. GROUP BY & HAVING

### GROUP BY

```sql
-- 그룹화
GROUP BY FLAVOR

-- HAVING으로 그룹 필터링
GROUP BY FLAVOR HAVING SUM(TOTAL_ORDER) >= 3000
```

---

## 사용 예시

### 복합 쿼리 예시 1: 날짜 함수 + CASE + DATE_FORMAT

```sql
SELECT
    HISTORY_ID,
    CAR_ID,
    DATE_FORMAT(START_DATE, "%Y-%m-%d") AS START_DATE,
    DATE_FORMAT(END_DATE, "%Y-%m-%d") AS END_DATE,
    CASE
        WHEN DATEDIFF(END_DATE, START_DATE) >= 29
        THEN '장기 대여'
        ELSE '단기 대여'
    END AS RENT_TYPE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE YEAR(START_DATE)=2022 AND MONTH(START_DATE)=9
ORDER BY HISTORY_ID DESC;
```

### 복합 쿼리 예시 2: JOIN + GROUP BY + HAVING

```sql
SELECT TB1.FLAVOR
FROM FIRST_HALF AS TB1 NATURAL JOIN ICECREAM_INFO AS TB2
WHERE TB2.INGREDIENT_TYPE='fruit_based'
GROUP BY FLAVOR HAVING SUM(TOTAL_ORDER) >= 3000
ORDER BY TOTAL_ORDER DESC;
```

### 복합 쿼리 예시 3: 다중 테이블 JOIN

```sql
SELECT BOARD.TITLE, BOARD.BOARD_ID, REPL.REPLY_ID, REPL.WRITER_ID, REPL.CONTENTS,
       DATE_FORMAT(REPL.CREATED_DATE, "%Y-%m-%d") AS CREATED_DATE
FROM USED_GOODS_REPLY as REPL, USED_GOODS_BOARD as BOARD
WHERE REPL.BOARD_ID=BOARD.BOARD_ID
  AND YEAR(BOARD.CREATED_DATE)=2022
  AND MONTH(BOARD.CREATED_DATE)=10
ORDER BY CREATED_DATE ASC, BOARD.TITLE ASC;
```

---

## 문법 빈도 분석

### 가장 많이 사용된 문법 TOP 5

1. **SELECT**: 모든 쿼리에서 사용
2. **ORDER BY**: 대부분의 쿼리에서 정렬 사용
3. **WHERE**: 조건 필터링에 광범위하게 사용
4. **집계 함수 (COUNT, MAX, MIN, AVG)**: 데이터 분석에 필수
5. **날짜 함수 (YEAR, MONTH, DATE_FORMAT)**: 날짜 데이터 처리에 필수

### 난이도별 문법

- **기초**: SELECT, WHERE, ORDER BY, COUNT, MAX, MIN
- **중급**: JOIN, GROUP BY, HAVING, DATE_FORMAT, IFNULL, CASE
- **고급**: 비트 연산, NATURAL JOIN, DATEDIFF
