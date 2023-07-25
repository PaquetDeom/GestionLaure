package fr.paquet.ihm.ToolBar;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class JButtonToolBar extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SequenceToolBar sequenceToolBar = null;

	public JButtonToolBar(String name, SequenceToolBar sequenceToolBar) {
		super();

		setSequenceToolBar(sequenceToolBar);
		setText(name);
		if (getIcon() != null)
			setIcon(getIcon());
		addActionListener(this);

	}

	protected int taille() {
		return 24;
	}

	public abstract ImageIcon getIcon();

	protected SequenceToolBar getSequenceToolBar() {
		return sequenceToolBar;
	}

	private void setSequenceToolBar(SequenceToolBar sequenceToolBar) {
		this.sequenceToolBar = sequenceToolBar;
	}

}
