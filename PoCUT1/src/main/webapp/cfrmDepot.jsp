<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="metier.Seance"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.*"%>
 <%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
<p>La/les séance/s que vous avez choisi</p>
<% String[] listeSeancesChoisies = (String[]) session.getAttribute("listeSeancesChoisies");
	List<String>  MyList = (List<String>) Arrays.asList(listeSeancesChoisies);
	HashMap<Seance, String> listeSeances = (HashMap<Seance, String>)session.getAttribute("listeSeances");
	
	Iterator it = listeSeances.keySet().iterator();
	while(it.hasNext()){
		Seance se = (Seance) it.next();
		out.print("<table>");
		if(MyList.contains(String.valueOf(se.getNumSeance()))){
			out.print("<tr><td>"+se.getCours().getNomCours()+"</td><td>"+se.getDateSeance()+"</td><td>"+se.getHeureDebutSeance()+"-"+se.getHeureFinSeance()+"</td><td>");
		}
		out.print("</table>");
	}
	%>

<a href="deposer.jsp">confirmer</a>
<%
String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
out.print("<a href=\"CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in\">Annuler</a>");
%>
<a href="CtrlDeconnexion">Se deconnecter</a>
</body>
</html>