
<%@ page language="java" contentType="text/html; charset=gbk" import ="java.io.*,java.sql.*, bd.Bd" %>
<%
//String id = request.getParameter("ids");

//mysql����
    Connection con = Bd.connection();
    try{
// ׼�����ִ�ж���
		OutputStream outs;
		
        Statement stmt = con.createStatement();
        String sql = "SELECT PhotoLienE FROM Etudiant WHERE NumE=20911234";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            response.setContentType("image/png");
            Blob b = rs.getBlob("PhotoLienE");
            long size = b.length();
            byte[] bs = b.getBytes(1, (int)size);
            
            out.clear();
            outs = response.getOutputStream();
            
            outs.write(bs);
            
            outs.flush();
            rs.close();
         
        }
        else {
            rs.close();
            response.sendRedirect("./images/error.gif");
        }
    }
    finally{
        con.close();
    }%>