package fr.paquet.ihm.commun;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import fr.paquet.activite.Activite_1;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.commun.explorerInternet.SwingBrowserJavaFxWeb;
import fr.paquet.ihm.principal.activite.ActivitePanelConceptionDocuments;
import fr.paquet.ihm.principal.activite.ActivitePanelConceptionRessources;
import fr.paquet.ihm.principal.activite.ActivitePanelConceptionTrace;
import main.MainFrame;

public class KLink extends JLabel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Color LINK_COLOR = new Color(51, 123, 200);

	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	private String url = null;

	private JPanelListJLabelButton jPanelListJLabelButton = null;

	public KLink(String text, JPanelListJLabelButton jPanelListJLabelButton) {
		super(text);

		setjPanelListJLabelButton(jPanelListJLabelButton);

		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setFocusable(true);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				requestFocus();
				if (getUrl() != null) {
					if (event.getClickCount() == 2)
						fireActionEvent(event);
				}
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_DELETE) {
					fireActionEvent(event);
				}

			}

		});

		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				setBorder(null);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				Border border = null;
				if (getUrl() != null)
					border = BorderFactory.createLineBorder(LINK_COLOR);
				else
					border = BorderFactory.createLineBorder(Color.BLACK);
				setBorder(border);
			}
		});

		addActionListener(this);
	}

	public KLink(String text, String Url, JPanelListJLabelButton jPanelListJLabelButton) {
		this(text, jPanelListJLabelButton);

		setUrl(Url);

		this.setForeground(LINK_COLOR);

	}

	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}

	public void removeActionListener(ActionListener listener) {
		this.actionListeners.remove(listener);
	}

	private void fireActionEvent(KeyEvent event) {

		if (MainFrame.getUniqInstance().getSequenceVersion()
				.isModifiable()) {
			if (getText() != null && !getText().equals("")) {
				if (getUrl() != null && !getUrl().equals(""))
					getjPanelListJLabelButton().removeRessouce(getText(), getUrl());
				else
					getjPanelListJLabelButton().removeRessouce(getText(), null);
			}
			if (getjPanelListJLabelButton() instanceof ActivitePanelConceptionDocuments == true)
				getActivite().removeDocument(getText());
			if (getjPanelListJLabelButton() instanceof ActivitePanelConceptionRessources == true)
				getActivite().removeRessource(getText());
			if (getjPanelListJLabelButton() instanceof ActivitePanelConceptionTrace == true)
				getActivite().removeTrace(getText());
		} else
			new AlertWindow(AlertType.ATTENTION, "La séquence n'est pas modifiable");

	}

	private Activite_1 getActivite() {
		return getjPanelListJLabelButton().getActivitePanelConception().getActivitepanel().getOngletActivite()
				.getActivite();
	}

	private void fireActionEvent(MouseEvent event) {
		ActionEvent actionEvent = new ActionEvent(this, event.getID(), null);
		for (ActionListener listener : actionListeners) {
			listener.actionPerformed(actionEvent);
		}
	}

	private String getUrl() {
		return url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new SwingBrowserJavaFxWeb(getUrl());

	}

	private JPanelListJLabelButton getjPanelListJLabelButton() {
		return jPanelListJLabelButton;
	}

	private void setjPanelListJLabelButton(JPanelListJLabelButton jPanelListJLabelButton) {
		this.jPanelListJLabelButton = jPanelListJLabelButton;
	}

}
