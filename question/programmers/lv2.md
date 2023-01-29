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


<details>
<summary>진료과별 총 예약 횟수 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/132202
```sql
SELECT      MCDP_CD AS 진료과코드, COUNT(*) AS 5월예약건수
FROM        APPOINTMENT
WHERE       DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY    MCDP_CD, DATE_FORMAT(APNT_YMD, '%Y-%m')
ORDER BY    5월예약건수, 진료과코드;
```
</details>


<details>
<summary>성분으로 구분한 아이스크림 총 주문량</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/133026
```sql
SELECT      ICECREAM_INFO.INGREDIENT_TYPE, SUM(TOTAL_ORDER) AS TOTAL_ORDER
FROM        FIRST_HALF, ICECREAM_INFO
WHERE       FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR
GROUP BY    ICECREAM_INFO.INGREDIENT_TYPE
ORDER BY    TOTAL_ORDER;
```
</details>


<details>
<summary>가격대 별 상품 개수 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131530
```sql
SELECT      TRUNCATE(PRICE, -4) AS PRICE_GROUP, COUNT(*) AS PRODUCTS
FROM        PRODUCT
GROUP BY    TRUNCATE(PRICE, -4)
ORDER BY    PRODUCTS;
```
</details>


<details>
<summary>자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/151137
```sql
SELECT      CAR_TYPE, COUNT(CAR_TYPE) CARS
FROM        CAR_RENTAL_COMPANY_CAR
WHERE       OPTIONS LIKE '%통풍시트%'
OR          OPTIONS LIKE '%열선시트%'
OR          OPTIONS LIKE '%가죽시트%'
GROUP BY    CAR_TYPE
ORDER BY    CAR_TYPE;
```
</details>