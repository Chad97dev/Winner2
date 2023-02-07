package ctrl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CtrlAfficher
 */
@WebServlet("/CtrlAfficher")
public class CtrlAfficher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		InputStream inputStream = null;
		ServletOutputStream outputStream = null;

		try {
		  connection = bd.Bd.connection();
		  statement = connection.prepareStatement("SELECT PhotoLienE FROM Etudiant WHERE NumE=20911234");
		  resultSet = statement.executeQuery();
		  while (resultSet.next()) {
		    inputStream = resultSet.getBinaryStream("PhotoLienE");
		    
		    FileOutputStream out = new FileOutputStream("C:/uploads");
		    byte[] buffer = new byte[1024];
		    int bytesRead;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		      out.write(buffer, 0, bytesRead);
		    }
		    request.getRequestDispatcher("Lire").forward(request, response);
		  }
		} catch (Exception e) {
		  e.printStackTrace();
		} finally {
		  if (outputStream != null) {
		    outputStream.close();
		  }
		  if (inputStream != null) {
		    inputStream.close();
		  }
		  if (resultSet != null) {
		    //resultSet.close();
		  }
		  if (statement != null) {
		    //statement.close();
		  }
		  if (connection != null) {
		    //connection.close();
		  }}
	}

	/**
	 *doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
