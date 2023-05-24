package fr.paquet.traitement.agenda;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evenement {

	/**
	 * <br>
	 * Déclaration des attributs de la class, elles sont privates car elles ne
	 * doivent pas être atteinte directement par une autre class du programme</br>
	 */

	private String caption = null;
	private EventType type = null;
	private String description = null;
	private LocalDate debutdate = null;
	private LocalDate findate = null;
	private LocalTime debutTime = null;
	private LocalTime finTime = null;

	/**
	 * Constructeur d'événement : Un événement à :
	 * 
	 * @param Caption     : Titre
	 * @param description
	 * @param debutD      : date de début
	 * @param finD        : date de fin
	 * @param debutT      : heure de début
	 * @param finT        : heure de fin
	 * @param type
	 * @throws Exception : titre manquant ; type manquant ; date de début manquant
	 */
	public Evenement(String Caption, String description, LocalDate debutD, LocalDate finD, LocalTime debutT,
			LocalTime finT, EventType type) throws Exception {

		super();
		setCaption(Caption);
		setDescription(description);
		setDebutdate(debutD);
		setDebutTime(debutT);
		setFindate(finD);
		setFinTime(finT);
		setType(type);
	}

	public String getCaption() {
		return caption;
	}

	/**
	 * 
	 * @param caption
	 * @throws Exception : un événement doit avoir une titre
	 */

	private void setCaption(String caption) throws Exception {
		if (caption == null)
			throw new Exception("Un événement doit comporter un titre");
		this.caption = caption;
	}

	public EventType getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 * @throws Exception : Un événement doit comporter un type
	 */
	private void setType(EventType type) throws Exception {
		if (type == null)
			throw new Exception("Un événement doit comporter un type");
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDebutdate() {
		return debutdate;
	}

	/**
	 * 
	 * @param debutdate
	 * @throws Exception :Un événement doit avoir une date de début
	 */
	private void setDebutdate(LocalDate debutdate) throws Exception {
		if (debutdate == null)
			throw new Exception("Un événement doit avoir une date de début");
		this.debutdate = debutdate;
	}

	public LocalDate getFindate() {
		return findate;
	}

	/**
	 * si la date de fin est null alors elle prend la valeur date de début
	 * @param findate
	 */
	private void setFindate(LocalDate findate) {
		if (findate == null)
			this.findate = this.debutdate;
		else
			this.findate = findate;
	}

	public LocalTime getDebutTime() {
		return debutTime;
	}

	private void setDebutTime(LocalTime debutTime) {
		this.debutTime = debutTime;
	}

	public LocalTime getFinTime() {
		return finTime;
	}

	/**
	 * si l'heure de fin est null alors elle prend la valeur heure de début +1h
	 * @param finTime
	 */
	private void setFinTime(LocalTime finTime) {
		if (finTime == null)
			this.finTime = this.debutTime.plusHours(1);
		this.finTime = finTime;
	}
}
