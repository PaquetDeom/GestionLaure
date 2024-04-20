package fr.paquet.traitement.commun;

import java.util.regex.Pattern;

import javax.persistence.*;

@Entity
@Table(name = "COORDONNEES")
public class Coordonnees {

	// Attributs privés
	@Id
	@GeneratedValue
	@Column(name = "coordonnees_id")
	public long coordonnees_id = 0;

	@Column(name = "cPostal", length = 5)
	private String cPostal = null;

	@Column(name = "Ligne_1", length = 255)
	private String ligne1 = null;

	@Column(name = "Ligne_2", length = 255)
	private String ligne2 = null;

	@Column(name = "Ligne_3", length = 255)
	private String ligne3 = null;

	@Column(name = "Commune", length = 255)
	private String commune = null;

	@Column(name = "Tel_1", length = 10)
	private String nTelephone1 = null;

	@Column(name = "Tel_2", length = 10)
	private String nTelephone2 = null;

	@Column(name = "mail", length = 255)
	private String courriel = null;

	// Constructeur vide
	public Coordonnees() {
		super();

	}

	// Constructeur avec tous les attributs
	public Coordonnees(String codePostal, String ligne1, String ligne2, String ligne3, String commune,
			String nTelephone1, String nTelephone2, String courriel) throws Exception {
		this(codePostal, ligne1, ligne2, ligne3, commune, nTelephone1, courriel);

		setNTelephone2(nTelephone2);
	}

	// Constructeur pour etablissement
	public Coordonnees(String codePostal, String ligne1, String ligne2, String ligne3, String commune,
			String nTelephone1, String courriel) throws Exception {
		this(nTelephone1, courriel);

		setCodePostal(codePostal);
		setLigne1(ligne1);
		setLigne2(ligne2);
		setLigne3(ligne3);
		setCommune(commune);
	}

	// Constructeur pour personnel
	public Coordonnees(String nTelephone1, String courriel) throws Exception {
		this();

		setNTelephone1(nTelephone1);
		setCourriel(courriel);
	}

	// Getters et setters
	public String getCodePostal() {
		return cPostal;
	}

	private void setCodePostal(String codePostal) throws Exception {
		if (!Pattern.matches("[0-9]{5}", codePostal)) {
			throw new IllegalArgumentException("Le code postal doit comporter 5 chiffres.");
		}
		this.cPostal = codePostal;
	}

	public String getLigne1() {
		return ligne1;
	}

	private void setLigne1(String ligne1) {
		this.ligne1 = ligne1;
	}

	public String getLigne2() {
		return ligne2;
	}

	private void setLigne2(String ligne2) {
		this.ligne2 = ligne2;
	}

	public String getLigne3() {
		return ligne3;
	}

	private void setLigne3(String ligne3) {
		this.ligne3 = ligne3;
	}

	public String getCommune() {
		return commune;
	}

	private void setCommune(String commune) {
		this.commune = commune;
	}

	public String getNTelephone1() {
		return nTelephone1;
	}

	private void setNTelephone1(String nTelephone1) throws Exception {
		if (!Pattern.matches("^0[1-9]{9}$", nTelephone1)) {
			throw new IllegalArgumentException("Le numéro de téléphone 1 doit être au format 0123456789.");
		}
		this.nTelephone1 = nTelephone1;
	}

	public String getNTelephone2() {
		return nTelephone2;
	}

	private void setNTelephone2(String nTelephone2) throws Exception {
		if (nTelephone2 != null && !Pattern.matches("^0[1-9]{9}$", nTelephone2)) {
			throw new IllegalArgumentException("Le numéro de téléphone 2 doit être au format 0123456789 (ou null).");
		}
		this.nTelephone2 = nTelephone2;
	}

	public String getCourriel() {
		return courriel;
	}

	private void setCourriel(String courriel) throws Exception {
		if (courriel != null && !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", courriel)) {
			throw new IllegalArgumentException("L'adresse email doit être au format exemple@domaine.com.");
		}
		this.courriel = courriel;
	}

}
