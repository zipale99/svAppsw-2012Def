<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Stay</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>
	
	<div class = "main">
		<div class = "content">
			<h3>SearchItineraryOrJourney</h3>
			
Inserisci i parametri di ricerca per un Itinerario o un viaggio 

<hr>
				<form action="Controller" method="POST">
					Località di partenza:<br>
					<input type="text" name="startLoc"/>
					<br> Località di arrivo: <br>
					<input type="text" name="endLoc"/>
					<br>Durata(gg):<br>
					<input type="text" name="durata"/>
					<br> Nome: <br>
					<input type="text" name="nome"/>
					<br>Categoria:<br>
					<input type="text" name="categoria"/>
					<input type="hidden" name="operazione" value="searchItineraryOrJourney">
					<br><br><input type="submit" name="conferma" value="Search">
				</form>				
							
		</div>
		
			<div class = "sidenav">
				<jsp:include page="barraLaterale.jsp"></jsp:include>			
			</div>	
		
	</div>
	
	<div class = "footer">
		Progetto di Svilluppo Applicazioni Software di Lanciano Alessandro e Ficarra Sergio
	</div>
	
</div>
</body>
</html>