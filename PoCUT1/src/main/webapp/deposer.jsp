<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulaire d'upload de fichier</title>
</head>
<body>
	<h2>Formulaire d'upload de fichier</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><br><br>
		<input type="submit" value="Télécharger">
	</form>
</body>
</html>