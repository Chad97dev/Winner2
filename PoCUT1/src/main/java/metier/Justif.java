package metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class Justif {

	private long numE;
	private String nomE;
	private String prenomE;
	private Date dateSe;
	private String etatE;
    private String doc;
    
	public Justif(long numE, String nomE, String prenomE, Date dateSe, String etatE, String doc) {
		super();
		this.numE = numE;
		this.nomE = nomE;
		this.prenomE = prenomE;
		this.dateSe = dateSe;
		this.etatE = etatE;
		this.doc = doc;
	}

	public long getNumE() {
		return numE;
	}

	public void setNumE(long numE) {
		this.numE = numE;
	}

	public String getNomE() {
		return nomE;
	}

	public void setNomE(String nomE) {
		this.nomE = nomE;
	}

	public String getPrenomE() {
		return prenomE;
	}

	public void setPrenomE(String prenomE) {
		this.prenomE = prenomE;
	}

	public Date getDateSe() {
		return dateSe;
	}

	public void setDateSe(Date dateSe) {
		this.dateSe = dateSe;
	}

	public String getEtatE() {
		return etatE;
	}

	public void setEtatE(String etatE) {
		this.etatE = etatE;
	}

	public String getDoc() {
		return doc;
	}	

	public void setDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public String toString() {
		return "Justif [numE=" + numE + ", nomE=" + nomE + ", prenomE=" + prenomE + ", dateSe=" + dateSe + ", etatE="
				+ etatE + ", doc=" + doc + "]";
	}
    
    
	
}
