package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.Bd;

/**
 * Servlet implementation class CtrlDecisionJustificatif
 */
@WebServlet("/CtrlDecisionJustificatif")
public class CtrlDecisionJustificatif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlDecisionJustificatif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String decision = request.getParameter("decision");
		String numE = request.getParameter("numE");
		String numSe = request.getParameter("numSE");
		
		
		try {
			long longNumE = Long.parseLong(numE);
			long longNumSe = Long.parseLong(numSe);
			Bd.validationJustificatif("valide", longNumE, longNumSe);
			System.out.println(decision);
			//request.getRequestDispatcher("verifierJustificatif").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			//request.getRequestDispatcher("verifierJustificatif").forward(request, response);
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
