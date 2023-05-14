package fr.paquet.ihm.alert;

public enum AlertType {

	ERREUR, ATTENTION, INFORMATION, QUESTION;

	/**
	 * 
	 * @return le type de message</br>
	 */
	public String getStringType() {
		switch (this) {
		case ERREUR:
			return "Erreur";
		case ATTENTION:
			return "Attention";
		case INFORMATION:
			return "Information";
		case QUESTION:
			return "Question";
		}
		return null;
	}

}
