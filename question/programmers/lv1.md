## 프로그래머스 SQL Level 1 문제

<details>
<summary>평균 일일 대여 요금 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/151136
```sql
SELECT      ROUND(AVG(DAILY_FEE)) AS AVERAGE_FEE
FROM        CAR_RENTAL_COMPANY_CAR
WHERE       CAR_TYPE = 'SUV';
```
</details>


<details>
<summary>조건에 맞는 도서 리스트 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/144853
```sql
SELECT      BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM        BOOK
WHERE       PUBLISHED_DATE BETWEEN '2021-01-01' AND '2021-12-31'
AND         CATEGORY = '인문'
ORDER BY    PUBLISHED_DATE;
```
</details>


<details>
<summary>강원도에 위치한 생산공장 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131112
```sql
SELECT      FACTORY_ID, FACTORY_NAME, ADDRESS
FROM        FOOD_FACTORY
WHERE       ADDRESS LIKE '강원도 %'
ORDER BY    FACTORY_ID;
```
</details>


<details>
<summary>인기있는 아이스크림</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/133024
```sql
SELECT      FLAVOR
FROM        FIRST_HALF
ORDER BY    TOTAL_ORDER DESC, SHIPMENT_ID;
```
</details>


<details>
<summary>흉부외과 또는 일반외과 의사 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/132203
```sql
SELECT      DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD
FROM        DOCTOR
WHERE       MCDP_CD IN ('CS', 'GS')
ORDER BY    HIRE_YMD DESC, DR_NAME;
```
</details>


<details>
<summary>과일로 만든 아이스크림 고르기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/133025
```sql
SELECT      FIRST_HALF.FLAVOR AS FLAVOR
FROM        FIRST_HALF, ICECREAM_INFO
WHERE       FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR
AND         FIRST_HALF.TOTAL_ORDER > 3000
AND         ICECREAM_INFO.INGREDIENT_TYPE = 'fruit_based'
ORDER BY    FIRST_HALF.TOTAL_ORDER DESC;
```
</details>


<details>
<summary>12세 이하인 여자 환자 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/132201
```sql
SELECT      PT_NAME, PT_NO, GEND_CD, AGE, CASE WHEN TLNO IS NULL THEN 'NONE' ELSE TLNO END AS TLNO
FROM        PATIENT
WHERE       GEND_CD = 'W'
AND         AGE <= 12
ORDER BY    AGE DESC, PT_NAME;
```
</details>


<details>
<summary>조건에 맞는 회원수 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131535
```sql
SELECT      COUNT(*)
FROM        USER_INFO
WHERE       JOINED BETWEEN '2021-01-01' AND '2021-12-31'
AND         AGE BETWEEN 20 AND 29
```
</details>