## 프로그래머스 SQL Level 4 문제

<details>
<summary>서울에 위치한 식당 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131118
```sql
-- REST_INFO : REST_REVIEW = 1 : N
SELECT      REST_INFO.REST_ID,
            REST_INFO.REST_NAME,
            REST_INFO.FOOD_TYPE,
            REST_INFO.FAVORITES,
            REST_INFO.ADDRESS,
            REVIEW.SCORE
FROM        REST_INFO
LEFT JOIN   (
                SELECT      REST_ID, ROUND(AVG(REVIEW_SCORE), 2) AS SCORE
                FROM        REST_REVIEW
                WHERE       REVIEW_SCORE IS NOT NULL
                GROUP BY    REST_ID
            ) REVIEW
ON          REST_INFO.REST_ID = REVIEW.REST_ID
WHERE       REST_INFO.ADDRESS LIKE '서울%'
AND         REVIEW.SCORE IS NOT NULL
ORDER BY    REVIEW.SCORE DESC, REVIEW.SCORE DESC;
```
</details>


<details>
<summary>오프라인/온라인 판매 데이터 통합하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131537
```sql
-- ONLINE_SALE, OFFLINE_SALE
SELECT      SALES_DATE,
            PRODUCT_ID,
            USER_ID,
            SALES_AMOUNT
FROM        (
                SELECT      DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
                FROM        ONLINE_SALE
                WHERE       SALES_DATE BETWEEN '2022-03-01' AND '2022-03-31'
                UNION ALL
                SELECT      DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, NULL, SALES_AMOUNT
                FROM        OFFLINE_SALE
                WHERE       SALES_DATE BETWEEN '2022-03-01' AND '2022-03-31'
            ) AS SALE
ORDER BY    SALES_DATE, PRODUCT_ID, USER_ID;
```
</details>


<details>
<summary>저자 별 카테고리 별 매출액 집계하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/144856
```sql
-- BOOK : AUTHOR = N : 1
-- BOOK : BOOK_SALES = 1 : N
SELECT      AUTHOR.AUTHOR_ID, 
            AUTHOR.AUTHOR_NAME,
            SUB.CATEGORY,
            SUB.TOTAL_SALES
FROM        (
                SELECT      BOOK.AUTHOR_ID,
                            BOOK.CATEGORY,
                            (BOOK.PRICE * BOOK_SALES.SALES) AS TOTAL_SALES
                FROM        BOOK, BOOK_SALES
                WHERE       BOOK.BOOK_ID = BOOK_SALES.BOOK_ID
                AND         YEAR(SALES_DATE) = 2022
                AND         MONTH(SALES_DATE) = 1
            ) AS SUB,
            AUTHOR
WHERE       SUB.AUTHOR_ID = AUTHOR.AUTHOR_ID
GROUP BY    SUB.AUTHOR_ID, SUB.CATEGORY
ORDER BY    AUTHOR.AUTHOR_ID, SUB.CATEGORY DESC;
```
</details>


<details>
<summary>식품분류별 가장 비싼 식품의 정보 조회하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131116
```sql
SELECT      FOOD_PRODUCT.CATEGORY,
            SUB.MAX_PRICE,
            FOOD_PRODUCT.PRODUCT_NAME
FROM        FOOD_PRODUCT,
            (
                SELECT      CATEGORY, MAX(PRICE) AS MAX_PRICE
                FROM        FOOD_PRODUCT
                WHERE       CATEGORY IN ('과자', '국', '김치', '식용유')
                GROUP BY    CATEGORY
            ) AS SUB
WHERE       FOOD_PRODUCT.CATEGORY = SUB.CATEGORY
AND         FOOD_PRODUCT.PRICE = SUB.MAX_PRICE
ORDER BY    SUB.MAX_PRICE DESC;
```
</details>


<details>
<summary>년, 월, 성별 별 상품 구매 회원 수 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131532
```sql
SELECT      YEAR,
            MONTH,
            GENDER,
            COUNT(USER_ID)
FROM        (
                SELECT      DISTINCT
                YEAR(SALES_DATE) AS YEAR,
                MONTH(SALES_DATE) AS MONTH,
                USER_INFO.GENDER AS GENDER,
                ONLINE_SALE.USER_ID
                FROM        USER_INFO, ONLINE_SALE
                WHERE       USER_INFO.USER_ID = ONLINE_SALE.USER_ID
                AND         USER_INFO.GENDER IS NOT NULL
            ) AS SUB
GROUP BY    YEAR, MONTH, GENDER
ORDER BY    YEAR, MONTH, GENDER;
```
</details>


<details>
<summary>그룹별 조건에 맞는 식당 목록 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131532
```sql
-- MEMBER_PROFILE : REST_REVIEW = 1 : N
SELECT      MEMBER_PROFILE.MEMBER_NAME,
            REST_REVIEW.REVIEW_TEXT,
            DATE_FORMAT(REST_REVIEW.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM        MEMBER_PROFILE, REST_REVIEW
WHERE       MEMBER_PROFILE.MEMBER_ID = REST_REVIEW.MEMBER_ID
AND         REST_REVIEW.MEMBER_ID IN (
                                        SELECT      MEMBER_ID
                                        FROM        REST_REVIEW
                                        GROUP BY    MEMBER_ID
                                        HAVING      COUNT(*) = (
                                                                    SELECT      MAX(REVIEW_COUNT)
                                                                    FROM        (
                                                                                    SELECT      COUNT(*) AS REVIEW_COUNT
                                                                                    FROM        REST_REVIEW
                                                                                    GROUP BY    MEMBER_ID
                                                                                ) AS SUB
                                        )
                                    )
ORDER BY    REST_REVIEW.REVIEW_DATE, REST_REVIEW.REVIEW_TEXT;
```
</details>


<details>
<summary>5월 식품들의 총매출 조회하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131117
```sql
-- FOOD_PRODUCT : FOOD_ORDER = 1 : N
SELECT      FOOD_PRODUCT.PRODUCT_ID, 
            FOOD_PRODUCT.PRODUCT_NAME,
            (FOOD_PRODUCT.PRICE * SUB.TOTAL_AMOUNT) AS TOTAL_SALES
FROM        FOOD_PRODUCT,
            (
                SELECT      PRODUCT_ID, SUM(AMOUNT) AS TOTAL_AMOUNT
                FROM        FOOD_ORDER
                WHERE       YEAR(PRODUCE_DATE) = 2022
                AND         MONTH(PRODUCE_DATE) = 5
                GROUP BY    PRODUCT_ID
            ) AS SUB
WHERE       FOOD_PRODUCT.PRODUCT_ID = SUB.PRODUCT_ID
ORDER BY    TOTAL_SALES DESC, FOOD_PRODUCT.PRODUCT_ID;
```
</details>


<details>
<summary>주문량이 많은 아이스크림들 조회하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/133027
```sql
SELECT      FLAVOR
FROM        (
                SELECT      FLAVOR, TOTAL_ORDER
                FROM        FIRST_HALF
                UNION ALL
                SELECT      FLAVOR, TOTAL_ORDER
                FROM        JULY
            ) AS SUB
GROUP BY    FLAVOR
ORDER BY    SUM(TOTAL_ORDER) DESC
LIMIT       3;
```
</details>