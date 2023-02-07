package ctrl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Bd;

/**
 * Servlet implementation class CtrlImage
 */
@WebServlet("/CtrlImage")
public class CtrlImage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Récupérer le numéro de l'image à partir de la requête
		String imageId = request.getParameter("id");
		// Récupérer l'image à partir de la base de données en utilisant la classe Bd
		byte[] image;
		
		try {
			image = Bd.getImageData(imageId);
			
			if(image != null) {
				response.setContentType("image/jpeg");
				response.setContentLength(image.length); 

				OutputStream out = response.getOutputStream();
				out.write(image);
				out.close();
			}
			else {
				response.sendRedirect("./error.jpg");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doGet(request, response);
		}

	}
