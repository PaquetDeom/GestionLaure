package fr.paquet.ihm.style;

import java.awt.Font;

public enum StyleFont {

	TEXTEAREASEQUENCE, TITLESEQ, TITLEACT, TITLECORPS;

	public Font getFont() {
		switch (this) {
		case TEXTEAREASEQUENCE:
			return new Font("Arial", Font.PLAIN, 14);
		case TITLESEQ:
			return new Font("Arial", Font.PLAIN, 16);
		case TITLEACT:
			return new Font("Arial", Font.PLAIN, 16);
		case TITLECORPS:
			return new Font("Arial", Font.BOLD, 14);

		default:
			return null;
		}
	}
}
