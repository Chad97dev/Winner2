package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bd.Bd;
import metier.User;

/**
 * Servlet implementation class CtrlConnexion
 */
@WebServlet("/CtrlConnexion")
public class CtrlConnexion extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String mail = request.getParameter("username");
		String PWD = request.getParameter("password");

		try {
			User utilisateur = Bd.verifConnexion(type, mail, PWD);
			if(utilisateur.getConnexion() == true) {
				HttpSession session = request.getSession();
				session.setAttribute("email", mail);
				session.setAttribute("nom", utilisateur.getNom());
				session.setAttribute("prenom", utilisateur.getPrenom());
				switch (type)
				{
				case "Etudiant":
					request.getRequestDispatcher("AcceuilEtudiant").forward(request, response);
					break;

				case "Enseignant":
					request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
					break;
				case "Scolarite":
					request.getRequestDispatcher("AcceuilScolarite").forward(request, response);
					break;
				}
			}
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
