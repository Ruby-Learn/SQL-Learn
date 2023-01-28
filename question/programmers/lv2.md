## 프로그래머스 SQL Level 2 문제

<details>
<summary>3월에 태어난 여성 회원 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131120
```sql
SELECT      MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') AS DATE_OF_BIRTH
FROM        MEMBER_PROFILE
WHERE       GENDER = 'W'
AND         MONTH(DATE_OF_BIRTH) = 3
AND         TLNO IS NOT NULL
ORDER BY    MEMBER_ID;
```
</details>


<details>
<summary>재구매가 일어난 상품과 회원 리스트 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131536
```sql
SELECT      USER_ID, PRODUCT_ID
FROM        ONLINE_SALE
GROUP BY    USER_ID, PRODUCT_ID
HAVING      COUNT(*) > 1
ORDER BY    USER_ID, PRODUCT_ID DESC;
```
</details>


<details>
<summary>가격이 제일 비싼 식품의 정보 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131115
```sql
SELECT      PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
FROM        FOOD_PRODUCT
WHERE       PRICE = (
                        SELECT      MAX(PRICE)
                        FROM        FOOD_PRODUCT
                    );
```
</details>