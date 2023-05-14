package fr.paquet.ihm.ToolBar;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import javax.swing.JOptionPane;

public class Coller {

	public Coller() {
		super();

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String text = "";

		try {
			text = (String) clipboard.getData(DataFlavor.stringFlavor);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		this.text = text;

	}

	private String text = null;

	public String getText() {
		return text;
	}

}
