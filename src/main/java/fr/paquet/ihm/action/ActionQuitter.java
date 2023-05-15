package fr.paquet.ihm.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class ActionQuitter extends ActionBDA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActionQuitter() {
		super();
		putValue(NAME, getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public String getParentMenuName() {
		return "Fichier";
	}

	@Override
	public String getName() {

		return "Quitter";
	}

	@Override
	protected void Enable() {

	}

	@Override
	public JMenuItem getJMenuItem() {
		JMenuItem mItem = new JMenuItem(this);

		mItem.setIcon(new ImageIcon("./target/classes/images/icons/exit.png"));
		return mItem;
	}

}
