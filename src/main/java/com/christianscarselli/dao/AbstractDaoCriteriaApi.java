package com.christianscarselli.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDaoCriteriaApi <I extends Serializable,Id extends Serializable>
implements GenericRepository<I, Id> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final Class<I> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDaoCriteriaApi()
	{
		this.entityClass = (Class<I>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public List<I> getAll()
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<I> query = builder.createQuery(this.entityClass);

        return this.entityManager.createQuery(
        		query.select(query.from(this.entityClass))).getResultList();
	}
	
	@Override
	public I getById(Id id)
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<I> query = builder.createQuery(this.entityClass);
			
		return this.entityManager.createQuery(
					query.where(
							builder.equal(
									query.from(this.entityClass).
									get("id"), id))).
					getSingleResult();
			
	}
	
	@Override
	public int insert(I entity)
	{
		this.entityManager.persist(entity);
		flushAndClear();
		return 0;
	}
	
	@Override
	public void update(I entity)
	{
		this.entityManager.merge(entity); 
		flushAndClear();
	}
	
	@Override
	public void delete(I entity)
	{
		
		this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
		flushAndClear();
		
	}
	
	@Override
	public void deleteById(Id id)
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaDelete<I> query = builder.createCriteriaDelete(this.entityClass);
		
		this.entityManager.createQuery(
				query.where(
						builder.equal(
								query.from(this.entityClass)
								.get("id"), id)
        )).executeUpdate();
		
		flushAndClear();
	}
	
	private void flushAndClear() 
	{
	    entityManager.flush();
	    entityManager.clear();
	}

}
