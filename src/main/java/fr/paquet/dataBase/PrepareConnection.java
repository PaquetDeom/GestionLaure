package fr.paquet.dataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import fr.paquet.dataBase.Factory.commun.UserFactory;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;
import fr.paquet.ihm.auteur.JDialogAuteur;
import fr.paquet.ihm.dataBase.JDialogIdDataBase;
import fr.paquet.io.dataBase.ReadTxtUser;

public class PrepareConnection {

	private User user = null;

	public PrepareConnection(User user) {
		super();

		if (user != null)
			setUser(user);

	}

	public void prepareAuteur() throws Exception {

		if (getUser().getAuteur() == null) {
			new JDialogAuteur();
		}

	}

	public void save() throws Exception {
		new UserFactory().persist(getUser());

	}

	public void prepareUser() throws Exception {

		BufferedReader buff;
		try {
			buff = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("./target/classes/user/user.txt")), "UTF-8"));
			ReadTxtUser rt = new ReadTxtUser(buff);
			createUser(new User(rt.getLines().get(0), rt.getLines().get(1)));
			buff.close();

		} catch (UnsupportedEncodingException e) {

			new AlertWindow(AlertType.ERREUR, e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e1) {

			new JDialogIdDataBase();

		}

	}

	public void createUser(User user) throws Exception {

		setUser(user);

		if (userIsInDb(user.getUtilisateur()))
			setUser(new UserFactory().FindUserByUserName(user.getUtilisateur()));

	}

	private boolean userIsInDb(String utilisateur) throws Exception {
		UserFactory uF = new UserFactory();
		User user = uF.FindUserByUserName(utilisateur);
		if (user == null)
			return false;
		else
			return true;
	}

	public User getUser() {
		return user;
	}

	private void setUser(User user) {
		this.user = user;
	}
}
