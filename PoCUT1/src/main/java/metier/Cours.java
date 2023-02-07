package metier;

public class Cours {
	
	//Attribut
	int numCours;
	String nomCours;
	String salleCours;
	
	//Constructor
	public Cours(int numCours, String nomCours, String salleCours) {
		super();
		this.numCours = numCours;
		this.nomCours = nomCours;
		this.salleCours = salleCours;
	}

	//Getter and setter 
	
	public int getNumCours() {
		return numCours;
	}

	public void setNumCours(int numCours) {
		this.numCours = numCours;
	}

	public String getNomCours() {
		return nomCours;
	}

	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}

	public String getSalleCours() {
		return salleCours;
	}

	public void setSalleCours(String salleCours) {
		this.salleCours = salleCours;
	}
	

	
	
	
	

}
