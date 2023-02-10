package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javax.mail.*;

import javax.mail.internet.*;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.PasswordAuthentication;

import bd.Bd;

@WebServlet("/CtrlValiderFicheAppel")
public class CtrlValiderFicheAppel extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String numS = (String) request.getParameter("numSeance");
		String[] listeAbsent = (String[]) request.getParameterValues("absent");
		String[] listeRetard = (String[]) request.getParameterValues("retard");
		String retard = "Retard";
		String absent = "Absent";
		try {
			if(listeAbsent == null && listeRetard == null) {
				Bd.updateValidationFicheAppel(numS);
				request.setAttribute("msg_validationFA", "La fiche d'appel avec tout le monde présent a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
			else if(listeAbsent != null && listeRetard == null) {
				Bd.enregistrerFicheAppel(listeAbsent, numS, absent );
				request.setAttribute("msg_validationFA", "La fiche d'appel avec absent mais sans retard a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
				
				for(String eleveAbsent : listeAbsent) {
				    long idAbsence = Long.parseLong(eleveAbsent);
				    String mailEleves = Bd.getMail(idAbsence);
				    if (mailEleves != null) {
				        System.out.println(idAbsence);
				        System.out.println(mailEleves);
				        Bd.sendEmailEleves(mailEleves);
				    } else {
				        System.out.println("Aucun e-mail trouvé pour l'identifiant d'absence " + idAbsence);
				    }
				}
			}
			else if (listeAbsent == null && listeRetard != null) {
				Bd.enregistrerFicheAppel(listeRetard, numS, retard );
				request.setAttribute("msg_validationFA", "La fiche d'appel avec retard mais sans absents a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
			else {
				Bd.enregistrerFicheAppel(listeRetard, numS, retard );
				Bd.enregistrerFicheAppel(listeAbsent, numS, absent );
				request.setAttribute("msg_validationFA", "La fiche d'appel avec retard et absent a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
