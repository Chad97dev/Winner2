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
			String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
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
	
	<a href="CtrlDeconnexion">Se deconnecter</a>
	
	<div>${requestScope.msg_validationFA}</div>
	<div id="seances"></div>



	<script type="text/JavaScript" src="js/Ajax.js"></script>
</body>
</html>