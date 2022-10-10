package com.christianscarselli.service;

import java.util.List;

import com.christianscarselli.entities.Iscritto;

public interface IscrittoService {
	  
	  int insert(Iscritto i);
	  void delete(Iscritto i);
	  void update (Iscritto i);
	  List<Iscritto> getAll();
	  Iscritto getByEmail(Iscritto i);
	  
}
