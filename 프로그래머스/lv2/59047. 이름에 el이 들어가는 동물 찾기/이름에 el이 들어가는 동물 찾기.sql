-- 코드를 입력하세요
SELECT a.ANIMAL_ID, a.NAME
FROM ANIMAL_INS as a
WHERE LOWER(NAME) LIKE '%el%' AND a.ANIMAL_TYPE = 'Dog'
ORDER BY NAME