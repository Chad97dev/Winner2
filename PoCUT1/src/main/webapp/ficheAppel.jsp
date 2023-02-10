<%@page import="metier.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="bd.Bd"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles2.css">
<meta charset="UTF-8">
<title>Fiche d'appel</title>


</head>
<body>


	<H2>Fiche d'appel</H2>
	<div class="table-wrapper">

		<%
		String cours = (String) request.getAttribute("cours");
		String date = (String) request.getAttribute("date");
		String heureDeb = (String) request.getAttribute("heureDeb");
		String heureFin = (String) request.getAttribute("heureFin");
		String nomFormation = (String) request.getAttribute("nomFormation");
		out.print("<p name=\"seance\">" + nomFormation + " " + date + " " + heureDeb + "-" + heureFin + "</p>");
		%>


		<form id="ficheAppel" method="get">
			<%
			String numSeance = (String) request.getAttribute("numSeance");
			%>
			<input type="hidden" name="numSeance" value="<%=numSeance%>">


			<table class="fl-table" cellspacing="1" cellpadding="5">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Prenom</th>
						<th>Type formation</th>
						<th>Photo</th>
						<th>Retard</th>
						<th>Absent</th>
				</thead>
				<tbody>

					<%
					String[] presents = (String[]) session.getAttribute("presents");
					String[] retards = (String[]) session.getAttribute("retards");
					String[] absents = (String[]) session.getAttribute("absents");
					ArrayList<String> retardValidee = (ArrayList<String>) request.getAttribute("retardValidee");
					ArrayList<String> absentValidee = (ArrayList<String>) request.getAttribute("absentValidee");
					String validation = (String) request.getAttribute("validation");

					for (Etudiant e : (ArrayList<Etudiant>) request.getAttribute("liste")) {
						out.println("<tr><td>" + e.getNomU() + "</td>");
						out.println("<td>" + e.getPrenomU() + "</td>");
						out.println("<td>" + e.getTypeE() + "</td>");
						out.println("<td><img class='profile' src=CtrlImage?id=" + e.getNumU() + "/></td>");

						if (retards != null && Arrays.asList(retards).contains((e.getNumU() + ""))) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"retard\"/ checked>Retard</td>");
						} else if (validation.equals("Oui") && retardValidee.contains((e.getNumU() + ""))) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU()
							+ "\" name=\"retard\"/ checked disabled>Retard</td>");
						} else if (validation.equals("Oui")) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/ disabled>Retard</td>");
						} else {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"retard\"/> Retard</td>");
						}

						if (absents != null && Arrays.asList(absents).contains((e.getNumU() + ""))) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/ checked>Absent</td>");
						} else if (validation.equals("Oui") && absentValidee.contains((e.getNumU() + ""))) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU()
							+ "\" name=\"retard\"/ checked disabled>Absent</td>");
						} else if (validation.equals("Oui")) {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"/ disabled>Absent</td>");
						} else {
							out.println("<td><input type=\"checkbox\"value=\"" + e.getNumU() + "\" name=\"absent\"  >Absent</td>");
						}
					}
					%>
				</tbody>
			</table>
	</div>
	<br>
	<%
	if (validation.equals("Non")) {
		out.println(
		"<input type=\"submit\" id=\"btn\" value=\"Enregistrer mes choix\" class=\"btn btn-success\" onclick=\"document.getElementById('ficheAppel').action='CtrlEnregistrerChoixAppel';\" >");
		out.println(
		"<input type=\"submit\" value=\"Valider fiche d'appel\" class=\"btn btn-success\" onclick=\"document.getElementById('ficheAppel').action='CtrlValiderFicheAppel';\" >");
		out.println("<br>");
	}
	%>
	<br>

	<a id="deconnection" class="btn btn-primary" href="CtrlDeconnexion">Se
		deconnecter</a>



	</form>
  

	<script>
		document.addEventListener("change", function(event) {
			if (event.target.name === "retard") {
				var absentCheckboxes = document
						.querySelectorAll("input[name='absent']");
				for (var i = 0; i < absentCheckboxes.length; i++) {
					if (absentCheckboxes[i].closest("tr")
							.contains(event.target)) {
						absentCheckboxes[i].checked = false;
					}
				}
			} else if (event.target.name === "absent") {
				var retardCheckboxes = document
						.querySelectorAll("input[name='retard']");
				for (var i = 0; i < retardCheckboxes.length; i++) {
					if (retardCheckboxes[i].closest("tr")
							.contains(event.target)) {
						retardCheckboxes[i].checked = false;
					}
				}
			}
		});
	</script>
</body>
</html>