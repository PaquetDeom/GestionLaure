package fr.paquet.ihm.commun.gestionnaire;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.paquet.ihm.style.StyleBorder;

public abstract class JPanelGestionnaireRight extends JPanel implements SelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialogGestion jDialogGestion = null;
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public JPanelGestionnaireRight(JDialogGestion jdialogGestion) {
		super();

		// set des composants
		setButtons(new ArrayList<JButton>());
		setjDialogGestion(jdialogGestion);
		setPanelTitre(null);
		setPanelLeft(null);
		setPanelRight(null);
		if (getjDialogGestion() != null)
			setMaximumSize(new Dimension(getjDialogGestion().getHeight(), getjDialogGestion().getWidth() / 2));

		// Ajout du layout
		setLayout(new GridBagLayout());

		// Ajout des composants
		affiche();

		// listener
		if (getjDialogGestion() != null)
			for (JButton button : getButtons()) {
				button.addActionListener(getjDialogGestion().getJButtomPanel());
			}

		// attributs du Panel
		setBorder(StyleBorder.BORDERPANEL.getBorder());

	}

	protected Object objectSelected = null;

	public Object getObjectSelected() {
		return objectSelected;
	}

	public void affiche() {
		removeAll();

		add(getPanelTitre(), new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		if (getPanelRight() != null) {
			add(getPanelLeft(), new GridBagConstraints(0, 1, 1, 1, 0.25, 1, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			add(getPanelRight(), new GridBagConstraints(1, 1, 1, 1, 0.25, 0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		} else
			add(getPanelLeft(), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		if (!getButtons().isEmpty())
			add(getPanelButton(), new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		revalidate();
	}

	protected PanelLeftRight panelLeft = null;

	protected abstract void setPanelLeft(Object object);

	protected PanelLeftRight getPanelLeft() {

		return panelLeft;
	}

	protected PanelLeftRight panelRight = null;

	protected abstract void setPanelRight(Object object);

	protected PanelLeftRight getPanelRight() {
		return panelRight;
	}

	protected JPanel panelTitre = null;

	protected abstract void setPanelTitre(String Titre);

	private JPanel getPanelTitre() {

		return panelTitre;
	}

	private JPanel getPanelButton() {
		JPanel panel = new JPanel(new GridBagLayout());

		panel.add(new JLabel(" "), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		for (int i = 0; i < getButtons().size(); i++) {
			panel.add(getButtons().get(i), new GridBagConstraints(i + 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		}

		return panel;
	}

	protected JDialogGestion getjDialogGestion() {
		return (JDialogGestion) jDialogGestion;
	}

	private void setjDialogGestion(JDialogGestion jDialogGestion) {
		this.jDialogGestion = jDialogGestion;
	}

	protected List<JButton> buttons = null;

	public List<JButton> getButtons() {
		if (buttons == null)
			buttons = new ArrayList<JButton>();
		return buttons;
	}

	protected abstract void setButtons(List<JButton> buttons);

}
