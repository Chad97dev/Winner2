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
 *  CtrlProfil
 */
@WebServlet("/CtrlProfil")
public class CtrlProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		Integer numU = (Integer) session.getAttribute("numU");
		try {
			request.setAttribute("formation", Bd.getFormation(numU));
			request.setAttribute("tele", Bd.getTele(numU));
			request.setAttribute("adresse", Bd.getAdresse(numU));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("Profil").forward(request, response);
		}

	/**
	 * doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
