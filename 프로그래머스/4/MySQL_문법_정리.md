# 프로그래머스 Level 4 - SQL 문제 정리

## 📋 문제 목록

- [131116] 식품분류별 가장 비싼 식품의 정보 조회하기
- [131117] 5월 식품들의 총매출 조회하기
- [131118] 서울에 위치한 식당 목록 출력하기
- [131124] 그룹별 조건에 맞는 식당 목록 출력하기
- [131532] 년， 월， 성별 별 상품 구매 회원 수 구하기
- [131537] 오프라인／온라인 판매 데이터 통합하기
- [132204] 취소되지 않은 진료 예약 조회하기
- [133027] 주문량이 많은 아이스크림들 조회하기
- [144856] 저자 별 카테고리 별 매출액 집계하기
- [151141] 자동차 대여 기록 별 대여 금액 구하기
- [157339] 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기
- [276035] FrontEnd 개발자 찾기
- [276036] 언어별 개발자 분류하기
- [284528] 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기
- [301650] 특정 세대의 대장균 찾기
- [59045] 보호소에서 중성화한 동물
- [59413] 입양 시각 구하기（2）
- [62284] 우유와 요거트가 담긴 장바구니

---

## 📚 문제별 상세 내용

### [131116] 식품분류별 가장 비싼 식품의 정보 조회하기

**SQL 쿼리:**

```sql
SELECT
    CATEGORY,
    PRICE AS MAX_PRICE,
    PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE(CATEGORY, PRICE) IN (
    SELECT CATEGORY, MAX(PRICE)
    FROM FOOD_PRODUCT
    WHERE CATEGORY IN('과자', '국', '김치', '식용유')
    GROUP BY CATEGORY
)
ORDER BY MAX_PRICE DESC;
```

**핵심 문법:**

- 다중 컬럼 IN 서브쿼리 `(CATEGORY, PRICE) IN (...)`
- GROUP BY를 활용한 집계 함수 (MAX)
- WHERE절 IN 조건
- ORDER BY 정렬

---

### [131117] 5월 식품들의 총매출 조회하기

**SQL 쿼리:**

```sql
SELECT FP.PRODUCT_ID,
    FP.PRODUCT_NAME,
    FP.PRICE * SUM(FO.AMOUNT) AS TOTAL_SALES
FROM FOOD_PRODUCT FP INNER JOIN FOOD_ORDER FO
    ON FP.PRODUCT_ID=FO.PRODUCT_ID
        AND FO.PRODUCE_DATE BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY FO.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, FP.PRODUCT_ID ASC;
```

**핵심 문법:**

- INNER JOIN
- BETWEEN을 활용한 날짜 범위 조건
- GROUP BY와 SUM 집계
- 계산 컬럼 (PRICE \* SUM)
- 다중 컬럼 정렬

---

### [131118] 서울에 위치한 식당 목록 출력하기

**SQL 쿼리:**

```sql
SELECT RI.REST_ID,
    RI.REST_NAME,
    RI.FOOD_TYPE,
    RI.FAVORITES,
    RI.ADDRESS,
    ROUND(AVG(RR.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO RI INNER JOIN REST_REVIEW RR ON RI.REST_ID=RR.REST_ID
WHERE RI.ADDRESS LIKE '서울%'
GROUP BY RR.REST_ID
ORDER BY SCORE DESC, RI.FAVORITES DESC;
```

**핵심 문법:**

- INNER JOIN
- LIKE 패턴 매칭
- ROUND 함수와 AVG 집계
- GROUP BY
- 다중 컬럼 정렬

---

### [131124] 그룹별 조건에 맞는 식당 목록 출력하기

**SQL 쿼리:**

```sql
SELECT M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE M INNER JOIN REST_REVIEW R ON M.MEMBER_ID=R.MEMBER_ID
WHERE M.MEMBER_ID=(
    SELECT M.MEMBER_ID
    FROM MEMBER_PROFILE M INNER JOIN REST_REVIEW R ON M.MEMBER_ID=R.MEMBER_ID
    GROUP BY R.MEMBER_ID
    ORDER BY COUNT(R.MEMBER_ID) DESC
    LIMIT 1
)
ORDER BY R.REVIEW_DATE ASC, R.REVIEW_TEXT ASC;
```

