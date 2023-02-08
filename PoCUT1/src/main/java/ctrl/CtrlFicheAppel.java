package ctrl;

import java.io.IOException;
import java.net.URLDecoder;

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
		String numS = request.getParameter("numS");
		String seance = URLDecoder.decode(request.getParameter("seance"), "UTF-8");
		try {
			request.setAttribute("liste", Bd.listeEtudiant(numS));
			request.setAttribute("validation", Bd.verifValidationFicheAppel(numS));
			request.setAttribute("numSeance", numS);
			request.setAttribute("seance", seance);
			request.getRequestDispatcher("FicheAppel").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
