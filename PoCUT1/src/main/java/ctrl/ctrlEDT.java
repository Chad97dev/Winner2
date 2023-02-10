package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Bd;
import metier.Cours;
import metier.Seance;

@WebServlet("/ctrlEDT")
public class ctrlEDT extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int numE = (int) session.getAttribute("numU");
		String numSemaine = request.getParameter("numSemaine");
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
		{
			/*----- Ecriture de la page XML -----*/
			out.println("<?xml version=\"1.0\"?>");
			out.println("<liste_seances>");
			/*----- Récupération des paramètres -----*/
			try {
				LinkedHashMap<Cours, Seance> seances = Bd.edt(numE+ "", numSemaine);
				for (Entry<Cours, Seance> s : seances.entrySet()) {
					out.println("<seance>" + s.getValue().getDateSeance()+ "  " + s.getValue().getHeureDebutSeance() + " " + s.getValue().getHeureFinSeance() + "  " + s.getKey().getNomCours() + "  " + s.getKey().getSalleCours()+ "</seance>");
					out.println("<numSeance>" + s.getValue().getNumSeance()+ "</numSeance>");
					out.println("<dateSeance>" + s.getValue().getDateSeance()+ "</dateSeance>");
					out.println("<cours>" + s.getKey().getNomCours().replace(" ", "-")+ "</cours>");
					out.println("<heureDebSeance>" + s.getValue().getHeureDebutSeance() + "</heureDebSeance>");
					out.println("<heureFinSeance>" + s.getValue().getHeureFinSeance() + "</heureFinSeance>");
				}
			} catch (Exception e) { 
				// TODO Auto-generated catch block
				out.println("<seance>Erreur - " + e.getMessage() + "</seance>");
			}
			out.println("</liste_seances>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