**핵심 문법:**

- INNER JOIN
- 스칼라 서브쿼리 (WHERE절)
- GROUP BY와 COUNT
- ORDER BY와 LIMIT (최댓값 찾기)
- DATE_FORMAT 날짜 포맷팅

---

### [131532] 년， 월， 성별 별 상품 구매 회원 수 구하기

**SQL 쿼리:**

```sql
SELECT YEAR(TB2.SALES_DATE) AS YEAR,
    MONTH(TB2.SALES_DATE) AS MONTH,
    TB1.GENDER AS GENDER,
    COUNT(DISTINCT TB2.USER_ID) AS USERS
FROM USER_INFO AS TB1 INNER JOIN ONLINE_SALE AS TB2 ON TB1.USER_ID=TB2.USER_ID
WHERE TB1.GENDER IS NOT NULL
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER;
```

**핵심 문법:**

- INNER JOIN
- YEAR, MONTH 날짜 함수
- COUNT(DISTINCT) 중복 제거 집계
- IS NOT NULL 조건
- 다중 컬럼 GROUP BY

---

### [131537] 오프라인／온라인 판매 데이터 통합하기

**SQL 쿼리:**

```sql
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE,
    PRODUCT_ID,
    USER_ID,
    SUM(SALES_AMOUNT) AS SALES_AMOUNT
FROM (
    SELECT *
    FROM ONLINE_SALE
    UNION ALL
    SELECT OFFLINE_SALE_ID, NULL AS USER_ID, PRODUCT_ID, SALES_AMOUNT, SALES_DATE
    FROM OFFLINE_SALE
) AS NEW_TB
WHERE SALES_DATE BETWEEN '2022-03-01' AND '2022-03-31'
GROUP BY SALES_DATE, PRODUCT_ID, USER_ID
ORDER BY SALES_DATE ASC, PRODUCT_ID ASC, USER_ID ASC;
```

**핵심 문법:**

- UNION ALL (중복 포함 합집합)
- 서브쿼리 (FROM절 인라인 뷰)
- NULL 값 처리
- DATE_FORMAT
- BETWEEN 날짜 범위
- 다중 컬럼 GROUP BY와 정렬

---

### [132204] 취소되지 않은 진료 예약 조회하기

**SQL 쿼리:**

```sql
SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, D.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM APPOINTMENT A
    INNER JOIN DOCTOR D ON D.DR_ID=A.MDDR_ID
    INNER JOIN PATIENT P ON P.PT_NO=A.PT_NO
WHERE A.MCDP_CD='CS'
    AND A.APNT_CNCL_YN='N'
    AND DATE_FORMAT(A.APNT_YMD, '%Y-%m-%d')='2022-04-13'
ORDER BY A.APNT_YMD ASC;
```

**핵심 문법:**

- 다중 테이블 INNER JOIN (3개 테이블)
- 복합 WHERE 조건
- DATE_FORMAT

---

### [133027] 주문량이 많은 아이스크림들 조회하기

**SQL 쿼리:**

```sql
SELECT FLAVOR
FROM (
    SELECT *
    FROM FIRST_HALF
    UNION ALL
    SELECT *
    FROM JULY
) AS ICECREAM
GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC
LIMIT 3;
```

**핵심 문법:**

- UNION ALL
- 서브쿼리 (FROM절)
- GROUP BY와 SUM
- ORDER BY에서 집계 함수 사용
- LIMIT

---

### [144856] 저자 별 카테고리 별 매출액 집계하기

**SQL 쿼리:**

