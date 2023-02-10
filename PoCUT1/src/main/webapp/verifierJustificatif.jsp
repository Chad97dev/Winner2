<%@page import="metier.Justif"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Justificatifs à vérifier</title>
<link rel="stylesheet" href="css/stylesUs9.css">

<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<style>
</style>
</head>
<body>
	<h1>Bienvenue dans l'espace de la scolarité</h1>
<a class="btn btn-primary" href="CtrlDeconnexion">Déconnexion</a>
	<div id="listeJustificatif" class="table-wrapper">
		<table class="fl-table">
			<thead>
				<tr>
					<th>Numero Etudiant</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Date Séance</th>
					<th>Etat</th>
					<th>Justifactif</th>
					<th>Valider</th>
					<th>Invalider</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<%
					List<Justif> liste = (List<Justif>) request.getAttribute("liste");
					if (liste != null) {
						for (Justif justif : liste) {
							String b = justif.getIdJ();
							String nom = justif.getNomE();
							String prenom = justif.getPrenomE();
							Date date = justif.getDateSe();
					%>
				
				<tr>
					<td><%=justif.getNumE()%></td>
					<td><%=justif.getNomE()%></td>
					<td><%=justif.getPrenomE()%></td>
					<td><%=justif.getDateSe()%></td>
					<td><%=justif.getEtatE()%></td>
					<td><a
						href="CtrlTelechargerJustificatif?b=<%=b%>&nom=<%=nom%>&prenom=<%=prenom%>&date=<%=date%>">Justificatif
					</a></td>
					<td><a
						href="CtrlDecisionJustificatif?decision=Valide&numE=<%=justif.getNumE()%>&idJ=<%=justif.getIdJ()%>"><input
							type="button" value="Valider"></a></td>
					<td><a
						href="CtrlDecisionJustificatif?decision=Invalide&numE=<%=justif.getNumE()%>&idJ=<%=justif.getIdJ()%>"><input
							type="button" value="Invalider"></a></td>
				</tr>
			<tbody>

				<%
				}
				}
				%>
				
				
				<!-- </table>
</div>  
</body>
</html> -->