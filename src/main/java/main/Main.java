package main;

import java.awt.EventQueue;
import java.io.IOException;

import org.hsqldb.server.ServerAcl.AclFormatException;

import fr.paquet.dataBase.CreateDB;
import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;

public class Main {

	/**
	 * Demmarrage de l application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				// Démarage du server de base de donnée
				try {
					CreateDB.getUniqinstance();

				} catch (IOException | AclFormatException e) {
					new AlertWindow(AlertType.ERREUR, "La base de donnée n'a pas été créée");
					FermetureAvecErreur();
					e.printStackTrace();
				}

				CreateDB.getServer().start();

				// creation de la mainFrame
				MainFrame mainFrame = MainFrame.getUniqInstance();
				mainFrame.setVisible(true);

			}
		});
	}

	public static void Fermeture() {
		new AlertWindow(AlertType.QUESTION, "Etes-vous sûre de vouloir quitter ?", new AlertListener() {

			@Override
			public void buttonClick(String button) {
				if (button.equals("Oui"))
					FermetureDeLaDB();
				FermetureSansErreur();

			}
		});
	}

	public static void FermetureSansErreur() {

		System.exit(0);
	}

	public static void FermetureAvecErreur() {

		System.exit(1);
	}

	public static void FermetureDeLaDB() {
		CreateDB.getServer().stop();
	}

}
