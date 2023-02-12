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


<details>
<summary style="font-size: x-large; font-weight: 600;">JOIN</summary>

## JOIN
- 여러 테이블을 연결하여 결합하는 연산
- 조인을 통한 조회 결과 역시 테이블의 형태로 GROUP BY, ORDER BY 등을 적용할 수 있다.
  ```sql
                                                    -- 실행 순서
  SELECT    name, SUM(saleprice)                    -- 5
  FROM      Customer, Orders                        -- 1
  WHERE     Customer.custid = Orders.custid         -- 2
  GROUP BY  Customer.name                           -- 3
  ORDER BY  Customer.name                           -- 4
  ```

### Cartesian Product
- 조인된 테이블 간의 결합으로 발생할 수 있는 모든 경우를 출력하는 연산
  - 조인된 결과가 연관관계에 맞지 않는 경우까지 포함
- 테이블을 조인할 때 별도의 조건을 걸지 않을 경우에 발생
  - 대부분 실무에서 Cartesian Product 의 결과를 조회하는 일은 거의 없다.

### JOIN 문법
![img.png](img/join.png)

- 내부 조인
  ```sql
  SELECT        속성들
  FROM          table1 , table2
  WHERE         조인 조건
  AND           검색 조건
  
  SELECT        속성들
  FROM          table1
  INNER JOIN    table2
  ON            조인 조건
  WHERE         검색 조건
  AND           검색 조건
  ```

- 외부 조인
  ```sql
  SELECT                                속성들
  FROM                                  table1
  [LEFT | RIGHT | FULL] OUTER JOIN      table2
  ON                                    조인 조건
  WHERE                                 검색 조건
  AND                                   검색 조건
  ```

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">Sub Query - 부속질의</summary>

## Sub Query
- 부속질의. SQL 문 내에 또 다른 SQL 문을 작성
- 조회 결과를 또 다른 SQL 문에 활용하기 위해 사용

### Sub Query 의 종류
1. 중첩질의 - WHERE 부속질의
   - WHERE 절에서 사용되는 부속질의
   - 데이터를 선택하는 조건 혹은 술어와 함께 사용됨
   - 중첩질의 연산자의 종류
     ![img.png](img/subquery-operation.png)  
     1. 비교 연산자
        - 비교 연산자는 부속질의가 반드시 단일 행, 단일 열을 반환해야한다.
          ```sql
          SELECT  bookname
          FROM    Book
          WHERE   price = (
                          SELECT  MAX(price)
                          FROM    Book
                      );
          ```
         
     2. 집합 연산자
        - IN
          - 메인 질의의 속성 값이 부속질의에서 제공한 결과 집합에 있는지 확인
        - NOT IN
          - 메인 질의의 속성 값이 부속질의에서 제공한 결과 집합에 없는지 확인
        ```sql
        SELECT  SUM(saleprice) AS 'total'
        FROM    Orders
        WHERE   custid IN (
                             SELECT  custid
                             FROM    Customer
                             WHERE   address LIKE '%대한민국%'
                         );
        ```
       
     3. 한정 연산자
        - 비교 연산자와 함께 사용
        - ALL
          - 부속질의의 모든 결과와 비교하여 비교 조건에 모두 만족하는지 확인
        - SOME
          - 부속질의의 모든 결과와 비교하여 비교 조건에 하나라도 만족하는지 확인
        ```sql
        SELECT   orderid, saleprice
        FROM     Orders
        WHERE    saleprice > ALL (
                                     SELECT  saleprice
                                     FROM    Orders
                                     WHERE   custid = '3'
                                 );
       
        SELECT   orderid, saleprice
        FROM     Orders
        WHERE    saleprice > SOME (
                                     SELECT  saleprice
                                     FROM    Orders
                                     WHERE   custid = '3'
                                 );
        ```
    
     4. 존재 연산자
        - 데이터의 존재 여부를 확인할 때 사용
        - EXISTS
          - 메인 질의에서 부속질의로 제공된 속성의 값울 가지고 부속질의의 조건을 만족하여 값이 존재할 경우
          메인 질의에서 해당 행의 데이터를 출력
        - NOT EXISTS
          - 메인 질의에서 부속질의로 제공된 속성의 값울 가지고 부속질의의 조건을 만족하여 값이 존재하지 않을 경우
            메인 질의에서 해당 행의 데이터를 출력
        ```sql
        -- FROM 에 해당하는 데이터의 행들 중에서 부속질의의 조건을 만족하는 행들을 조회
        SELECT   SUM(saleprice) AS 'total'
        FROM     Orders od
        WHERE    EXISTS (
                             SELECT  *
                             FROM    Customer cs
                             WHERE   address LIKE '%대한민국%'
                             AND     cs.custid = od.custid
                         )    
        ```
       
