with alwaysSuccessfulQuestThemes AS
	(
		SELECT DISTINCT theme
		FROM Quest
		EXCEPT
		SELECT DISTINCT theme
		FROM Quest
		WHERE succeeded IS NULL
		ORDER BY theme
	)

SELECT alwaysSuccessfulQuestThemes.theme, 
	(SELECT COUNT(*)
	FROM Quest
	WHERE alwaysSuccessfulQuestThemes.theme = Quest.theme) AS quests
FROM alwaysSuccessfulQuestThemes
ORDER BY theme


;