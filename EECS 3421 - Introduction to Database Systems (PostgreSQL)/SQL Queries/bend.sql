	
with genderBenders AS 
	(
		SELECT DISTINCT Player.login
		FROM Player, Avatar
		WHERE Player.gender <> Avatar.gender
		AND Player.login = Avatar.login
		ORDER BY Player.login ASC
	)
	
	SELECT genderBenders.login, Player.name, Player.gender,
		(SELECT COUNT(*)
		FROM Avatar
		WHERE Avatar.login = genderBenders.login) AS avatars
	FROM genderBenders, Player
	WHERE genderBenders.login = Player.login	
	ORDER BY login
;


-- carter, coder, dazzle, hills, indigo, macho, skills, storm, thesix, thunder, whisper gender swapped in Avatar table
--json, and tzdale are a special case where, although they did not change gender their gender in Avatar, 
--they did change their gender from Player to Avatar.