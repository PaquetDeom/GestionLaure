package fr.paquet.traitement.agenda;

import fr.paquet.traitement.projet.PriseEnCharge;

public class EventType {

	private PriseEnCharge priseEnCharge = null;
	private String type = null;

	public EventType(PriseEnCharge priseEnCharge) throws Exception {
		super();

		setPriseEnCharge(priseEnCharge);
	}

	public EventType(String type) throws Exception {
		super();

		setType(type);
	}

	private PriseEnCharge getPriseEnCharge() {
		return priseEnCharge;
	}

	private void setPriseEnCharge(PriseEnCharge priseEnCharge) throws Exception {
		if (getType() != null)
			throw new Exception("EventType est déja de type String");
		this.priseEnCharge = priseEnCharge;
	}

	private String getType() {
		return type;
	}

	private void setType(String type) throws Exception {
		if (getPriseEnCharge() != null)
			throw new Exception("EventType est déja de type PriseEnCharge");
		this.type = type;
	}

	/**
	 * return le type d'événement
	 */
	public void type() {
		if (getPriseEnCharge() == null)
			getType();
		else
			getPriseEnCharge();
	}
}
