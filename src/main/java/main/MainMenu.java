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

		getJMenu(getActionNouveau().getParentMenuName()).add(getActionNouveau().getJMenuItem());
		getJMenu(getActionGestionnaire().getParentMenuName()).add(getActionGestionnaire().getJMenuItem());
		getJMenu(getActionRef().getParentMenuName()).add(getActionRef().getJMenuItem());
		getJMenu(getActionSave().getParentMenuName()).add(getActionSave().getJMenuItem());
		getJMenu(getActionNewVersion().getParentMenuName()).add(getActionNewVersion().getJMenuItem());
		getJMenu(getActionVisible().getParentMenuName()).add(getActionVisible().getJMenuItem());
		getJMenu(getActionCompetences().getParentMenuName()).add(getActionCompetences().getJMenuItem());
		getJMenu(getActionActivites().getParentMenuName()).add(getActionActivites().getJMenuItem());
		getJMenu(getActionQuitter().getParentMenuName()).add(getActionPDF().getJMenuItem());
		getJMenu(getActionQuitter().getParentMenuName()).add(getActionQuitter().getJMenuItem());

	}

	private ActionPdf actionPdf = null;

	private ActionBDA getActionPDF() {
		if (actionPdf == null)
			actionPdf = new ActionPdf();
		return actionPdf;
	}

	private ActionQuitter actionQuitter = null;

	private ActionBDA getActionQuitter() {
		if (actionQuitter == null)
			actionQuitter = new ActionQuitter();
		return actionQuitter;
	}

	private ActionActivite actionActivites = null;

	private ActionBDA getActionActivites() {
		if (actionActivites == null)
			actionActivites = new ActionActivite();
		return actionActivites;
	}

	private ActionCompetences actionCompetences = null;

	private ActionBDA getActionCompetences() {
		if (actionCompetences == null)
			actionCompetences = new ActionCompetences();
		return actionCompetences;
	}

	private ActionVisible actionVisible = null;

	private ActionBDA getActionVisible() {
		if (actionVisible == null)
			actionVisible = new ActionVisible();
		return actionVisible;
	}

	private ActionNewVersion actionNewVersion = null;

	private ActionBDA getActionNewVersion() {
		if (actionNewVersion == null)
			actionNewVersion = new ActionNewVersion();
		return actionNewVersion;
	}

	private ActionSave actionSave = null;

	private ActionBDA getActionSave() {
		if (actionSave == null)
			actionSave = new ActionSave();
		return actionSave;
	}

	private ActionRef actionRef = null;

	private ActionBDA getActionRef() {
		if (actionRef == null)
			actionRef = new ActionRef();
		return actionRef;
	}

	private ActionGestionnaire actionGestionnaire = null;

	private ActionBDA getActionGestionnaire() {
		if (actionGestionnaire == null)
			actionGestionnaire = new ActionGestionnaire();
		return actionGestionnaire;
	}

	private ActionNouveau actionNouveau = null;

	private ActionBDA getActionNouveau() {
		if (actionNouveau == null)
			actionNouveau = new ActionNouveau();
		return actionNouveau;
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
