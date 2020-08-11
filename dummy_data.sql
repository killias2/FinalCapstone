DELETE FROM team_match;
DELETE FROM matches;
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'alpha', 1);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'bravo', 2);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'charlie', 3);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'delta', 4);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'echo', 5);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'foxtrot', 6);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'golf', 7);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'hotel', 8);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'india', 9);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'juliet', 10);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'kilo', 11);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'lima', 12);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'mike', 13);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'november', 14);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'oscar', 15);
INSERT INTO teams (tournamentid, teamname, seed) VALUES (1, 'papa', 16);

INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 0, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 1, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 1, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 1, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 1, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 2, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 2, 1);
INSERT INTO matches (is_complete, round, tournamentid) VALUES (false, 3, 1);

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
