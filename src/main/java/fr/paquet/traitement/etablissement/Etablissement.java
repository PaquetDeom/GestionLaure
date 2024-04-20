package fr.paquet.traitement.etablissement;

import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.*;

import fr.paquet.traitement.commun.Coordonnees;
import fr.paquet.traitement.personnel.Perdir;
import fr.paquet.traitement.personnel.Professeur;

@Entity
@Table(name = "ETABLISSEMENT")
public class Etablissement {

	@Id
	@Column(name = "Rne", length = 8)
	private String rne = null;

	@Enumerated(EnumType.STRING)
	private EtabCategorie categorie = null;

	@Enumerated(EnumType.STRING)
	private EtabSecteur secteur = null;

	@Column(name = "Nom", length = 255)
	private String nom = null;

	@Column(name = "Bassin", length = 255)
	private String bassin = null;

	@OneToOne
	@JoinColumn(name = "coordonnees_id")
	private Coordonnees coordonnees = null;

	@OneToOne
	@JoinColumn(name = "id")
	private Perdir perdir = null;

	@ManyToMany
	private List<Professeur> professeurs = null;

	// Constructeur vide
	public Etablissement() {
		super();
	}

	// Constructeur avec tous les attributs
	public Etablissement(String rne, EtabCategorie categorie, EtabSecteur secteur, String nom, String bassin,
			Coordonnees coordonnees) throws Exception {
		this();
		setRne(rne);
		setCategorie(categorie);
		setSecteur(secteur);
		setNom(nom);
		setBassin(bassin);
		setCoordonnees(coordonnees);
	}

	// Getters et setters

	private void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public Coordonnees getCoordonnes() {
		return coordonnees;
	}

	public String getRne() {
		return rne;
	}

	private void setRne(String rne) throws Exception {
		if (!Pattern.matches("[0-9]{7}[A-Z]", rne)) {
			throw new IllegalArgumentException("Le RNE doit comporter 7 chiffres et une lettre majuscule.");
		}
		this.rne = rne;
	}

	public EtabCategorie getCategorie() {
		return categorie;
	}

	private void setCategorie(EtabCategorie categorie) {
		this.categorie = categorie;
	}

	public EtabSecteur getSecteur() {
		return secteur;
	}

	private void setSecteur(EtabSecteur secteur) {
		this.secteur = secteur;
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	public String getBassin() {
		return bassin;
	}

	private void setBassin(String bassin) {
		this.bassin = bassin;
	}

}
