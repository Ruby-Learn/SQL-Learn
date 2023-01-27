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

</details>


