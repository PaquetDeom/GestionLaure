package fr.paquet.traitement.personnel;

public enum Grade {

	PLPCN, PLPHC, PLPCE, AC2C1;

	public String getLibelleCourt() {
		switch (this) {
		case PLPCN:
			return "PLP CN";
		case PLPHC:
			return "PLP HC";
		case PLPCE:
			return "PLP CE";
		case AC2C1:
			return "AC2C1";
		}
		return null;
	}

	public String getLibelleLong() {
		switch (this) {
		case PLPCN:
			return "Classe normale";
		case PLPHC:
			return "Hors classe";
		case PLPCE:
			return "Classe exeptionnelle";
		case AC2C1:
			return "Contractuel";
		}
		return null;
	}
}
