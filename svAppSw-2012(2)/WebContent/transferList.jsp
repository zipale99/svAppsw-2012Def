<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="stayResults" class="resources.StaySearchResults" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectTransfer</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>SelectTransfer</h3>
			
Seleziona una tappa di trasferimento da aggiungere al proprio itinerario.			

			<table>
				<tr>
					<th>Transfer</th>
				</tr>
<%
int size = stayResults.size();
int i = 0;
while(size > 0) {
	System.out.println("transfer: " + stayResults.get(i).toString());
%>
	
				<tr>
					<td> <%= stayResults.get(i).toString() %> </td>
					<td>  
						 
						<form action="Controller" method="POST">
							<input type="hidden" name="operazione" value="configureTransferParameter">
							<input type="hidden" name="idTr" value='<%= i %>'>
							<input type="submit" value="Configura tappa di trasferimento"/>
						</form>
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