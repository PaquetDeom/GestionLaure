package fr.paquet.ihm.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file = null;

	public FileChooser() {

		super();

		FileSystemView.getFileSystemView().getHomeDirectory();
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.pdf", "pdf"));
		this.setDialogTitle("Sauvegarde : ");
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnValue = showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			setFile(getSelectedFile());

		}

	}

	public File getFile() {
		return file;
	}

	private void setFile(File file) {
		this.file = file;
	}

	public String getPath() {
		return getFile().getAbsolutePath();
	}

}
