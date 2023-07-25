package fr.paquet.ihm.commun;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

import fr.paquet.ihm.style.StyleBorder;

public class CommunTableauJLabel extends JPanel {

	public class CommunLabel extends JLabel implements JLabelValue {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CommunLabel(int i) throws Exception {
			super(" ");
			setHorizontalAlignment(SwingConstants.CENTER);
			setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
		}

		@Override
		public String getValue(int column) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int columns = 0;
	private HashMap<Integer, CommunLabel> jLabels = null;

	public CommunTableauJLabel(int columns) throws Exception {
		super();

		setColumns(columns);

		// ajout du layout
		setLayout(new GridLayout(1, getColumns(), 0, 0));

		for (int i = 0; i < getColumns(); i++) {
			CommunLabel communLabel = new CommunLabel(i);
			getjLabels().put(i, communLabel);
			add(communLabel);
		}
		setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height * 5));
		setMinimumSize(getPreferredSize());
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public HashMap<Integer, CommunLabel> getjLabels() {
		if (jLabels == null)
			jLabels = new HashMap<Integer, CommunLabel>();
		return jLabels;
	}

}
