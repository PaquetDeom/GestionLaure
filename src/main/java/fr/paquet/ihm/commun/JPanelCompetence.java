package fr.paquet.ihm.commun;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import fr.paquet.ihm.style.StyleBorder;
import fr.paquet.ihm.style.StyleTextDocument;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.CritereEvaluation;

public class JPanelCompetence extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTableCompetences jTableCompetences = null;
	private List<CompetenceIntermediaire> compInts = null;
	private List<CritereEvaluation> criteres = null;

	public JPanelCompetence(JTableCompetences jTableCompetences) throws BadLocationException {
		super();

		// set des composants
		setjTableCompetences(jTableCompetences);
		this.compInts = getjTableCompetences().getCompetenceIntermediaires();

		// layout
		setLayout(new GridBagLayout());

		// ajout des Composants
		affiche();

	}

	private void affiche() throws BadLocationException {
		removeAll();

		for (int i = 0; i < this.compInts.size(); i++) {
			CompetenceIntermediaire comp = this.compInts.get(i);
			JTextPane text = createText(comp.toString());
			text.setPreferredSize(new Dimension(500, 60));

			add(text, new GridBagConstraints(0, i, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));

		}

		for (int i = 0; i < getCriteres().size(); i++) {

			JTextPane text = createText(getCriteres().get(i).getCritere());
			text.setPreferredSize(new Dimension(500, 60));

			add(text, new GridBagConstraints(1, i, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		revalidate();

	}

	private JTextPane createText(String str) throws BadLocationException {

		JTextPane textPane = new JTextPane();
		textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), str,
				StyleTextDocument.COMPETENCE.getStyleText());
		textPane.setEditable(false);
		// textPane.setPreferredSize(getPreferredSize());
		textPane.setBorder(StyleBorder.BORDERTITLEAREA.getBorder());
		return textPane;
	}

	public JTableCompetences getjTableCompetences() {
		return jTableCompetences;
	}

	public void setjTableCompetences(JTableCompetences jTableCompetences) {
		this.jTableCompetences = jTableCompetences;
	}

	private List<CritereEvaluation> getCriteres() {
		if (criteres == null) {
			criteres = new ArrayList<CritereEvaluation>();
			for (CompetenceIntermediaire comp : this.compInts) {
				for (CritereEvaluation crit : comp.getCritereEvaluations()) {
					if (!this.criteres.contains(crit))
						this.criteres.add(crit);
				}

			}
		}
		return criteres;
	}

}
