package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  CtrlCfrmAbs
 */
@WebServlet("/CtrlCfrmAbs")
public class CtrlCfrmAbs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String[] listeSeancesChoisies = request.getParameterValues("cb_seance");
		if(listeSeancesChoisies != null) {
			session.setAttribute("listeSeancesChoisies", listeSeancesChoisies);
			request.getRequestDispatcher("CfrmDepot").forward(request, response);
		}else {
			request.setAttribute("msg_info", "Veuillez choisir au mois une séance !");
			request.getRequestDispatcher("AcceuilEtudiant").forward(request, response);
		}
	}

	/**
	 * doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
