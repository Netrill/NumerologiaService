package com.christianscarselli.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DbAccessImpl implements DbAccess {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void select(int id) {
		String sql = "SELECT * FROM ARTICOLI";
		jdbcTemplate.execute(sql);
		
	}

	@Override
	public Object insert() {
		// TODO Auto-generated method stub
		return null;
	}

}
