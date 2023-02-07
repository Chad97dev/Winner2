package ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bd.Bd;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Etudiant;

/**
 * Servlet implementation class EtudiantServlet
 */
@WebServlet("/EtudiantServlet")
public class EtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EtudiantServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Etudiant> Etudiant = new ArrayList<>();
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet resulset = null;
		 
		 
		 

		    try {
		        	connection = Bd.connection();
		        	String sql = "SELECT * FROM Etudiant WHERE NumF = 1";
		        	statement = connection.prepareStatement(sql);
		        	ResultSet resultSet = statement.executeQuery();
		        	System.out.println("conn ok" + connection);
		      while (resultSet.next()) {
		        Etudiant.add(new Etudiant(
		            resultSet.getInt("NumE"),
		            resultSet.getString("NomE"),
		            resultSet.getString("PrenomE")));
		      }
		    	
		    
		    }catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    request.setAttribute("Etudiant", Etudiant);
		    request.getRequestDispatcher("/Etudiant.jsp").forward(request, response);
		  }
		  /**
		   * 
		   * @param request
		   * @param response
		   * @throws ServletException
		   * @throws IOException
		   */
		  protected void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		    doGet(request, response);
		  }
		  }