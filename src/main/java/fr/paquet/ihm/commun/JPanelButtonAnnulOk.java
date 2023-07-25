package fr.paquet.ihm.commun;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class JPanelButtonAnnulOk extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel = null;
	protected JButton btnAnnul = null;
	protected JButton btnOk = null;

	public JPanelButtonAnnulOk(JPanel panel) {
		super();

		// setteur des éléments
		setjPanel(panel);
		setBtnAnnul(new JButton("Annuler"));
		setBtnOk(new JButton("Ok"));

		// ajout du layout
		setLayout(new BorderLayout());

		// Affichage
		JPanel panelbtn = new JPanel();
		add(panelbtn, BorderLayout.SOUTH);
		panelbtn.setLayout(new GridLayout(0, 3, 0, 0));
		panelbtn.add(new JPanel());
		panelbtn.add(getBtnAnnul());
		panelbtn.add(getBtnOk());
	}

	protected JPanel getJPanel() {
		return panel;
	}

	public abstract void setjPanel(JPanel panel);

	public JButton getBtnAnnul() {
		return btnAnnul;
	}

	public abstract void setBtnAnnul(JButton btnAnnul);

	public JButton getBtnOk() {
		return btnOk;
	}

	public abstract void setBtnOk(JButton btnOk);

}
