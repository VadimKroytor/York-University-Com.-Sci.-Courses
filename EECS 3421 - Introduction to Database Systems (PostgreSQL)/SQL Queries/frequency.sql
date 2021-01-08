WITH frequencyAndVisits AS 
	(

		SELECT Visit.login, Visit.realm, 
			(SELECT COUNT(Visit.realm)
			WHERE COUNT(Visit.realm) > 1) AS visits, 
			(SELECT 
			 	CAST((CAST(MAX(Visit.day) 
					- 
					MIN(Visit.day)::DATE AS NUMERIC(5,2))
					/
						((SELECT COUNT(Visit.realm)
						WHERE COUNT(Visit.realm) > 1)
					-
					1))
					AS NUMERIC(5,2))) AS frequency
		FROM Visit
		GROUP BY Visit.login, Visit.realm
		ORDER BY Visit.login, Visit.realm
	)
	
	SELECT frequencyAndVisits.login, frequencyAndVisits.realm, frequencyAndVisits.visits, frequencyAndVisits.frequency
	FROM frequencyAndVisits
	WHERE visits > 1
	ORDER BY login, realm
;
/*
First, only players who have been on more than one quest in a realm.
For such a player & realm, we can determine the number of days between
the earliest quest in that realm in which they participated and the last.
Dividing that by the number of quests in that realm in which they participated
minus one is the measure that we are calling frequency; e.g., “Parke quested in
region ‘Buffalo’ on average once every thirteen days.”
*/