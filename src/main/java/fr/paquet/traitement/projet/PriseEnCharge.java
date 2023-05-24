package fr.paquet.traitement.projet;

import java.time.LocalDate;
import java.time.LocalTime;

import fr.paquet.traitement.agenda.Evenement;
import fr.paquet.traitement.agenda.EventType;

public class PriseEnCharge extends Evenement{

	public PriseEnCharge(String Caption, String description, LocalDate debutD, LocalDate finD, LocalTime debutT,
			LocalTime finT, EventType type) throws Exception {
		super(Caption, description, debutD, finD, debutT, finT, type);
		// TODO Auto-generated constructor stub
	}

}
