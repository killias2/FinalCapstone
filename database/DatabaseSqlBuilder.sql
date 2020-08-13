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

CREATE TABLE games (
        gameid serial   CONSTRAINT      pk_gameid       PRIMARY KEY,
        game_name       varchar(50)     NOT NULL,
        game_description        varchar(300)
);

CREATE TABLE bracket_types (
        bracketid       serial  CONSTRAINT      pk_bracketid    PRIMARY KEY,
        bracket_name    varchar(50)     NOT NULL
);


CREATE TABLE tournaments (
        tournamentid serial CONSTRAINT pk_tournamentid  PRIMARY KEY,
        t_name varchar(100)     NOT NULL,
        is_open boolean         NOT NULL,
        gameid integer,
        bracketid integer,
        is_complete boolean     NOT NULL,
        winner_team_id  integer,
        organizerid     integer,
        CONSTRAINT fk_gameid    FOREIGN KEY (gameid)    REFERENCES      games(gameid),
        CONSTRAINT fk_bracketid FOREIGN KEY (bracketid) REFERENCES      bracket_types(bracketid),
        CONSTRAINT organizerid  FOREIGN KEY (organizerid)       REFERENCES      users(user_id)
);


CREATE TABLE teams (
        teamid  serial  CONSTRAINT      pk_teamid        PRIMARY KEY,
        tournamentid    integer,
        general_manager_id integer,
        teamname        varchar(100)    NOT NULL,
        CONSTRAINT fk_tournamentid      FOREIGN KEY (tournamentid)      REFERENCES      tournaments(tournamentid),
        CONSTRAINT fk_general_manager_id     FOREIGN KEY (general_manager_id)   REFERENCES      users(user_id)
);

ALTER TABLE tournaments ADD CONSTRAINT fk_winner_team_id    FOREIGN KEY (winner_team_id)    REFERENCES    teams(teamid);  

CREATE TABLE matches (
        matchid serial  CONSTRAINT      pk_matchid      PRIMARY KEY,
        is_complete     boolean,
        winner_team_id  integer,
        round smallint NOT NULL,
        tournamentid integer NOT NULL,
        CONSTRAINT fk_winner_team_id   FOREIGN KEY (winner_team_id) REFERENCES teams(teamid),
        CONSTRAINT fk_tournamentid      FOREIGN KEY (tournamentid)      REFERENCES tournaments(tournamentid)
);

CREATE TABLE team_match (
        matchid integer,
        teamid  integer,
        CONSTRAINT fk_matchid   FOREIGN KEY (matchid)   REFERENCES matches(matchid),
        CONSTRAINT fk_teamid    FOREIGN KEY (teamid)    REFERENCES teams(teamid)
);

CREATE TABLE team_tournament (
        teamid  integer,
        tournamentid integer,
        CONSTRAINT fk_teamid    FOREIGN KEY (teamid)    REFERENCES teams(teamid),
        CONSTRAINT fk_tournamentid      FOREIGN KEY (tournamentid)      REFERENCES      tournaments(tournamentid)
);

ALTER TABLE tournaments ADD COLUMN start_date date NOT NULL,
        ADD COLUMN end_date date;

ALTER TABLE tournaments ADD COLUMN is_seeded boolean;
        
ALTER TABLE matches ADD COLUMN start_time timestamp,
        ADD COLUMN end_time timestamp;

INSERT INTO games (game_name, game_description) VALUES
        ('Baseball', 'A bat-and-ball game where two teams take turns at batting and fielding, hoping to earn or prevent their opponent from earning "runs"'),
        ('Golf', 'A club-and-ball sport in which players use clubs in order to hit a ball closer to and then into a hole, while minimizing strokes'),
        ('Bridge', 'A card game where teams attempt to score points by predicting the number of tricks a team can take for a given deal'),
        ('Pool', 'A cue sport played on a table with 6 pockets. Players taking turns trying to hit balls into pockets, often with rules guiding which balls to hit and/or which pockets to aim for'),
        ('Ping Pong', 'A game in which individuals or teams hit a "ping pong ball" back and forth across a small table with small rackets. Players hope to prevent opponents from returning the ball, so that they can score points'),
        ('Soccer', 'A team sport where two sides attempt to take control of a ball and kick it into the opposing team''s goal. Also known as Association Football'),
        ('Starcraft', 'A Real-Time-Strategy game where multiple opponents or teams attempt to collect resources and build armies in order to destroy the opposing sides');

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

ALTER TABLE tournaments ADD COLUMN number_of_teams integer;

INSERT INTO bracket_types (bracket_name) VALUES ('Single Elimination');
INSERT INTO bracket_types (bracket_name) VALUES ('Double Elimination'), ('Round Robin');
        
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

ALTER TABLE teams ADD COLUMN seed integer;
ALTER TABLE users ADD COLUMN email_address varchar(40);
ALTER TABLE teams ADD COLUMN team_email_address varchar(40);

ALTER TABLE tournaments ADD COLUMN is_full boolean;