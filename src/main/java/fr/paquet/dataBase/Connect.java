package fr.paquet.dataBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {

	/**
	 * @author Nathanael
	 * 
	 *         Connection a la base de donnees<br/>
	 */

	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

	/**
	 * 
	 * @return la connection a la base de donnee "derby"<br/>
	 *         url de la db jdbc:hsqldb:hsql://localhost:5434/r408
	 */
	public static EntityManagerFactory getEmf() {
		if (emf != null && emf.isOpen())
			return emf;
		else
			emf = Persistence.createEntityManagerFactory("gestionLaure");
		return emf;

	}

	/**
	 * 
	 * @return entity manager unique<br/>
	 */
	public static EntityManager getEm() {
		if (em == null)
			em = getEmf().createEntityManager();
		return em;
	}

}