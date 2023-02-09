<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulaire d'upload de fichier</title>
</head>
<body>
	<h2>Ajouter un justificatif sous format PDF</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><br><br>
		<input type="submit" value="Déposer">
	</form>
	<% //version long
	   String msg_info = (String)request.getAttribute("msg_error");
	   if(msg_info != null){
		   out.print("<div>" + msg_info + "</div>");
	   }
	   
	
	
	%>
<%
String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
out.print("<a href=\"CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in\">Annuler</a>");
%>
<a href="CtrlDeconnexion">Se deconnecter</a>
</body>
</html>