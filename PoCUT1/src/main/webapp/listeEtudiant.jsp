<%@page import="metier.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bd.Bd"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<H1>Liste Etudiant</H1>


	<table border="1" cellspacing="1" cellpadding="5">
		<tr>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Type formation</th>
			<th>Photo</th>

		</tr>

		<%
			for (Etudiant e : (ArrayList<Etudiant>) request.getAttribute("liste")) {
				out.println("<tr><td>" + e.getNomU() + " " + e.getPrenomU() + " " + e.getTypeE() + "</td>");
				out.println("<td>" + e.getPrenomU() + "</td>");
				out.println("<td>" + e.getTypeE() + "</td>");
				out.println("<td><img src=CtrlImage?id=" + e.getNumU() + "/></td>");
				
			}
			%>
	</table>
	<p>
		<input type="submit" value="Enregistrer" /> <input type="submit"
			value="Valider" />
	</p>

</body>
</html>