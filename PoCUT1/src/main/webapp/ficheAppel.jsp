<%@page import="metier.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
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
	<%
	String seance = (String) request.getAttribute("seance");
	out.print("<H2>" + seance + "</H2>");
	%>


	<form id="ficheAppel" method="get">
		<%
		String numSeance = (String) request.getAttribute("numSeance");
		%>
		<input type="hidden" name="numSeance" value="<%=numSeance%>">


		<table border="1" cellspacing="1" cellpadding="5">
			<tr>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Type formation</th>
				<th>Photo</th>
				<th>Retard</th>
				<th>Absent</th>
			</tr>

			<%
			String[] presents = (String[]) session.getAttribute("presents");
			String[] retards = (String[]) session.getAttribute("retards");
			String[] absents = (String[]) session.getAttribute("absents");

			for (Etudiant e : (ArrayList<Etudiant>) request.getAttribute("liste")) {
				out.println("<tr><td>" + e.getNomU() + " " + e.getPrenomU() + " " + e.getTypeE() + "</td>");
				out.println("<td>" + e.getPrenomU() + "</td>");
				out.println("<td>" + e.getTypeE() + "</td>");
				out.println("<td><img src=CtrlImage?id=" + e.getNumU() + "/></td>");

				if (retards != null && Arrays.asList(retards).contains((e.getNumU() + ""))) {
					out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"retard\"/ checked >Retard</td>");
				} else {
					out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"retard\"/ >Retard</td>");
				}

				if (absents != null && Arrays.asList(absents).contains((e.getNumU() + ""))) {
					out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/ checked >Absent</td>");
				} else {
					out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/ >Absent</td>");
				}
			}
			%>
		</table>
		<br>
		<%
		String validation = (String) request.getAttribute("validation");
		//${ sessionScope.}
		if (validation.equals("Non")) {
			out.println(
			"<input type=\"submit\" value=\"Enregistrer mes choix\" onclick=\"document.getElementById('ficheAppel').action='CtrlEnregistrerChoixAppel';\" >");
			out.println(
			"<input type=\"submit\" value=\"Valider fiche d'appel\" onclick=\"document.getElementById('ficheAppel').action='CtrlValiderFicheAppel';\" >");
			out.println("<br>");
		}
		%>

		<a href="CtrlDeconnexion">Se deconnecter</a>



	</form>
  


</body>
</html>