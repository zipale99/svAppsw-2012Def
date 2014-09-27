<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="composite.*, decorator.*, resources.*, controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="tappa" class="composite.StayTemplateComposite" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectOptionValue</title>
<link rel="stylesheet" type="text/css" href="stile.css"/>
</head>
<body>

	<div class = "container">
	<div class = "header">
		<h1>Awesome Journey</h1>
	</div>	
	
	<div class = "main">
		<div class = "content">
			<h3>SelectOptionValue</h3>
			

Scegli i valori per le opzioni proposte:

		<table>
		
			<tr>
				<th>Option</th>
				<th>Value</th>
			</tr>	

<%
//Recupero la lista delle opzioni per il leaf selezionato(idLeaf)
ManagementController mc = (ManagementController)session.getAttribute("managementController");
AbstractUserComponent auc = mc.getCurrentUser();
int idLeaf = Integer.parseInt(request.getParameter("idLeaf"));
StayTemplate stayLeaf = auc.getStay().getStayTemplate(idLeaf);
int sizeListOption = stayLeaf.getOptionListSize();
int i = 0;
while(sizeListOption > 0) {
	Option opt = stayLeaf.getOption(i);
%>	
	
	
			<tr>
				<td> <%= opt.toString() %> </td>
				<td> 
<%
int sizePossibleValue = opt.getPossibleValueSize();
int j = 0;
while(sizePossibleValue > 0) {
	OptionValue optValue = opt.getOptionValue(j);
%>				
							
					
					<form action="Controller" method="POST" >
<%
if (optValue.getValue().equals(opt.getValue().getValue())) {
%>					
						<input type="radio" name="optValue" value='<%= j %>' checked/><%= optValue.toString() %>
						<br>
							
<%
}
else {
%>		
						<input type="radio" name="optValue" value='<%= j %>'/><%= optValue.toString() %>
						<br>
<%
}
%>					
						<input type="hidden" name="idLeaf" value='<%= idLeaf %>'>
						<input type="hidden" name="idOption" value='<%= i %>'>		
<%
sizePossibleValue--;
j++;
}
%>					
						<input type="hidden" name="operazione" value="selectValue">
						<input type="submit" value="Conferma il valore"/>	
					</form>
					 		
					
				</td>
			</tr>
				
		

<%
sizeListOption--;
i++;
}
%>	
		</table>
		
		<a href="configureStayParameter.jsp">Torna alla configurazione della tappa</a>
				
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