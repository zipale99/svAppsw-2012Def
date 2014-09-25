<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="stayResults" class="resources.StaySearchResults" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectStayTemplate</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>SelectStayTemplate</h3>
			
Seleziona uno StayTemplate da personalizzare e aggiungere all'itinerario:			

			<table>
				<tr>
					<th>StayTemplate</th>
					<th>Gestione Tappa</th>
				</tr>
<%
int size = stayResults.size();
int i = 0;
while(size > 0) {
	System.out.println("tappa: " + stayResults.get(i).toString());
%>
	
				<tr>
					<td> <%= stayResults.get(i).toString() %> </td>
					<td>  
						<form action="Controller" method="POST" >
							<input type="hidden" name="operazione" value="configureStayParameter">
							<input type="hidden" name="id" value='<%= i %>'>
							<input type="submit" value="ConfigureStayParameter"/>
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
		Progetto di Teconologie Web di Lanciano Alessandro
	</div>
	
</div>
</body>
</html>