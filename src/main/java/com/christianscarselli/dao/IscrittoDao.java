package com.christianscarselli.dao;

import java.util.List;

import com.christianscarselli.entities.Iscritto;

public interface IscrittoDao {
	
	int insert(Iscritto i);
	
	void delete(Iscritto i);
	
	void update(Iscritto i);
	
	Iscritto searchByEmail (String email);
	
	List <Iscritto>getAll();

}
