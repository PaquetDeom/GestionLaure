package fr.paquet.ihm.commun;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;

import javax.swing.*;

public abstract class CommunJPanelButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button = null;

	protected CommunJPanelButton(String titleButton) {
		super();

		// setteur des objet
		setButton(new JButton(titleButton));

		// ajout du layout
		setLayout(new GridBagLayout());

		// gestion des contrainte d'affichage
		add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getButton(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// attribut du panel
	}

	private JButton getButton() {
		return button;
	}

	protected abstract void setButton(JButton button);

}
