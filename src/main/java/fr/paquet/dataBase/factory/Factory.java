package fr.paquet.dataBase.factory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;

public abstract class Factory {

	protected abstract Class<?> getClassObject();

	public List<? extends Object> findAll() throws Exception {

		String queryString = null;

		queryString = "SELECT " + getClass().toString() + " FROM " + getClass().toString() + " "
				+ getClass().toString();

		Query query = Connect.getEm().createQuery(queryString);

		@SuppressWarnings("unchecked")
		List<Object> objects = (List<Object>) query.getResultList();
		if (objects.isEmpty())
			throw new Exception("Il n'y a pas d'objet de type" + getClassObject() + "dans la base de donnee");
		return objects;
	}

	public void persist(Object object) throws Exception {

		EntityManager em = Connect.getEm();
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();
			em.persist(object);
			em.flush();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}

	}

	public void remove(Object object) throws Exception {

		EntityManager em = Connect.getEm();
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();
			em.remove(object);
			em.flush();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}

	}

}
