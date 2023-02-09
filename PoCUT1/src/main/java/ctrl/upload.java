package ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.text.SimpleDateFormat;
import java.util.*;

import bd.Bd;

@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.getWriter().append("Served at: ").append(request.getContextPath());
        }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//récupérer les attributs dans la session
        HttpSession session = request.getSession(true);
        String[] listeSeancesChoisies = (String[]) session.getAttribute("listeSeancesChoisies");
        Integer numU = (Integer) session.getAttribute("numU");
        String nomU = (String) session.getAttribute("nom");
        String prenomU = (String) session.getAttribute("prenom");
        
        //generer un ID pour justificatif
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String idJP = df.format(date);
        String idJ = nomU + " " + prenomU + " " + idJP;
        
        //récupérer le fichier 
        Part filePart = request.getPart("file");
        InputStream inputStream = null;
        if(filePart != null) {
        	long fileSize = filePart.getSize();
        	String fileContent = filePart.getContentType();
        	System.out.println("fileContent :"+fileContent+"!");
        	
        	//判断文件类型
        	if(fileContent.equals("application/pdf")) {
        		//判断文件大小
        		System.out.println("fileSize "+fileSize);
        		if(fileSize < 1048576) {
        				
                           	//insérer justificatif
					        for (String s : listeSeancesChoisies) {		
					        	Integer numSe=Integer.parseInt(s);
					        	inputStream = filePart.getInputStream();
					        	
						        try {Bd.deposerJus(numU, numSe, inputStream, idJ);
						        inputStream.close();
						        String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
						        request.getRequestDispatcher("CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in").forward(request, response);
						        }
						        catch (Exception e) {
						        	e.printStackTrace();
						        	request.setAttribute("msg_error", "dépot échoué");
						            request.getRequestDispatcher("Deposer").forward(request, response);} 
					        }
        		}else {
        			//文件太大
        			request.setAttribute("msg_error", "La taille du fichier est trop élévée pour être déposé");
		            request.getRequestDispatcher("Deposer").forward(request, response);
        		}
        		
        	}else {
        		//请提交PDF
        		request.setAttribute("msg_error", "Veuillez choisir un fichier pdf");
	            request.getRequestDispatcher("Deposer").forward(request, response);
        	}
        	
	        
	    }else {
    		//请提交PDF
    		request.setAttribute("msg_error", "Veuillez choisir un fichier pdf");
            request.getRequestDispatcher("Deposer").forward(request, response);
    	}
        
        
}}