package fr.paquet.traitement.calendrier;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.*;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import fr.paquet.ihm.alert.AlertListener;
import fr.paquet.ihm.alert.AlertType;
import fr.paquet.ihm.alert.AlertWindow;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.swt.events.DisposeEvent;

public class Calendrier {

	public static String getUri() {
		return "https://calendar.google.com/calendar/u/0/r";
	}
	/**
	 * https://github.com/google/google-api-java-client-samples Assurez-vous de
	 * spécifier le nom de votre application. Si le nom de l'application est
	 * {@code null} ou vide, l'application enregistrera un avertissement. Format
	 * suggéré est "MyCompany-ProductName/1.0".
	 */
	private static final String APPLICATION_NAME = "gestionlaure";

	/**
	 * Répertoire pour stocker les informations d'identification des utilisateurs.
	 */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			"./target/classes/calendar");

	/**
	 * Instance globale de {@link DataStoreFactory}. La meilleure pratique est de
	 * faire il s'agit d'une instance unique partagée à l'échelle mondiale dans
	 * votre application.
	 */
	private static FileDataStoreFactory dataStoreFactory;

	/** Instance globale du transport HTTP. */
	private static HttpTransport httpTransport;

	/** Instance globale de la fabrique JSON. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static com.google.api.services.calendar.Calendar client;

	static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

	/**
	 * Autorise l'application installée à accéder aux données protégées de
	 * l'utilisateur.
	 */
	private static Credential authorize() throws Exception {

		InputStreamReader streamReader = new InputStreamReader(
				Calendrier.class.getClassLoader().getResourceAsStream("./clients_secrets.json"));
		// load client secrets
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, streamReader);
		if (clientSecrets.getDetails().getClientId().startsWith("Enter")
				|| clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
			System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
					+ "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
			System.exit(1);
		}
		// configurer le flux de code d'autorisation

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				clientSecrets, Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
						.build();
		// autorise
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	public static void main(String[] args) {
		try {
			// initialise le transport
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();

			// initialise l'usine de stockage de données
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

			// autorisation
			Credential credential = authorize();

			// configurer une instance de calendrier globale
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();

			// démarre les commandes
			showCalendars();
			addCalendarsUsingBatch();
			Calendar calendar = addCalendar();
			updateCalendar(calendar);
			addEvent(calendar);
			showEvents(calendar);
			deleteCalendarsUsingBatch();
			deleteCalendar(calendar);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Throwable t) {
			new AlertWindow(AlertType.QUESTION, "Voulez-vous connecter votre application à google calendar",
					new AlertListener() {

						@Override
						public void buttonClick(String button) {
							if (button.equals("Non")) {
								new AlertWindow(AlertType.INFORMATION,
										"Votre application à besoin d'être connectée à google");
								//Fermer l'application
								//Oui Afficher la procédure de création de l'api google
							}
						}
					});
			t.printStackTrace();
		}
		System.exit(1);
	}

	private static void showCalendars() throws IOException {
		View.header("Show Calendars");
		CalendarList feed = client.calendarList().list().execute();
		View.display(feed);
	}

	private static void addCalendarsUsingBatch() throws IOException {
		View.header("Add Calendars using Batch");
		BatchRequest batch = client.batch();

		// Créez le rappel.
		JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

			@Override
			public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
				View.display(calendar);
				addedCalendarsUsingBatch.add(calendar);
			}

			@Override
			public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
				System.out.println("Error Message: " + e.getMessage());
			}
		};

		// Créez 2 entrées de calendrier à insérer.
		Calendar entry1 = new Calendar().setSummary("Calendar for Testing 1");
		client.calendars().insert(entry1).queue(batch, callback);

		Calendar entry2 = new Calendar().setSummary("Calendar for Testing 2");
		client.calendars().insert(entry2).queue(batch, callback);

		batch.execute();
	}

	private static Calendar addCalendar() throws IOException {
		View.header("Add Calendar");
		Calendar entry = new Calendar();
		entry.setSummary("Calendar for Testing 3");
		Calendar result = client.calendars().insert(entry).execute();
		View.display(result);
		return result;
	}

	private static Calendar updateCalendar(Calendar calendar) throws IOException {
		View.header("Update Calendar");
		Calendar entry = new Calendar();
		entry.setSummary("Updated Calendar for Testing");
		Calendar result = client.calendars().patch(calendar.getId(), entry).execute();
		View.display(result);
		return result;
	}

	private static void addEvent(Calendar calendar) throws IOException {
		View.header("Add Event");
		Event event = newEvent();
		Event result = client.events().insert(calendar.getId(), event).execute();
		View.display(result);
	}

	private static Event newEvent() {
		Event event = new Event();
		event.setSummary("New Event");
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + 3600000);
		DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
		event.setStart(new EventDateTime().setDateTime(start));
		DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
		event.setEnd(new EventDateTime().setDateTime(end));
		return event;
	}

	private static void showEvents(Calendar calendar) throws IOException {
		View.header("Show Events");
		Events feed = client.events().list(calendar.getId()).execute();
		View.display(feed);
	}

	private static void deleteCalendarsUsingBatch() throws IOException {
		View.header("Delete Calendars Using Batch");
		BatchRequest batch = client.batch();
		for (Calendar calendar : addedCalendarsUsingBatch) {
			client.calendars().delete(calendar.getId()).queue(batch, new JsonBatchCallback<Void>() {

				@Override
				public void onSuccess(Void content, HttpHeaders responseHeaders) {
					System.out.println("Delete is successful!");
				}

				@Override
				public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
					System.out.println("Error Message: " + e.getMessage());
				}
			});
		}

		batch.execute();
	}

	private static void deleteCalendar(Calendar calendar) throws IOException {
		View.header("Delete Calendar");
		client.calendars().delete(calendar.getId()).execute();
	}

}
