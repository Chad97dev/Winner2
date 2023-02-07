package ctrl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
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

    Part filePart = request.getPart("file");
    String fileName = filePart.getSubmittedFileName();
    InputStream fileContent = filePart.getInputStream();

    File uploads = new File("C:/uploads");
    File file = new File(uploads, fileName);

    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    //récupérer le lien du fichier
    String lien = file.getAbsolutePath();
    System.out.println(lien);
    //inserer le lien dans la base de données
    Connection conn;
    String sql = "UPDATE Participer SET LienJ=? WHERE NumE=22006489 AND NumSE=2";
    try {
        conn = bd.Bd.connection();
            System.out.println("conn ok" + conn);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, lien);
        ps.executeUpdate();
    response.setContentType("text/html");
    response.getWriter().println("File uploaded successfully: " + fileName);
  }catch (SQLException e) {
      e.printStackTrace();
  }catch(Exception e){
      e.printStackTrace();
  }
}}