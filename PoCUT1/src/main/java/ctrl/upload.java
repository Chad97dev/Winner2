package ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("/webapp/deposer.jsp").forward(request, response);
        }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer le fichier envoyé via le formulaire d'upload
    	/*InputStream inputStream = null;
    	long fileSize = filePart.getSize();
    	String fileContent = filePart.getContentType();
        //Part filePart = request.getPart("file");
        inputStream = filePart.getInputStream(); */

        // Connexion à la base de données
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            // Ouvrir une connexion à la base de données
            conn = bd.Bd.connection();
            System.out.println("conn ok" + conn);
            
            Part filePart = request.getPart("file");
        	
            InputStream inputStream = null;
            if(filePart != null) {
            	long fileSize = filePart.getSize();
            	String fileContent = filePart.getContentType();
                inputStream = filePart.getInputStream();
            }
           

            // Préparer une requête SQL pour enregistrer le fichier dans la base de données
            String sql = "INSERT INTO Participer(NumE, NumSE, EtatEtu, Justificatif) VALUES (?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 21915858);
            statement.setInt(2, 3);
            statement.setString(3, "");
            statement.setBlob(4, inputStream);
            
            /*int returCode = statement.executeUpdate();
            if(returCode == 0) {
            	request.setAttribute("Message", "Error inserting file");
            }else {
            	request.setAttribute("Message", "Your record inserted success fully");
            }*/

            // Exécuter la requête pour enregistrer le fichier
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }}
