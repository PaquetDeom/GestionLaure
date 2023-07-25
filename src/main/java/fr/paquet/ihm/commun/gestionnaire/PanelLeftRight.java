package fr.paquet.ihm.commun.gestionnaire;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import fr.paquet.ihm.commun.CommunJLabelJTextPaneVertical;

public class PanelLeftRight extends CommunJLabelJTextPaneVertical {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelLeftRight(String text, String title, SimpleAttributeSet simpleAttribiteSet)
			throws BadLocationException {
		super(text, title, simpleAttribiteSet);
	}

	@Override
	protected void Editable() {
		getTextPane().setEditable(false);

	}

	@Override
	protected void setJTextPaneSize(Dimension dimensionTextPane) {

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
		
	}

}
