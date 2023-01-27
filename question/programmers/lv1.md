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