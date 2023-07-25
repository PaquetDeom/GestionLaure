package fr.paquet.ihm.gestionnaire.sequence;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.commun.gestionnaire.JPanelGestionnaireRight;
import fr.paquet.ihm.commun.gestionnaire.PanelLeftRight;
import fr.paquet.ihm.commun.gestionnaire.SelectedEvent;
import fr.paquet.ihm.commun.gestionnaire.UserObject;
import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleColor;
import fr.paquet.ihm.style.StyleFont;
import fr.paquet.ihm.style.StyleTextDocument;
import fr.paquet.sequence.SequenceVersion;

public class JPanelGestionnaireOuvrirRight extends JPanelGestionnaireRight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialogGestionnaireOuvrir jDialogGestionnaireOuvrir = null;

	public JPanelGestionnaireOuvrirRight(JDialogGestionnaireOuvrir jDialogGestionnaireOuvrir) {
		super(null);

		setjDialogGestionnaireOuvrir(jDialogGestionnaireOuvrir);

		// listener
		jDialogGestionnaireOuvrir.getJTreeGestionnaireGestionnaire().addSelectionListeners(this);

	}

	@Override
	public void SelectionChange(SelectedEvent eventObject) {
		UserObject userObject = (UserObject) eventObject.getObject();
		SequenceVersion sequenceVersion = (SequenceVersion) userObject.getObject();
		setSequenceVersion(sequenceVersion);
		setPanelTitre(sequenceVersion.toString());
		setPanelLeft(sequenceVersion);

		affiche();

	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	private void setSequenceVersion(SequenceVersion sequenceVersion) {
		SequenceVersion oldSequence = (SequenceVersion) this.objectSelected;
		this.objectSelected = sequenceVersion;
		this.pcs.firePropertyChange("sequenceVersion", oldSequence, sequenceVersion);

	}

	@Override
	protected void setPanelLeft(Object object) {
		String title = "Séquence";
		String text = "attente de selection";

		if (object != null) {
			SequenceVersion seqVersion = (SequenceVersion) object;
			if (seqVersion.getElementsARetenir() != null)
				text = seqVersion.getElementsARetenir();

		}

		PanelLeftRight panel;
		try {
			panel = new PanelLeftRight(text, title, StyleTextDocument.COMPETENCE.getStyleText());
			this.panelLeft = panel;
		} catch (BadLocationException e) {
			e.printStackTrace();
			new AlertWindow(AlertType.ERREUR, e.getMessage());
		}

	}

	@Override
	protected void setPanelRight(Object object) {

	}

	@Override
	protected void setPanelTitre(String Titre) {

		JPanel panel = new JPanel(new BorderLayout());

		JLabel label = new JLabel();
		label.setFont(StyleFont.TITLECORPS.getFont());

		if (Titre != null)
			label.setText(Titre);
		else
			label.setText("attente de sélection");

		panel.add(label, BorderLayout.CENTER);
		panel.setBackground(StyleColor.PANELTITLE.getColor());
		panel.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());

		this.panelTitre = panel;
	}

	private SequenceVersion getSequenceVersion() {
		return (SequenceVersion) getObjectSelected();
	}

	public class Buttonsuppr extends JButton implements PropertyChangeListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Buttonsuppr(JPanelGestionnaireOuvrirRight panel) {
			super("Supprimer");

			Enable();

			// listener
			panel.addPropertyChangeListener(this);
		}

		private void Enable() {
			if (getSequenceVersion() == null
					|| !getSequenceVersion().isModifiable())
				this.setEnabled(false);
			else
				this.setEnabled(true);
		}

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			Enable();

		}

	}

	@Override
	protected void setButtons(List<JButton> buttons) {

		buttons.add(new JButton("Ouvrir"));
		buttons.add(new Buttonsuppr(this));
		buttons.add(new JButton("Nouveau"));
		buttons.add(new JButton("Annuler"));
		this.buttons = buttons;

	}

	public JDialogGestionnaireOuvrir getjDialogGestionnaireOuvrir() {
		return jDialogGestionnaireOuvrir;
	}

	public void setjDialogGestionnaireOuvrir(JDialogGestionnaireOuvrir jDialogGestionnaireOuvrir) {
		this.jDialogGestionnaireOuvrir = jDialogGestionnaireOuvrir;
	}

}
