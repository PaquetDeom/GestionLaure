package main;

import java.util.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import fr.paquet.activite.Activite_1;
import fr.paquet.ihm.principal.activite.OngletActivite;
import fr.paquet.ihm.principal.progression.OngletModule;
import fr.paquet.ihm.principal.progression.OngletProgression;
import fr.paquet.ihm.principal.sequence.OngletSequence;
import fr.paquet.module.ModuleVersion;
import fr.paquet.progression.ProgressionVersion;
import fr.paquet.sequence.SequenceVersion;

@SuppressWarnings("serial")
public class MainOnglet extends JTabbedPane {

	private OngletSequence ongletSequence = null;
	private OngletModule ongletModule = null;
	private List<OngletActivite> ongletsActivites = null;

	/**
	 * Constructeur de la class private car ne doit etre instanciee qu une fois<br/>
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	private MainOnglet() {
		super(SwingConstants.TOP);

		// attributs de l'onglet

	}

	private static MainOnglet mainOnglet = null;

	public static MainOnglet getUniqInstance() {
		if (mainOnglet == null)
			mainOnglet = new MainOnglet();
		return mainOnglet;
	}

	public void afficheVersion() throws BadLocationException {
		getOngletSequence().getSequencePanel().getsequencePanelVersion().affiche();

		for (OngletActivite oA : getOngletsActivites()) {
			oA.getActivitePanel().getsequencePanelVersion().affiche();
		}
	}

	public void removeAllOngletActivites() {
		ongletsActivites = null;
	}

	public void affiche() throws BadLocationException {

		removeAll();

		addTab("Séquence " + MainFrame.getUniqInstance().getSequenceVersion().getTitre(), getOngletSequence());

		if (!MainFrame.getUniqInstance().getSequenceVersion().getActivites().isEmpty()) {
			for (int i = 0; i < MainFrame.getUniqInstance().getSequenceVersion().getMapActivite().size(); i++) {

				Activite_1 act = MainFrame.getUniqInstance().getSequenceVersion().getMapActivite().get(i + 1);
				MainFrame.getUniqInstance().getSequenceVersion().addPropertyChangeListener(act);
				OngletActivite Oa = new OngletActivite(act);
				getOngletsActivites().add(Oa);
				addTab(Oa.getActivitePanel().getTitre(), Oa);

			}
		}

		/**
		 * if
		 * (!MainFrame.getUniqInstance().getSequenceVersion().getActivites().isEmpty())
		 * { for (Activite_1 act :
		 * MainFrame.getUniqInstance().getSequenceVersion().getActivites()) {
		 * MainFrame.getUniqInstance().getSequenceVersion().addPropertyChangeListener(act);
		 * OngletActivite Oa = new OngletActivite(act); getOngletsActivites().add(Oa);
		 * addTab(Oa.getActivitePanel().getTitre(), Oa);
		 * 
		 * } }
		 */

		revalidate();
	}

	public void init(SequenceVersion sequence) throws Exception {

		this.removeAll();

		// setteur des component
		this.ongletsActivites = null;
		setOngletSequence(new OngletSequence(sequence));
		affiche();

	}

	public void init(ProgressionVersion progression) throws Exception {

		this.removeAll();

		// setteur des component
		setOngletProgression(new OngletProgression(progression));
		affiche();

	}
	
	public void init(ModuleVersion module) throws Exception {

		this.removeAll();

		// setteur des component
		setOngletModule(new OngletModule(module));
		affiche();

	}
	
	public OngletModule getOngletModule() {
		return ongletModule;
	}

	public void setOngletModule(OngletModule ongletModule) {
		this.ongletModule = ongletModule;
	}
	
	public OngletSequence getOngletSequence() {
		return ongletSequence;
	}

	public void setOngletSequence(OngletSequence ongletSequence) {
		this.ongletSequence = ongletSequence;
	}

	public List<OngletActivite> getOngletsActivites() {

		if (ongletsActivites == null)
			ongletsActivites = new ArrayList<OngletActivite>();
		return ongletsActivites;
	}

	public void addOngletsActivites(OngletActivite ongletsActivite) {
		getOngletsActivites().add(ongletsActivite);
	}

	private OngletProgression ongletProgression = null;
	
	public void setOngletProgression(OngletProgression ongletProgression) {
		this.ongletProgression = ongletProgression;
			
		
	}
	
	public OngletProgression getOngletProgression() {
		return ongletProgression;
	}

}
