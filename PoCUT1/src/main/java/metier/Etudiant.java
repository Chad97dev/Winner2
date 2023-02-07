package metier;

import java.util.ArrayList;

public class Etudiant {
		
	    private String NomE;
	    private String PrenomE;
	    private int NumE;
	    private ArrayList<Seance> listeSeances;

	    public Etudiant(int NumE, String NomE, String PrenomE) {
	        this.NumE = NumE;
	        this.NomE = NomE;
	        this.PrenomE = PrenomE;
	    }
	   
		public int getNumE() {
	        return NumE;
	    }
	    public void setNumE(int NumE) {
	        this.NumE = NumE;
	    }
	    public String getNomE() {
	        return NomE;
	    }
	    public void setNomE(String NomE) {
	        this.NomE = NomE;
	    }
	    public String getPrenomE() {
	        return PrenomE;
	    }
	    public void setPrenomE(String PrenomE) {
	        this.PrenomE = PrenomE;
	    }
	    public ArrayList<Seance> getListeSeances() {
			return listeSeances;
		}
		public void setListeSeances(ArrayList<Seance> listeSeances) {
			this.listeSeances = listeSeances;
		}

	}