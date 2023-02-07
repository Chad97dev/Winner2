package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Bd;

/**
 * Servlet implementation class CtrlListeEtudiants
 */
@WebServlet("/CtrlListeEtudiants")
public class CtrlListeEtudiants extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String numS = request.getParameter("numS");
		try {
			request.setAttribute("liste", Bd.listeEtudiant(numS));
			request.getRequestDispatcher("ListeEtudiant").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
