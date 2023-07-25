package fr.paquet.ihm.commun;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CommunPanelImage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommunJPanelImg communJPanelImg = null;

	public CommunPanelImage(CommunJPanelImg communJPanelImg) {
		super();

		// set des composants
		setCommunJPanelImg(communJPanelImg);

		// ajout du Layout
		setLayout(new GridBagLayout());

		// ajout des Composants
		add(getPanelImage(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0));
		add(getPanelJButton(), new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0));
	}

	private JButton getPanelJButton() {
		JButton jButton = getCommunJPanelImg().getjButton();
		jButton.setPreferredSize(getPreferredSize());
		return jButton;
	}

	private JPanel getPanelImage() {
		JPanel p = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = getCommunJPanelImg().getImage();
				g.drawImage(img.getScaledInstance(getWidth(), -1, Image.SCALE_SMOOTH), 0, 0, null);
			}

		};
		p.setLayout(new FlowLayout());
		return p;
	}

	public CommunJPanelImg getCommunJPanelImg() {
		return communJPanelImg;
	}

	private void setCommunJPanelImg(CommunJPanelImg communJPanelImg) {
		this.communJPanelImg = communJPanelImg;
	}

}
