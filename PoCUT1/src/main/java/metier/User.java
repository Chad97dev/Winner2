package metier;

public class User {
	//Attributs
	
	String nom;
	String prenom;
	Boolean connexion;
	
	//Constructor 
	
	public User(String nom, String prenom, Boolean connexion) {
		this.nom = nom;
		this.prenom = prenom;
		this.connexion = connexion;
	}
	
	//Getter and Setter
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Boolean getConnexion() {
		return connexion;
	}
	public void setConnexion(Boolean connexion) {
		this.connexion = connexion;
	}
	

}
