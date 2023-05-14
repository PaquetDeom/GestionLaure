package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;
import javax.swing.text.BadLocationException;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.gestionnaire.competence.JDialogCompetence;
import fr.paquet.ihm.principal.sequence.SequencePanel;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionCompetences extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionCompetences() {
		super();
		putValue(NAME, getName());
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
		Enable();
	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	private SequencePanel getSequencePanel() throws BadLocationException {
		return MainFrame.getUniqInstance().getMainOnglet().getOngletSequence().getSequencePanel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			new JDialogCompetence(getSequenceVersion(), getSequencePanel());
		} catch (BadLocationException e) {
			new AlertWindow(AlertType.ERREUR, e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public String getParentMenuName() {

		return "Outils";
	}

	@Override
	public String getName() {

		return "Gestion compétences";
	}

	@Override
	protected void Enable() {
		if (getSequenceVersion() != null) {
			if (getSequenceVersion().isModifiable())
				setEnabled(true);
			else
				setEnabled(false);
		} else
			setEnabled(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

		if (getSequenceVersion() != null)
			getSequenceVersion().addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					Enable();

				}
			});

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		return mItem;
	}

}
