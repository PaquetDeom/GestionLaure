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
import fr.paquet.io.jrxml.GeneratePDF;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionPdf extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ActionPdf() {
		super();

		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		Enable();
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			new GeneratePDF(getSequenceVersion());
		} catch (Exception e) {
			new AlertWindow(AlertType.ERREUR, "Rapport non généré");
			e.printStackTrace();
		}

	}

	@Override
	public String getParentMenuName() {

		return "Fichier";
	}

	@Override
	public String getName() {

		return "Générer un pdf";
	}

	@Override
	protected void Enable() {
		if (getSequenceVersion() == null)
			setEnabled(false);
		else
			setEnabled(true);

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/new.png"));
		return mItem;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

}
