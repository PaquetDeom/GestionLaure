package fr.paquet.ihm.style;

import java.awt.Color;

public enum StyleColor {

	PANELTITLE, PANELTITLESUPPORT, PANELCORPS, PANELSUPINT, PANELACTTITRE;

	public Color getColor() {
		switch (this) {
		case PANELTITLE:
			return new Color(153, 204, 255);
		case PANELTITLESUPPORT:
			return new Color(102, 153, 204);
		case PANELCORPS:
			return new Color(135, 132, 132);
		case PANELSUPINT:
			return new Color(207, 203, 202);
		case PANELACTTITRE:
			return new Color(201, 177, 202);

		default:
			return null;
		}
	}

}
