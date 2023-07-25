package fr.paquet.ihm.newUriCalendar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.paquet.ihm.commun.CommunJPanelHorizontalJLabelJTexField;

public class JPanelUriCalendarCreation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialogUriCalendarCreation jDialogUriCalendarCreation = null;

	public JPanelUriCalendarCreation(JDialogUriCalendarCreation jDialogUriCalendarCreation) {
		super();

		// setteur des éléments
		setjDialogUriCalendarCreation(jDialogUriCalendarCreation);

		// ajout du layout
		setLayout(new GridBagLayout());

		// ajout des elements aux panel
		add(getcommunJPanelHorizontalJLabelJTexField(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		add(getJPanelButton(), new GridBagConstraints(0, 1, 1, 1, 0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

	}

	private CommunJPanelHorizontalJLabelJTexField communJPanelHorizontalJLabelJTexField = null;
	
	public CommunJPanelHorizontalJLabelJTexField getcommunJPanelHorizontalJLabelJTexField() {
		if(communJPanelHorizontalJLabelJTexField == null)
			communJPanelHorizontalJLabelJTexField = new CommunJPanelHorizontalJLabelJTexField("URL Calendrier : ", this) {
				
				@Override
				public void objectSelected(PropertyChangeEvent o) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			};
		return communJPanelHorizontalJLabelJTexField;
	}
	
	private JButton buttonOK = null;

	public JButton getButtonOk() {
		if (buttonOK == null)
			buttonOK = new JButton("Valider");
		return buttonOK;
	}

	private JButton buttonAnnul = null;

	public JButton getButtonAnnul() {
		if (buttonAnnul == null)
			buttonAnnul = new JButton("Annuler");
		return buttonAnnul;
	}

	private JPanel getJPanelButton() {

		// création des composants
		JPanel panel = new JPanel(new GridBagLayout());

		// ajout des composants aux panels
		panel.add(getButtonOk(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(getButtonAnnul(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		return panel;
	}

	public JDialogUriCalendarCreation getjDialogUriCalendarCreation() {
		return jDialogUriCalendarCreation;
	}

	public void setjDialogUriCalendarCreation(JDialogUriCalendarCreation jDialogUriCalendarCreation) {
		this.jDialogUriCalendarCreation = jDialogUriCalendarCreation;
	}

}
