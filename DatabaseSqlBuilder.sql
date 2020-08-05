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
        CONSTRAINT fk_winner_team_id   FOREIGN KEY (winner_team_id) REFERENCES teams(teamid)
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

/*old code
ALTER TABLE tournaments ADD COLUMN start_time timestamp,
        ADD COLUMN end_time timestamp;
oldcode*/


/*start date is not null, as we need an idea of when it starts
end date can be null, as it may not be determined initially how long it will last*/
ALTER TABLE tournaments ADD COLUMN start_date date NOT NULL,
        ADD COLUMN end_date date;

ALTER TABLE tournaments ADD COLUMN is_seeded boolean;
        
ALTER TABLE matches ADD COLUMN start_time timestamp,
        ADD COLUMN end_time timestamp;


/*if necessary, do this
ALTER TABLE tournaments DROP COLUMN bracket_seed_id;
DROP TABLE bracket_seed_types;
ALTER TABLE tournaments DROP COLUMN start_time, 
        DROP COLUMN end_time;
if the above isn't necessary, skip*/

INSERT INTO games (game_name, game_description) VALUES
        ('Baseball', 'A bat-and-ball game where two teams take turns at batting and fielding, hoping to earn or prevent their opponent from earning "runs"'),
        ('Golf', 'A club-and-ball sport in which players use clubs in order to hit a ball closer to and then into a hole, while minimizing strokes'),
        ('Bridge', 'A card game where teams attempt to score points by predicting the number of tricks a team can take for a given deal'),
        ('Pool', 'A cue sport played on a table with 6 pockets. Players taking turns trying to hit balls into pockets, often with rules guiding which balls to hit and/or which pockets to aim for'),
        ('Ping Pong', 'A game in which individuals or teams hit a "ping pong ball" back and forth across a small table with small rackets. Players hope to prevent opponents from returning the ball, so that they can score points'),
        ('Soccer', 'A team sport where two sides attempt to take control of a ball and kick it into the opposing team''s goal. Also known as Association Football'),
        ('Starcraft', 'A Real-Time-Strategy game where multiple opponents or teams attempt to collect resources and build armies in order to destroy the opposing sides');
        
ALTER TABLE tournaments ADD COLUMN number_of_teams integer;

INSERT INTO bracket_types (bracket_name) VALUES ('Single Elimination');
        
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;