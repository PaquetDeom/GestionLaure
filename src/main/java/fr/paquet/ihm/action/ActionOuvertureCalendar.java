package fr.paquet.ihm.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenuItem;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;

public class ActionOuvertureCalendar extends ActionBDA {
	
	public ActionOuvertureCalendar() {
		super();
		putValue(NAME, getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    try {
				Desktop.getDesktop().browse(new URI("https://calendar.google.com/calendar/u/0/r"));
			} catch (IOException e1) {
				new AlertWindow(AlertType.ERREUR, "Erreur lors de la conexion au calendrier");
				e1.printStackTrace();
			} catch(URISyntaxException e2) {
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

}
