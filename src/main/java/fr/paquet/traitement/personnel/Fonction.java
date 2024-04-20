package fr.paquet.traitement.personnel;

public enum Fonction {

	ENSEIGNEMENT_DEVANT_ELEVES, DIRECTEUR_ASS_DELEGUE_FORM_PRO_TECHNO, FORMATION_CONTINUE_DES_ADULTES;

	public String getLibelle() {
		switch (this) {
		case ENSEIGNEMENT_DEVANT_ELEVES:
			return "ENSEIGNEMENT DEVANT ELEVES";
		case DIRECTEUR_ASS_DELEGUE_FORM_PRO_TECHNO:
			return "DIRECTEUR/ASS DELEGUE FORM PRO TECHNO";
		case FORMATION_CONTINUE_DES_ADULTES:
			return "FORMATION CONTINUE DES ADULTES";
		}
		return null;
	}
}