package metier;

public class User {
	//Attributs
	
	int numU;
	String nomU;
	String prenomU;
	String typeU;
	String mailU;
	Boolean connexion;
	
	//Constructor 
	public User(int numU, String nomU, String prenomU, String typeU, String mailU, Boolean connexion) {
		this.numU = numU;
		this.nomU = nomU;
		this.prenomU = prenomU;
		this.typeU = typeU;
		this.mailU = mailU;
		this.connexion = connexion;
	}
	
	 //Getter and Setter 
	public int getNumU() {
		return numU;
	}
	public void setNumU(int numU) {
		this.numU = numU;
	}
	public String getNomU() {
		return nomU;
	}
	public void setNomU(String nomU) {
		this.nomU = nomU;
	}
	public String getPrenomU() {
		return prenomU;
	}
	public void setPrenomU(String prenomU) {
		this.prenomU = prenomU;
	}
	public String getTypeU() {
		return typeU;
	}
	public void setTypeU(String typeU) {
		this.typeU = typeU;
	}
	public String getMailU() {
		return mailU;
	}
	public void setMailU(String mailU) {
		this.mailU = mailU;
	}
	public Boolean getConnexion() {
		return connexion;
	}
	public void setConnexion(Boolean connexion) {
		this.connexion = connexion;
	}
	
	
	
	

}
