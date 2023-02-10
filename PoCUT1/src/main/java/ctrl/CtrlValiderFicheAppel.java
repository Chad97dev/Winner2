package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				request.setAttribute("msg_validationFA", "La fiche d'appel a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
			else if(listeAbsent != null && listeRetard == null) {
				Bd.enregistrerFicheAppel(listeAbsent, numS, absent );
				request.setAttribute("msg_validationFA", "La fiche d'appel a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
			else if (listeAbsent == null && listeRetard != null) {
				Bd.enregistrerFicheAppel(listeRetard, numS, retard );
				request.setAttribute("msg_validationFA", "La fiche d'appel a bien été enregistrée.");
				session.removeAttribute("presents");
				session.removeAttribute("retards");
				session.removeAttribute("absents");
				request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
			}
			else {
				Bd.enregistrerFicheAppel(listeRetard, numS, retard );
				Bd.enregistrerFicheAppel(listeAbsent, numS, absent );
				request.setAttribute("msg_validationFA", "La fiche d'appel a bien été enregistrée.");
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
