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
			<h3>Create Itinerary</h3>
			
<%
ManagementController mc = (ManagementController)session.getAttribute("managementController");
AbstractUserComponent auc = mc.getCurrentUser();
Itinerary myIt = auc.getItinerary();
myIt.sort();
%>			
			
			<p><b>Creatore:</b> <%= myIt.getUser() %> </p>
			<p><b>Nome:</b> <%= myIt.getNome() %> </p>
			<p><b>Descrizione:</b> <%= myIt.getDesc() %> </p>
			<p><b>Categoria:</b> <%= myIt.getCategoria() %> </p>

<%
int size = myIt.getSize();
if (size != 0) {
%>			
			
			<table>
				<tr>
					<th>#</th>
					<th>Tappa</th>
					<th>Gestione Tappa</th>
				</tr>
<%

	int i = 0;
	while(size > 0) {
%>
	
				<tr>
					<td> <%= myIt.getStayTemplate(i).getTimeOffset() %>
					<td> <%= myIt.getStayTemplate(i) %> </td>
					<td>  
							<button onclick="location.href='Controller?operazione=modificaTappa&idTappa=<%= i %>'">Modifica Tappa</button> 
							
							<form action="Controller" method="POST" >
								<input type="hidden" name="operazione" value="deleteStay">
								<input type="hidden" name="idTappa" value='<%= i %>'>
								<input type="submit" value="Elimina Tappa" onClick="return(confirm('Sei sicuro di voler procedere?'))"/>
							</form>
							
							<form action="Controller" method="POST" >
								<input type="hidden" name="operazione" value="addTransferStay">
								<input type="hidden" name="idTappa" value='<%= i %>'>
								<input type="submit" value="Add Transfer Stay"/>
							</form>
					</td>
				</tr>
<%
	size--;
	i++;
	}

}
%>	
	
			</table>	
			
			
				<form action="Controller" method="POST" >
					<input type="hidden" name="operazione" value="searchtStayTemplate">
					<input type="submit" value="Aggiungi Tappa"/>
				</form>
				
				<br>
		
				<form action="Controller" method="POST" >
					<input type="hidden" name="operazione" value="saveItinerary">
					<input type="submit" value="Salva Itinerario"/>
				</form>
			
			
			
			
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