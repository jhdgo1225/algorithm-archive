# Level 5 MySQL 문법 정리

## 문제별 주요 개념

### 1. 상품을 구매한 회원 비율 구하기 (131534)
**난이도**: Level 5
**주요 개념**: 
- 서브쿼리를 사용한 비율 계산
- NATURAL JOIN
- 날짜 함수 (YEAR, MONTH)
- ROUND 함수
- COUNT DISTINCT

**문제 요약**: 2021년에 가입한 회원 중 상품을 구매한 회원의 월별 비율 구하기

**핵심 SQL 문법**:
```sql
SELECT YEAR(TB2.SALES_DATE) AS YEAR,
    MONTH(TB2.SALES_DATE) AS MONTH,
    COUNT(DISTINCT TB2.USER_ID) AS PURCHASED_USERS,
    ROUND(COUNT(DISTINCT TB2.USER_ID) / (
            SELECT COUNT(DISTINCT USER_ID)
            FROM USER_INFO
            WHERE YEAR(JOINED)=2021
        ), 1) AS PUCHASED_RATIO
FROM USER_INFO AS TB1 NATURAL JOIN ONLINE_SALE AS TB2
WHERE TB1.USER_ID=TB2.USER_ID AND YEAR(TB1.JOINED)=2021
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH
```

**학습 포인트**:
- `NATURAL JOIN`: 두 테이블의 같은 이름을 가진 모든 컬럼을 기준으로 조인
- `서브쿼리`: SELECT 절에서 서브쿼리를 사용하여 전체 대비 비율 계산
- `ROUND(값, 소수점자리)`: 소수점 반올림
- `COUNT(DISTINCT 컬럼)`: 중복 제거 후 카운트
- `YEAR(날짜)`, `MONTH(날짜)`: 날짜에서 연도, 월 추출

---

### 2. 멸종위기의 대장균 찾기 (301651)
**난이도**: Level 5
**주요 개념**: 
- 재귀 쿼리 (Recursive CTE)
- WITH RECURSIVE
- 계층 구조 탐색
- LEFT JOIN을 활용한 자식 노드 확인

**문제 요약**: 대장균의 세대별로 자식이 없는 개체 수를 구하기

**핵심 SQL 문법**:
```sql
WITH RECURSIVE CTE
AS ( 
    SELECT ID, PARENT_ID, 1 as GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    UNION ALL
    SELECT TB1.ID, TB1.PARENT_ID, CTE.GENERATION + 1
    FROM ECOLI_DATA as TB1 INNER JOIN CTE on TB1.PARENT_ID=CTE.ID
)
SELECT COUNT(DISTINCT TB1.ID) AS COUNT,
    TB1.GENERATION
FROM CTE AS TB1 LEFT JOIN CTE AS TB2 ON TB1.ID=TB2.PARENT_ID
WHERE TB2.ID IS NULL
GROUP BY TB1.GENERATION
ORDER BY TB1.GENERATION;
```

**학습 포인트**:
- `WITH RECURSIVE CTE AS`: 재귀 쿼리를 사용하여 계층 구조 탐색
- **재귀 쿼리 구조**:
  1. Anchor Member: 초기 행 설정 (PARENT_ID가 NULL인 루트)
  2. `UNION ALL`로 연결
  3. Recursive Member: 이전 결과와 조인하여 다음 세대 추가
- `LEFT JOIN`을 활용한 리프 노드 찾기: 자식이 없으면 TB2.ID가 NULL
- 세대를 표시하기 위해 재귀할 때마다 `GENERATION + 1`

---

## Level 5 핵심 문법 요약

### 1. 재귀 쿼리 (Recursive CTE)
```sql
WITH RECURSIVE CTE_NAME AS (
    -- Anchor Member (초기 조건)
    SELECT 컬럼들, 초기값 AS 레벨
    FROM 테이블
    WHERE 초기조건
    
    UNION ALL
    
    -- Recursive Member (재귀 조건)
    SELECT 컬럼들, 레벨 + 1
    FROM 테이블 INNER JOIN CTE_NAME ON 조인조건
)
SELECT * FROM CTE_NAME;
```

### 2. 비율 계산 및 서브쿼리
```sql
SELECT 
    컬럼,
    COUNT(*) / (SELECT COUNT(*) FROM 테이블 WHERE 조건) AS 비율
FROM 테이블
GROUP BY 컬럼;
```

### 3. 계층 구조에서 리프 노드 찾기
```sql
-- LEFT JOIN을 사용하여 자식이 없는 노드 찾기
SELECT TB1.*
FROM 테이블 AS TB1
LEFT JOIN 테이블 AS TB2 ON TB1.ID = TB2.PARENT_ID
WHERE TB2.ID IS NULL;
```

### 4. NATURAL JOIN
```sql
-- 같은 이름의 모든 컬럼을 기준으로 조인
SELECT *
FROM 테이블1 NATURAL JOIN 테이블2;
```

---

## Level 5 문제 특징
- **고급 쿼리 기법 필요**: 재귀 쿼리, 복잡한 서브쿼리
- **계층 구조 처리**: 부모-자식 관계 탐색
- **복합적인 집계**: 여러 단계의 계산과 그룹화
- **성능 최적화 고려**: 효율적인 쿼리 작성 필요

---

*마지막 업데이트: 2026년 1월 13일*
