<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, controller.*, decorator.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ManageItineraryPro</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
<%
ManagementController mc = (ManagementController)session.getAttribute("managementController");
AbstractUserComponent auc = mc.getCurrentUser();
Itinerary myIt = auc.getItinerary();
%>		
	
	<div class = "main">
		<div class = "content">
			<h3>Creazione Tappa Manuale (HMS)</h3>
			<h4>La tappa verrà creata e aggiunta all'itinerario che si sta configurando</h4>
			<form action="Controller" method="POST">
			Località di partenza: <br>
			<input type="text" name="startLoc"/>
			<br> Località di arrivo: <br>
			<input type="text" name="endLoc"/>
			<br> Nome: <br>
			<input type="text" name="nome"/>
			<input type="hidden" name="operazione" value="createHMS">
			<br><br><input type="submit" name="conferma" value="Crea Tappa Manuale">
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