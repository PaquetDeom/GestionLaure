package fr.paquet.ihm.newUriCalendar;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.traitement.calendrier.Calendrier;
import main.MainFrame;

public class JDialogUriCalendarCreation extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JDialogUriCalendarCreation() {
		super(MainFrame.getUniqInstance());

		// construction de la fenêtre
		setTitle("Saisi de l'url du calendrier google");
		setSize(600, 130);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(false);

		// ajout du panel
		setContentPane(getJPanelUriCalendarCreation());

		// listener
		getJPanelUriCalendarCreation().getButtonOk().addActionListener(this);
		getJPanelUriCalendarCreation().getButtonAnnul().addActionListener(this);

		setVisible(true);
	}

	private JPanelUriCalendarCreation jPanelUriCalendarCreation = null;

	private JPanelUriCalendarCreation getJPanelUriCalendarCreation() {
		if (jPanelUriCalendarCreation == null)
			jPanelUriCalendarCreation = new JPanelUriCalendarCreation(this);
		return jPanelUriCalendarCreation;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		JButton button = (JButton) evt.getSource();
		String buttontext = button.getText();

		if (buttontext.equals("Valider")) {
			try {
				MainFrame.getUniqInstance().setCalendrier(new Calendrier(
						getJPanelUriCalendarCreation().getcommunJPanelHorizontalJLabelJTexField().getText()));
			} catch (Exception e) {
				new AlertWindow(AlertType.ERREUR, e.getMessage());
				e.printStackTrace();
			}
			try {
				Desktop.getDesktop().browse(new URI(MainFrame.getUniqInstance().getCalendrier().getUri()));
			} catch (IOException e1) {
				new AlertWindow(AlertType.ERREUR, "Erreur lors de la conexion au calendrier");
				e1.printStackTrace();
			} catch (URISyntaxException e2) {
				new AlertWindow(AlertType.ERREUR, "L'url n'est pas conforme");
				e2.printStackTrace();
			}
			dispose();

		} else {

			dispose();

		}
	}

}
