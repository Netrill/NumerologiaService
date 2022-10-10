package com.christianscarselli.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.christianscarselli.entities.Iscritto;

public class IscrittoDaoImplCriteriaApi extends AbstractDaoCriteriaApi<Iscritto,String>  implements IscrittoDao  {

	@Override
	public int insert(Iscritto i) {
		return super.insert(i);
		
	}

	@Override
	public void delete(Iscritto i) {
		super.delete(i);
		
	}

	@Override
	public void update(Iscritto i) {
		super.update(i);
		
	};

	@Override
	public Iscritto searchByEmail(String email) {
		return super.getById(email);
	}

	@Override
	public List<Iscritto> getAll() {
		return super.getAll();
	}

}
