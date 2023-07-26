package fr.paquet.ihm.gestionnaire.projet;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.traitement.projet.Projet;
import fr.paquet.traitement.projet.Seance;
import main.MainFrame;

public class JDialogGestionnaireOuvrir extends JDialog implements ActionListener, AlertListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrepareListProjet prepareListProjet = null;
	private JButton buttonAnnul = new JButton("Fermer");

	public JDialogGestionnaireOuvrir() throws Exception {
		super(MainFrame.getUniqInstance());

		// construction de la fenetre
		setTitle("Gestionnaire de projet");
		setSize(1800, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(false);

		// set des Elements
		this.prepareListProjet = new PrepareListProjet(getProjets());
		setJSplitPaneLeft(getJTreeGestionnaireGestionnaire());
		setJSplitPaneRight(getJPanelGestionnaireRight());

		// listener
		buttonAnnul.addActionListener(this);

		if (!getJPanelGestionnaireRight().getButtons().isEmpty())
			for (JButton button : getJPanelGestionnaireRight().getButtons()) {
				button.addActionListener(this);
			}

		affiche();

		// visible
		setVisible(true);

	}

	private void affiche() {
		if (getContentPane().getComponents().length != 0)
			getContentPane().remove(0);
		setContentPane(getMainPane());
		revalidate();
	}

	private JPanel mainPane = new JPanel();

	private JPanel getMainPane() {

		mainPane.setLayout(new GridBagLayout());
		mainPane.add(getTopPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		mainPane.add(getJButtonPanel(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		return mainPane;
	}

	private JPanel topPanel = null;

	private JPanel getTopPanel() {

		topPanel = new JPanel(new GridBagLayout());
		topPanel.setBorder(StyleBorder.BORDERPANEL.getBorder());
		topPanel.add(getSplitPane(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		return topPanel;
	}

	private JSplitPane jS = null;

	private JSplitPane getSplitPane() {
		if (jS == null) {
			jS = new JSplitPane();
			jS.setLeftComponent(getJSplitPaneLeft());
			jS.setRightComponent(getJSplitPaneRight());
		}
		return jS;
	}

	private Component leftComponent = null;

	private void setJSplitPaneLeft(Component component) {
		this.leftComponent = component;
	}

	private Component getJSplitPaneLeft() {
		return leftComponent;
	}

	private Component rightComponent = null;

	private Component getJSplitPaneRight() {
		return rightComponent;
	}

	private void setJSplitPaneRight(Component component) {
		this.rightComponent = component;
	}

	private JPanel getJButtonPanel() {
		JPanel panel = new JPanel(new GridBagLayout());

		panel.add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		panel.add(buttonAnnul, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		return panel;
	}

	private List<Projet> getProjets() throws Exception {
		return prepareListProjet.getProjets();
	}

	private JTreeGestionnaireGestionnaire jTreeGestionnaireGestionnaire = null;

	public JTreeGestionnaireGestionnaire getJTreeGestionnaireGestionnaire() {
		if (jTreeGestionnaireGestionnaire == null) {

			try {
				this.jTreeGestionnaireGestionnaire = new JTreeGestionnaireGestionnaire(getSequenceVersion(),
						getReferentiel());
			} catch (Exception e) {
				e.printStackTrace();
				new AlertWindow(AlertType.ERREUR, "L'arboressence n'a pas été crée");
				dispose();
			}

		}
		return jTreeGestionnaireGestionnaire;
	}

	private JPanelGestionnaireOuvrirRight jPanelGestionnaireOuvrirRight = null;

	public JPanelGestionnaireOuvrirRight getJPanelGestionnaireRight() {
		if (jPanelGestionnaireOuvrirRight == null)
			this.jPanelGestionnaireOuvrirRight = new JPanelGestionnaireOuvrirRight(this);
		return jPanelGestionnaireOuvrirRight;

	}

	private Seance getSeance() {
		return (Seance) getJPanelGestionnaireRight().getObjectSelected();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		String buttonString = button.getText();

		if (buttonString.equals("Annuler") || buttonString.equals("Fermer"))
			this.dispose();

		if (buttonString.equals("Ouvrir")) {
			try {
				if (getSeance() != null) {
					MainFrame.getUniqInstance().addPanel(getSeance(), null, null);
					dispose();
				} else
					new AlertWindow(AlertType.ATTENTION, "Veuillez saisir une version de séquence");
			} catch (Exception e) {
				e.printStackTrace();
				new AlertWindow(AlertType.ERREUR, "La séquence n'a pas pu s'ouvrir");
			}
		}

		if (buttonString.equals("Nouveau")) {

			// TODO
			dispose();
		}

		if (buttonString.equals("Supprimer")) {
			new AlertWindow(AlertType.QUESTION, "Voulez-vous vraiment supprimer ?", this);
		}

	}

	@Override
	public void buttonClick(String button) {

		// TODO
		/**
		 * if (button.equals("Oui")) {
		 * 
		 * try {
		 * 
		 * MainFrame.getUniqInstance().setSequenceVersion(null);
		 * MainFrame.getUniqInstance().affichePanelOuverture();
		 * 
		 * new SequenceVersionFactory().removeObject(getSequence());
		 * 
		 * // supprimer le noeud du Jtree ((DefaultTreeModel)
		 * getJTreeGestionnaireGestionnaire().getJTreeValue().getModel())
		 * .removeNodeFromParent((MutableTreeNode)
		 * getJTreeGestionnaireGestionnaire().getJTreeValue()
		 * .getSelectionPath().getLastPathComponent());
		 * 
		 * this.dispose();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); new
		 * AlertWindow(AlertType.ERREUR, "La séance n'a pas été supprimer"); } }
		 * 
		 */
	}

}
