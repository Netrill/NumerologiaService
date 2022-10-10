package com.christianscarselli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christianscarselli.dao.IscrittoDao;
import com.christianscarselli.dao.IscrittoDaoJPQLImpl;
import com.christianscarselli.entities.Iscritto;

@Service ("iscrittoServiceImpJPQL")
@Transactional
public class IscrittoServiceImpJPQL implements IscrittoService{
	
	 @Autowired
	 IscrittoDao iscrittoDaoImplJDPQL;

	@Override
	public int insert(Iscritto i) {
		return iscrittoDaoImplJDPQL.insert(i);
		
	}

	@Override
	public void delete(Iscritto i) {
		iscrittoDaoImplJDPQL.delete(i);
		
	}

	@Override
	public void update(Iscritto i) {
		iscrittoDaoImplJDPQL.update(i);
		
	}

	@Override
	public List<Iscritto> getAll() {
		return iscrittoDaoImplJDPQL.getAll();
	}

	@Override
	public Iscritto getByEmail(Iscritto i) {
		 
		return iscrittoDaoImplJDPQL.searchByEmail(i.getEmail());
	}
	
}
