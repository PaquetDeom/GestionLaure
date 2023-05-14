package fr.paquet.ihm.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.paquet.ihm.commun.explorerInternet.SwingBrowserJavaFxWeb;
import fr.paquet.sequence.SequenceVersion;
import main.MainFrame;

public class ActionRef extends ActionBDA implements PropertyChangeListener {

	/**
	 * @author paquet
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ActionRef() {
		super();

		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
		Enable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		new SwingBrowserJavaFxWeb(getUrl());

		/**
		 * try { Runtime.getRuntime().exec(getUrl());
		 * 
		 * } catch (IOException e) { new AlertWindow(AlertType.ERREUR, "Firefox n'est
		 * pas installé sur l'ordinateur"); e.printStackTrace(System.out); }
		 */

	}

	@Override
	public String getParentMenuName() {

		return "Outils";
	}

	private String getUrl() {
		return getSequenceVersion().getReferentiel().getUrl();
	}

	private SequenceVersion getSequenceVersion() {

		return MainFrame.getUniqInstance().getSequenceVersion();

	}

	@Override
	public String getName() {

		return "Referentiel";
	}

	@Override
	protected void Enable() {
		if (getSequenceVersion() != null) {
			if (getSequenceVersion().getReferentiel().getUrl() != null)
				setEnabled(true);
			else
				setEnabled(false);
		} else
			setEnabled(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		
		return mItem;
	}

}
