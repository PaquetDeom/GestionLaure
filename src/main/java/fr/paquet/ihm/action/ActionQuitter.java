package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import main.Main;
import main.MainFrame;
import main.MainOnglet;

public class ActionQuitter extends ActionBDA implements AlertListener, PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionQuitter() {
		super();
		putValue(NAME, getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (MainOnglet.getUniqInstance().getOngletSequence() != null) {

			try {
				if (MainFrame.getUniqInstance().getSequenceVersion() == null || !MainFrame.getUniqInstance()
						.getSequenceVersion().isModifiable())
					Main.Fermeture();
				else
					new AlertWindow(AlertType.QUESTION, "Voulez vous enregistrer la séquence", this);

			} catch (Exception e1) {
				new AlertWindow(AlertType.ERREUR, "Comparaison impossible");
				Main.FermetureAvecErreur();
				e1.printStackTrace();
			}

		} else
			Main.Fermeture();

	}

	@Override
	public String getParentMenuName() {
		return "Fichier";
	}

	@Override
	public String getName() {

		return "Quitter";
	}

	@Override
	protected void Enable() {

	}

	@Override
	public void buttonClick(String button) {
		if (button.equals("Oui")) {
			try {
				new Save();
				Main.FermetureSansErreur();
			} catch (Exception e) {
				new AlertWindow(AlertType.ERREUR, "Sauvegarde de la sequence impossible");
				e.printStackTrace();
				Main.FermetureAvecErreur();
			}
		}

		if (button.equals("Non"))
			Main.FermetureSansErreur();

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/exit.png"));
		return mItem;
	}

}