```sql
SELECT NEW_TB.AUTHOR_ID,
    NEW_TB.AUTHOR_NAME,
    NEW_TB.CATEGORY,
    SUM(NEW_TB.TOTAL_SALES) AS TOTAL_SALES
FROM (
    SELECT TB2.AUTHOR_ID,
        TB2.AUTHOR_NAME,
        TB1.CATEGORY,
        TB1.PRICE * TB3.SALES AS TOTAL_SALES
    FROM BOOK TB1
        JOIN AUTHOR TB2 ON TB1.AUTHOR_ID=TB2.AUTHOR_ID
        JOIN BOOK_SALES TB3 ON TB1.BOOK_ID=TB3.BOOK_ID
    WHERE TB3.SALES_DATE >= '2022-01-01' AND TB3.SALES_DATE <= '2022-01-31'
    ORDER BY TB2.AUTHOR_ID, TB1.CATEGORY
) AS NEW_TB
GROUP BY NEW_TB.AUTHOR_ID, NEW_TB.CATEGORY
ORDER BY NEW_TB.AUTHOR_ID, NEW_TB.CATEGORY DESC;
```

**핵심 문법:**

- 다중 테이블 JOIN (3개 테이블)
- 서브쿼리 (FROM절)
- 이중 집계 (서브쿼리 내 계산 + 외부 SUM)
- 날짜 범위 조건
- 다중 컬럼 GROUP BY

---

### [151141] 자동차 대여 기록 별 대여 금액 구하기

**SQL 쿼리:**

```sql
SELECT HISTORY_ID,
    ROUND(TB2.DAILY_FEE * (
        1 - (
            CASE
                WHEN TB2.DIFF < 7 THEN 0
                ELSE (
                    SELECT DISCOUNT_RATE
                    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                    WHERE (CAR_TYPE, DURATION_TYPE) =
                        (TB2.CAR_TYPE, TB2.DURATION_TYPE)
                )
            END
        ) / 100
    ) * TB2.DIFF) AS FEE
FROM (SELECT TB2.HISTORY_ID, TB2.CAR_ID, TB1.CAR_TYPE,
    DATEDIFF(END_DATE, START_DATE) + 1 AS DIFF,
    CASE
        WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 90 THEN '90일 이상'
        WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '30일 이상'
        WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 7 THEN '7일 이상'
    END AS DURATION_TYPE,
    TB1.DAILY_FEE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS TB2
    LEFT JOIN CAR_RENTAL_COMPANY_CAR AS TB1
        ON TB2.CAR_ID=TB1.CAR_ID
ORDER BY TB2.HISTORY_ID ASC) AS TB2
WHERE TB2.CAR_TYPE='트럭'
ORDER BY FEE DESC, HISTORY_ID DESC;
```

**핵심 문법:**

- LEFT JOIN
- DATEDIFF 날짜 차이 계산
- 중첩된 CASE 표현식
- 상관 서브쿼리 (CASE 내부)
- 복잡한 수식 계산
- 서브쿼리 (FROM절)

---

### [157339] 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기

**SQL 쿼리:**

```sql
SELECT DISTINCT TB1.CAR_ID,
    TB1.CAR_TYPE,
    ROUND(TB1.DAILY_FEE * (1 - TB2.DISCOUNT_RATE / 100) * 30) AS FEE
FROM CAR_RENTAL_COMPANY_CAR AS TB1
    JOIN (
        SELECT CAR_TYPE, DISCOUNT_RATE
        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
        WHERE DURATION_TYPE='30일 이상'
    ) AS TB2 ON TB1.CAR_TYPE=TB2.CAR_TYPE
WHERE TB1.CAR_TYPE IN ('SUV', '세단')
    AND CAR_ID NOT IN (
        SELECT DISTINCT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE <= '2022-11-30' AND END_DATE >= '2022-11-01'
    )
GROUP BY TB1.CAR_ID HAVING FEE BETWEEN 500000 AND 1999999
ORDER BY FEE DESC, TB1.CAR_TYPE ASC, TB1.CAR_ID DESC;
```

**핵심 문법:**

- JOIN과 서브쿼리 (FROM절)
- NOT IN 서브쿼리
- DISTINCT
- HAVING절 조건
- BETWEEN
- 날짜 범위 겹침 조건

