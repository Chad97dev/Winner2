package ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import bd.Bd;

/**
 * Servlet implementation class CtrlTelechargerJustificatif
 */
@WebServlet("/CtrlTelechargerJustificatif")
public class CtrlTelechargerJustificatif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlTelechargerJustificatif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bValue = request.getParameter("b");
		/*try {
			Blob b = new SerialBlob(Base64.getDecoder().decode(bValue.getBytes()));
			File outputFile = new File("C:/uploads/text.pdf");
			FileOutputStream fout = new FileOutputStream(outputFile);
			IOUtils.copy(b.getBinaryStream(), fout);
		} catch (S) {
			
			e.printStackTrace();
		}*/
		try {
			Blob blobPdf = Bd.getBlob(bValue);
			File outputFile = new File("C:/uploads/text.pdf");
			FileOutputStream fout = new FileOutputStream(outputFile);
			IOUtils.copy(blobPdf.getBinaryStream(), fout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
