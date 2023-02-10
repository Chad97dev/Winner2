<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emploi du temps</title>
<style>
	table {line-height:40px;}
	p{margin:0px;}
</style>
</head>
<body>
	<%

	Calendar calendar = Calendar.getInstance();
	int currentWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
	%>

	<select id="weekNumber">
		<%
		for (int i = 1; i <= 52; i++) {
			calendar.set(Calendar.WEEK_OF_YEAR, i);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date date = calendar.getTime();
			String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		%>
		<option id="<%=i%>" value="<%=i%>"
			<%=(i == currentWeekNumber) ? "selected" : ""%>>Semaine
			<%=i%> -
			<%=formattedDate%></option>
		<%
		}
		%>
	</select>
	<br />
	
	

	<div id="seances"></div>
	<div id="seancesbis" style="display: none">
		<table border="1">
			<tr><td class="seance" id="t10"> </td> <td class="seance" id="t11"> </td> <td class="seance" id="t12"> </td> <td class="seance" id="t13"> </td> <td class="seance" id="t14"> </td> <td class="seance" id="t15"> </td></tr>
			<tr><td class="seance" id="t20"> </td> <td class="seance" id="t21"> </td> <td class="seance" id="t22"> </td> <td class="seance" id="t23"> </td> <td class="seance" id="t24"> </td> <td class="seance" id="t25"> </td></tr>
			<tr><td class="seance" id="t30"> </td> <td class="seance" id="t31"> </td> <td class="seance" id="t32"> </td> <td class="seance" id="t33"> </td> <td class="seance" id="t34"> </td> <td class="seance" id="t35"> </td></tr>
			<tr><td class="seance" id="t40"> </td> <td class="seance" id="t41"> </td> <td class="seance" id="t42"> </td> <td class="seance" id="t43"> </td> <td class="seance" id="t44"> </td> <td class="seance" id="t45"> </td></tr>
			<tr><td class="seance" id="t50"> </td> <td class="seance" id="t51"> </td> <td class="seance" id="t52"> </td> <td class="seance" id="t53"> </td> <td class="seance" id="t54"> </td> <td class="seance" id="t55"> </td></tr>
			<tr><td class="seance" id="t60"> </td> <td class="seance" id="t61"> </td> <td class="seance" id="t62"> </td> <td class="seance" id="t63"> </td> <td class="seance" id="t64"> </td> <td class="seance" id="t65"> </td></tr>
			<tr><td class="seance" id="t70"> </td> <td class="seance" id="t71"> </td> <td class="seance" id="t72"> </td> <td class="seance" id="t73"> </td> <td class="seance" id="t74"> </td> <td class="seance" id="t75"> </td></tr>
			<tr><td class="seance" id="t80"> </td> <td class="seance" id="t81"> </td> <td class="seance" id="t82"> </td> <td class="seance" id="t83"> </td> <td class="seance" id="t84"> </td> <td class="seance" id="t85"> </td></tr>
			<tr><td class="seance" id="t90"> </td> <td class="seance" id="t91"> </td> <td class="seance" id="t92"> </td> <td class="seance" id="t93"> </td> <td class="seance" id="t94"> </td> <td class="seance" id="t95"> </td></tr>
		
		</table>
	</div>
	
	<div>${requestScope.msg_validationFA}</div>
	<div><a href="CtrlDeconnexion">Se deconnecter</a></div>



	<script type="text/JavaScript" src="js/Ajax.js"></script>
</body>
</html>
