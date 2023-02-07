<%@page import="metier.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bd.Bd"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche d'appel</title>
</head>
<body>
	<H1>Fiche d'appel</H1>


	<form action="" method="get">
		<table border="1" cellspacing="1" cellpadding="5">
			<tr>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Type formation</th>
				<th>Présent</th>
				<th>Retard</th>
				<th>Absent</th>
			</tr>

			<%
			for (Etudiant e : (ArrayList<Etudiant>) request.getAttribute("liste")) {
				out.println("<tr><td>" + e.getNomU() + " " + e.getPrenomU() + " " + e.getTypeE() + "</td>");
				out.println("<td>" + e.getPrenomU() + "</td>");
				out.println("<td>" + e.getTypeE() + "</td>");
				out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"present\"/ checked >Présent</td>");
				out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"retard\"/>Retard</td>");
				out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/>Absent</td></tr>");
			}
			%>
		</table>
		<p>
			<input type="submit" value="Enregistrer" />
			<input type="submit" value="Valider" />
		</p>
	</form>

</body>
</html>