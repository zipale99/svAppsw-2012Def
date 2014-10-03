<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*, controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="tappa" class="composite.StayTemplateComposite" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configureStayParameter</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>ConfigureStayParameter</h3>
			
<%
ManagementController mc = (ManagementController)session.getAttribute("managementController");
AbstractUserComponent auc = mc.getCurrentUser();
StayTemplate stay = auc.getStay();
%>

StayTemplate selezionato:

		<table>
		
			<tr>
				<th>StayTemplate</th>
				<th>Attività</th>
			</tr>
	
			<tr>
				<td> <%= stay.toString() %> </td>
				<td> 
					 <%= stay.getActivityList().toString() %> 
					<form action="Controller" method="POST" >
						<input type="hidden" name="operazione" value="searchActivity">
						<input type="hidden" name="id" value='<%= stay.getId() %>'>
						<input type="submit" value="Aggiungi un'attività"/>
					</form>
				</td>
			</tr>
				
		</table>

Il Template è composto dalle seguenti parti:
			
			<table>
				<tr>
					<th>StayTemplateLeaf</th>
					<th>Opzioni disponibili</th>
				</tr>
<%
int sizeListLeaf = stay.getSize();
int i = 0;
while(sizeListLeaf > 0) {
	StayTemplate stayLeaf = stay.getStayTemplate(i); //recupero il leaf del composite
%>

				<tr>
					<td> <%= stayLeaf.toString() %> </td>
					<td> 
						 <%= stayLeaf.getOptionList().toString() %> 
						<form action="Controller" method="POST" >
							<input type="hidden" name="operazione" value="viewOptionValues">
							<input type="hidden" name="idLeaf" value='<%= i %>'>
							<input type="submit" value="Imposta un valore"/>
						</form>
					</td>
				</tr>
				
<%
sizeListLeaf--;
i++;
}
%>	
			</table>	
				
<%
System.out.println(request.getHeader("Referer"));
boolean modifica = (Boolean)session.getAttribute("modifica");
if(!modifica) {
%>				
			<a href="Controller?operazione=addStay">Conferma e aggiungi la tappa all'itinerario</a>
<%
} else {
%>
			<a href="creaItinerario.jsp">Conferma modifiche</a>
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