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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<style>
body{
	padding-top:20px;
	padding-left:30px;
	padding-right:30px;
}
table {
	line-height: 40px;
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
}

table td {
	border: 1px solid black;
	padding: 10px;
	text-align: center;
}

p {
	margin: 0px;
}

#seancesbis {
	margin-top: 30px;
	margin-bottom: 30px;
}

#t10 {
	background-color: #4c8dae;
	color: white;
}

#t11 {
	background-color: #4c8dae;
	color: white;
}

#t12 {
	background-color: #4c8dae;
	color: white;
}

#t13 {
	background-color: #4c8dae;
	color: white;
}

#t14 {
	background-color: #4c8dae;
	color: white;
}

#t15 {
	background-color: #4c8dae;
	color: white;
}

#t10 {
	background-color: #4c8dae;
	color: white;
}

#t20 {
	background-color: #4c8dae;
	color: white;
}

#t30 {
	background-color: #4c8dae;
	color: white;
}

#t40 {
	background-color: #4c8dae;
	color: white;
}

#t50 {
	background-color: #4c8dae;
	color: white;
}

#t60 {
	background-color: #4c8dae;
	color: white;
}

#t70 {
	background-color: #4c8dae;
	color: white;
}

#t80 {
	background-color: #4c8dae;
	color: white;
}

#t90 {
	background-color: #4c8dae;
	color: white;
}
#deconnection{
	float:right;
}
</style>
</head>
<body>
	<div>
		<a id="deconnection" class="btn btn-primary" href="CtrlDeconnexion">Déconnexion</a>
	</div>
	<div>
		<h1>Bienvenue dans votre espace de cours</h1>

	<h2>Vous pouvez consulter votre emploi du temps</h2>
	
	</div>


	
	

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
		<table>
			<tr>
				<td class="seance" id="t10"></td>
				<td class="seance" id="t11"></td>
				<td class="seance" id="t12"></td>
				<td class="seance" id="t13"></td>
				<td class="seance" id="t14"></td>
				<td class="seance" id="t15"></td>
			</tr>
			<tr>
				<td class="seance" id="t20"></td>
				<td class="seance" id="t21"></td>
				<td class="seance" id="t22"></td>
				<td class="seance" id="t23"></td>
				<td class="seance" id="t24"></td>
				<td class="seance" id="t25"></td>
			</tr>
			<tr>
				<td class="seance" id="t30"></td>
				<td class="seance" id="t31"></td>
				<td class="seance" id="t32"></td>
				<td class="seance" id="t33"></td>
				<td class="seance" id="t34"></td>
				<td class="seance" id="t35"></td>
			</tr>
			<tr>
				<td class="seance" id="t40"></td>
				<td class="seance" id="t41"></td>
				<td class="seance" id="t42"></td>
				<td class="seance" id="t43"></td>
				<td class="seance" id="t44"></td>
				<td class="seance" id="t45"></td>
			</tr>
			<tr>
				<td class="seance" id="t50"></td>
				<td class="seance" id="t51"></td>
				<td class="seance" id="t52"></td>
				<td class="seance" id="t53"></td>
				<td class="seance" id="t54"></td>
				<td class="seance" id="t55"></td>
			</tr>
			<tr>
				<td class="seance" id="t60"></td>
				<td class="seance" id="t61"></td>
				<td class="seance" id="t62"></td>
				<td class="seance" id="t63"></td>
				<td class="seance" id="t64"></td>
				<td class="seance" id="t65"></td>
			</tr>
			<tr>
				<td class="seance" id="t70"></td>
				<td class="seance" id="t71"></td>
				<td class="seance" id="t72"></td>
				<td class="seance" id="t73"></td>
				<td class="seance" id="t74"></td>
				<td class="seance" id="t75"></td>
			</tr>
			<tr>
				<td class="seance" id="t80"></td>
				<td class="seance" id="t81"></td>
				<td class="seance" id="t82"></td>
				<td class="seance" id="t83"></td>
				<td class="seance" id="t84"></td>
				<td class="seance" id="t85"></td>
			</tr>
			<tr>
				<td class="seance" id="t90"></td>
				<td class="seance" id="t91"></td>
				<td class="seance" id="t92"></td>
				<td class="seance" id="t93"></td>
				<td class="seance" id="t94"></td>
				<td class="seance" id="t95"></td>
			</tr>

		</table>
	</div>

	<div>${requestScope.msg_validationFA}</div>
	



	<script type="text/JavaScript" src="js/Ajax.js"></script>
</body>
</html>
