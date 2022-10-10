package com.christianscarselli.dao;

import java.util.List;

import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.christianscarselli.entities.Iscritto;
@Repository
public class IscrittoDaoJPQLImpl extends AbstractDaoJPQL<Iscritto,String>implements IscrittoDao{

	@Override
	public int insert(Iscritto i) {
		try {
			return entityManager.createNativeQuery("INSERT INTO Iscritto (email, nome, cognome,dataNascita) VALUES (?,?,?,?)")
		      .setParameter(1, i.getEmail())
		      .setParameter(2, i.getNome())
		      .setParameter(3, i.getCognome())
		      .setParameter(4, i.getDataNascita())
		      .executeUpdate();
		}catch (Exception e) {return 0;}
		
	}

	@Override
	public void delete(Iscritto i) {
		try {
			Query query = this.entityManager.createQuery("DELETE FROM Iscritto i WHERE i.email = :email ");
		    query.setParameter("email",i.getEmail());
			query.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	 

	@Override
	public Iscritto searchByEmail(String email) {
		Iscritto iscritto = null;
		try {
			iscritto = (Iscritto) this.entityManager.createQuery("SELECT i FROM Iscritto i WHERE i.email = :email").
					setParameter("email",email).getSingleResult();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return iscritto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Iscritto> getAll() {
		List <Iscritto> lista = null;
		try {
			lista = (List <Iscritto>) this.entityManager.createQuery("FROM Iscritto").getResultList();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public void deleteById(@NotNull String id) {
		
		
	}

	@Override
	public Iscritto getById(@NotNull String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Iscritto i) {
		try {
			Query query = this.entityManager.createQuery("UPDATE Iscritto i SET i.nome = :nome , "
					  + "i.cognome = :cognome , "
					  + "i.dataNascita = :dataNascita "
		              + "WHERE i.email = :email");
			query.setParameter("nome", i.getNome());
			query.setParameter("cognome", i.getCognome());
			query.setParameter("dataNascita", i.getDataNascita());
			query.setParameter("email",i.getEmail());
			query.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
