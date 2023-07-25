package fr.paquet.ihm.commun;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import fr.paquet.activite.ActiviteStrategie;
import fr.paquet.activite.Activite_1;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.principal.activite.JSplitPaneActProFEleve;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleFont;
import main.MainFrame;

public abstract class JPanelCommunEleveProf extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JButton jButtonAdd = null;
	protected List<JPanelActiviteProfEleve> jPanelActiviteProfEleves = null;
	private JSplitPaneActProFEleve jSplitPaneActProFEleve = null;
	private String titre = null;

	public JPanelCommunEleveProf(JSplitPaneActProFEleve jSplitPaneActProFEleve, String titre) {
		super();

		// set des composants
		setjSplitPaneActProFEleve(jSplitPaneActProFEleve);
		setTitre(titre);
		initData();

		// Ajout des composants
		affiche();

	}

	protected abstract void initData();

	private JPanel jpanelTitre = null;

	private JPanel getJpanelTitre() {

		if (jpanelTitre == null) {
			jpanelTitre = new JPanel();
			jpanelTitre.setLayout(new GridBagLayout());

			JLabel label = new JLabel(getTitre());
			label.setFont(StyleFont.TITLEACT.getFont());
			label.setHorizontalAlignment(SwingConstants.CENTER);

			jpanelTitre.add(label, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

			jpanelTitre.setBackground(StyleColor.PANELTITLE.getColor());
			jpanelTitre.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
		}

		return jpanelTitre;
	}

	protected JPanel jPanelActivite = null;

	private JPanel getJPanelActivite() {

		if (jPanelActivite == null) {
			jPanelActivite = new JPanel();
			jPanelActivite.setLayout(new GridBagLayout());
			int i = 0;
			if (getJPanelActiviteProfEleves().size() != 0) {

				for (JPanelActiviteProfEleve jP : getJPanelActiviteProfEleves()) {
					jPanelActivite.add(jP, new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

					i++;
				}
			}

			jPanelActivite.add(new JPanel(), new GridBagConstraints(0, i, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		return jPanelActivite;
	}

	protected void affiche() {
		this.removeAll();

		this.setLayout(new GridBagLayout());
		jPanelActivite = null;

		add(getJpanelTitre(), new GridBagConstraints(0, 0, 1, 1, 1, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getPanelButton(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getJPanelActivite(), new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		this.revalidate();

	}

	protected Activite_1 getActivite() {
		return getjSplitPaneActProFEleve().getActivitePanelStrategie().getActivitepanel().getOngletActivite()
				.getActivite();
	}

	protected abstract void addActiviteProfEleve();

	protected List<ActiviteStrategie> activiteStrategies = null;

	public List<ActiviteStrategie> getActiviteStrategie() {
		if (activiteStrategies == null)
			activiteStrategies = new ArrayList<ActiviteStrategie>();
		return activiteStrategies;
	}

	private JPanel PanelButton = null;

	private JPanel getPanelButton() {
		if (PanelButton == null) {
			PanelButton = new JPanel(new GridBagLayout());
			PanelButton.add(getjButtonAdd(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			PanelButton.add(new JPanel(), new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		return PanelButton;
	}

	public JButton getjButtonAdd() {

		if (jButtonAdd == null) {

			jButtonAdd = new JButton("Ajouter");

			jButtonAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (MainFrame.getUniqInstance().getSequenceVersion()
							.isModifiable())
						addActiviteProfEleve();
					else
						new AlertWindow(AlertType.INFORMATION, "La séquence n'est pas modifiable");

				}
			});
		}
		return jButtonAdd;

	}

	public void addjPanelActiviteProfEleves(JPanelActiviteProfEleve jPanelActiviteProfEleves) {
		getJPanelActiviteProfEleves().add(jPanelActiviteProfEleves);
		affiche();
	}

	public List<JPanelActiviteProfEleve> getJPanelActiviteProfEleves() {
		if (jPanelActiviteProfEleves == null)
			jPanelActiviteProfEleves = new ArrayList<JPanelActiviteProfEleve>();

		return jPanelActiviteProfEleves;
	}

	public JSplitPaneActProFEleve getjSplitPaneActProFEleve() {
		return jSplitPaneActProFEleve;
	}

	private void setjSplitPaneActProFEleve(JSplitPaneActProFEleve jSplitPaneActProFEleve) {
		this.jSplitPaneActProFEleve = jSplitPaneActProFEleve;
	}

	private String getTitre() {
		return titre;
	}

	private void setTitre(String titre) {
		this.titre = titre;
	}

	public void removePanelActiviteProfEleve(JPanelActiviteProfEleve jPanelActiviteProfEleve) {
		getJPanelActiviteProfEleves().remove(jPanelActiviteProfEleve);
		affiche();

	}

}
