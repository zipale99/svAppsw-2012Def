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

			
				<ul>
				
				<li><b>NOME:</b> <%= it.getNome() %></li>
				
				<li><b>PARTENZA:</b> <%= it.getStartLoc() %></li>
				
				<li><b>ARRIVO:</b> <%= it.getEndLoc() %></li>
				
				<li><b>DURATA:</b> <%= it.getDurata() %></li>
				
				<li><b>PREZZO:</b> <%= it.getPrice() %></li>
				
				<li><b>CATEGORIA:</b> <%= it.getCategoria() %></li>
				
				<li><b>DESCRIZIONE:</b> <%= it.getDesc() %></li>
				
				<li><b>STATO:</b> <%= it.getStato() %></li>
			
				</ul>		
				
			
			
<%
int size = it.getSize();
for (int i = 0; i < size; i++) {
%>					
			
			<table>
				<tr>
					<th>Tappa</th>
					<th>Lista Attività</th>
				</tr>
	
				<tr>
					<td class="tdViewTappa"> <%= it.getStayTemplate(i) %> </td>
					<td> <%= it.getStayTemplate(i).getActivityList().toString()%> </td>
				</tr>
			</table>
			
		
			<table>
				<tr>
					<th>Leaf</th>
					<th>Lista Opzioni</th>
				</tr>
				
				
							
				
<%
int j = 0;
int sizeLeaf = it.getStayTemplate(i).getSize();
while(sizeLeaf > 0) {
%>						
						<tr>
						 <td class="tdViewTappa"><%= it.getStayTemplate(i).getStayTemplate(j).toString() %></td>
						 <td><%= it.getStayTemplate(i).getStayTemplate(j).getOptionList().toString() %></td>
						</tr>
<%
sizeLeaf--;
j++;
}
%>						 
						
					
					
					</table>	
<hr size="3" color="red">			
<%
}
%>			
			
			
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