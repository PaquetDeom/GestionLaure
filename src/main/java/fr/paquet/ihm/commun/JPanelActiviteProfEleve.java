package fr.paquet.ihm.commun;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import fr.paquet.activite.ActiviteEleve;
import fr.paquet.activite.ActiviteProf;
import fr.paquet.activite.ActiviteStrategie;
import fr.paquet.dataBase.Factory.Activite.ActiviteEleveFactory;
import fr.paquet.dataBase.Factory.Activite.ActiviteProfFactory;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleTextDocument;
import main.MainFrame;

public class JPanelActiviteProfEleve extends JPanel implements ActionListener, FocusListener, PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane JtextDescription = null;
	private JComboBox<String> JComboTemps = null;
	private JButton buttonSuppr = null;
	private JPanelCommunEleveProf jPanelCommunEleveProf = null;
	private ActiviteStrategie activiteStrategie = null;

	public JPanelActiviteProfEleve(ActiviteEleve activiteEleve, String text,
			JPanelCommunEleveProf jPanelCommunEleveProf) throws BadLocationException {
		this(text, jPanelCommunEleveProf);

		setActiviteStrategie(activiteEleve);
	}

	public JPanelActiviteProfEleve(ActiviteProf activiteProf, String text, JPanelCommunEleveProf jPanelCommunEleveProf)
			throws BadLocationException {
		this(text, jPanelCommunEleveProf);

		setActiviteStrategie(activiteProf);
	}

	private JPanelActiviteProfEleve(String text, JPanelCommunEleveProf jPanelCommunEleveProf)
			throws BadLocationException {
		super();

		// ajout du layout
		setjPanelCommunEleveProf(jPanelCommunEleveProf);
		setLayout(new GridBagLayout());

		if (text == null || text.equals(""))
			setJtextDescription(null);
		else
			setJtextDescription(text);

		setJComboTemps(new JComboBox<String>());
		setButtonSuppr(new JButton("suppr"));

		// ajout des composants
		add(getJtextDescription(), new GridBagConstraints(0, 0, 1, 3, 0.8, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getJPanelTitre(), new GridBagConstraints(1, 0, 1, 1, 0.2, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getJComboTemps(), new GridBagConstraints(1, 1, 1, 1, 0.2, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(getButtonSuppr(), new GridBagConstraints(1, 2, 1, 1, 0.2, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// attributs du panel
		setBorder(StyleBorder.BORDERTITLEAREA.getBorder());

		// Listener
		getButtonSuppr().addActionListener(this);
		getJtextDescription().addFocusListener(this);
		MainFrame.getUniqInstance().getSequenceVersion().addPropertyChangeListener(this);
	}

	private JPanel getJPanelTitre() {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
		panel.setBackground(StyleColor.PANELACTTITRE.getColor());

		JLabel label = new JLabel("durée");
		label.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	public JTextPane getJtextDescription() {
		return JtextDescription;
	}

	private void setJtextDescription(String jtextDescription) throws BadLocationException {

		JTextPane textPane = new JTextPane();

		if (jtextDescription != null)
			textPane.getDocument().insertString(textPane.getDocument().getLength(), jtextDescription,
					StyleTextDocument.SAISI.getStyleText());
		else
			textPane.getStyledDocument().setCharacterAttributes(0, textPane.getStyledDocument().getLength(),
					StyleTextDocument.SAISI.getStyleText(), false);

		textPane.setEditable(Enable());

		this.JtextDescription = textPane;

	}

	public JButton getButtonSuppr() {
		return buttonSuppr;
	}

	private void setButtonSuppr(JButton buttonSuppr) {
		buttonSuppr.setEnabled(Enable());
		this.buttonSuppr = buttonSuppr;
	}

	public JComboBox<String> getJComboTemps() {
		return JComboTemps;
	}

	private void setJComboTemps(JComboBox<String> jComboTemps) {

		jComboTemps.addItem("5");
		jComboTemps.addItem("10");
		jComboTemps.addItem("15");
		jComboTemps.addItem("20");
		jComboTemps.addItem("25");
		jComboTemps.addItem("30");
		jComboTemps.addItem("35");
		jComboTemps.addItem("40");
		jComboTemps.addItem("45");
		jComboTemps.addItem("50");
		jComboTemps.addItem("55");

		jComboTemps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				@SuppressWarnings("rawtypes")
				JComboBox combo = (JComboBox) event.getSource();

				String temps = (String) combo.getSelectedItem();
				double tmps = 0.0;

				if (getActiviteStrategie() instanceof ActiviteEleve == true) {
					ActiviteEleve act = (ActiviteEleve) getActiviteStrategie();
					if (temps != null && !temps.equals("")) {
						tmps = Double.parseDouble(temps);
						act.setDuree(tmps);
					}

				}
			}

		});

		JComboTemps = jComboTemps;
	}

	public JPanelCommunEleveProf getjPanelCommunEleveProf() {
		return jPanelCommunEleveProf;
	}

	private void setjPanelCommunEleveProf(JPanelCommunEleveProf jPanelCommunEleveProf) {
		this.jPanelCommunEleveProf = jPanelCommunEleveProf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (getActiviteStrategie() instanceof ActiviteEleve == true) {
			getjPanelCommunEleveProf().getActivite().getStrategie().getActiviteEleves().remove(getActiviteStrategie());
			new ActiviteEleveFactory().removeObject((ActiviteEleve) getActiviteStrategie());
		}

		if (getActiviteStrategie() instanceof ActiviteProf == true) {
			getjPanelCommunEleveProf().getActivite().getStrategie().getActiviteProfs().remove(getActiviteStrategie());
			new ActiviteProfFactory().removeObject((ActiviteProf) getActiviteStrategie());
		}

		getjPanelCommunEleveProf().removePanelActiviteProfEleve(this);

	}

	@Override
	public void focusGained(FocusEvent event) {

	}

	@Override
	public void focusLost(FocusEvent event) {

		getActiviteStrategie().setDescription(getJtextDescription().getText());

	}

	public ActiviteStrategie getActiviteStrategie() {
		return activiteStrategie;
	}

	public void setActiviteStrategie(ActiviteStrategie activiteStrategie) {
		this.activiteStrategie = activiteStrategie;
	}

	private boolean Enable() {
		if (!MainFrame.getUniqInstance().getSequenceVersion()
				.isModifiable())
			return false;
		else
			return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		getButtonSuppr().setEnabled(Enable());
		getJtextDescription().setEditable(Enable());

	}

}
