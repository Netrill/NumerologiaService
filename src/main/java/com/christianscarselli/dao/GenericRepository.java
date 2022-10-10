package com.christianscarselli.dao;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public interface GenericRepository <I extends Serializable,E extends Serializable>{
	
	@NotNull
	List <I> getAll ();
	
	int insert (@NotNull I entity);
	
	void update (@NotNull I entity);
	
	void delete (@NotNull I entity);
	
	void deleteById (@NotNull E id);
	
	I getById (@NotNull E id);

}
