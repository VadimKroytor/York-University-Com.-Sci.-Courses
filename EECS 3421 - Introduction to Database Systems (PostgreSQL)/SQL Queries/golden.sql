SELECT DISTINCT realm, day, theme
FROM Loot
WHERE treasure ILIKE '%gold%'
AND login IS NOT NULL
ORDER BY day, realm, theme;