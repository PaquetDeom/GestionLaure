package fr.paquet.traitement.etablissement;


public enum EtabCategorie {

	LP, SEP, ZA, LYC, SGT, EREA, SES, ZR, CLG, SOC;

	public String getLibelleLong() {
		switch (this) {
		case LP:
			return "Lycée Professionnel";
		case SEP:
			return "Section d'Enseignement Professionnel";
		case ZA:
			return "Zone A";
		case LYC:
			return "Lycée";
		case SGT:
			return "Section Générale et Technologique";
		case EREA:
			return "Etablissement Régional d'Enseignement Adapté";
		case SES:
			return "Section d'Enseignement Spécialisé";
		case ZR:
			return "Zone R";
		case CLG:
			return "Collège";
		case SOC:
			return "Structure d'Observation et d'Orientation";
		}
		return null;
	}

	public String getLibelleCourt() {
		switch (this) {
		case LP:
			return "LP";
		case SEP:
			return "SEP";
		case ZA:
			return "ZA";
		case LYC:
			return "LYC";
		case SGT:
			return "SGT";
		case EREA:
			return "EREA";
		case SES:
			return "SES";
		case ZR:
			return "ZR";
		case CLG:
			return "CLG";
		case SOC:
			return "SOC";
		}
		return null;
	}
}
