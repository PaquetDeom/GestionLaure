package fr.paquet.traitement.projet;

import java.util.ArrayList;
import java.util.List;

public class Projet {

	private List<Seance> seances = null;
	
	public List<Seance> getSeances(){
		if(seances == null)
			seances = new ArrayList<Seance>();
		return seances;
	}
	
	public Projet(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getTitre() {
		// TODO Auto-generated method stub
		return null;
	}

}
