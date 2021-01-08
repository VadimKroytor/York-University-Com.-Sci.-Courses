with
	cheatingDateAndLogin AS (
		SELECT day, login
		FROM Actor
		GROUP BY day, login
		HAVING COUNT(day) >= 2
		AND
		COUNT(DISTINCT login) = 1 
		AND
		COUNT(realm) >= 2
		AND 
		COUNT(theme) >= 2

	)

SELECT cheatingDateAndLogin.login, Player.name, cheatingDateAndLogin.day, Actor.realm, Actor.theme
	FROM cheatingDateAndLogin, Actor, Player
	WHERE cheatingDateAndLogin.login = Actor.login
	AND cheatingDateAndLogin.day = Actor.day
	AND cheatingDateAndLogin.login = Player.login
	ORDER BY login, name, day, realm, theme
;