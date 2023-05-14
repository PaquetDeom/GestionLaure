package fr.paquet.ihm.action;

import fr.paquet.activite.ActiviteEleve;
import fr.paquet.activite.ActiviteProf;
import fr.paquet.activite.Activite_1;

import fr.paquet.dataBase.Factory.Activite.ActiviteEleveFactory;
import fr.paquet.dataBase.Factory.Activite.ActiviteProfFactory;
import fr.paquet.dataBase.Factory.Activite.Activite_1Factory;
import fr.paquet.dataBase.Factory.Activite.StrategieFactory;
import fr.paquet.dataBase.Factory.sequence.SequenceVersionFactory;
import main.MainFrame;

public class Save {

	public Save() throws Exception {
		super();

		if (MainFrame.getUniqInstance().getSequenceVersion()
				.isModifiable()) {
			SequenceVersionFactory pF = new SequenceVersionFactory();
			Activite_1Factory aF = new Activite_1Factory();
			StrategieFactory sF = new StrategieFactory();
			ActiviteEleveFactory aE = new ActiviteEleveFactory();
			ActiviteProfFactory aP = new ActiviteProfFactory();

			pF.persist(MainFrame.getUniqInstance().getSequenceVersion());

			if (!MainFrame.getUniqInstance().getSequenceVersion().getActivites().isEmpty())
				for (Activite_1 act : MainFrame.getUniqInstance().getSequenceVersion().getActivites()) {

					if (!act.getStrategie().getActiviteEleves().isEmpty())
						for (ActiviteEleve aEl : act.getStrategie().getActiviteEleves()) {
							aE.persist(aEl);
						}

					if (!act.getStrategie().getActiviteProfs().isEmpty())
						for (ActiviteProf aPr : act.getStrategie().getActiviteProfs()) {
							aP.persist(aPr);
						}

					sF.persist(act.getStrategie());
					aF.persist(act);
				}
		}
	}
}
