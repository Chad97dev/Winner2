package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.Bd;
import metier.Justif;

/**
 * Servlet implementation class CtrlVerifierJustificatif
 */
@WebServlet("/CtrlVerifierJustificatif")
public class CtrlVerifierJustificatif extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CtrlVerifierJustificatif() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String test = request.getParameter("test");
		ArrayList<Justif> liste = new ArrayList<>();
		if (test == "test") {
			try{
				liste = Bd.listerJustif();
				if(liste.isEmpty()) {
					request.setAttribute("reponse", "la liste est vide");
					request.getRequestDispatcher("verifierJustificatif").forward(request, response);
				}else {
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("verifierJustificatif").forward(request, response);
				}
			}
			
			catch(Exception e) {
				request.setAttribute("msg_info", e.getMessage());
				request.getRequestDispatcher("verifierJustificatif").forward(request, response);
			}
		}
	}

	
}