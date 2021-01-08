SELECT *
FROM Player
WHERE name ILIKE format('%%%s%%', login)
ORDER BY login ASC;
