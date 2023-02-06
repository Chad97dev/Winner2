<%@page import="metier.Justif" %>
<%@page import="java.util.List" %>
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
     	    <th>Nom </th>
     	    <th>Prenom</th>
     	    <th>Date seance</th>
     	    <th>Etat absence</th>
     	    <th>Justificatif</th>
     	</tr>
        <%
        	List<Justif> liste = (List<Justif>) request.getAttribute("liste");
        	for(Justif justif : liste){
        %>
          <tr>
           	  <td><%= justif.getNumE() %></td>
           	  <td><%= justif.getNomE()%></td>
           	  <td><%= justif.getPrenomE() %></td>
           	  <td><%= justif.getDateSe() %></td>
           	  <td><%= justif.getEtatE() %></td>
           	  <td><%= justif.getDoc() %></td>
          </tr>
          <%
        	}
          %>
     </table>
</body>
</html>