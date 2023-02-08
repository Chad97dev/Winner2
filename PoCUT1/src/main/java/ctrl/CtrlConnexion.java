package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bd.Bd;
import metier.Seance;
import metier.User;

/**
 * Servlet implementation class CtrlConnexion
 */
@WebServlet("/CtrlConnexion")
public class CtrlConnexion extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("username");
		String PWD = request.getParameter("password");
		String type;

		try {
			type = Bd.verifTypeUser(mail, PWD);
			User utilisateur = Bd.verifConnexion(type, mail, PWD);
			if (!type.equals("false")){
				HttpSession session = request.getSession();
				session.setAttribute("email", mail);
				session.setAttribute("numU", utilisateur.getNumU());
				session.setAttribute("nom", utilisateur.getNomU());
				session.setAttribute("typeU", utilisateur.getTypeU());
				session.setAttribute("prenom", utilisateur.getPrenomU());
				session.setAttribute("pwd", PWD);
				switch (type)
				{
				case "Etudiant":
					HashMap<Seance, String> listeSeances =  Bd.listeSeancesInjus(utilisateur.getNumU());
					session.setAttribute("listeSeances", listeSeances);
					request.getRequestDispatcher("AcceuilEtudiant").forward(request, response);
					break;
				case "Enseignant":
					request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
					break;
				case "Scolarite":
					request.getRequestDispatcher("VerifierJustificatif").forward(request, response);
					break;
				}}
			else {
			    request.setAttribute("msg_erreur", "Mot de passe ou identifiant incorrect");
			    request.getRequestDispatcher("Login").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
