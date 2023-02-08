<%@page import="metier.Seance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="metier.Seance"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCEUIL</title>
</head>
<body>

	<h1>ACCEUIL ETUDIANT</h1>
	
	
		
		<% 
		HashMap<Seance, String> listeSeances = (HashMap<Seance, String>)session.getAttribute("listeSeancesJus");
		if(listeSeances == null){
			out.print("<p>Vous n'avons pas de séance absente</p>");
		}else{
			Iterator it = listeSeances.keySet().iterator();
			Integer i = 0;
			out.print("<p>Liste des absences avec un justificatif</p><table><tr><td>COURS</td><td>DATE</td><td>HEURE</td></tr>");
			while(it.hasNext()){
				Seance se = (Seance) it.next();
				i++;
				out.print("<tr><td>"+se.getCours().getNomCours()+"</td><td>"+se.getDateSeance()+"</td><td>"+se.getHeureDebutSeance()+"-"+se.getHeureFinSeance()+"</td><td>");
			}
			out.print("</table>");
		}
		%>
	<p>${requestScope.msg_info}</p>
	<a href="CtrlProfil">MON PROFIL</a>
</body>
</html>