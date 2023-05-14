package fr.paquet.ihm.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.commun.type.JDialogType;
import fr.paquet.ihm.nouveau.sequence.JDialogNewSequence;
import fr.paquet.module.ModuleVersion;
import fr.paquet.progression.ProgressionVersion;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionNouveau extends ActionBDA implements AlertListener, PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ActionNouveau() {
		super();

		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			if (getObjectVersion() == null || !isModifiable())
				new JDialogType(this);
			else
				new AlertWindow(AlertType.QUESTION, "Voulez vous enregistrer", this);

		} catch (Exception e) {
			new AlertWindow(AlertType.ERREUR, "Erreur lors du chargement de la fenêtre");
			e.printStackTrace();
		}

	}

	@Override
	public String getParentMenuName() {
		return "Fichier";
	}

	@Override
	public String getName() {

		return "Nouveau";
	}

	@Override
	protected void Enable() {

	}

	private Object getObjectVersion() {

		if (ObjectType.SEQUENCE.getObjectVersion() != null)
			return (SequenceVersion) ObjectType.SEQUENCE.getObjectVersion();
		if (ObjectType.MODULE.getObjectVersion() != null)
			return (ModuleVersion) ObjectType.MODULE.getObjectVersion();
		if (ObjectType.PROGRESSION.getObjectVersion() != null)
			return (ProgressionVersion) ObjectType.PROGRESSION.getObjectVersion();
		return null;
	}

	private boolean isModifiable() {
		if (ObjectType.SEQUENCE.isModifiable())
			return true;
		if (ObjectType.MODULE.isModifiable())
			return true;
		if (ObjectType.PROGRESSION.isModifiable())
			return true;
		return false;
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
			new JDialogNewSequence();
		} catch (Exception e) {
			new AlertWindow(AlertType.ERREUR, "Erreur lors du chargement de la fenêtre");
			e.printStackTrace();
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/gen.png"));
		return mItem;
	}

}
