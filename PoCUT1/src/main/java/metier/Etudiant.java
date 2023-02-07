package metier;

import java.sql.Blob;

public class Etudiant extends User{
	
	
	//Attribus 
	String typeE;
	Blob photoLienE;
	String numTel;
	String adresse;
	
	//Constructor 
	
	public Etudiant(int numU, String nomU, String prenomU, String typeU, String mailU, Boolean connexion, String typeE,
			Blob photoLienE, String numTel, String adresse) {
		super(numU, nomU, prenomU, typeU, mailU, connexion);
		this.typeE = typeE;
		this.photoLienE = photoLienE;
		this.numTel = numTel;
		this.adresse = adresse;
	}

	public String getTypeE() {
		return typeE;
	}

	public void setTypeE(String typeE) {
		this.typeE = typeE;
	}

	public Blob getPhotoLienE() {
		return photoLienE;
	}

	public void setPhotoLienE(Blob photoLienE) {
		this.photoLienE = photoLienE;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	


}
