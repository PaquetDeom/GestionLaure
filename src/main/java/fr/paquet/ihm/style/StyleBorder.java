package fr.paquet.ihm.style;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public enum StyleBorder {
	
	BORDERPANEL, BORDERTITLEAREA, BORDERPANELCOMP, BORDERPANELSAVOIR;

	public Border getBorder() {
		switch (this) {
		case BORDERPANEL:
			return BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "");
		case BORDERTITLEAREA:
			return BorderFactory.createLineBorder(Color.BLACK);
		case BORDERPANELCOMP:
			return BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Compétences");
		case BORDERPANELSAVOIR :
			return BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Savoirs");
		default:
			return null;
		}
	}
}
