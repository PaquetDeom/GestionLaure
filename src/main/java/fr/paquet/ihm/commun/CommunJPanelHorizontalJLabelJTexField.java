package fr.paquet.ihm.commun;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class CommunJPanelHorizontalJLabelJTexField extends JPanel implements ActionListener, SelectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String textLabel = null;
	private JPanel panel = null;
	protected Class<JSearchDialog> dialogClass = null;

	@SuppressWarnings("unchecked")
	public CommunJPanelHorizontalJLabelJTexField(String textLabel, JPanel panel,
			@SuppressWarnings("rawtypes") Class dialogClass) {
		super();

		// setteur des éléments
		setTextLabel(textLabel);
		setPanel(panel);

		// Ajout du layout
		setLayout(new GridBagLayout());

		// Attributs du panel

		// Getion de l'affichage

		add(getJlabel(), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(getjTextField(), new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		if (dialogClass != null) {
			this.dialogClass = dialogClass;
			add(getButtonPanel(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		}
	}

	public CommunJPanelHorizontalJLabelJTexField(String textLabel, JPanel panel) {
		this(textLabel, panel, (Class<JSearchDialog>) null);
	}

	private Component getButtonPanel() {
		JPanel btnPanel = new JPanel(new GridLayout(1, 1));
		JButton btnSearch = new JButton("cherche");
		btnSearch.addActionListener(this);
		btnPanel.add(btnSearch);
		return btnPanel;
	}

	protected JSearchDialog dialog = null;

	protected void setDialog(JSearchDialog dialog) {
		this.dialog = dialog;
	}

	public JSearchDialog getDialog() {
		return dialog;
	}

	protected JLabel jlabel = null;

	public JLabel getJlabel() {
		if (jlabel == null)
			jlabel = new JLabel(getTextLabel() + " : ");
		return jlabel;
	}

	private JTextField jTextField = null;

	public JTextField getjTextField() {
		if (jTextField == null)
			jTextField = new JTextField();
		return jTextField;
	}

	public void setText(String text) {
		jTextField.setText(text);

	}

	public String getText() {
		return jTextField.getText();
	}

	private Object objet = null;

	protected void setObject(Object objet) {
		this.objet = objet;
	}

	public Object getObjet() {
		return objet;
	}

	private String getTextLabel() {
		return textLabel;
	}

	private void setTextLabel(String textLabel) {
		this.textLabel = textLabel;
	}

	public JPanel getPanel() {
		return panel;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
