## 프로그래머스 SQL Level 5 문제

<details>
<summary>상품을 구매한 회원 비율 구하기</summary>

- https://school.programmers.co.kr/learn/courses/30/lessons/131534
```sql
-- USER_INFO : ONLINE_SALE = 1 : N
-- 상품을 구매한 회원수, 구매한 회원 비율를 년, 월 로 그룹핑
SELECT      YEAR,
            MONTH,
            COUNT(SUB.USER_ID) AS PUCHASED_USERS,
            ROUND(COUNT(SUB.USER_ID) / TOTAL_COUNT.USER_COUNT, 1) AS PUCHASED_RATIO
FROM        (
                SELECT      DISTINCT
                YEAR(SALES_DATE) AS YEAR,
                MONTH(SALES_DATE) AS MONTH,
                USER_INFO.USER_ID
                FROM        USER_INFO, ONLINE_SALE
                WHERE       USER_INFO.USER_ID = ONLINE_SALE.USER_ID
                AND         YEAR(JOINED) = 2021
            ) AS SUB,
            (
                SELECT      COUNT(*) AS USER_COUNT
                FROM        USER_INFO
                WHERE       YEAR(JOINED) = 2021
            ) AS TOTAL_COUNT
GROUP BY    YEAR, MONTH
ORDER BY    YEAR, MONTH;
```
</details>