package fr.paquet.ihm.gestionnaire.projet;

import java.util.List;

import fr.paquet.dataBase.factory.ProjetFactory;
import fr.paquet.traitement.projet.Projet;

public class PrepareListProjet {

	private List<Projet> projets = null;
	
	public PrepareListProjet(List<Projet> projets) {
		super();

	}

	@SuppressWarnings({ "unchecked" })
	public List<Projet> getProjets() throws Exception {
		if (projets == null)
			projets = (List<Projet>) new ProjetFactory().findAll();
		return projets;
	}

}
