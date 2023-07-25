package fr.paquet.ihm.commun.gestionnaire;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtomPanel buttomPanel = null;
	private List<JButton> buttons = null;

	public PanelButton(ButtomPanel buttomPanel, List<JButton> buttons) {
		super();

		// setteur des composants
		setButtomPanel(buttomPanel);
		setButtons(buttons);

		// ajout du layout
		setLayout(new GridBagLayout());

		// ajout des composants au panel
		add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		for (int i = 0; i < getButtons().size(); i++) {
			add(getButtons().get(i), new GridBagConstraints(i + 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		}

		// listener
		addListener();

	}

	private void addListener() {
		for (int i = 0; i < getButtons().size(); i++) {
			getButtons().get(i).addActionListener(getButtomPanel());
		}

	}

	public ButtomPanel getButtomPanel() {
		return buttomPanel;
	}

	public void setButtomPanel(ButtomPanel buttomPanel) {
		this.buttomPanel = buttomPanel;
	}

	private List<JButton> getButtons() {
		return buttons;
	}

	private void setButtons(List<JButton> buttons) {
		this.buttons = buttons;
	}

}
