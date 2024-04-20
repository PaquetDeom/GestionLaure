package fr.paquet.traitement.personnel;

import java.util.*;

import javax.persistence.*;

import fr.paquet.traitement.commun.*;
import fr.paquet.traitement.discipline.*;
import fr.paquet.traitement.etablissement.Etablissement;

@Entity
@Table(name = "PROFESSEUR")
@AttributeOverrides({ @AttributeOverride(name = "nom", column = @Column(name = "nom", length = 255)),
		@AttributeOverride(name = "nomDeJeuneFille", column = @Column(name = "nomDeJeuneFille")),
		@AttributeOverride(name = "prenom", column = @Column(name = "prenom", length = 255)),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "dateDeNaissance")) })
public class Professeur extends Personne {

	@Id
	@Column(name = "professeur_id")
	private String id = null;

	@Temporal(TemporalType.DATE)
	private Date dateEntreeAc = null;

	@Enumerated(EnumType.STRING)
	private Grade grade = null;

	@Column(name = "Echelon", length = 255)
	private String echelon = null;

	@Enumerated(EnumType.STRING)
	private Fonction fonction = null;

	@ManyToMany
	private List<Affectation> affectations = null;

	@ManyToOne
	private Recrutement recrutement = null;

	@ManyToMany
	private List<Etablissement> etablissements = null;

	@OneToOne
	@JoinColumn(name = "coordonnees_id")
	private Coordonnees coordonnees = null;

	public Professeur() {
		super();
	}

	// création avec une seule affectation
	public Professeur(String nom, String nomDeJeuneFille, String prenom, Civilite civilite, Date dateDeNaissance1,
			Coordonnees coordonnees, Date dateEntreeAc, Grade grade, String echelon, Fonction fonction,
			Recrutement recrutement, Affectation affectation) {
		super(nom, nomDeJeuneFille, prenom, civilite, dateDeNaissance1);

		setDateEntreeAc(dateEntreeAc);
		setCoordonnees(coordonnees);
		setGrade(grade);
		setEchelon(echelon);
		setFonction(fonction);
		setRecrutement(recrutement);
		addAffectation(affectation);
		setId(nom, prenom, nomDeJeuneFille);
	}

	// création avec une liste d'affectations
	public Professeur(String nom, String nomDeJeuneFille, String prenom, Civilite civilite, Date dateDeNaissance1,
			Coordonnees coordonnees, Date dateEntreeAc, Grade grade, String echelon, Fonction fonction,
			Recrutement recrutement, List<Affectation> affectations) {
		super(nom, nomDeJeuneFille, prenom, civilite, dateDeNaissance1);

		setDateEntreeAc(dateEntreeAc);
		setCoordonnees(coordonnees);
		setGrade(grade);
		setEchelon(echelon);
		setFonction(fonction);
		setRecrutement(recrutement);
		setAffectations(affectations);
		setId(nom, prenom, nomDeJeuneFille);
	}

	@Override
	protected void setId(String nom, String prenom, String nomDeJeuneFille) {
		this.id = nom + prenom + nomDeJeuneFille;

	}

	public Date getDateEntreeAc() {
		return dateEntreeAc;
	}

	private void setDateEntreeAc(Date dateEntreeAc) {
		this.dateEntreeAc = dateEntreeAc;
	}

	public Grade getGrade() {
		return grade;
	}

	private void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getEchelon() {
		return echelon;
	}

	private void setEchelon(String echelon) {
		this.echelon = echelon;
	}

	public Fonction getFonction() {
		return fonction;
	}

	private void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public List<Affectation> getAffectations() {
		if (affectations == null)
			this.affectations = new ArrayList<Affectation>();
		return affectations;
	}

	public void addAffectation(Affectation affectation) {
		getAffectations().add(affectation);
	}

	private void setAffectations(List<Affectation> affectations) {
		this.affectations = affectations;
	}

	public Recrutement getRecrutement() {

		return recrutement;
	}

	public void setRecrutement(Recrutement recrutement) {
		this.recrutement = recrutement;
	}

	@Override
	protected void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;

	}

	@Override
	public Coordonnees getCoordonnees() {

		return coordonnees;
	}

}
