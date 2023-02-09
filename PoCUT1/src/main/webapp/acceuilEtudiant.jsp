<%@page import="metier.Seance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="metier.Seance"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/styleUS12.css">
<meta charset="UTF-8">
<title>ACCEUIL</title>
</head>
<body>

	<h1>ACCEUIL ETUDIANT</h1>
	
	
		
		<% 
		String listeId = (String)session.getAttribute("email");
		HashMap<Seance, String> listeSeances = (HashMap<Seance, String>)session.getAttribute("listeSeances");
		if(listeSeances == null){
			out.print("<p>Vous n'avons pas de séance absente</p>");
		}else{
			Iterator it = listeSeances.keySet().iterator();
			Integer i = 0;
			out.print("<p>Liste des absences</p><p>Veuillez choisir les séances pour lesquelles vous voulez déposer un justificatif : </p><form action=\"CtrlCfrmAbs\" method=\"post\" ><table><tr><td>JUSTIFICATIF</td><td>COURS</td><td>DATE</td><td>HEURE</td></tr>");
			while(it.hasNext()){
				Seance se = (Seance) it.next();
				i++;
				out.print("<tr><td><input type=\"checkbox\" id=\"seance"+i+"\" name=\"cb_seance\" value=\""+se.getNumSeance()+"\" /></td><td>"+se.getCours().getNomCours()+"</td><td>"+se.getDateSeance()+"</td><td>"+se.getHeureDebutSeance()+"-"+se.getHeureFinSeance()+"</td><td>");
			}
			out.print("</table><input type=\"submit\" value = \"Confirmer\"/></form>");
		}
		%>
	<p>${requestScope.msg_info}</p>
	<a href="CtrlAbsJus">Consulter mes absences justifiées</a>
	<a href="CtrlProfil">MON PROFIL</a>
</body>
</html>