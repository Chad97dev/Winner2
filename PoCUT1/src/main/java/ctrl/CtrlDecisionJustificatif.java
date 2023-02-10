package ctrl;


import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bd.Bd;
import metier.Justif;


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
   	 String idJ = request.getParameter("idJ");
   	 
   	 try {
   		 long longNumE = Long.parseLong(numE);
   		 Bd.validationJustificatif(decision, longNumE, idJ);
   		 System.out.println(decision);
   		 //request.getRequestDispatcher("verifierJustificatif").forward(request, response);
   	 } catch (Exception e) {
   		 // TODO Auto-generated catch block
   		 
   		 e.printStackTrace();
   		 //request.getRequestDispatcher("verifierJustificatif").forward(request, response);
   	 }
   	 
   	 try{
   		 Bd.connexion();
   		 List<Justif> liste =  Bd.listerJustif();
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


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 // TODO Auto-generated method stub
   	 doGet(request, response);
    }


}






