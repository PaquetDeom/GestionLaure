package fr.paquet.dataBase.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public abstract class Factory {

	public Factory() {
		super();
	}

	public abstract Class<?> getClassObject();

	private void test(Object object) throws Exception {
		if (object.getClass() != getClassObject())
			throw new Exception("L'objet n'est pas de type " + getClassObject());
	}

	public void persist(Object object) throws Exception {

		test(object);
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

	public abstract void removeObject(Object object);

	protected void remove(Object object) throws Exception {

		test(object);

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
