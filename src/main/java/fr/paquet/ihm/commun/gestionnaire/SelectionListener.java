package fr.paquet.ihm.commun.gestionnaire;

import java.util.EventListener;



public interface SelectionListener extends EventListener{
	
	public void SelectionChange(SelectedEvent  eventObject);

}
