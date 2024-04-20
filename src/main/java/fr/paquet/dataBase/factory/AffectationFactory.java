package fr.paquet.dataBase.factory;

import fr.paquet.traitement.discipline.Affectation;

public class AffectationFactory extends Factory {

	@Override
	protected Class<?> getClassObject() {

		return Affectation.class;
	}

}
