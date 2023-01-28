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