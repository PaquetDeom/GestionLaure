package fr.paquet.test.client;

import org.junit.Test;

import fr.paquet.traitement.agenda.Evenement;
import fr.paquet.traitement.projet.PriseEnCharge;

public class EvenementTest {

	/**
	 * de type Prise en charge
	 * @return
	 * @throws Exception 
	 */
	private Evenement getEvenementPC() throws Exception {
		PriseEnCharge pc = new PriseEnCharge(null, null, null, null, null, null, null);
		return pc;
	}
	
	/**
	 * de type String
	 * @return
	 * @throws Exception 
	 */
	private Evenement getEvenementS() throws Exception {
		Evenement event = new Evenement(null, null, null, null, null, null, null);
		return event;
	}
	
	@Test
	public void testGetCaption() {
		
	}
}
