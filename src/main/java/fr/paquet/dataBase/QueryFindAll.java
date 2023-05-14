package fr.paquet.dataBase;

import fr.paquet.referentiel.Referentiel;

public enum QueryFindAll {
	REFERENTIEL;

	public String getQuery() {
		switch (this) {
		case REFERENTIEL:

			return "SELECT referentiel FROM Referentiel referentiel";

		default:
			return null;
		}
	}

	public Class<? extends Object> getClassObject() {
		switch (this) {
		case REFERENTIEL:
			return Referentiel.class;

		default:
			return null;
		}
	}
}
