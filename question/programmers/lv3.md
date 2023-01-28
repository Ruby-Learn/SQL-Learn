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