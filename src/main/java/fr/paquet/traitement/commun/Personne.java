package fr.paquet.traitement.commun;

import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {

	@Column(name = "Nom", length = 255)
	private String nom = null;

	@Column(name = "NomDeJeuneFille", length = 255)
	private String nomDeJeuneFille = null;

	@Column(name = "Prenom", length = 255)
	private String prenom = null;

	@Enumerated(EnumType.STRING)
	private Civilite civilte = null;

	@Temporal(TemporalType.DATE)
	private Date dateDeNaissance = null;

	protected Personne() {
		super();
	}

	protected Personne(String nom, String nomDeJeuneFille, String prenom, Civilite civilite, Date dateDeNaissance1) {
		this(nom, prenom);

		setNomDeJeuneFille(nomDeJeuneFille);
		setCivilte(civilite);
		setDateDeNaissance(dateDeNaissance1);
	}

	protected Personne(String nom, String prenom) {
		this();

		setNom(nom);
		setPrenom(prenom);
	}

	protected abstract void setId(String nom, String prenom, String nomDeJeuneFille);

	protected abstract void setCoordonnees(Coordonnees coordonnees);

	public abstract Coordonnees getCoordonnees();

	protected String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	protected String getNomDeJeuneFille() {
		return nomDeJeuneFille;
	}

	private void setNomDeJeuneFille(String nomDeJeuneFille) {
		this.nomDeJeuneFille = nomDeJeuneFille;
	}

	protected String getPrenom() {
		return prenom;
	}

	private void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	protected Civilite getCivilte() {
		return civilte;
	}

	private void setCivilte(Civilite civilte) {
		this.civilte = civilte;
	}

	protected Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	private void setDateDeNaissance(Date dateDeNaissance) {

		this.dateDeNaissance = dateDeNaissance;
	}

}