---

### [276035] FrontEnd 개발자 찾기

**SQL 쿼리:**

```sql
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS
WHERE SKILL_CODE & (SELECT SUM(CODE)
                        FROM SKILLCODES
                        WHERE CATEGORY='Front End') > 0
ORDER BY ID;
```

**핵심 문법:**

- 비트 연산자 (&)
- 서브쿼리 (WHERE절)
- SUM 집계

---

### [276036] 언어별 개발자 분류하기

**SQL 쿼리:**

```sql
WITH SKILL_P AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'Python'
),
SKILL_F AS (
    SELECT SUM(CODE) AS CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
),
SKILL_C AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'
)
SELECT CASE
        WHEN SKILL_P.CODE & SKILL_CODE AND SKILL_F.CODE & SKILL_CODE THEN 'A'
        WHEN SKILL_C.CODE & SKILL_CODE THEN 'B'
        WHEN SKILL_F.CODE & SKILL_CODE THEN 'C'
        END GRADE,
        ID, EMAIL
FROM DEVELOPERS, SKILL_P, SKILL_F, SKILL_C
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID
```

**핵심 문법:**

- CTE (WITH 절) - 여러 개
- 비트 연산자 (&)
- CROSS JOIN (암묵적)
- CASE 표현식
- HAVING절

---

### [284528] 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기

**SQL 쿼리:**

```sql
SELECT E.EMP_NO,
    E.EMP_NAME,
    CASE
        WHEN AVG(G.SCORE) >= 96 THEN 'S'
        WHEN AVG(G.SCORE) >= 90 THEN 'A'
        WHEN AVG(G.SCORE) >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE,
    E.SAL * (
        CASE
            WHEN AVG(G.SCORE) >= 96 THEN 0.2
            WHEN AVG(G.SCORE) >= 90 THEN 0.15
            WHEN AVG(G.SCORE) >= 80 THEN 0.1
            ELSE 0
        END
    ) AS BONUS
FROM HR_EMPLOYEES E JOIN HR_GRADE G ON E.EMP_NO=G.EMP_NO
GROUP BY E.EMP_NO
ORDER BY E.EMP_NO ASC;
```

**핵심 문법:**

- JOIN
- GROUP BY와 AVG
- 중첩된 CASE 표현식
- CASE 내에서 집계 함수 사용

---

### [301650] 특정 세대의 대장균 찾기

**SQL 쿼리:**

```sql
WITH RECURSIVE RECV AS (
    SELECT ID, PARENT_ID, 1 AS GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    UNION ALL
    SELECT TB1.ID, TB1.PARENT_ID, TB2.GENERATION + 1
    FROM ECOLI_DATA TB1 INNER JOIN RECV TB2 ON TB1.PARENT_ID=TB2.ID
)
SELECT ID
FROM RECV
WHERE GENERATION=3
ORDER BY ID ASC;
```

**핵심 문법:**

- 재귀 CTE (WITH RECURSIVE)
- UNION ALL
- 자기 참조 JOIN
- 계층 구조 쿼리

---

### [59045] 보호소에서 중성화한 동물

**SQL 쿼리:**

```sql
SELECT INS.ANIMAL_ID, INS.ANIMAL_TYPE, INS.NAME
FROM ANIMAL_INS AS INS JOIN ANIMAL_OUTS AS OUTS ON INS.ANIMAL_ID=OUTS.ANIMAL_ID
WHERE INS.SEX_UPON_INTAKE LIKE 'Intact%'
    AND (OUTS.SEX_UPON_OUTCOME LIKE 'Neutered%'
    OR OUTS.SEX_UPON_OUTCOME LIKE 'Spayed%')
ORDER BY INS.ANIMAL_ID;
```

**핵심 문법:**

- JOIN
- LIKE 패턴 매칭
- OR 조건

---

### [59413] 입양 시각 구하기（2）

**SQL 쿼리:**

