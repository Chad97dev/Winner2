package ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bd.Bd;

/**
 * Servlet implementation class CtrlUploadImg
 */
@WebServlet("/CtrlUploadImg")
@MultipartConfig
public class CtrlUploadImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	response.getWriter().append("Served at: ").append(request.getContextPath());
	        }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	//récupérer les attributs dans la session
	        HttpSession session = request.getSession(true);
	        Integer numU = (Integer) session.getAttribute("numU");
	        
	        //récupérer le fichier 
	        Part filePart = request.getPart("file");
	        InputStream inputStream = null;
	        if(filePart != null) {
	        	System.out.println("fichier find");
	        	long fileSize = filePart.getSize();
	        	String fileContent = filePart.getContentType();
	        	
	        	if(fileSize < 1048576) {
	        		 inputStream = filePart.getInputStream();
	     	        
	 		        //insérer justificatif
	 		        try {Bd.deposerImg(numU, inputStream);
	 		        System.out.println("update ok");
	 		        System.out.println("aller à la page suivante");
	 		        request.getRequestDispatcher("CtrlProfil").forward(request, response);
	 		        }
	 		        catch (Exception e) {
	 		        	e.printStackTrace();
	 		        	request.setAttribute("msg_error", "dépot échoué");
	 		            request.getRequestDispatcher("Deposer").forward(request, response);} 
	        	}else {
        			request.setAttribute("msg_error", "La taille du fichier est trop élévée pour être déposé");
		            request.getRequestDispatcher("CtrlProfil").forward(request, response);
	        	}
	           
		    }else {
		    	request.setAttribute("msg_error", "veuillez choisir un photo");
	            request.getRequestDispatcher("CtrlProfil").forward(request, response);} 
		    }
	        
}