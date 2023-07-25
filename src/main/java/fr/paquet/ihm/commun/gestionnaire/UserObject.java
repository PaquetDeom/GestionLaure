package fr.paquet.ihm.commun.gestionnaire;

public class UserObject {

	private Object object = null;
	private String text = null;

	public UserObject(Object object, String text) {
		super();
		this.object = object;
		this.text = text;
	}

	public Object getObject() {
		return object;
	}

	public String getText() {
		return text;
	}
}
