package fr.paquet.ihm.commun.gestionnaire;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;

import fr.paquet.ihm.style.StyleBorder;
import main.MainFrame;

public abstract class JDialogGestion extends JDialog implements ActionListener, SelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Object object = null;
	protected JTreeGestionnaire jtreeGestionnaire = null;
	protected JPanelGestionnaireRight jPanelGestionnaireRight = null;

	protected abstract void setJtreeGestionnaire();

	public JTreeGestionnaire getJtreGestionnaire() {
		return jtreeGestionnaire;
	}

	protected abstract void setJPanelGestionnaireRight();

	protected JPanelGestionnaireRight getJPanelGestionnaireRight() {
		return jPanelGestionnaireRight;
	}

	public JDialogGestion(String title, Object obj) {
		super(MainFrame.getUniqInstance());

		// construction de la fenetre
		setTitle(title);
		setSize(1800, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(false);

		// ajout des composants
		addButton();
		setObject(obj);
		setJtreeGestionnaire();
		setJPanelGestionnaireRight();
		setJSplitPaneLeft(getJtreGestionnaire());
		setJSplitPaneRight(getJPanelGestionnaireRight());

		// listener
		if (!getJPanelGestionnaireRight().getButtons().isEmpty())
			for (JButton button : getJPanelGestionnaireRight().getButtons()) {
				button.addActionListener(this);
			}
		getJtreGestionnaire().addSelectionListeners(this);
		// visible
		setVisible(true);
	}

	protected void affiche() {
		if (getContentPane().getComponents().length != 0)
			getContentPane().remove(0);
		setContentPane(getMainPane());
		revalidate();
	}

	private void setObject(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
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

	protected JPanel getTopPanel() {

		topPanel = new JPanel(new GridBagLayout());
		topPanel.setBorder(StyleBorder.BORDERPANEL.getBorder());
		topPanel.add(getSplitPane(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		if (getJButtomPanel() != null)
			topPanel.add(getJButtomPanel(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		return topPanel;
	}

	protected JPanel buttomPanel = null;

	protected abstract void setButtomPanel();

	public ButtomPanel getJButtomPanel() {

		return (ButtomPanel) buttomPanel;
	}

	private JSplitPane jS = null;

	protected JSplitPane getSplitPane() {
		if (jS == null) {
			jS = new JSplitPane();
			jS.setLeftComponent(getJSplitPaneLeft());
			jS.setRightComponent(getJSplitPaneRight());
		}
		return jS;
	}

	protected Component leftComponent = null;

	protected void setJSplitPaneLeft(Component component) {
		this.leftComponent = component;
	}

	private Component getJSplitPaneLeft() {
		return leftComponent;
	}

	protected Component rightComponent = null;

	private Component getJSplitPaneRight() {
		return rightComponent;
	}

	protected void setJSplitPaneRight(Component component) {
		this.rightComponent = component;
	}

	private List<JButton> buttons = null;

	protected List<JButton> getButtons() {
		if (buttons == null)
			buttons = new ArrayList<JButton>();
		return buttons;
	}

	protected abstract void addButton();

	private JPanel getJButtonPanel() {
		JPanel panel = new JPanel(new GridBagLayout());

		panel.add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		if (!getButtons().isEmpty())
			for (int i = 0; i < getButtons().size(); i++) {
				panel.add(getButtons().get(i), new GridBagConstraints(i + 1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
			}

		return panel;
	}

	private DefaultMutableTreeNode node = null;

	protected DefaultMutableTreeNode getNode() {
		return node;
	}

	private void setNode(DefaultMutableTreeNode node) {
		this.node = node;
	}

	@Override
	public void SelectionChange(SelectedEvent eventObject) {
		setNode(eventObject.getNode());

	}

}
