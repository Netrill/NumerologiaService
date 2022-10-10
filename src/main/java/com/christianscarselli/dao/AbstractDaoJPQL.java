package com.christianscarselli.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractDaoJPQL <I extends Serializable,Id extends Serializable> 
implements GenericRepository<I, Id> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final Class<I> entityClass;
	
	@SuppressWarnings("unchecked")
	AbstractDaoJPQL () {
		this.entityClass = (Class<I>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
}	 