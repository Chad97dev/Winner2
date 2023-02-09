<%@page import="metier.Justif" %>
<%@page import="java.util.List" %>
<%@page import="java.sql.Blob" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Justificatifs à vérifier</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<style>
#listeJustificatif {margin-left:300px;}
</style>
</head>
<body>

<div id="listeJustificatif">

     <table border="1">
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
        if(liste != null){
        	for(Justif justif : liste){
        		String b = justif.getIdJ();
        %>
          <tr>
           	  <td><%= justif.getNumE() %></td>
           	  <td><%= justif.getNomE()%></td>
           	  <td><%= justif.getPrenomE() %></td>
           	  <td><%= justif.getDateSe() %></td>
           	  <td><%= justif.getEtatE() %></td>
           	  <td><a href="CtrlTelechargerJustificatif?b=<%= b%>">Justificatif </a></td>
           	  <td><a href="CtrlDecisionJustificatif?decision=Valide&numE=<%= justif.getNumE() %>&numSE=<%= justif.getIdSe()%>"><input type="button" value="Valider"></a></td>
           	  <td><a href="CtrlDecisionJustificatif?decision=Invalide&numE=<%= justif.getNumE() %>&numSE=<%= justif.getIdSe()%>"><input type="button" value="Invalider"></a></td>
           	  <td></td>
          </tr>
          
          <%
        	}
        }
          %>
     </table>
    
    
    
</div>    
</body>
</html>