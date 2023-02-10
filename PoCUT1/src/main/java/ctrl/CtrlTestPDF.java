package ctrl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import com.itextpdf.io.*;

/**
 * Servlet implementation class CtrlTestPDF
 */
@WebServlet("/CtrlTestPDF")
public class CtrlTestPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Create Document instance.
			Document document = new Document();
		 
			//Create OutputStream instance.
			OutputStream outputStream = new FileOutputStream(new File("D:\\TestTableFile.pdf"));
		 
			//Create PDFWriter instance.
		        PdfWriter.getInstance(document, outputStream);
		 
		        //Open the document.
		        document.open();
		 
		        //Create Table object, Here 4 specify the no. of columns
		        PdfPTable pdfPTable = new PdfPTable(4);
		 
		        //Create cells
		        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Cell 1"));
		        PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Cell 2"));
		        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Cell 3"));
		        PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Cell 4"));
		 
		        //Add cells to table
		        pdfPTable.addCell(pdfPCell1);
		        pdfPTable.addCell(pdfPCell2);
		        pdfPTable.addCell(pdfPCell3);
		        pdfPTable.addCell(pdfPCell4);
		 
		        //Add content to the document using Table objects.
		        document.add(pdfPTable);
		 
		        //Close document and outputStream.
		        document.close();
		        outputStream.close();
		 
		        System.out.println("Pdf created successfully.");
		    } catch (Exception e) {
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
