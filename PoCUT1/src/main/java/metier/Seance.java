package metier;

import java.sql.Date;
import java.sql.Time;

public class Seance {
	
	//Attribut
	int numSeance;
	Date dateSeance;
	Time heureDebutSeance;
	Time heureFinSeance;
	int numeroSemaine;
	
	//Constructor
	
	public Seance(int numSeance, Date dateSeance, Time heureDebutSeance, Time heureFinSeance, int numeroSemaine) {
		this.numSeance = numSeance;
		this.dateSeance = dateSeance;
		this.heureDebutSeance = heureDebutSeance;
		this.heureFinSeance = heureFinSeance;
		this.numeroSemaine = numeroSemaine;
	}

	//Getter and setter 
	
	public int getNumSeance() {
		return numSeance;
	}

	public void setNumSeance(int numSeance) {
		this.numSeance = numSeance;
	}

	public Date getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	public Time getHeureDebutSeance() {
		return heureDebutSeance;
	}

	public void setHeureDebutSeance(Time heureDebutSeance) {
		this.heureDebutSeance = heureDebutSeance;
	}

	public Time getHeureFinSeance() {
		return heureFinSeance;
	}

	public void setHeureFinSeance(Time heureFinSeance) {
		this.heureFinSeance = heureFinSeance;
	}

	public int getNumeroSemaine() {
		return numeroSemaine;
	}

	public void setNumeroSemaine(int numeroSemaine) {
		this.numeroSemaine = numeroSemaine;
	}


	
	

	
}
