package fr.paquet.traitement.calendrier;

import java.util.regex.Pattern;

public class Calendrier {

	private String uriCalendar = null;

	public Calendrier(String uri) throws Exception {
		super();
		setUriCalendar(uri);
	}

	public String getUri() {
		// "https://calendar.google.com/calendar/u/0/r"
		return this.uriCalendar;
	}

	private void setUriCalendar(String uri) throws Exception {
		if (uri != null && !Pattern.matches(
				"^((https?|ftp)://)?(www\\\\.)?([a-zA-Z0-9@:%._\\\\+~#?&//=]*)([a-zA-Z0-9@:%._\\\\+~#?&//=]*)$",
				uri)) {
			throw new IllegalArgumentException("L'adresse du site n'est pas valide.");
		}
		this.uriCalendar = uri;
	}
}
