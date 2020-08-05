package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.BracketType;

@Service
public class BracketTypeSqlDAO implements BracketTypeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public BracketTypeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean createBracketType(BracketType bracketType) {
		String sql = "INSERT INTO bracket_types (bracket_name) VALUES (?)";
		jdbcTemplate.update(sql, bracketType.getBracketName());
		return true;
	}
	
	@Override
	public BracketType[] getBracketTypeArray() {
		String sql = "SELECT bracketid, bracket_name FROM bracket_types";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<BracketType> bracketTypeList = new ArrayList<>();
		while (results.next()){
			BracketType returnedBracketType = new BracketType();
			returnedBracketType.setBracketName(results.getString("bracket_name"));
			returnedBracketType.setBracketId(results.getLong("bracketid"));
			bracketTypeList.add(returnedBracketType);
		}
		BracketType[] bracketTypeArray = new BracketType[bracketTypeList.size()];
		bracketTypeArray = bracketTypeList.toArray(bracketTypeArray);
		return bracketTypeArray;
	}

}
