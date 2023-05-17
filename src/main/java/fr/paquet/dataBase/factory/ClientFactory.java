package fr.paquet.dataBase.factory;

import fr.paquet.traitement.client.Client;

public class ClientFactory extends Factory {

	private Client client = null;

	public ClientFactory(Client client) {
		super();

		this.setClient(client);
	}

	private Client getClient() {
		return client;
	}

	private void setClient(Client client) {
		this.client = client;
	}

	@Override
	protected Class<?> getClassObject() {

		return getClient().getClass();
	}

}
