package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;

import fr.paquet.ihm.gestionnaire.activite.JDialogActivte;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionActivite extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionActivite() {
		super();
		putValue(NAME, getName());
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
		Enable();

	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		new JDialogActivte(getSequenceVersion());

	}

	@Override
	public String getParentMenuName() {

		return "Outils";
	}

	@Override
	public String getName() {

		return "Gestion ativites";
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
