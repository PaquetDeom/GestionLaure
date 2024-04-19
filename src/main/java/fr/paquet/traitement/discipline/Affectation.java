package fr.paquet.traitement.discipline;

import java.util.regex.Pattern;

import javax.persistence.*;

@Entity
@Table(name = "DISCAFFECTATION")
public class Affectation implements Discipline {

	@Id
	@Column(name = "Code", length = 255)
	private String codeDiscipline = null;

	@Column(name = "Libelle", length = 255)
	private String LibelleDiscipline = null;

	public Affectation() {
		super();
	}

	public Affectation(String codeDiscipline, String LibelleDiscipline) throws Exception {
		this();
		setCodeDiscipline(codeDiscipline);
		setLibelleDiscipline(LibelleDiscipline);
	}

	@Override
	public void setCodeDiscipline(String codeDiscipline) throws Exception {
		if (!Pattern.matches("[A-Z][0-9]{7}", codeDiscipline)) {
			throw new IllegalArgumentException("Le Code disciline doit comporter une lettre majuscule et 4 choffres.");
		}
		this.codeDiscipline = codeDiscipline;

	}

	@Override
	public void setLibelleDiscipline(String libelleDiscipline) {
		this.LibelleDiscipline = libelleDiscipline;

	}

	@Override
	public String getCodeDiscipline() {

		return codeDiscipline;
	}

	@Override
	public String getLibelleDiscipline() {

		return LibelleDiscipline;
	}

}
