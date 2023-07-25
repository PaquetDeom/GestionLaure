package fr.paquet.ihm.action;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.newUriCalendar.JDialogUriCalendarCreation;
import main.MainFrame;

public class ActionOuvertureCalendar extends ActionBDA implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionOuvertureCalendar() {
		super();
		putValue(NAME, getName());
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				if (MainFrame.getUniqInstance().getCalendrier() == null) {
					new JDialogUriCalendarCreation();
				} else {
					Desktop.getDesktop().browse(new URI(MainFrame.getUniqInstance().getCalendrier().getUri()));

				}

			} catch (IOException e1) {
				new AlertWindow(AlertType.ERREUR, "Erreur lors de la conexion au calendrier");
				e1.printStackTrace();
			} catch (URISyntaxException e2) {
				new AlertWindow(AlertType.ERREUR, "L'url n'est pas conforme");
				e2.printStackTrace();
			}
		}

	}

	@Override
	public String getParentMenuName() {

		return "Outils";
	}

	@Override
	public String getName() {

		return "Ouverture calendrier";
	}

	@Override
	protected void Enable() {

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);
		return mItem;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

}