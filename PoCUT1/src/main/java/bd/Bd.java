package bd;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.User;

public class Bd {
	
	
	private static String URL = "jdbc:mysql://localhost:3307/db_21915858"; 
	private static String LOGIN = "21915858";
	private static String PWD = "U02KG0";
	
	private static Connection cx;
	
	// methode connexion
	public static void connexion() throws Exception{
		// chargement du pilote

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// 
			throw new Exception("Erreur Bd.connexion() - Chargement du pilote" + e.getMessage());
		}
		//ouverture de la connexion à la BD
		try {
			cx = DriverManager.getConnection(URL, LOGIN, PWD);
			System.out.println("conn ok");
		}
		catch (SQLException sqle) {
			throw new Exception("Erreur Bd.connexion() - ouverture de la connexion" + sqle.getMessage());
		}
	}
	
	public static Connection connection() throws Exception{
		// chargement du pilote

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// 
			throw new Exception("Erreur Bd.connexion() - Chargement du pilote" + e.getMessage());
		}
		//ouverture de la connexion à la BD
		try {
			cx = DriverManager.getConnection(URL, LOGIN, PWD);
		}
		catch (SQLException sqle) {
			throw new Exception("Erreur Bd.connexion() - ouverture de la connexion" + sqle.getMessage());
		}
		return cx;
	}
	
	
	// methode test
	public static void afficherTest()  throws Exception{
		if(cx==null) {
			Bd.connexion();
		}
		
		String query = "SELECT * FROM Enseignant";
		try(PreparedStatement st = cx.prepareStatement(query)){
			try(ResultSet rs = st.executeQuery()){
				System.out.println("--------result attendu--------");
				System.out.println("--------BOUR--------");
				System.out.println("--------ANDONNOF--------");
				System.out.println("--------NAVARRE--------");
				System.out.println();
				System.out.println("--------result du test--------");
				while (rs.next()) {
					System.out.println("Nom Enseignant : " + rs.getString("NomEN"));
					
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.afficherTest - afficher des messages - " + sqle.getMessage());
		}
		
	}
	
	public static void upload(InputStream filecontent) throws Exception {
		// Ouvrir une connexion à la base de données
        if(cx==null) {
        	Bd.connexion();
        }
        
        try {
            // Préparer une requête SQL pour enregistrer le fichier dans la base de données
            String sql = "INSERT INTO `Justificatif`(`Lien`, `NumE`) VALUES (?, 21707106)";
            PreparedStatement st = cx.prepareStatement(sql);
            // statement = conn.prepareStatement(sql);
            st.setBlob(1, filecontent);

            // Exécuter la requête pour enregistrer le fichier
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion et la requête
            // DatabaseUtils.close(conn, statement, null);
        }
	}
	
	public static Blob afficherFichier() throws Exception {
		// Ouvrir une connexion à la base de données
        if(cx==null) {
        	Bd.connexion();
        }
        
        try {
            // Préparer une requête SQL pour enregistrer le fichier dans la base de données
            String sql = "SELECT Justificatif FROM `Participer` WHERE NumE=21915858 AND NumSE=2";
            PreparedStatement st = cx.prepareStatement(sql);
            // statement = conn.prepareStatement(sql);
            try {
            	ResultSet rs = st.executeQuery();
            	while (rs.next()) {
					Blob content = rs.getBlob("Justificatif");
					return content;
				}
            }catch (SQLException sqle) //deux try utiliser le même catch
			{ 
			throw new Exception ("Exception Bd.afficherFichier() - Lecture des messages - " + sqle.getMessage());
			}
            // Exécuter la requête pour enregistrer le fichier
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion et la requête
            // DatabaseUtils.close(conn, statement, null);
        }
        return null;
	}
	


	//Méthodes 
	
	//Méthode de verifications de connexion utilisateurs
	public static User verifConnexion(String type ,String mail, String mdp) throws Exception {
		boolean res = false;
		if(cx==null) {
			Bd.connexion();
		} 
		
		
		
		String query = "";
		switch (type)
		{
		case "Etudiant":
			query = "SELECT NomE, PrenomE FROM Etudiant WHERE EmailE=? AND MotPasseE=?";
			break;

		case "Enseignant":
			query = "SELECT NomEN, PrenomEN FROM Enseignant WHERE EmailEN=? AND MotPasseEN=?";
			break;
		case "Scolarite":
			query = "SELECT NomRespS, PrenomRespoS FROM Scolarite WHERE EmailS=? AND MotPasseS=?";
			break;
		}	
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, mail);
			st.setString(2, mdp);
			
			try(ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					res = true;
					return new User(rs.getString(1), rs.getString(2), res);
				}
				else {
					return new User(null, null, false);
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.verifConnexion - Verification connexion - " + sqle.getMessage());
		}
	}
	
	
	//Zone de test 
	public static void main(String[] args) {
		try {
			Bd.connexion();
			System.out.println("chargement du pilote réussi");
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve"));
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getNom());
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getPrenom());
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getConnexion());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