```sql
WITH RECURSIVE CTE AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1 FROM CTE
    WHERE HOUR < 23
)
SELECT CTE.HOUR,
    IFNULL(COUNT(TB1.ANIMAL_ID), 0) AS COUNT
FROM ANIMAL_OUTS AS TB1 RIGHT JOIN CTE ON HOUR(TB1.DATETIME)=CTE.HOUR
GROUP BY CTE.HOUR
ORDER BY CTE.HOUR;
```

**핵심 문법:**

- 재귀 CTE (WITH RECURSIVE) - 숫자 생성
- RIGHT JOIN
- HOUR 날짜 함수
- IFNULL 함수
- GROUP BY

---

### [62284] 우유와 요거트가 담긴 장바구니

**SQL 쿼리:**

```sql
SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME IN ('Milk', 'Yogurt')
GROUP BY CART_ID HAVING COUNT(DISTINCT NAME)=2
ORDER BY CART_ID;
```

**핵심 문법:**

- IN 조건
- GROUP BY
- HAVING절
- COUNT(DISTINCT) - 조건부 카운팅

---

## 🔑 주요 학습 포인트

### 1. **재귀 CTE (Recursive Common Table Expression)**

Level 4의 핵심 기법으로, 계층 구조 데이터나 순차 데이터 생성에 사용됩니다.

- `WITH RECURSIVE` 구문
- 자기 참조를 통한 반복 처리
- 예: 세대 추적, 시간대 생성 (0~23시)

### 2. **복잡한 JOIN 패턴**

- 다중 테이블 JOIN (3개 이상)
- LEFT JOIN, RIGHT JOIN, INNER JOIN 혼합
- 서브쿼리와 JOIN 결합
- CROSS JOIN (암묵적 사용)

### 3. **고급 서브쿼리**

- FROM절 서브쿼리 (인라인 뷰)
- WHERE절 서브쿼리 (스칼라, IN, NOT IN)
- 상관 서브쿼리
- 다중 컬럼 비교 `(COL1, COL2) IN (...)`

### 4. **UNION / UNION ALL**

서로 다른 테이블의 데이터를 통합할 때 사용:

- `UNION`: 중복 제거
- `UNION ALL`: 중복 포함 (성능 우수)

### 5. **CASE 표현식의 고급 활용**

- 중첩된 CASE 문
- CASE 내에서 집계 함수 사용
- CASE 내에서 서브쿼리 사용
- 조건부 계산 및 분류

### 6. **비트 연산**

스킬 코드나 플래그 관리에 사용:

- `&` (AND 비트 연산자)
- 여러 스킬 조합 판별

### 7. **날짜 함수 활용**

- `DATEDIFF`: 날짜 차이 계산
- `DATE_FORMAT`: 날짜 포맷 변환
- `YEAR`, `MONTH`, `HOUR`: 날짜 부분 추출
- 날짜 범위 겹침 판단

### 8. **집계 함수와 GROUP BY**

- `COUNT(DISTINCT)`: 중복 제거 카운트
- `AVG`, `SUM`, `MAX`
- 다중 컬럼 GROUP BY
- HAVING절을 통한 집계 결과 필터링

### 9. **윈도우 함수 대안**

Level 4에서는 재귀 CTE와 서브쿼리로 윈도우 함수 기능을 구현하는 경우가 많습니다.

### 10. **NULL 처리**

- `IS NULL`, `IS NOT NULL`
- `IFNULL`, `COALESCE`
- LEFT/RIGHT JOIN과 NULL 처리

### 11. **성능 최적화 기법**

- `DISTINCT` 사용 시기
- `UNION ALL` vs `UNION`
- 서브쿼리 vs JOIN 선택
- 인덱스를 고려한 WHERE 조건 작성

### 난이도 특징

Level 4는 **여러 기법을 조합**하여 복잡한 비즈니스 로직을 구현하는 능력을 요구합니다. 단순히 문법을 아는 것을 넘어, 문제 상황에 맞는 최적의 쿼리 구조를 설계하는 능력이 중요합니다.
