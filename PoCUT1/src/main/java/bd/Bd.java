package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import metier.Cours;
import metier.Etudiant;
import metier.Seance;
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
		}
		catch (SQLException sqle) {
			throw new Exception("Erreur Bd.connexion() - ouverture de la connexion" + sqle.getMessage());
		}
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

	//Zone de test 
	public static void main(String[] args) {
		try {
			Bd.connexion();
			System.out.println("chargement du pilote réussi");
			String type = Bd.verifTypeUser("raphael.bour@ut-capitole.fr","raphael");
			System.out.println(Bd.verifConnexion(type,"raphael.bour@ut-capitole.fr","raphaela"));
			System.out.println(Bd.verifConnexion(type, "raphael.bour@ut-capitole.fr","raphaela").getNomU());
			System.out.println(Bd.verifConnexion(type, "raphael.bour@ut-capitole.fr","raphaela").getPrenomU());
			System.out.println(Bd.verifConnexion(type, "raphael.bour@ut-capitole.fr","raphaela").getMailU());
			System.out.println(Bd.verifConnexion(type, "raphael.bour@ut-capitole.fr","raphaela").getTypeU());
			System.out.println(Bd.verifConnexion(type, "raphael.bour@ut-capitole.fr","raphaela").getConnexion());
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println(Bd.verifTypeUser("raphael.bour@ut-capitole.fr","raphaela"));
			System.out.println("-------------------------------------------------------------------------------------------");
			for(Etudiant e : Bd.listeEtudiant("1")) {
				System.out.println("numE : " + e.getNumU() + " NomE : " + e.getNomU() + " PrenomE : " + e.getPrenomU() + " TypeU : " + e.getTypeU() + " Mail : " + e.getMailU() + " connexion : " + e.getConnexion() + " TypeE : " + e.getTypeE() + " numTel : " + e.getNumTel() + " adresse : " + e.getAdresse());
			}
			String[] abs = {"20911234"};
			String numS = "1";
			String typeU ="Present";
			//Bd.enregistrerFicheAppel(abs, numS, typeU);
			Bd.updateValidationFicheAppel(numS);
			System.out.println(Bd.getFormation(numS));
			System.out.println("-------------------------------------------------------------------------------------------");
			for(String e : Bd.getAbsentValidee("1")) {
				System.out.println(e);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	//Méthodes 

	//Méthode de verifications du type d'utilsateur 
	public static String verifTypeUser(String mail, String mdp) throws Exception {
		String res = "false";
		if(cx==null) {
			Bd.connexion();
		} 

		String query = "SELECT C.Type FROM Connexion C WHERE C.Email=? AND C.MotDePasse=?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, mail);
			st.setString(2, mdp);

			try(ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					res = rs.getString(1);
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.verifTypeUser - Verification type user - " + sqle.getMessage());
		}
		return res;
	}

	public static User verifConnexion(String type, String mail, String mdp) throws Exception {
		String res = "false";
		if(cx==null) {
			Bd.connexion();
		} 
		String query = "";

		switch(type){
		case "Etudiant" : 
			query = "SELECT E.NumE, E.NomE, E.PrenomE, C.Type, C.Email FROM Connexion C, Etudiant E WHERE C.Email = E.EmailE AND C.Email=? AND C.MotDePasse=?";
			break;
		case "Enseignant" :
			query = "SELECT E.NumEN, E.NomEN, E.PrenomEN, C.Type, C.Email FROM Connexion C, Enseignant E WHERE C.Email = E.EmailEn AND C.Email=? AND C.MotDePasse=?";
			break;
		case "Scolarite" : 
			query = "SELECT S.NumS, S.NomRespS, S.PrenomRespoS, C.Type, C.Email FROM Connexion C, Scolarite S WHERE C.Email = S.EmailS AND C.Email=? AND C.MotDePasse=?";
			break;
		case "false" :
			query = "SELECT C.Type FROM Connexion C WHERE C.Email=? AND C.MotDePasse=?";
		}

		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, mail);
			st.setString(2, mdp);

			try(ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					return new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), true); 
				}
				else return new User(0,null,null,null,null,false); 
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.verifConnexion - Verification connexion - " + sqle.getMessage());
		}

	}

	//Méthode qui retourne l'emploi du temps d'un prof
	public static LinkedHashMap<Cours, Seance> edt(String numE, String numSemaine) throws Exception {
		LinkedHashMap<Cours, Seance> seances = new LinkedHashMap<>();
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT S.NumSE, S.DateSE, S.HeureDebutSE, S.HeureFinSE, S.NumeroSemaine, C.NumCO, C.NomCO, C.SalleCO FROM Seance S, Enseignant E, Cours C WHERE E.NumEN = S.NumEN AND C.NumCO = S.NumCO AND E.NumEN=? AND S.NumeroSemaine =? ORDER BY S.DateSE ASC, S.HeureDebutSE ASC";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numE);
			st.setString(2, numSemaine);
			try(ResultSet rs = st.executeQuery()){
				while (rs.next()) {
					seances.put(new Cours(rs.getInt(6),rs.getString(7), rs.getString(8)),new Seance(rs.getInt(1),rs.getDate(2),rs.getTime(3),rs.getTime(4),rs.getInt(5)));
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.edt - afficher les séances - " + sqle.getMessage());
		}
		return seances;
	}

	//Methode qui retourne la liste des etudiants participant à une séance de cours

	public static ArrayList<Etudiant> listeEtudiant(String numSeance) throws Exception {
		ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT E.* FROM Etudiant E, Participer P, Seance S, Enseignant EN WHERE E.NumE = P.NumE AND P.NumSE = S.NumSE AND S.NumEN = EN.NumEN AND S.NumSE=?";

		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numSeance);
			try(ResultSet rs = st.executeQuery()){
				while (rs.next()) {
					listeEtudiants.add(new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3),"Etudiant", rs.getString(9), true , rs.getString(4),rs.getBlob(5), rs.getString(6), rs.getString(7)));
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.listeEtudiant - afficher les séances - " + sqle.getMessage());
		}
		return listeEtudiants;	
	}

	public static byte[] getImageData(String numU) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT PhotoLienE FROM Etudiant WHERE NumE=?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numU);
			try(ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					return rs.getBytes(1);
				}
				else {
					return null;
				}
			}catch(Exception sqle) {
				throw new Exception("Exception Bd.getImageData - afficher l'image - " + sqle.getMessage());
			}
		}
	}

	public static int enregistrerFicheAppel(String[] listeAbsents, String numS, String typeUpdate) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		String query = "UPDATE Participer P JOIN Seance S ON S.NumSE = P.NumSE JOIN Etudiant E ON E.NumE = P.NumE SET P.EtatEtu = ?, S.validee = 'Oui' WHERE P.NumE = ? AND S.NumSE = ?";
		int nb = 0;
		try(PreparedStatement st = cx.prepareStatement(query)){

			for(int i = 0 ; i<listeAbsents.length; i++) {
				st.setString(1, typeUpdate);
				st.setString(2, listeAbsents[i]);
				st.setString(3, numS);
				nb = st.executeUpdate();
			}	
		}
		catch(SQLException sqle) {
			throw new Exception ("Exception bd.enregistrerFicheAppel - probleme a l'Update - " + sqle.getMessage());

		}
		return nb;
	}

	public static String verifValidationFicheAppel(String numS) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT validee from Seance where NumSE = ?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numS);
			try(ResultSet rs = st.executeQuery()){
				rs.next();
				return rs.getString(1);
			}
		}
		catch(SQLException sqle) {
			throw new Exception ("Exception bd.verifValidationFicheAppel - probleme a l'Update - " + sqle.getMessage());

		}
	}

	public static void updateValidationFicheAppel(String numS) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		int nb = 0;
		String query = "UPDATE Seance SET validee = 'Oui' WHERE NumSE = ?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numS);
			nb = st.executeUpdate();
		}
		catch(SQLException sqle) {
			throw new Exception ("Exception bd.updateFicheAppel - probleme a l'Update - " + sqle.getMessage());

		}
	}

	public static String getFormation(String numS) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		String query = "Select DISTINCT F.NomF, F.NiveauF, F.StatusF, C.NomCO FROM Cours C, Formation F, Etudiant E, Participer P, Seance S WHERE S.NumSE = P.NumSE AND P.NumE = E.NumE AND E.NumF = F.NumF AND S.NumCO = C.NumCO AND S.NumSE=? ";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numS);
			try(ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					return rs.getString(4) + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
					}
				else { 
					return "Il n'y a pas de formation disponible";
					}
				}
		}
		catch(SQLException sqle) {
			throw new Exception ("Exception bd.getFormation - probleme au retour des formations - " + sqle.getMessage());

		}}

	public static ArrayList<String> getRetardValidee(String numS) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		ArrayList<String> listeEtudiants = new ArrayList<>();
		String query = "SELECT E.NumE FROM Etudiant E, Participer P, Seance S WHERE E.NumE = P.NumE AND P.NumSE = S.NumSE AND P.NumSE=? AND P.EtatEtu = 'Retard'";

		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numS);
			try(ResultSet rs = st.executeQuery()){
				while (rs.next()) {
					listeEtudiants.add(rs.getString(1));
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.getRetardValidee - afficher les séances - " + sqle.getMessage());
		}
		return listeEtudiants;	
	}

	public static ArrayList<String> getAbsentValidee(String numS) throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		ArrayList<String> listeEtudiants = new ArrayList<>();
		String query = "SELECT E.NumE FROM Etudiant E, Participer P, Seance S WHERE E.NumE = P.NumE AND P.NumSE = S.NumSE AND P.NumSE=? AND P.EtatEtu = 'Absent'";

		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numS);
			try(ResultSet rs = st.executeQuery()){
				while (rs.next()) {
					listeEtudiants.add(rs.getString(1));
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.getAbsentValidee - afficher les séances - " + sqle.getMessage());
		}
		return listeEtudiants;	
	}
















}
