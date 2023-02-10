package ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.http.fileupload.IOUtils;

import bd.Bd;
import metier.Justif;

/**
 * Servlet implementation class CtrlTelechargerJustificatif
 */
@WebServlet("/CtrlTelechargerJustificatif")
public class CtrlTelechargerJustificatif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlTelechargerJustificatif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bValue = request.getParameter("b");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date = request.getParameter("date");
		System.out.println(date);
		try {
			Blob blobPdf = Bd.getBlob(bValue);
			File outputFile = new File("C:/uploads/"+nom+ prenom+date+".pdf");
			FileOutputStream fout = new FileOutputStream(outputFile);
			IOUtils.copy(blobPdf.getBinaryStream(), fout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 try{
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
