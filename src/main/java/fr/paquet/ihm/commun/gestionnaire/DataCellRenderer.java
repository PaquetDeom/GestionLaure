package fr.paquet.ihm.commun.gestionnaire;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


public class DataCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataCellRenderer(JTreeGestionnaire jTreeGestionnaire) {
		super();
		
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object node, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {

		UserObject data = (UserObject) ((DefaultMutableTreeNode) node).getUserObject();
		JLabel nodeLabel=(JLabel)super.getTreeCellRendererComponent(tree, node, selected, expanded, leaf, row, hasFocus);
		nodeLabel.setText(data.getText());
		
		
		return nodeLabel;
	}

}
