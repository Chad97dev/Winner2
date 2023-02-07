package ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import bd.Bd;

/**
 * Servlet implementation class ctrlTest
 */
@WebServlet("/ctrlTest")
public class ctrlTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Blob blobPdf = Bd.getJus();
			File outputFile = new File("C:/uploads/text.pdf");
			FileOutputStream fout = new FileOutputStream(outputFile);
			IOUtils.copy(blobPdf.getBinaryStream(), fout);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
