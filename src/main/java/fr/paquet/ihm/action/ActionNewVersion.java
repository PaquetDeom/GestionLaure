package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import fr.paquet.ihm.nouveau.JDialogNewVersion;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;


public class ActionNewVersion extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionNewVersion() {
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

		new JDialogNewVersion(getSequenceVersion());

	}

	@Override
	public String getParentMenuName() {

		return "Outils";
	}

	@Override
	public String getName() {

		return "Créer une nouvelle version";
	}

	@Override
	protected void Enable() {
		if (getSequenceVersion() != null)
			setEnabled(true);
		else
			setEnabled(false);

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/new.png"));
		return mItem;
	}

}
