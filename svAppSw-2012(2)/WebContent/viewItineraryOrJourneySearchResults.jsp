<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="itineraryResults" class="resources.ItinerarySearchResults" scope="session"/>
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
			
			<table>
				<tr>
					<th>Itinerari</th>
					<th></th>
				</tr>
<%
int size = itineraryResults.getElencoItinerari().size();
int i = 0;
while(size > 0) {
	Itinerary it = itineraryResults.getElencoItinerari().get(i);
%>
				<tr>
					<td> 
						<%= it.toString() %>					
					</td>
					<td>  
						<button onclick="location.href='Controller?operazione=selectItinerary&idItinerary=<%= i %>'">Select</button> 
					</td>
				</tr>
<%
size--;
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