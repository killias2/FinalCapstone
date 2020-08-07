package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.techelevator.model.Game;

@Service
public class GameSqlDAO implements GameDAO {
	private JdbcTemplate jdbcTemplate;
	public GameSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Game> getGames(){
		String sql = "SELECT * FROM games";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Game> games = new ArrayList<>();
		while(results.next()) {
			Game game = mapRowToGame(results);
			games.add(game);
		}
		return games;
	}
	
	public Game mapRowToGame(SqlRowSet rs) {
		Game game = new Game();
		game.setGameId(rs.getLong("gameid"));
		game.setGameName(rs.getString("game_name"));
		game.setGameDescription(rs.getString("game_description"));
		return game;
	}
}
