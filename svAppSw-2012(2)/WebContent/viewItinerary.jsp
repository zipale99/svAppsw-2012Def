<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ManageItineraryBase</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>View Itinerary</h3>
			
<%
Itinerary it = (Itinerary)request.getAttribute("itinerario");
%>			
			<table>
				<tr>
					<th>Itinerario</th>
				</tr>
				
				<tr>
					<td> <%= it.toString() %> </td>
				</tr>
			
			</table>
			

<%
int size = it.getSize();
if (size != 0) {
%>			
			
			<table>
				<tr>
					<th>Tappe</th>
					<th>Leaf</th>
					<th>Lista Attività</th>
				</tr>
<%

	int i = 0;
	while(size > 0) {
%>
	
				<tr>
					<td> <%= it.getStayTemplate(i) %> </td>
					<td> <%= it.getStayTemplate(i).print()%> </td>
					<td> <%= it.getStayTemplate(i).getActivityList().toString()%> </td>
				</tr>
<%
	size--;
	i++;
	}

}
%>	
			</table>
			
			
		</div>
		
		<div class = "sidenav">
			<jsp:include page="barraLaterale.jsp"></jsp:include>			
		</div>	
		
	</div>
	
	<div class = "footer">
		Progetto di Sviluppo Applicazioni Software di Lanciano Alessandro e Ficarra Sergio
	</div>
	
</div>
</body>
</html>