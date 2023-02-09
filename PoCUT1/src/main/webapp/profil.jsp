<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
<link rel="stylesheet" href="css/styleCss.css">
</head>
<body>
<h1>profil</h1>

<h2>Informations personnelles</h2>
	
<table>
		

		<tr>
			<td>Nom</td>
			<td><%String nom = (String)session.getAttribute("nom");
				out.print("<p>"+nom+"</p>");%></td>
		</tr>

		<tr>
			<td>Prenom</td>
			<td><%String prenom = (String)session.getAttribute("prenom");
				out.print("<p>"+prenom+"</p>");%></td>
		</tr>

		<tr>
			<td>Adresse email</td>
			<td><%String email = (String)session.getAttribute("email");
				out.print("<p>"+email+"</p>");%></td>
		</tr>

		<tr>
			<td>Formation</td>
			<td><%
				String formation = (String) request.getAttribute("formation");
				out.print("<p>"+formation+"</p>");%></td>
		</tr>
		
</table>
<h2>Informations supplémentaire</h2>
<table>
		<tr>
			<td>Numéro de téléphone</td>
			<td><%
				String tele = (String) request.getAttribute("tele");
				out.print("<p>"+tele+"</p>");%></td>
		</tr>

		<tr>
			<td>Adresse</td>
			<td><%
				String adresse = (String) request.getAttribute("adresse");
				out.print("<p>"+adresse+"</p>");%></td>
		</tr>
</table>
<h2>Photo</h2>
<% int numU = (int) session.getAttribute("numU") ;
out.print("<img src=CtrlImage?id=" + numU +"/>"); %>
<p id="modifImage" onclick="afficherModif()">modifier</p>
<div id="zoneModifImage">
	<p>choisir un photo</p>
	<form action="CtrlUploadImg" method="post" enctype="multipart/form-data">
		<input type="file" accept="image/*" capture="camera " name="file"><br><br>
		<input type="submit" value="Enregistrer">
		<p onclick="cacherModif()">Annuler</p>
	</form>
	<p>${requestScope.msg_error}</p>
</div>
<%
String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
out.print("<a href=\"CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in\">Home</a>");
%>
<a href="CtrlDeconnexion">Se deconnecter</a>
</body>
</html>

<script type="text/javascript">
function afficherModif(){
	document.getElementById("zoneModifImage").style.display="block";
}
function cacherModif(){
	document.getElementById("zoneModifImage").style.display="none";
}
</script>