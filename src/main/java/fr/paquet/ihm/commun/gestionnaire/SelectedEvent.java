package fr.paquet.ihm.commun.gestionnaire;

import java.util.EventObject;

import javax.swing.tree.DefaultMutableTreeNode;

public class SelectedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object = null;
	private DefaultMutableTreeNode node = null;

	public SelectedEvent(Object source, Object object, DefaultMutableTreeNode node) {
		super(source);
		if (node == null)
			throw new NullPointerException();
		this.object = object;
		this.node = node;
	}

	public Object getObject() {
		return object;
	}

	public DefaultMutableTreeNode getNode() {
		return node;
	}

}