2. 스칼라 부속질의 - SELECT 부속질의
    - SELECT 절에 사용되는 부속질의
    - 단일 행, 단일 열의 스칼라 값(단일 값)으로 반환
      - 원칙적으로 스칼라 값이 들어갈 수 있는 모든 곳에 사용 가능하며, 읿잔적으로 SELECT 문과 UPDATE SET 절에 사용됨
      ```sql
      -- 모든 행에 대해서 행의 수만큼 부속질의가 실행되므로 성능상 문제가 될 수 있다.
                                                                -- 실행 순서
      SELECT                                                    -- 4
                custid, 
                (                                               -- 3
                    SELECT      name
                    FROM        Customer cs
                    WHERE       cs.custid = od.custid
                ),
                SUM(saleprice)
      FROM      Orders od                                       -- 1
      GROUP BY  custid;                                         -- 2

      
      -- Orders 테이블의 모든 bookname 을 Book 테이블의 Book.bookid = Orders.bookid 조건에 맞는 bookname 으로 변경
      UPDATE    Orders
      SET       bookname = (
                                SELECT      bookname
                                FROM        Book
                                WHERE       Book.bookid = Orders.bookid
                            ) 
      ```
      
3. 인라인 뷰 - FROM 부속질의
    - FROM 절에서 사용되는 부속질의
      - VIEW
        - 일시적으로 만들어진 가상의 테이블
    - 테이블 대신 인라인 뷰 부속질의를 사용
      - 부속질의의 조회 결과가 테이블의 역할을 담당
    ```sql
                                                        -- 실행 순서
    SELECT      cs.name, SUM(id.saleprice) 'total'      -- 4
    FROM        (
                                                        -- 1
                    SELECT      custid, name
                    FROM        Customer
                    WHERE       custid <= 2
                ) AS cs,
                Orders od
    WHERE       cs.custid = od.custid                   -- 2
    GROUP BY    cs.name;                                -- 3
    ```

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">Set Operation - 집합연산</summary>

## Set Operation
- 집합연산. 두 개 이상의 테이블에서 조인을 사용하지 않고 연관된 데이터를 조회하는 방법  
  ![img.png](img/set-operation.png)

### UNION
- 두 조회결과를 합한 하나의 결과를 조회
  - A UNION B
    - A 와 B 를 합한 결과를 중복을 제거하여 조회
  - A UNION ALL B
    - A 와 B 를 합한 결과를 중복을 제거하지 않고 조회
  ```sql
  -- Customer 와 team 에 있는 모든 이름을 중복을 제거하여 조회
  SELECT    name
  FROM      Customer
  UNION
  SELECT    name
  FROM      team
  
  -- Customer 와 team 에 있는 모든 이름을 중복을 제거하지 않고 조회
  SELECT    name
  FROM      Customer
  UNION ALL
  SELECT    name
  FROM      team
  ```

### INTERSECT
- 두 조회결과에 모두 존재하는 행들을 조회
  ```sql
  -- Customer 와 team 양쪽에 모두 존재하는 이름을 조회
  SELECT    name
  FROM      Customer
  INTERSECT
  SELECT    name
  FROM      team
  ```

### MINUS
- 반대쪽 결과에 존재하지 않는 행들을 조회
  ```sql
  -- Customer 에 있는 이름들 중 team 에 없는 이름을 조회
  SELECT    name
  FROM      Customer
  MINUS
  SELECT    name
  FROM      team
  ```

### EXISTS
- 부속질의의 결과의 행이 존재하는 조건을 만족하는 메인질의의 행을 결과에 포함하여 조회
  ```sql
  -- 주문내역이 있는 고객의 이름과 주소를 조회
                                                            -- 실행 순서
  SELECT    name, address                                   -- 3
  FROM      Customer cs                                     -- 1
  WHERE     EXISTS (                                        -- 2 (1의 모든 행에 대하여 부속질의를 확인)
                        SELECT  *
                        FROM    Orders od
                        WHERE   cs.custid = od.custid
                    );
  ```
</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">INSERT</summary>

### INSERT
- 테이블에 새로운 튜플을 삽입하는 명령어
  ```sql
  INSERT INTO   MEMBER(name, email)             -- 컬럼을 지정하지 않을 경우 values 에 모든 컬럼을 값을 넣어주어야 한다.
  VALUES        ('ruby', 'ruby@gmail.com');
  ```

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">UPDATE</summary>

### UPDATE
- 특정 속성값을 수정하는 명령어
  ```sql
  UPDATE    MEMBER
  SET       name = 'diamond', email = 'diamond@gmail.com'
  WHERE     id = 1;
  -- 조건을 걸지 않으면 테이블의 모든 행을 대상으로 속성을 변경하므로 주의해야한다.
  -- 특정 한 행의 속성을 변경할 때에는 조건에 PK, Unique 값을 조건으로 걸어준다.
  ```

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">DELETE</summary>

### DELETE
- 테이블의 기존 튜플을 삭제하는 명령어
  ```sql
  DELETE
  FROM      MEMBER
  WHERE     id = 1;
  -- 조건을 걸지 않으면 테이블의 모든 행을 삭제하므로 주의해야한다.
  -- 특정 한 행의 속성을 삭제할 때에는 조건에 PK, Unique 값을 조건으로 걸어준다.
  ```

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">STORED FUNCTION - 내장함수</summary>

