package fr.paquet.ihm.style;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public abstract class StyleText extends SimpleAttributeSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StyleText(String font, int fontSize, Color color, boolean underLine, boolean bold, boolean italic,
			int alignement) {
		super();

		StyleConstants.setFontFamily(this, font);
		StyleConstants.setFontSize(this, fontSize);
		StyleConstants.setForeground(this, color);
		StyleConstants.setUnderline(this, underLine);
		StyleConstants.setBold(this, bold);
		StyleConstants.setItalic(this, italic);
		StyleConstants.setAlignment(this, alignement);
	}

}
