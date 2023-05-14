package fr.paquet.dataBase;

import javax.persistence.*;

import fr.paquet.sequence.Auteur;

@Entity
@Table(name = "USERDB")
public class User {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private long Id = 0;

	@Column(name = "USUSUT", length = 20)
	private String utilisateur = null;

	@Column(name = "USUSPA", length = 20)
	private String pass = null;

	@ManyToOne
	private Auteur auteur = null;

	public User() {
		super();
	}

	public User(String utilisateur, String pass) throws Exception {
		super();
		setUtilisateur(utilisateur);
		setPass(pass);
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	private void setUtilisateur(String utilisateur) throws Exception {
		if (utilisateur == null || utilisateur.equals(""))
			throw new Exception("Veuillez saisir un nom d'utilisateur");
		this.utilisateur = utilisateur;
	}

	public String getPass() {

		return pass;
	}

	private void setPass(String pass) throws Exception {
		if (pass == null || pass.equals(""))
			throw new Exception("Veuillez saisir un mot de passe");
		this.pass = pass;
	}

	public Auteur getAuteur() {

		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
}
