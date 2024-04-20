package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;

import fr.paquet.ihm.io.FileChooser;
import main.MainFrame;

public class ActionIntegrationDB extends ActionBDA implements PropertyChangeListener {

	public ActionIntegrationDB() {
		super();

		putValue(NAME, getName());
		MainFrame.getUniqInstance().addPropertyChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new FileChooser();

	}

	@Override
	public String getParentMenuName() {

		return "Base de données";
	}

	@Override
	public String getName() {

		return "Charger des données";
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
	public void propertyChange(PropertyChangeEvent evt) {
		Enable();

	}

}
