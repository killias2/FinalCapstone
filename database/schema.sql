BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;

SELECT * FROM tournaments t
JOIN users ON user_id = organizerid  
LEFT JOIN games g ON t.gameid = g.gameid
WHERE CURRENT_DATE > end_date;
UPDATE tournaments SET is_open = true WHERE tournamentid = 8;
UPDATE tournaments SET is_open = true WHERE tournamentid = 19;

INSERT INTO games (game_name, game_description)
VALUES ('', '');

DELETE FROM games where gameid between 32 and 35;

INSERT INTO games (game_name, game_description)
VALUES ('Volleyball', 'A game for two teams, usually of six players, in which a large ball is hit by hand over a high net, the aim being to score points by making the ball reach the ground on the opponents side of the court.');
INSERT INTO games (game_name, game_description)
VALUES ('Tennis', 'A game in which two or four players strike a ball with rackets over a net stretched across a court. The usual form (originally called lawn tennis ) is played with a felt-covered hollow rubber ball on a grass, clay, or artificial surface.');
INSERT INTO games (game_name, game_description)
VALUES ('Football', 'A form of team game played in North America with an oval ball on a field marked out as a gridiron.');
INSERT INTO games (game_name, game_description)
VALUES ('Super Smash Bros', 'A series of crossover fighting video games published by Nintendo, and primarily features characters from various Nintendo franchises.');
INSERT INTO games (game_name, game_description)
VALUES ('Kickball', 'An informal game combining elements of baseball and soccer, in which an inflated ball is thrown to a person who kicks it and proceeds to run the bases.');
INSERT INTO games (game_name, game_description)
VALUES ('Rugby', 'A team game played with an oval ball that may be kicked, carried, and passed from hand to hand. Points are scored by grounding the ball behind the opponents goal line (thereby scoring a try) or by kicking it between the two posts and over the crossbar of the opponents goal.');
INSERT INTO games (game_name, game_description)
VALUES ('Basketball', 'a game played between two teams of five players in which goals are scored by throwing a ball through a netted hoop fixed above each end of the court.');
INSERT INTO games (game_name, game_description)
VALUES ('Darts', 'An indoor game in which small pointed missiles with feather or plastic flights are thrown at a circular target marked with numbers in order to score points.');

ALTER TABLE tournaments ADD COLUMN is_full boolean;

UPDATE tournaments SET is_full = false;