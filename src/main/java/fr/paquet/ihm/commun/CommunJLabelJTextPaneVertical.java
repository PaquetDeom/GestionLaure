package fr.paquet.ihm.commun;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import fr.paquet.ihm.ToolBar.SequenceToolBar;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleFont;
import fr.paquet.ihm.style.StyleTextDocument;
import main.MainFrame;

public abstract class CommunJLabelJTextPaneVertical extends JPanel implements PropertyChangeListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Title = null;
	private JLabel titleLabel = null;

	private JPanel panelTitre = null;
	private JPanel panelJTextArea = null;
	private SimpleAttributeSet simpleAttributeSet = null;

	protected CommunJLabelJTextPaneVertical(String text, String title, SimpleAttributeSet simpleAttributesSet)
			throws BadLocationException {
		super();

		// set des éléments
		setTitle(title);
		setTitleLabel(new JLabel(getTitle()));

		if (simpleAttributesSet != null) {
			setSimpleAttributeSet(simpleAttributesSet);
			if (text != null) {
				getTextPane().getStyledDocument().insertString(getTextPane().getStyledDocument().getLength(), text,
						getSimpleAttributeSet());
			}
		} else {
			setSimpleAttributeSet(StyleTextDocument.SAISI.getStyleText());

			getTextPane().getStyledDocument().setCharacterAttributes(0, getTextPane().getStyledDocument().getLength(),
					getSimpleAttributeSet(), false);

			getTextPane().getStyledDocument().setParagraphAttributes(getTextPane().getStyledDocument().getLength(),
					getTextPane().getStyledDocument().getLength(), getSimpleAttributeSet(), false);
		}

		setPanelTitre(new JPanel());
		setJTextPaneSize(getPreferredSize());
		setPanelJTextArea(new JPanel());
		Editable();

		// listener
		getTextPane().addFocusListener(SequenceToolBar.getUniqintance());

		if (MainFrame.getUniqInstance().getSequenceVersion() != null) {
			MainFrame.getUniqInstance().getSequenceVersion().addPropertyChangeListener(this);
			getTextPane().addFocusListener(this);
		}

		// Ajout du Layout
		setLayout(new GridBagLayout());

		// Ajout des Elements
		affiche();
	}

	protected abstract void Editable();

	public void affiche() {
		removeAll();

		add(getPanelTitre(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0));
		add(getPanelJTextArea(), new GridBagConstraints(0, 1, 1, 1, 0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		revalidate();

	}

	private String getTitle() {
		return Title;
	}

	private void setTitle(String title) {

		Title = title;
	}

	private JLabel getTitleLabel() {
		return titleLabel;
	}

	private void setTitleLabel(JLabel titleLabel) {

		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(StyleFont.TITLESEQ.getFont());
		this.titleLabel = titleLabel;
	}

	private JTextPane textPane = null;

	protected JTextPane getTextPane() {
		if (textPane == null)
			textPane = new JTextPane();
		return textPane;
	}

	private JPanel getPanelTitre() {
		return panelTitre;
	}

	private void setPanelTitre(JPanel panelTitre) {

		// ajout du Layout
		panelTitre.setLayout(new FlowLayout());

		// attribut du panel
		panelTitre.setBackground(StyleColor.PANELTITLE.getColor());
		panelTitre.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());

		// ajut des component
		panelTitre.add(getTitleLabel());

		this.panelTitre = panelTitre;
	}

	private JPanel getPanelJTextArea() {
		return panelJTextArea;
	}

	protected Dimension dimensionTextPane = null;

	protected abstract void setJTextPaneSize(Dimension dimensionTextPane);

	private Dimension getDimensionTextArea() {
		return dimensionTextPane;
	}

	private void setPanelJTextArea(JPanel panelJTextArea) {

		// ajout du Layout
		panelJTextArea.setLayout(new GridLayout(1, 0, 0, 0));

		// Scrollpane
		JScrollPane sc = new JScrollPane(getTextPane());
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// ajout des component
		panelJTextArea.add(sc, BorderLayout.CENTER);

		// Attribut du panel
		panelJTextArea.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
		setPreferredSize(getDimensionTextArea());
		this.panelJTextArea = panelJTextArea;
	}

	public SimpleAttributeSet getSimpleAttributeSet() {
		return simpleAttributeSet;
	}

	private void setSimpleAttributeSet(SimpleAttributeSet simpleAttributeSet) {
		this.simpleAttributeSet = simpleAttributeSet;
	}

}
