package fr.paquet.ihm.alert;

import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class AlertWindow extends JOptionPane {

	@SuppressWarnings("unused")
	private String message = null;
	@SuppressWarnings("unused")
	private String[] buttons = null;
	private AlertType alertType = null;

	private ArrayList<AlertListener> listenerList = new ArrayList<AlertListener>();

	/**
	 * Ajout des AlertListener a la liste<br/>
	 * 
	 * @param listener
	 */
	public void addButtonListener(AlertListener listener) {
		listenerList.add(listener);
	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param AlertType de la JOptionPane<br/>
	 *                  ex : AlertType.ERREUR, AlertType.ATTENTION...<br/>
	 * @param message   d'erreur ou d'alerte<br/>
	 */
	public AlertWindow(AlertType type, String message) {

		super();


		ImageIcon img = new ImageIcon();

		if (type == AlertType.ERREUR) {
			img = new ImageIcon("./target/classes/images/iconErreur.jpeg");
			showMessageDialog(null, message, type.getStringType(), JOptionPane.ERROR_MESSAGE, img);
		}
		if (type == AlertType.ATTENTION) {
			img = new ImageIcon("./target/classes/images/iconWarning.jpeg");

			showMessageDialog(null, message, type.getStringType(), JOptionPane.WARNING_MESSAGE, img);
		}
		if (type == AlertType.INFORMATION) {
			img = new ImageIcon("./target/classes/images/iconInfo.jpeg");

			showMessageDialog(null, message, type.getStringType(), JOptionPane.INFORMATION_MESSAGE, img);
		}

	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param title    de la JOptionPane doit être "Question"<br/>
	 * @param message  d'erreur ou d'alerte<br/>
	 * @param buttons  : est un tableau de Jbuttons<br/>
	 * @param listener la class qui passe le listener doit implementer l'interface
	 *                 AlertListener<br/>
	 */
	public AlertWindow(AlertType type, String message, AlertListener listener) {
		this(type, message);
		addButtonListener(listener);

		if (type == AlertType.QUESTION) {
			ImageIcon img = new ImageIcon("./target/classes/images/iconQuestion.jpeg");

			int option = showConfirmDialog(null, message, type.getStringType(), JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, img);

			if (option == JOptionPane.OK_OPTION) {

				for (AlertListener l : listenerList) {
					l.buttonClick("Oui");
				}
			}
			if (option == JOptionPane.NO_OPTION) {

				for (AlertListener l : listenerList) {
					l.buttonClick("Non");
				}
			}
		}
		
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

}
