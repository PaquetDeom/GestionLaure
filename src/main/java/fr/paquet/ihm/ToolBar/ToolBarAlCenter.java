package fr.paquet.ihm.ToolBar;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ToolBarAlCenter extends JButtonToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolBarAlCenter(SequenceToolBar sequenceToolBar) {
		super(null, sequenceToolBar);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (getSequenceToolBar().getTextPane() != null) {
			int debutSel = getSequenceToolBar().getTextPane().getSelectionStart();
			int finSel = getSequenceToolBar().getTextPane().getSelectedText().length();

			SimpleAttributeSet centrer = new SimpleAttributeSet();
			StyleConstants.setAlignment(centrer, StyleConstants.ALIGN_CENTER);

			getSequenceToolBar().getStyle().setParagraphAttributes(debutSel, finSel, centrer, false);
		}
	}

	@Override
	public ImageIcon getIcon() {

		ImageIcon icon = new ImageIcon("./target/classes/images/icons/icons8-centré-50.png");
		return new ImageIcon(icon.getImage().getScaledInstance(taille(), taille(), Image.SCALE_DEFAULT));

	}

}
