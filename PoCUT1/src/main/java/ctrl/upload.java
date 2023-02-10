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
        String mailU = (String) session.getAttribute("email");
        String pwd = (String) session.getAttribute("pwd");
        System.out.println("attribute ok");
        
        //generer un ID pour justificatif
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String idJP = df.format(date);
        String idJ = nomU + " " + prenomU + " " + idJP;
        System.out.println("idJ ok");
        
        //récupérer le fichier 
        Part filePart = request.getPart("file");
        InputStream inputStream = null;
        if(filePart != null) {
        	System.out.println("fichier find");
        	long fileSize = filePart.getSize();
        	String fileContent = filePart.getContentType();
            inputStream = filePart.getInputStream();
        
	        //insérer justificatif
	        for (String s : listeSeancesChoisies) {		
	        	Integer numSe=Integer.parseInt(s);
	        	System.out.println("attribute" + numU + numSe + idJ);
	        	
		        try {Bd.deposerJus(numU, numSe, inputStream, idJ);
		        System.out.println("update ok");
		        System.out.println("aller à la page suivante");
		        inputStream.close();
		        Bd.envoyerMail(mailU, pwd, prenomU, nomU);
		        String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
		        request.getRequestDispatcher("CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in").forward(request, response);
		        }
		        catch (Exception e) {
		        	e.printStackTrace();
		        	request.setAttribute("msg_error", "dépot échoué");
		            request.getRequestDispatcher("Deposer").forward(request, response);} 
	        }
	        
	    }
        
        
}}