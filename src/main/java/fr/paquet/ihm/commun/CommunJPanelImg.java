package fr.paquet.ihm.commun;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.paquet.ihm.style.StyleBorder;

public abstract class CommunJPanelImg extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton jButton = null;
	private BufferedImage image = null;

	protected CommunJPanelImg(BufferedImage image) {
		super();

		// set des composants
		setImage(image);

		// ajout du Layout
		setLayout(new BorderLayout());

		// Ajout des Elements
		add(getComponent(), BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.WEST);

		// attribut du panel
		setBorder(StyleBorder.BORDERPANEL.getBorder());

	}

	protected abstract void setJButton(JButton jButton);

	public JButton getjButton() {
		return jButton;
	}

	private CommunPanelImage communPanelImage = null;

	private CommunPanelImage getCommunPanelImage() {
		if (communPanelImage == null)
			communPanelImage = new CommunPanelImage(this);
		return communPanelImage;
	}

	public BufferedImage getImage() {
		return image;
	}

	private void setImage(BufferedImage image) {
		if (image == null)
			setJButton(new JButton());
		else
			this.image = image;
	}

	private Component getComponent() {
		if (getImage() == null)
			return getjButton();
		else
			return getCommunPanelImage();
	}

}
