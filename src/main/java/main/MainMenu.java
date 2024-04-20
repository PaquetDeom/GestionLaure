package main;

import java.util.Hashtable;

import javax.swing.*;

import fr.paquet.ihm.action.*;

public class MainMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Nathanaël
	 */

	private static MainMenu mainMenu = null;

	/**
	 * Constructeur de la class ajoute les Action a MainMenu<br/>
	 * 
	 * @throws Exception
	 */
	private MainMenu() {
		super();

		getJMenu(getActionQuitter().getParentMenuName()).add(getActionQuitter().getJMenuItem());
		getJMenu(getOuvertureCalandar().getParentMenuName()).add(getOuvertureCalandar().getJMenuItem());
		getJMenu(getActionIntegrationDB().getParentMenuName()).add(getActionIntegrationDB().getJMenuItem());

	}

	private ActionIntegrationDB actionIntegrationDB = null;
	
	private ActionBDA getActionIntegrationDB() {
		if (actionIntegrationDB == null)
			actionIntegrationDB = new ActionIntegrationDB();
		return actionIntegrationDB;
	}
	
	private ActionQuitter actionQuitter = null;

	private ActionBDA getActionQuitter() {
		if (actionQuitter == null)
			actionQuitter = new ActionQuitter();
		return actionQuitter;
	}
	
	private ActionOuvertureCalendar actionOuvertureCalendar = null;
	
	private ActionBDA getOuvertureCalandar() {
		if (actionOuvertureCalendar == null)
			actionOuvertureCalendar = new ActionOuvertureCalendar();
		return actionOuvertureCalendar;
	}

	public static MainMenu getUniqInstance() {
		if (mainMenu == null)
			mainMenu = new MainMenu();
		return mainMenu;
	}

	private Hashtable<String, JMenu> menus = new Hashtable<String, JMenu>();

	private JMenu getJMenu(String menuName) {
		JMenu menu = menus.get(menuName);
		if (menu == null) {
			menus.put(menuName, menu = new JMenu(menuName));
			super.add(menu);
		}
		return menu;
	}

}
