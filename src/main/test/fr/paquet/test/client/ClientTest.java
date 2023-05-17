package fr.paquet.test.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.paquet.traitement.client.Adresse;
import fr.paquet.traitement.client.Client;
import fr.paquet.traitement.projet.Projet;

public class ClientTest {

	private Client getClient() throws Exception {

		// creation adresse
		Adresse adresse = new Adresse("  Lieu     dit    le GRES   ", "  659   ", "  rue   ", "    Du pastEl  ", "AMBRES", "81500");
		Client client = new Client("             PAQUET-deom        ", "           NaThanAel         ");
		
		// creation des projets
		Projet p1 = new Projet("TITRE1");
		Projet p2 = new Projet("TITRE2");
		Projet p3 = new Projet("TITRE3");
		
		// add projets à la liste de projets de client
		client.addProjet(p1);
		client.addProjet(p2);
		client.addProjet(p3);

		return client;
	}

	@Test
	public void testGetProjets() {
		try {

			assertTrue(getClient().getProjets().size() == 3);

			for (int i = 0; i < getClient().getProjets().size(); i++) {
				Projet p = getClient().getProjets().get(i);
				switch (i) {
				case 0:
					assertTrue(p.getTitre().equals("Titre1"));
					break;
				case 1:
					assertTrue(p.getTitre().equals("Titre2"));
					break;
				case 2:
					assertTrue(p.getTitre().equals("Titre3"));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAdresse() {
		try {

			// assertTrue(getClient().getAdresse().getCommune().getCommune().equals("AMBRES"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
