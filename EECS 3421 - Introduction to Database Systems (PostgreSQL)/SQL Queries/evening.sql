SELECT theme, day, realm, succeeded
FROM Quest
WHERE succeeded > '20:00:00' OR succeeded IS NULL
ORDER BY theme, day, realm
;