## STORED FUNCTION
- 상수나 속성 이름을 입력값으로 받아 단일 값을 결과로 반환하는 함수

### 숫자 관련 내장 함수
![img.png](img/number-function.png)  
```sql
-- 숫자 대신 숫자 값을 가지는 열이름을 사용할 수도 있다.
SELECT      ABS(-5),                -- 5
            ROUND(124.56, 1),       -- 124,6
            ROUND(1245000, -3),     -- 1250000
            CEIL(124.56),           -- 124
            POWER(2, 3),            -- 8
            SQRT(9),                -- 3
            SIGN(14),               -- 1
            SIGN(-14),              -- -1
            SIGN(0),                -- 0
            LOG(10)                 -- 2.30259
FROM        DUAL;
```

### 문자 함수
![img_1.png](img/string-function.png)  
```sql
SELECT      REPLACE('플루트는 관약기', '플루트', '클라리넷'),          -- 클라리넷는 관약기
            LENGTH('플루트'),                                    -- 3
            SUBSTR('플루트는 관약기', 1, 6),                       -- 플루트는 관. 두번째 인자는 시작 위치(1부터 시작), 세번째 인자는 길이
            LOWER('FLUTE'),                                    -- flute 
            UPPER('flute'),                                    -- FLUTE
            LPAD('page 1', 10, '*'),                            -- ****page 1
            RPAD('page 1', 10, '*'),                            -- page 1****
FROM        DUAL;
```

### 날짜 / 시간 함수
![img.png](img/date-function.png)  
```sql
SELECT      TO_DATE('2023-01-30', 'yyyy-mm-dd'),                            -- 2023-01-30
            TO_CHAR(TO_DATE('2023-01-30', 'yyyy-mm-dd'), 'yyyymmdd'),       -- '20230130'
FROM        DUAL;
```
</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">ROWNUM</summary>

### ROWNUM
- 오라클 내부적으로 생성되는 가상 컬럼
- 조회 결과의 순번을 나타냄 (1번 부터 시작)
```sql
SELECT      ROWNUM AS '순번', custid, name, phone
FROM        CUSTOMER
WHERE       ROWNUM <= 2;
```
![img.png](img/rownum.png)

</details>


<details>
<summary style="font-size: x-large; font-weight: 600;">VIEW</summary>

## VIEW
- 하나 이상의 테이블을 합항녀 만든 가상의 테이블
- SELECT 문을 통해 얻은 최종 결과를 가상의 테이블로 정의하여 실제 테이블처럼 사용할 수 있도록 만든 데이터베이스 개체
  - 실제 데이터를 저장하지 않고 뷰를 생성할 때 사용한 SELECT 문의 정의를 DBMS 가 저장
    - JAVA 에서 변수에 값을 저장해두고 재사용하는 것과 같은 개념

### VIEW 의 생성, 수정, 삭제
- VIEW 생성 및 사용
    ```sql
    CREATE VIEW Vorders
    AS (
            SELECT  O.orderid, O.custid, C.name
            FROM    Orders O, Customer C
            WHERE   O.custid = C.custid
       );
       
    -- VIEW 를 통한 조회. 이 때, VIEW 는 SubQuery 와 같은 역할을 한다.
    SELECT  *
    FROM    Vorders;
    ```
- VIEW 수정
    ```sql
    CREATE OR REPLACE VIEW Vorders
    AS (
            -- 수정할 VIEW 의 내용
            SELECT  O.orderid, O.custid, C.name
            FROM    Orders O, Customer C
            WHERE   O.custid = C.custid
       );
    ```
- VIEW 삭제
    ```sql
    DROP VIEW Vorders;
    ```

### VIEW 의 장점
- 편의성
  - 미리 작성된 질의를 VIEW 로 정의해둠으로서 질의 재사용이 가능하며 해당 질의문이 필요할 때 간단하고 VIEW 를 통해 조회할 수 있다.
- 보안성
  - 원본 테이블에서 보안이 필요한 속성을 제외하고 VIEW 를 통해 새로운 가상의 테이블을 정의하여 사용자에게 제공함으로서 데이터 보안성을 높일 수 있다.
    - 이 때, 원본 테이블은 사용자게에 제공하지 않는다.
- 논리적 데이터 독립성
  - VIEW 를 정의하여 응용 프로그램이 사용하게 되면 개념 스키마에 정의된 테이블 구조가 변경되어도 응용 프로그램의 변경을 막아주기 때문에
  논리적 데이터 독립성을 제공한다.
    - API 에서 엔티티가 아닌 DTO 를 통해 클라이언트에게 데이터를 반환해주는 것과 같은 개념

### VIEW 의 단점
- 응용프로그램 쪽에서 VIEW 의 구조를 확인하기 위해서는 DB 를 확인해야함
- 응용프로그램에서 VIEW 에 사용된 질의를 관리할 수 없음

</details>