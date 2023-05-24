package fr.paquet.traitement.calendrier;

import java.util.ArrayList;
import java.util.Calendar;

import fr.paquet.traitement.agenda.Evenement;

public class Agenda extends Calendar{
	
	private ArrayList<Evenement> evenements = null;

	public Agenda(ArrayList<Evenement> evenements) {
		super();
		
		setEvenements(evenements);
	}
	
	
	@Override
	public void add(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGreatestMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void roll(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}


	public ArrayList<Evenement> getEvenements() {
		return evenements;
	}
	
	public void addEvenement(Evenement evenement) {
		getEvenements().add(evenement);
	}


	private void setEvenements(ArrayList<Evenement> evenements) {
		if (evenements == null)
			this.evenements = new ArrayList<Evenement>();
		this.evenements = evenements;
	}

}
