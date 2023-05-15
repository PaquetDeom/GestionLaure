package fr.paquet.dataBase;

import java.io.IOException;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class CreateDB {

	private static Server server = null;
	private static HsqlProperties properties = null;
	private static CreateDB createDB = null;

	private CreateDB() throws IOException, AclFormatException {
		super();

		setProperties(new HsqlProperties());
		setServer(new Server());

		server.start();

	}

	public CreateDB getUniqinstance() throws IOException, AclFormatException {

		if (CreateDB.createDB == null)
			CreateDB.createDB = new CreateDB();
		return CreateDB.createDB;
	}

	private static void setServer(Server server) throws IOException, AclFormatException {
		server.setProperties(getProperties());
		server.start();
		CreateDB.server = server;
	}

	public static Server getServer() {
		return server;
	}

	private static HsqlProperties getProperties() {
		return properties;
	}

	public static void setProperties(HsqlProperties properties) {
		properties.setProperty("server.database.0", "file:./target/classes/hsql/r408;user=r408;password=Login5340");
		properties.setProperty("server.dbname.0", "R408");
		properties.setProperty("server.port", "5434");
		CreateDB.properties = properties;
	}

}
