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