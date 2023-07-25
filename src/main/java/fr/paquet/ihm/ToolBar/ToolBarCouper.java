package fr.paquet.ihm.ToolBar;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;

public class ToolBarCouper extends JButtonToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolBarCouper(SequenceToolBar sequenceToolBar) {
		super(null, sequenceToolBar);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (getSequenceToolBar().getTextPane() != null) {
			try {
				int debutSel = getSequenceToolBar().getTextPane().getSelectionStart();
				int finSel = getSequenceToolBar().getTextPane().getSelectedText().length();

				new Copier(getSequenceToolBar().getTextPane().getSelectedText());

				getSequenceToolBar().getStyle().remove(debutSel, finSel);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public ImageIcon getIcon() {

		ImageIcon icon = new ImageIcon("./target/classes/images/icons/cut.png");
		return new ImageIcon(icon.getImage().getScaledInstance(taille(), taille(), Image.SCALE_DEFAULT));

	}

}
