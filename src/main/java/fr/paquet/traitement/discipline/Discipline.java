package fr.paquet.traitement.discipline;

public interface Discipline {

	public void setCodeDiscipline(String code) throws Exception;

	public void setLibelleDiscipline(String libelle);

	public String getCodeDiscipline();

	public String getLibelleDiscipline();
}
