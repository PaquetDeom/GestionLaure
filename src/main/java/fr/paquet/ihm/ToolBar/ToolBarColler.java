package fr.paquet.ihm.ToolBar;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;

public class ToolBarColler extends JButtonToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolBarColler(SequenceToolBar sequenceToolBar) {
		super(null, sequenceToolBar);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (getSequenceToolBar().getTextPane() != null) {
			try {
				Coller coller = new Coller();
				String textAColler = coller.getText();

				int debutSel = getSequenceToolBar().getTextPane().getSelectionStart();
				int finSel = 0;

				if (getSequenceToolBar().getTextPane().getSelectedText() != null) {
					finSel = getSequenceToolBar().getTextPane().getSelectedText().length();

					getSequenceToolBar().getStyle().remove(debutSel, finSel);
				}

				getSequenceToolBar().getStyle().insertString(debutSel, textAColler, null);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public ImageIcon getIcon() {

		ImageIcon icon = new ImageIcon("./target/classes/images/icons/paste.png");
		return new ImageIcon(icon.getImage().getScaledInstance(taille(), taille(), Image.SCALE_DEFAULT));

	}

}
