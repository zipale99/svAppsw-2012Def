<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="activityResults" class="resources.ActivitySearchResults" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectActivity</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>SelectActivity</h3>
			
Seleziona un'attività da aggiungere alla tappa che si sta personalizzando.			

			<table>
				<tr>
					<th>Attività</th>
				</tr>
<%
int size = activityResults.size();
int i = 0;
while(size > 0) {
	System.out.println("attività: " + activityResults.get(i).toString());
%>
	
				<tr>
					<td> <%= activityResults.get(i).toString() %> </td>
					<td>  
						<form action="Controller" method="POST" >
							<input type="hidden" name="operazione" value="addActivity">
							<input type="hidden" name="id" value='<%= i %>'>
							<input type="submit" value="Add Activity"/>
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