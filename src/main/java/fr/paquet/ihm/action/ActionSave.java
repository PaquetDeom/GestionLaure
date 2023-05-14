package fr.paquet.ihm.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionSave extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ActionSave() {
		super();

		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
		Enable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			if (isEnabled())
				new Save();

		} catch (Exception e) {
			e.printStackTrace();
			new AlertWindow(AlertType.ERREUR, e.getMessage());
		}

	}

	@Override
	public String getParentMenuName() {

		return "Fichier";
	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	@Override
	public String getName() {
		return "Sauver";
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

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/save.png"));
		return mItem;
	}

}
