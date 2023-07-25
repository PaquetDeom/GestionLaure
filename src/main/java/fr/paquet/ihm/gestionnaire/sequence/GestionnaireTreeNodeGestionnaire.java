package fr.paquet.ihm.gestionnaire.sequence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import fr.paquet.ihm.commun.gestionnaire.UserObject;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.sequence.SequenceImpl;
import fr.paquet.sequence.SequenceVersion;

public class GestionnaireTreeNodeGestionnaire extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GestionnaireTreeNodeGestionnaire(List<SequenceVersion> sequenceVersion, Referentiel ref) throws Exception {

		this.setUserObject(new UserObject(null, "Choix d'une séquence"));

		for (Capacite cap : ref.getCapacites()) {
			this.add(new TablesTreeNode(cap, sequenceVersion));
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
		private Capacite capacite = null;

		public TablesTreeNode(Capacite cap, List<SequenceVersion> seqVers) {
			super();

			setCapacite(cap);

			for (SequenceVersion seq : seqVers) {
				if (seq.getFirstSequence().getCapacite() != null) {
					if (seq.getFirstSequence().getCapacite().equals(cap))
						addSequenceImpl(seq.getFirstSequence());
				} else if (cap.getCode().equals("C99"))
					addSequenceImpl(seq.getFirstSequence());
			}

			this.setUserObject(new UserObject(getCapacite(), getCapacite().toString()));

			for (SequenceImpl sequenceImpl : getSequenceImpl()) {
				this.add(new TableTreeNod(sequenceImpl, seqVers));

			}
		}

		@Override
		public boolean isLeaf() {

			return false;
		}

		public Capacite getCapacite() {
			return capacite;
		}

		public void setCapacite(Capacite capacite) {
			this.capacite = capacite;
		}

		private List<SequenceImpl> sequenceImpl = null;

		private List<SequenceImpl> getSequenceImpl() {
			if (sequenceImpl == null)
				sequenceImpl = new ArrayList<SequenceImpl>();
			return sequenceImpl;

		}

		private void addSequenceImpl(SequenceImpl seqImpl) {
			if (!getSequenceImpl().contains(seqImpl))
				getSequenceImpl().add(seqImpl);
		}

	}

	public static class TableTreeNod extends DefaultMutableTreeNode {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TableTreeNod(SequenceImpl sequenceImpl, List<SequenceVersion> sequenceVersions) {
			super();

			this.setUserObject(new UserObject(sequenceImpl, sequenceImpl.toString()));

			for (SequenceVersion seqVersion : sequenceVersions) {
				if (seqVersion.getFirstSequence().equals(sequenceImpl))
					this.add(new TableTreeNode(seqVersion));
			}
		}

		@Override
		public boolean isLeaf() {

			return false;
		}
	}

	public static class TableTreeNode extends DefaultMutableTreeNode {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TableTreeNode(SequenceVersion sequenceVersion) {
			super();

			this.setUserObject(new UserObject(sequenceVersion, sequenceVersion.toString()));
		}

		@Override
		public boolean isLeaf() {

			return true;
		}
	}

}
