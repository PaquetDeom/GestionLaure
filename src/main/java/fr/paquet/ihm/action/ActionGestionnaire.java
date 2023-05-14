package fr.paquet.ihm.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.ouvrir.JDialogOpenSequence;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionGestionnaire extends ActionBDA implements AlertListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ActionGestionnaire() {
		super();
		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		try {
			if (getSequenceVersion() == null
					|| !getSequenceVersion().isModifiable())
				new JDialogOpenSequence();
			else
				new AlertWindow(AlertType.QUESTION, "Voulez vous enregistrer la séquence", this);

		} catch (Exception e) {
			new AlertWindow(AlertType.ERREUR, "Erreur lors du chargement de la fenêtre");
			e.printStackTrace();
		}

	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	@Override
	public String getParentMenuName() {

		return "Fichier";
	}

	@Override
	public String getName() {

		return "Gestionnaire de sequences";
	}

	@Override
	protected void Enable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonClick(String button) {
		if (button.equals("Oui"))
			try {
				new Save();

			} catch (Exception e) {
				new AlertWindow(AlertType.ERREUR, "La séquence n'a pas été sauvegardée");
				e.printStackTrace();
			}

		try {
			new JDialogOpenSequence();
		} catch (Exception e) {
			new AlertWindow(AlertType.ERREUR, "Erreur lors du chargement de la fenêtre");
			e.printStackTrace();
		}

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/open.png"));
		return mItem;
	}

}
