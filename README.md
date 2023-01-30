## DML - Data Manipulation Language
- 데이터 조작어. 테이블에 데이터를 검색, 삽입, 수정, 삭제하는 데 사용

<details>
<summary style="font-size: x-large; font-weight: 600;">SELECT</summary>

### SELECT
- 데이터를 검색하는 기본 문장. 특별히 질의어(Query) 라고 부른다.
- 검색한 결과를 테이블 형태로 출력
    ```sql
    SELECT      email
    FROM        member;
    ```
  
### WHERE
- 조건에 맞는 검색을 할 때 사용
- 조건으로 사용하는 술어는 비교, 범위, 집합, 패턴, NULL 이 있다.
  - 비교
    - =, <>, <, <=, >, >=
  - 범위
    - BETWEEN
  - 집합
    - IN, NOT IN
  - 패턴
    - LIKE
  - NULL
    - IS NULL, IS NOT NULL
  - 복합 조건
    - AND, OR, NOT
  ```sql
    SELECT      email
    FROM        member
    WHERE       email LIKE '%ruby%';
    AND         salary BETWEEN 100000 AND 200000
    AND         IN (BACKEND, FRONTEND)
    AND         NOT salary = 150000;
  ```

### ORDER BY
- SQL 문의 실행 결과를 특정 기준으로 정렬하여 출력할 때 사용
- 기본적으로 오름차순으로 정렬하며 내림차순으로 정렬 시에는 DESC 키워드를 사용
    ```sql
    -- 오름차순 조회시
    SELECT      email
    FROM        member
    ORDER BY    salary;
  
    -- 내림차순 조회시
    SELECT      email
    FROM        member
    ORDER BY    salary DESC;
    ```

### 집계(통계) 함수
- 여러 행으로부터 하나의 결과값을 반환하는 함수
- SELECT 구문에서만 사용됨
- 주로 평균, 합, 최대, 최소 등을 구하는 데 사용됨
  - COUNT
    - 지정한 컬럼(여러 컬럼 지정 가능)의 값이 NULL 이 아닌 행의 개수를 세는 집계함수.
  - SUM
    - 지정한 컬럼의 값의 총 합을 구하는 집계함수
  - AVG
    - 지정한 컬럼(여러 컬럼 지정 가능)의 값이 NULL 이 아닌 값들의 평균을 구하는 집계함수
  - MIN
    - 지정한 컬럼의 값들 중 최소값을 구하는 집계함수
  - MAX
    - 지정한 컬럼의 값들 중 최대값을 구하는 집계함수
  ```sql
  SELECT    SUM(saleprice) AS Total,
            AVG(saleprice) AS Average,
            MIN(saleprice) AS Mininum,
            MAX(saleprice) AS Maximum
  FROM      Orders;
  
  -- 판매 기록 수를 조회
    SELECT    COUNT(*)
    FROM      Orders;
    
    -- 중복을 제외한, 구매자 수를 조회
    SELECT    COUNT(DISRINCT shoper)
    FROM      Orders;
  ```
  
### GROUP BY
- 같은 속성 값끼리 그룹화할 때 사용
- 그룹화 한 결과의 SELECT 절에는 GROUP BY 에서 명시한 속성과 집계함수만 사용할 수 있다.
  ```sql
  SELECT      custid, COUNT(*) AS 판매수량, SUM(saleprice) AS 총 판매액
  FROM        Orders
  GROUP BY    custid;
  ```  
  ![img.png](img/group-by.png)  
  *그룹화 전과 그룹화 후의 결과 비교*  

### HAVING
- GROUP BY 로 그룹화한 그룹을 제한하여 조회할 때 사용
  1. WHERE 절에 작성한 결과를 GROUP BY 절로 그룹화
  2. 1의 결과에서 HAVING 조건애 해댱하는 결과를 최종 결과로 출력
  ```sql
                                                -- 실행 순서
  SELECT      custid, COUNT(*) AS 도서수량        -- 5
  FROM        Orders                            -- 1
  WHERE       saleprice >= 8000                 -- 2
  GROUP BY    custid                            -- 3
  HAVING      COUNT(*) >= 2                     -- 4
  ORDER BY    custid;                           -- 6
  ```
</details>


