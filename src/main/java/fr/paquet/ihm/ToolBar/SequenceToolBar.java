package fr.paquet.ihm.ToolBar;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.StyledDocument;

public class SequenceToolBar extends JToolBar implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SequenceToolBar sequenceToolBar = null;

	private SequenceToolBar() {
		super();

		add(new ToolBarCopy(this));
		add(new ToolBarCouper(this));
		add(new ToolBarColler(this));

		addSeparator();

		add(new ToolBarGras(this));
		add(new ToolBarSoul(this));
		add(new ToolBarItalique(this));

		addSeparator();

		add(new ToolBarAlGauche(this));
		add(new ToolBarAlCenter(this));
		add(new ToolBarAlDroite(this));
		add(new ToolBarAlJustifier(this));

		addSeparator();

	}

	private JTextPane textPane = null;

	public JTextPane getTextPane() {
		return textPane;
	}

	public StyledDocument getStyle() {
		return getTextPane().getStyledDocument();
	}

	@Override
	public void focusGained(FocusEvent event) {
		JTextPane textPane = (JTextPane) event.getSource();
		this.textPane = textPane;
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		

	}

	public static SequenceToolBar getUniqintance() {
		if (sequenceToolBar == null)
			sequenceToolBar = new SequenceToolBar();
		return sequenceToolBar;
	}

}
