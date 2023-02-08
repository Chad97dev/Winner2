package ctrl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Bd;
import metier.Seance;

/**
 * Servlet implementation class CtrlAbsJus
 */
@WebServlet("/CtrlAbsJus")
public class CtrlAbsJus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		HashMap<Seance, String> listeSeancesJus;
		try {
			listeSeancesJus = Bd.listeSeancesJus((int)session.getAttribute("numU"));
			if(listeSeancesJus != null) {
				session.setAttribute("listeSeancesJus", listeSeancesJus);
				request.getRequestDispatcher("listAbsJus.jsp").forward(request, response);
			}else {
				request.setAttribute("msg_info", "Veuillez choisir au mois une séance !");
				request.getRequestDispatcher("AcceuilEtudiant").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
