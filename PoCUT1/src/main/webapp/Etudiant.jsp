<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des etudiants</title>
</head>


<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prenom</th>
        </tr>
    </thead>
    <tbody>
    <a href="EtudiantServlet">list</a>
        <c:forEach items="${Etudiant}" var="Eachetudiant">
        
            <tr>
                <td>${Eachetudiant.NumE}</td>
                <td>${Eachetudiant.NomE}</td>
                <td>${Eachetudiant.PrenomE}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>