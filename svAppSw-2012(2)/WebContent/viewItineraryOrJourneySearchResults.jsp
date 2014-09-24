<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="elencoTappe" class="composite.StayTemplateComposite" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>risultatiRicerca</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>SearchItineraryOrJourney</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>Risultati ricerca:</h3>
			
<%
ProxyUser pu = (ProxyUser)session.getAttribute("proxy");
%>		
			
			<table>
				<tr>
					<th>Itinerari</th>
					<th></th>
				</tr>
<%
	for (Itinerary it : pu.getUser().getItineraryList()) {
%>
				<tr>
					<td> 
						<%= it.toString() %>					
					</td>
				</tr>
<%
}
%>	
		</table>	
				
		</div>
		
		<div class = "sidenav">
			<jsp:include page="barraLaterale.jsp"></jsp:include>			
		</div>	
		
	</div>
	
	<div class = "footer">
		Progetto di Teconologie Web di Lanciano Alessandro
	</div>
	
</div>
</body>
</html>