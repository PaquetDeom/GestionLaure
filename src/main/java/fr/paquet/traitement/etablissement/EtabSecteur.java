package fr.paquet.traitement.etablissement;

public enum EtabSecteur {

	PUBLIC, PRIVE;

	public String getSeteurLong() {
		switch (this) {
		case PUBLIC:
			return "SECTEUR PUBLIC";
		case PRIVE:
			return "SECTEUR PRIVE";
		}
		return null;
	}

	public String getSeteurCourt() {
		switch (this) {
		case PUBLIC:
			return "PUBLIC";
		case PRIVE:
			return "PRIVE";
		}
		return null;
	}
}
