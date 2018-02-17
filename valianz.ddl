CREATE TABLE `games` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_name` varchar(100) NOT NULL,
  `game_weight` float DEFAULT '1',
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `games_game_id_uindex` (`game_id`)
)

CREATE TABLE `teams` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(50) DEFAULT NULL,
  `team_score` float DEFAULT '0',
  `team_color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `teams_team_id_uindex` (`team_id`)
)

CREATE TABLE `game_scores` (
  `game_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `score` float DEFAULT '0',
  UNIQUE KEY `game_scores_pk` (`game_id`,`team_id`),
  KEY `game_scores_teams_fk` (`team_id`),
  CONSTRAINT `game_scores_games_fk` FOREIGN KEY (`game_id`) REFERENCES `games` (`game_id`),
  CONSTRAINT `game_scores_teams_fk` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`)
)
