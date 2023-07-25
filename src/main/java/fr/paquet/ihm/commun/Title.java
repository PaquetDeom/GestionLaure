package fr.paquet.ihm.commun;

import java.util.ArrayList;
import java.util.List;

public enum Title {

	COMPETENCES, ACTIVITES, STRATEGIE;

	public int getcolumn() {
		switch (this) {
		case COMPETENCES:
			return 1;
		case ACTIVITES:
			return 5;
		case STRATEGIE:
			return 1;
		default:
			return 0;
		}
	}

	public List<String> getTitle() {
		switch (this) {
		case COMPETENCES:
			List<String> values = new ArrayList<String>();
			values.add("Compétences / Indicateur de Performance");

			return values;
		case ACTIVITES:
			List<String> value = new ArrayList<String>();
			value.add("Questionnement");
			value.add("Démarche pédagogique");
			value.add("Activités élèves");
			value.add("Ressources - Matériels");
			value.add("Conclusion - Bilan");
			return value;
		case STRATEGIE:
			List<String> valu = new ArrayList<String>();
			valu.add("Stratégie pédagogique");

			return valu;
		default:
			return null;
		}
	}

}
