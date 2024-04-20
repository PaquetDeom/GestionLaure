package fr.paquet.traitement.personnel;

import javax.persistence.*;

import fr.paquet.traitement.commun.*;
import fr.paquet.traitement.etablissement.Etablissement;

@Entity
@Table(name = "PERDIR")
@AttributeOverrides({ @AttributeOverride(name = "nom", column = @Column(name = "nom", length = 255)),
		@AttributeOverride(name = "nomDeJeuneFille", column = @Column(name = "nomDeJeuneFille")),
		@AttributeOverride(name = "prenom", column = @Column(name = "prenom", length = 255)),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "dateDeNaissance")), })
public class Perdir extends Personne {

	@Id
	@Column(name = "perdir_id")
	private String id = null;

	@OneToOne
	private Etablissement etablissement = null;

	@OneToOne
	@JoinColumn(name = "coordonnees_id")
	private Coordonnees coordonnees = null;

	public Perdir() {
		super();
	}

	public Perdir(String nom, String prenom, Etablissement etablissement, Coordonnees coordonnees) {
		super(nom, prenom);

		setCoordonnees(coordonnees);
		setEtablisement(etablissement);
		setId(nom, prenom, "Perdir");
	}

	public Etablissement getEtablisement() {
		return etablissement;
	}

	private void setEtablisement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	@Override
	protected void setId(String nom, String prenom, String dateDeNaissance1) {
		this.id = nom + prenom + dateDeNaissance1;

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
