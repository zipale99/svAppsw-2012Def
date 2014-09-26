<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modificaItinerario</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>modificaItinerario</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>Elenco dei tuoi itinerari:</h3>
			
Seleziona l'itinerario che vuoi modificare

<%
ManagementController mc = (ManagementController)session.getAttribute("managementController");
if(mc == null)
System.out.println("mc uguale a nulllll");
AbstractUserComponent auc = mc.getCurrentUser();


%>		
			
			<table>
				<tr>
					<th>Itinerari</th>
					<th></th>
				</tr>
<%
	int i = 0;
	for (Itinerary it : auc.getItineraryList()) {
%>
				<tr>
					<td> 
						<%= it.toString() %>					
					</td>
					<td>  
						<button onclick="location.href='Controller?operazione=modificaItinerario&idItinerario=<%= i %>'">Modifica</button> 
					</td>
				</tr>
<%
i++;
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