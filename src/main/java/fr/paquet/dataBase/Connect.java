package fr.paquet.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class Connect {

	/**
	 * @author Nathanael
	 * 
	 *         Connection a la base de donnees<br/>
	 */

	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	private static PrepareConnection PConnexion = null;

	public static Connection getConnectionToSequenceDb() {
		
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// CONNECT TO DATABASE
		try {
			return DriverManager.getConnection(getUrl(), getPConnexion().getUser().getUtilisateur(),
					getPConnexion().getUser().getPass());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return la connection a la base de donnee "derby"<br/>
	 *         url de la db jdbc:postgresql://localhost:5432/sequence
	 * @throws Exception
	 */
	public static EntityManagerFactory getEmf() throws Exception {
		if (emf == null) {

			Map<String, String> properties = new HashMap<String, String>();

			properties.put(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML_DEFAULT, "META-INF/persistence.xml");
			properties.put(PersistenceUnitProperties.JDBC_USER, getPConnexion().getUser().getUtilisateur());
			properties.put(PersistenceUnitProperties.JDBC_PASSWORD, getPConnexion().getUser().getPass());
			// properties.put(PersistenceUnitProperties.JDBC_URL,
			// "jdbc:postgresql://localhost:5432/sequence");
			properties.put(PersistenceUnitProperties.JDBC_URL, getUrl());
			properties.put(PersistenceUnitProperties.JDBC_DRIVER, getDriver());

			emf = Persistence.createEntityManagerFactory("sequence", properties);

		}
		return emf;
	}

	private static String getUrl() {
		return "jdbc:postgresql://8c700b76-ff58-4bf2-9825-8bba06b94fbd.bm49h38l0bihsg5ne8d0.databases.appdomain.cloud:30362/sequence";
	}

	private static String getDriver() {
		return "org.postgresql.Driver";
	}

	/**
	 * 
	 * @return entity manager unique<br/>
	 * @throws Exception
	 */
	public static EntityManager getEm() throws Exception {
		if (em == null)
			em = getEmf().createEntityManager();
		return em;
	}

	public static PrepareConnection getPConnexion() {
		return PConnexion;
	}

	public static void setPConnexion(PrepareConnection pConnexion) {
		Connect.PConnexion = pConnexion;
	}

}