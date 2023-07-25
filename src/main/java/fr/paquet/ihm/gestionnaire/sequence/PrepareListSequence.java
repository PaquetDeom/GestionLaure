package fr.paquet.ihm.gestionnaire.sequence;

import java.util.ArrayList;
import java.util.List;

import fr.paquet.dataBase.Connect;
import fr.paquet.dataBase.Factory.sequence.SequenceVersionFactory;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.sequence.SequenceVersion;

public class PrepareListSequence {

	private Referentiel referentiel = null;
	private boolean quemoi = false;

	public PrepareListSequence(Referentiel referentiel, boolean Quemoi) {
		super();

		this.referentiel = referentiel;
		this.quemoi = Quemoi;
	}

	private Referentiel getReferentiel() {
		return referentiel;
	}

	private boolean isQuemoi() {
		return quemoi;
	}

	private List<SequenceVersion> seqVersionInDBReferentiel = null;

	private List<SequenceVersion> getSequenceVersionsinDB() throws Exception {
		if (seqVersionInDBReferentiel == null)
			seqVersionInDBReferentiel = new SequenceVersionFactory().findSequenceByReferentiel(getReferentiel());
		return seqVersionInDBReferentiel;
	}

	private List<SequenceVersion> seqVersionInDBAuteur = null;

	private List<SequenceVersion> getSequenceVersionsAuteur() throws Exception {
		if (seqVersionInDBAuteur == null)
			seqVersionInDBAuteur = new SequenceVersionFactory()
					.findSequenceVersionByAuteur(Connect.getPConnexion().getUser().getAuteur());
		return seqVersionInDBAuteur;
	}

	private List<SequenceVersion> sequenceVersion = new ArrayList<SequenceVersion>();

	@SuppressWarnings("unchecked")
	public List<SequenceVersion> getSequenceVersions() throws Exception {
		if (isQuemoi())
			return getSequenceVersionsAuteur();
		else {
			sequenceVersion = (ArrayList<SequenceVersion>) ((ArrayList<SequenceVersion>) getSequenceVersionsinDB())
					.clone();
			for (SequenceVersion seq : getSequenceVersionsAuteur()) {
				if (!sequenceVersion.contains(seq))
					sequenceVersion.add(seq);
			}
			return sequenceVersion;
		}
	}

}
