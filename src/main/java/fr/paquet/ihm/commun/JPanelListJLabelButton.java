package fr.paquet.ihm.commun;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.paquet.activite.Activite_1;
import fr.paquet.activite.Ressource;
import fr.paquet.ihm.activite.ressource.JDialogRessource;
import fr.paquet.ihm.principal.activite.ActivitePanelConception;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleFont;
import main.MainFrame;

public abstract class JPanelListJLabelButton extends JPanel implements ActionListener, PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Ressource> ressources = null;
	private ActivitePanelConception activitePanelConception = null;

	public JPanelListJLabelButton(String title, ActivitePanelConception activitePanelConception) {
		super();

		// set des Components
		setActivitePanelConception(activitePanelConception);
		setTitleLabel(new JLabel(title));

		// Set du Layout
		setLayout(new GridBagLayout());

		// attributs du Panel
		setBorder(StyleBorder.BORDERPANEL.getBorder());

		// ajout des components
		affiche();

		// listerner
		getButtonoAdd().addActionListener(this);
		MainFrame.getUniqInstance().addPropertyChangeListener(this);

		Enable();

	}

	protected Activite_1 getActivite() {
		return getActivitePanelConception().getActivitepanel().getOngletActivite().getActivite();
	}

	public void affiche() {
		removeAll();
		int count = 1;
		add(getPanelTitre(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0));

		if (getRessources() != null && !getRessources().isEmpty())
			for (int i = 0; i < getRessources().size(); i++) {

				Ressource res = getRessources().get(i);
				if (res.getUrl() != null) {
					add(new KLink(res.getText(), res.getUrl(), this), new GridBagConstraints(0, i + 1, 1, 1, 0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				} else
					add(new KLink(res.getText(), this), new GridBagConstraints(0, i + 1, 1, 1, 0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				count = i + 2;

			}

		add(getPanelButton(), new GridBagConstraints(0, count, 1, 1, 0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		revalidate();

	}

	private JPanel getPanelButton() {
		JPanel panel = new JPanel(new GridBagLayout());

		panel.add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0));
		panel.add(getButtonoAdd(), new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 1, 0));

		return panel;
	}

	private JButton btnAdd = null;

	private JButton getButtonoAdd() {
		if (btnAdd == null)
			btnAdd = new JButton("Add");
		return btnAdd;
	}

	protected void Enable() {

		if (MainFrame.getUniqInstance().getSequenceVersion()
				.isModifiable())
			getButtonoAdd().setEnabled(true);
		else
			getButtonoAdd().setEnabled(false);

	}

	private JLabel titleLabel = null;

	private JLabel getTitleLabel() {
		return titleLabel;
	}

	private void setTitleLabel(JLabel titleLabel) {

		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(StyleFont.TITLESEQ.getFont());
		this.titleLabel = titleLabel;
	}

	private JPanel panelTitre = null;

	private JPanel getPanelTitre() {

		if (panelTitre == null) {

			panelTitre = new JPanel(new FlowLayout());

			// attribut du panel
			panelTitre.setBackground(StyleColor.PANELTITLE.getColor());
			panelTitre.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());

			// ajut des component
			panelTitre.add(getTitleLabel());

		}
		return panelTitre;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new JDialogRessource(this);
	}

	protected abstract List<? extends Ressource> getRessources();

	public ActivitePanelConception getActivitePanelConception() {
		return activitePanelConception;
	}

	private void setActivitePanelConception(ActivitePanelConception activitePanelConception) {
		this.activitePanelConception = activitePanelConception;
	}

	public abstract void addRessouce(String text, String url);

	public abstract void removeRessouce(String text, String url);

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Enable();

	}

}
