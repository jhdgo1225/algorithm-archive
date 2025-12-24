# 레벨 3 - MySQL 문법 종합 정리

## 목차

1. [기본 SELECT 문](#1-기본-select-문)
2. [JOIN](#2-join)
3. [서브쿼리](#3-서브쿼리)
4. [윈도우 함수](#4-윈도우-함수)
5. [조건 처리 (IF)](#5-조건-처리-if)
6. [집계 함수](#6-집계-함수)
7. [정렬 (ORDER BY)](#7-정렬-order-by)
8. [NOT IN](#8-not-in)
9. [GROUP BY & HAVING](#9-group-by--having)

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

### NOT IN 서브쿼리

```sql
-- 서브쿼리에 없는 데이터 찾기
SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID NOT IN (
    SELECT DISTINCT PARENT_ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IS NOT NULL
)
ORDER BY ITEM_ID DESC;
```

- **NOT IN**: 서브쿼리 결과에 포함되지 않는 데이터만 선택
- **DISTINCT**: 중복 제거
- 서브쿼리에서 NULL 처리 주의 필요

---

## 4. 윈도우 함수

### ROW_NUMBER()

```sql
-- 순위 부여
ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC)
```

### COUNT() OVER()

```sql
-- 전체 행 수 계산
COUNT(*) OVER ()
```

### 복합 윈도우 함수 예시

```sql
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

- `ROW_NUMBER() OVER (ORDER BY ...)`: 정렬 순서대로 번호 부여
- `COUNT(*) OVER ()`: 전체 행 개수 반환 (파티션 없음)
- 사분위수(Quartile) 계산: 전체의 1/4, 2/4, 3/4 구간으로 분류

---

## 5. 조건 처리 (IF)

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

### COUNT with GROUP BY & HAVING

```sql
-- 그룹별 카운트 후 필터링
SELECT HOST_ID
FROM PLACES
GROUP BY HOST_ID
HAVING COUNT(HOST_ID) >= 2
```

### DISTINCT

```sql
-- 중복 제거
SELECT DISTINCT PARENT_ITEM_ID
FROM ITEM_TREE
WHERE PARENT_ITEM_ID IS NOT NULL
```

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

---

## 9. GROUP BY & HAVING

### GROUP BY

```sql
-- 그룹화
GROUP BY HOST_ID
```

### HAVING

```sql
-- 그룹화 후 조건 필터링
HAVING COUNT(HOST_ID) >= 2
```

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
-- 대장균 크기에 따라 4개 구간으로 분류
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

---

## 문법 빈도 분석

### 가장 많이 사용된 문법 TOP 10

1. **SELECT**: 모든 쿼리에서 사용
2. **FROM**: 모든 쿼리에서 사용
3. **WHERE**: 조건 필터링
4. **ORDER BY**: 정렬
5. **서브쿼리 (IN, NOT IN)**: 복잡한 조건 처리
6. **JOIN (RIGHT JOIN, 명시적 조인)**: 테이블 관계 처리
7. **윈도우 함수 (ROW_NUMBER, COUNT OVER)**: 고급 집계
8. **IF**: 복잡한 조건 분기
9. **GROUP BY & HAVING**: 그룹 집계와 필터링
10. **DISTINCT**: 중복 제거

### 레벨 2와의 차이점

- **RIGHT JOIN**: 외부 조인으로 누락된 데이터 찾기
- **NOT IN**: 제외 조건 처리
- **중첩 IF**: 여러 단계 조건 분기
- **ROW_NUMBER() 윈도우 함수**: 순위 기반 분류
- **COUNT(\*) OVER()**: 전체 행 수를 각 행에서 참조
- **사분위수 계산**: 데이터를 4개 구간으로 분류

### 난이도별 문법

- **기초**: SELECT, WHERE, ORDER BY, JOIN
- **중급**: 서브쿼리 (IN), GROUP BY, HAVING, DISTINCT
- **고급**: RIGHT JOIN, NOT IN, 윈도우 함수 (ROW_NUMBER, COUNT OVER), 중첩 IF

### 레벨 3 핵심 개념

1. **외부 조인 (RIGHT JOIN)**: 한쪽 테이블에 없는 데이터를 찾을 때
2. **윈도우 함수**: 전체 데이터 대비 현재 행의 위치/비율 계산
3. **NOT IN**: 특정 집합에 속하지 않는 데이터 필터링
4. **중첩 IF**: CASE보다 간결한 다중 조건 처리
5. **사분위수**: 데이터를 비율로 나누어 등급 부여

---

## 실무 활용 팁

### 1. RIGHT JOIN vs LEFT JOIN

```sql
-- RIGHT JOIN: 오른쪽 테이블 기준
FROM A RIGHT JOIN B ON A.id = B.id

-- 같은 의미 (LEFT JOIN)
FROM B LEFT JOIN A ON A.id = B.id
```

### 2. NOT IN 사용 시 주의사항

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

### 3. 윈도우 함수 활용

```sql
-- 전체 대비 비율 계산
ROW_NUMBER() OVER (ORDER BY col) / COUNT(*) OVER ()

-- 그룹별 순위
ROW_NUMBER() OVER (PARTITION BY group_col ORDER BY value_col)
```

### 4. IF vs CASE

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
