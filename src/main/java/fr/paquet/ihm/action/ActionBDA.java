package fr.paquet.ihm.action;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

public abstract class ActionBDA extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String getParentMenuName();

	public abstract String getName();

	protected abstract void Enable();

	public abstract JMenuItem getJMenuItem();

}
