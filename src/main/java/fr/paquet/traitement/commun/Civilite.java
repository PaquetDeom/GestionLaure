package fr.paquet.traitement.commun;

public enum Civilite {
	M, MM;

	public String getCiviliteCourt() {
		switch (this) {
		case M:
			return "M";
		case MM:
			return "MM";
		}
		return null;
	}

	public String getCiviliteLong() {
		switch (this) {
		case M:
			return "Monsieur";
		case MM:
			return "Madame";
		}
		return null;
	}
}
