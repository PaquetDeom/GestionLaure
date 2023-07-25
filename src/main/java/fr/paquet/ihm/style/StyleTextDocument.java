package fr.paquet.ihm.style;

import java.awt.Color;

import javax.swing.text.StyleConstants;

public enum StyleTextDocument {

	COMPETENCE, SAISI;

	public StyleText getStyleText() {
		switch (this) {
		case COMPETENCE:
			return new StyleTextCompetenceSavoir("arial", 14, Color.BLACK, false, false, false,
					StyleConstants.ALIGN_JUSTIFIED);
		case SAISI:
			return new StyleTextSaisi("arial", 12, Color.BLACK, false, false, false, StyleConstants.ALIGN_JUSTIFIED);

		default:
			return null;
		}

	}
}
