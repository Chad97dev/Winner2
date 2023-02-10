package ctrl;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.Bd;

/**
 * Servlet implementation class CtrlFicheAppel
 */
@WebServlet("/CtrlFicheAppel")
public class CtrlFicheAppel extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numS = request.getParameter("numSeance");
		String cours = request.getParameter("cours");
		String date = request.getParameter("date");
		String heureDeb = request.getParameter("heureDeb");
		String heureFin = request.getParameter("heureFin");
		
		
		try {
			request.setAttribute("retardValidee", (ArrayList<String>) Bd.getRetardValidee(numS));
			request.setAttribute("absentValidee", (ArrayList<String>) Bd.getAbsentValidee(numS));
			request.setAttribute("nomFormation", (String) Bd.getFormation(numS));
			request.setAttribute("liste", Bd.listeEtudiant(numS));
			request.setAttribute("validation", Bd.verifValidationFicheAppel(numS));
			request.setAttribute("numSeance", numS);
			request.setAttribute("cours", cours);
			request.setAttribute("date", date);
			request.setAttribute("heureDeb", heureDeb);
			request.setAttribute("heureFin", heureFin);
			request.getRequestDispatcher("FicheAppel").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}