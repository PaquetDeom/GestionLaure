package fr.paquet.ihm.gestionnaire.projet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import fr.paquet.ihm.commun.gestionnaire.UserObject;
import fr.paquet.traitement.projet.Projet;
import fr.paquet.traitement.projet.Seance;

public class GestionnaireTreeNodeGestionnaire extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GestionnaireTreeNodeGestionnaire(List<Projet> projets) throws Exception {

		this.setUserObject(new UserObject(null, "Choix d'un projet"));

		for (Projet projet : projets) {
			this.add(new TablesTreeNode(projet, projet.getSeances()));
		}

	}

	@Override
	public boolean isLeaf() {

		return false;
	}

	public static class TablesTreeNode extends DefaultMutableTreeNode {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Projet projet = null;

		public TablesTreeNode(Projet projet, List<Seance> seances) {
			super();

			setProjet(projet);

			for (Seance seance : seances) {
				if (seance.getProjet().equals(projet)) {
					addSeance(seance);
				}
			}

			this.setUserObject(new UserObject(getProjet(), getProjet().toString()));

		}

		@Override
		public boolean isLeaf() {

			return false;
		}

		public Projet getProjet() {
			return projet;
		}

		public void setProjet(Projet projet) {
			this.projet = projet;
		}

		private List<Seance> seances = null;

		private List<Seance> getSeances() {
			if (seances == null)
				seances = new ArrayList<Seance>();
			return seances;

		}

		private void addSeance(Seance seance) {
			if (!getSeances().contains(seance))
				getSeances().add(seance);
		}

	}

}