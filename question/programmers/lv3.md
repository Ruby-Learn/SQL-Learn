## 프로그래머스 SQL Level 3 문제

<details>
<summary>대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/151139
```sql
SELECT      MONTH(START_DATE) AS 'MONTH',
            CAR_ID,
            COUNT(*) AS RECORDS
FROM        CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE       YEAR(START_DATE) = 2022
AND         MONTH(START_DATE) BETWEEN 8 AND 10
AND         CAR_ID IN (
                        SELECT      CAR_ID
                        FROM        CAR_RENTAL_COMPANY_RENTAL_HISTORY
                        WHERE       YEAR(START_DATE) = 2022
                        AND         MONTH(START_DATE) BETWEEN 8 AND 10
                        GROUP BY    CAR_ID
                        HAVING      COUNT(CAR_ID) >= 5
                        )
GROUP BY    CAR_ID, MONTH(START_DATE)
ORDER BY    MONTH, CAR_ID DESC;
```
</details>


<details>
<summary>카테고리 별 도서 판매량 집계하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/144855
```sql
SELECT      CATEGORY, SUM(BOOK_SALES.SALES) AS TOTAL_SALES
FROM        BOOK, BOOK_SALES
WHERE       BOOK.BOOK_ID = BOOK_SALES.BOOK_ID
AND         YEAR(BOOK_SALES.SALES_DATE) = 2022
AND         MONTH(BOOK_SALES.SALES_DATE) = 1
GROUP BY    BOOK.CATEGORY
ORDER BY    BOOK.CATEGORY;
```
</details>


<details>
<summary>즐겨찾기가 가장 많은 식당 정보 출력하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131123
```sql
SELECT      REST_INFO.FOOD_TYPE,
            REST_INFO.REST_ID,
            REST_INFO.REST_NAME,
            SUB.FAVORITES
FROM        REST_INFO,
            (
                SELECT      FOOD_TYPE, MAX(FAVORITES) AS FAVORITES
                FROM        REST_INFO
                GROUP BY    FOOD_TYPE
            ) AS SUB
WHERE       REST_INFO.FAVORITES = SUB.FAVORITES
AND         REST_INFO.FOOD_TYPE = SUB.FOOD_TYPE
ORDER BY    FOOD_TYPE DESC;
```
</details>