package metier;

import java.sql.Blob;

public class Justif {

	private long numE;
	private long numSe;
	private String etatE;
    private Blob doc;
    

	public Justif(long numE, long numSe, String etatE, Blob blob) {
		super();
		this.numE = numE;
		this.numSe = numSe;
		this.etatE = etatE;
		this.doc = blob;
	}


	public long getNumE() {
		return numE;
	}


	public void setNumE(long numE) {
		this.numE = numE;
	}


	public long getNumSe() {
		return numSe;
	}


	public void setNumSe(long numSe) {
		this.numSe = numSe;
	}


	public String getEtatE() {
		return etatE;
	}


	public void setEtatE(String etatE) {
		this.etatE = etatE;
	}


	public Blob getDoc() {
		return doc;
	}


	public void setDoc(Blob doc) {
		this.doc = doc;
	}


	@Override
	public String toString() {
		return "Justificatif [numE=" + numE + ", numSe=" + numSe + ", etatE=" + etatE + ", doc=" + doc + "]";
	}
	
	
	
	
}
