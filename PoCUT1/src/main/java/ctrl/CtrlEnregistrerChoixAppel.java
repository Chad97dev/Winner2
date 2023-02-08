package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CtrlEnregistrerChoixAppel
 */
@WebServlet("/CtrlEnregistrerChoixAppel")
public class CtrlEnregistrerChoixAppel extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		// Récupérer les choix de l'utilisateur pour les checkbox
		String[] presents = (String[]) request.getParameterValues("present");
		String[] retards = (String[]) request.getParameterValues("retard");
		String[] absents = (String[]) request.getParameterValues("absent");

		// Stocker les choix de l'utilisateur pour les checkbox dans la session
		session.setAttribute("presents", presents);
		session.setAttribute("retards", retards);
		session.setAttribute("absents", absents);
		request.getRequestDispatcher("EmploiDuTemps").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
