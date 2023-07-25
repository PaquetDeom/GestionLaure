package fr.paquet.ihm.style;

public enum StyleRowHeight {

	COMPROWHEIGHT;

	public int getHeight() {
		switch (this) {
		case COMPROWHEIGHT:

			return 85;

		default:
			return 0;
		}
	}

}
