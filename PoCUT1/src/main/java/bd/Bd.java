package bd;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import metier.Cours;
import metier.Etudiant;
import metier.Seance;
import metier.Justif;
import metier.User;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;

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
	//Zone de test 
	public static void main(String[] args) throws Exception {
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
			/*System.out.println("chargement du pilote réussi");
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve"));
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getNom());
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getPrenom());
			System.out.println(Bd.verifConnexion("Scolarite","genevieve.labrousse01@gmail.com","genevieve").getConnexion());*/
			List<Justif> liste = Bd.listerJustif();
			System.out.println(liste);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("liste seances");
		//System.out.println(Bd.listeSeance());
		
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
			if(cx==null) {
				Bd.connexion();
			} 
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
		String query = "SELECT S.NumSE, S.DateSE, S.HeureDebutSE, S.HeureFinSE, S.NumeroSemaine, C.NumCO, C.NomCO, C.SalleCO FROM Seance S, Enseignant E, Cours C WHERE E.NumEN = S.NumEN AND C.NumCO = S.NumCO AND E.NumEN=? AND S.NumeroSemaine =? ORDER BY S.DateSE DESC, S.HeureFinSE DESC";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setString(1, numE);
			st.setString(2, numSemaine);
			try(ResultSet rs = st.executeQuery()){
				while (rs.next()) {
					seances.put(new Cours(rs.getInt(6),rs.getString(7), rs.getString(8)),new Seance(rs.getInt(1),rs.getDate(2),rs.getTime(3),rs.getTime(4),rs.getInt(5)));
				}
			}
		}catch(Exception sqle) {
			throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
		}
		return seances;
	}

	public static HashMap<Seance, String> listeSeance(Integer numU) throws Exception {
		HashMap<Seance, String> listeSeances = new HashMap<>();
		//Integer numU = 22006489;
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT s.NumSE, s.DateSE, s.HeureDebutSE, s.HeureFinSE, s.NumeroSemaine, s.NumCO, c.NomCO, c.SalleCO,  p.EtatJ FROM Participer p, Seance s, Cours c WHERE p.EtatEtu = 'Absent' AND p.NumE=? AND p.NumSE = s.NumSE AND c.numCO = s.NumCO";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				Boolean existe = false;
				while(rs.next()) {
					existe = true;
					Seance se = new Seance(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getTime(4), rs.getInt(5));
					se.setCours(new Cours(rs.getInt(6), rs.getString(7), rs.getString(8)));
					listeSeances.put(se, rs.getString(9));
				}
				if(!existe) {
					listeSeances = null;
				}
			}
		}catch(Exception sqle){
			throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
		}
		return listeSeances;
		
	}
	
	//méthode pour obtenir formation d'un étudiant
	public static String getFormation(Integer numU) throws Exception {
		String formation = null;
		//ouverture de connexion
		if(cx==null) {
			Bd.connexion();
		}
		
		//requête
		String query = "SELECT f.NomF FROM Formation f, Etudiant e WHERE e.NumF=f.NumF AND e.NumE=?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					formation = rs.getString(1);
				}
			}catch(Exception sqle){
				throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
			}
		return formation;
	}}
	
	//méthode pour obtenir numTele d'un étudiant
	public static String getTele(Integer numU) throws Exception {
		String tele = null;
		//ouverture de connexion
		if(cx==null) {
			Bd.connexion();
		}
		
		//requête
		String query = "SELECT NumTel FROM Etudiant WHERE NumE=?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					tele = rs.getString(1);
				}
			}catch(Exception sqle){
				throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
			}
		return tele;
	}}
	
	//méthode pour obtenir numTele d'un étudiant
	public static String getAdresse(Integer numU) throws Exception {
		String adresse = null;
		//ouverture de connexion
		if(cx==null) {
			Bd.connexion();
		}
		
		//requête
		String query = "SELECT 	Adresse FROM Etudiant WHERE NumE=?";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					adresse = rs.getString(1);
				}
			}catch(Exception sqle){
				throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
			}
		return adresse;
	}}

	//méthode test
	public static Blob getJus() throws Exception {
		if(cx==null) {
			Bd.connexion();
		}
		//requête
		String query = "SELECT Justificatif FROM Participer WHERE NumE=21915858 AND NumSE=2";
		try(PreparedStatement st = cx.prepareStatement(query)){
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					Blob j = rs.getBlob(1);
					return j;
				}
			}catch(Exception sqle){
				throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
			}
		return null;
	}
	}
	
	public static void deposerJus(Integer numU, Integer numSe, InputStream lien, String idJ) throws Exception {
		//connexion
		if(cx==null) {
			Bd.connexion();
		}
		//requête
		String query = "UPDATE Participer SET EtatJ = \"Invalide\", IdJ = ?, Justificatif = ? WHERE NumE = ? AND NumSE = ?;";
		try(PreparedStatement st = cx.prepareStatement(query)){
            st.setString(1, idJ);
            st.setBinaryStream(2, lien);
            st.setInt(3, numU);
            st.setInt(4, numSe);
            
            //execute
			st.executeUpdate();
			}catch(Exception sqle){
				throw new Exception("Exception Bd.deposerJus - deposer un justificatif - " + sqle.getMessage());
			}
		System.out.println("inserer ok");
	
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
			throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
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
	
	public static HashMap<Seance, String> listeSeancesInjus(Integer numU) throws Exception {
		HashMap<Seance, String> listeSeances = new HashMap<>();
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT s.NumSE, s.DateSE, s.HeureDebutSE, s.HeureFinSE, s.NumeroSemaine, s.NumCO, c.NomCO, c.SalleCO,  p.EtatJ FROM Participer p, Seance s, Cours c WHERE p.EtatEtu = 'Absent'AND p.Justificatif IS NULL AND p.NumE=? AND p.NumSE = s.NumSE AND c.numCO = s.NumCO";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				Boolean existe = false;
				while(rs.next()) {
					existe = true;
					Seance se = new Seance(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getTime(4), rs.getInt(5));
					se.setCours(new Cours(rs.getInt(6), rs.getString(7), rs.getString(8)));
					listeSeances.put(se, rs.getString(9));
				}
				if(!existe) {
					listeSeances = null;
				}
			}
		}catch(Exception sqle){
			throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
		}
		return listeSeances;
		
	}
	
	public static HashMap<Seance, String> listeSeancesJus(Integer numU) throws Exception {
		HashMap<Seance, String> listeSeances = new HashMap<>();
		if(cx==null) {
			Bd.connexion();
		}
		String query = "SELECT s.NumSE, s.DateSE, s.HeureDebutSE, s.HeureFinSE, s.NumeroSemaine, s.NumCO, c.NomCO, c.SalleCO,  p.EtatJ FROM Participer p, Seance s, Cours c WHERE p.EtatEtu = 'Absent'AND p.Justificatif<>\"\" AND p.NumE=? AND p.NumSE = s.NumSE AND c.numCO = s.NumCO";
		try(PreparedStatement st = cx.prepareStatement(query)){
			st.setInt(1, numU);
			try(ResultSet rs = st.executeQuery()){
				Boolean existe = false;
				while(rs.next()) {
					existe = true;
					Seance se = new Seance(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getTime(4), rs.getInt(5));
					se.setCours(new Cours(rs.getInt(6), rs.getString(7), rs.getString(8)));
					listeSeances.put(se, rs.getString(9));
				}
				if(!existe) {
					listeSeances = null;
				}
			}
		}catch(Exception sqle){
			throw new Exception("Exception Bd.afficherTest - afficher les séances - " + sqle.getMessage());
		}
		return listeSeances;
		
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
			throw new Exception ("Exception bd.enregistrerFicheAppel - probleme a l'Update - " + sqle.getMessage());

		}
	}

	
		public static List<Justif> listerJustif() throws Exception{
			if(cx==null) {
				Bd.connexion();
			}

			//String sql = "SELECT E.NumE ,E.NomE, E.PrenomE, S.DateSE, P.EtatEtu, P.Justificatif  FROM Participer P, Seance S, Etudiant E  WHERE P.NumE = E.NumE AND S.NumSE = P.NumSE AND P.EtatEtu ='Absent' AND P.Justificatif IS NOT NULL AND EtatJ IS NULL";
			String sql = "SELECT E.NumE ,E.NomE, E.PrenomE, S.DateSE, P.EtatEtu, P.IdJ, P.NumSE  FROM Participer P, Seance S, Etudiant E  WHERE P.NumE = E.NumE AND S.NumSE = P.NumSE AND P.EtatEtu ='Absent' AND P.Justificatif IS NOT NULL AND EtatJ IS NULL";
			
			ArrayList<Justif> liste = new ArrayList<>();
			
			try(PreparedStatement st = cx.prepareStatement(sql))
			  {
				try (ResultSet rs = st.executeQuery())
	                { 
		             while(rs.next())
		              {
			            Justif j = new Justif(rs.getLong(1), rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getLong(7));
			            liste.add(j);
		                }
		
		                return liste;
	                    }
				        }
				
				catch(SQLException sqle) {
					throw new Exception("Exception bd.listerJustif() -  Lecture des messages - "+ sqle.getMessage());
			       }
		
		}
	
	public static Blob getBlob(String id) throws Exception {
		
		Blob b = null;
		if(cx==null) {
			Bd.connexion();
		}
		String sql = "SELECT Justificatif FROM Participer WHERE IdJ =?";
		
		try(PreparedStatement st = cx.prepareStatement(sql)){
			st.setString(1, id);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					Blob j = rs.getBlob(1);
					return j;
				}
			}catch(Exception sqle) {
				throw new Exception("Exception Bd.getBlob - blob -" + sqle.getMessage());
			}
		return null;
		}
		
	}
	
	public static void envoyerMail(String mail, String mdp, String prenom, String nom) throws Exception {
		
		 Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp-mail.outlook.com");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 Authenticator auth = new Authenticator() {
		 protected PasswordAuthentication getPasswordAuthentication() {
		 return new PasswordAuthentication(mail, mdp);
		 }
		 };
		 Session session = Session.getInstance(props, auth);
		
		 try {
		 MimeMessage message = new MimeMessage(session);
		 message.setFrom(new InternetAddress(mail));
		 message.addRecipient(Message.RecipientType.TO, new InternetAddress("genevieve.labrousse01@outlook.fr"));
		 message.setSubject("Justificatif à valider");
		 message.setText("L'etudiant "+ nom + " " + prenom + " a deposé un justificatif pour validation.");
		
		 Transport.send(message);
		
		 } catch (MessagingException e) {e.printStackTrace();}
		 }
	
	
	public static void validationJustificatif(String decision, long numE, String idJ) throws Exception {
		System.out.println(decision);
		if(cx==null) {
			Bd.connexion();
		}
		
		if(decision.equals("Valide")) {
			
			String sql = "UPDATE Participer SET EtatJ ='Valide' WHERE NumE=? AND Idj=?";
			try(PreparedStatement st = cx.prepareStatement(sql)){
				st.setLong(1, numE);
				st.setString(2, idJ);
				st.executeUpdate();
				System.out.println(st);
			}
			
		} else if(decision.equals("Invalide")) {
			String sql = "UPDATE Participer SET EtatJ ='Invalide' WHERE NumE=? AND Idj=?";
			try(PreparedStatement st = cx.prepareStatement(sql)){
				st.setLong(1, numE);
				st.setString(2, idJ);
				st.executeUpdate();
			}
		}
	}



}
