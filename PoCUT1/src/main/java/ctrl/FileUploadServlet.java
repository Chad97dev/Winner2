package ctrl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

    response.setContentType("text/html");
    response.getWriter().println("File uploaded successfully: " + fileName);
  }
}