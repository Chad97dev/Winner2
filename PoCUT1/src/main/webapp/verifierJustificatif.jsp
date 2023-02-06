<%@page import="metier.Justif" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

     <table>
     	<tr>
     	    <th>Numero Etudiant</th>
     	    <th>seance</th>
     	    <th>Etat</th>
     	    <th>Justificatif</th>
     	</tr>
        <%
        	ArrayList<Justif> liste = (ArrayList<Justif>) request.getAttribute("liste");
        	for(Justif justif : liste){
        %>
          <tr>
           	  <td><%= justif.getNumE() %></td>
           	  <td><%= justif.getNumSe()%></td>
           	  <td><%= justif.getEtatE() %></td>
           	  <td><%= justif.getDoc() %></td>
          </tr>
          <%
        	}
          %>
     </table>
</body>
</html>