package fr.paquet.ihm.commun;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleFont;

public class CommunTitle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommunTitle(Title title) {
		super();

		// creation du layout
		// setLayout(new GridLayout(0, title.getcolumn(), 0, 0));
		setLayout(new GridBagLayout());
		int i = 0;
		for (String a : title.getTitle()) {
			JLabel label = new JLabel(a);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(StyleFont.TITLESEQ.getFont());
			JPanel panel = new JPanel();
			panel.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
			panel.setBackground(StyleColor.PANELTITLE.getColor());
			panel.add(label);
			add(panel, new GridBagConstraints(i, 0, 1, 1, 1 / title.getcolumn(), 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			i++;
		}

		// attributs du panel

	}

}
