package com.christianscarselli.repository;

import org.springframework.stereotype.Repository;

public interface DbAccess {
	
	public void select(int id);
	public Object insert();

}
