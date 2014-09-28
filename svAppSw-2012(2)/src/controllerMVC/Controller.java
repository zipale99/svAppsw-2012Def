package controllerMVC;

import decorator.*;
import composite.*;
import controller.*;
import resources.*;

import java.io.IOException;
import java.sql.SQLException;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import resources.OptionValue;
import controller.SearchController;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) { e.printStackTrace(); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
			throws ServletException, IOException {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(page);
			rd.forward(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalArgumentException, SQLException {
        
		//recupero l'operazione scelta dall'utente e l'oggetto in sessione utente
		String operazione = request.getParameter("operazione");
		HttpSession session = request.getSession();
		  
        AbstractUser proxy = null; //proxy
        AbstractUserComponent utenteDecorato = null;
        ManagementController managementController = null;
        //session.setAttribute("managementController", managementController);
       
        if(operazione == null) {
        	System.out.println("Operazione uguale uguale a null");
            if(session.isNew()) {
                System.out.println("creoProxy: "+ operazione);
                proxy = new ProxyUser();
                session.setAttribute("proxy", proxy);
                utenteDecorato = null;
                session.setAttribute("utenteDecorato", utenteDecorato);
            }
            else{
                proxy = (ProxyUser)session.getAttribute("proxy");
            }
            forward(request, response,"/login.jsp");
        }
        else { //Se l'operazione è diversa da null recupero dalla sessione utente gli oggetti proxy e decoratorUser
        
        	System.out.println("Operazione diversa da null");
        	proxy = (ProxyUser)session.getAttribute("proxy");
            utenteDecorato = (AbstractUserComponent)session.getAttribute("utenteDecorato");
            managementController = (ManagementController)session.getAttribute("managementController");
            System.out.println(operazione);
        }   
            
        /*
         * Recupero i parametri username e password dalla richiesta effettuata dall'utente
         * Li passo al metodo login di proxyUser, se ritorna true, allora assegno il getUser()
         * del proxy a session, altrimenti invalido la sessione.
         */
        if(operazione.equals("login")){
        	proxy = new ProxyUser();
        	if(proxy.login(request.getParameter("user"), request.getParameter("password"))) {
        		session.setAttribute("proxy", proxy);
                forward(request, response, "/Index.jsp");
        	}
            else {
               	request.getSession().invalidate();
    			forward(request, response,"/Index.jsp");
            }
        }
        
		if(operazione.equals("logout")){
			request.getSession().invalidate();
			utenteDecorato.invalida();
			forward(request, response, "/Index.jsp");
		}
        
		
        //Mostra itinerari dell'utente
        if(operazione.equals("searchMyItinerary")) {
        	System.out.println("utente: " + proxy.getUser().getUsername());
        	if (managementController == null)
                utenteDecorato = DecoratorUser.decora(proxy.getUser());
        	managementController = new ManagementController(utenteDecorato);
        	managementController.getMyItinerary(utenteDecorato);
        	session.setAttribute("managementController", managementController);
        	forward(request, response, "/viewMyItinerarySearchResults.jsp");
        }
        
        //Mostra tutti gli itinerari
        if(operazione.equals("searchItineraryOrJourney")) {
        	String startLoc = request.getParameter("startLoc");
        	String endLoc = request.getParameter("endLoc");
        	int durata = 0;
        	if (request.getParameter("durata") != "") 
        		durata = Integer.parseInt(request.getParameter("durata"));
        	String nome = request.getParameter("nome");
        	String cat = null;
        	if (request.getParameter("categoria") != "") 
        		cat = request.getParameter("categoria");
        	ItinerarySearchResults results = SearchController.searchItinerary(startLoc, endLoc, durata, nome, cat);
        	session.setAttribute("itineraryResults", results);
        	//session.setAttribute("proxy", proxy);
        	forward(request, response, "/viewItineraryOrJourneySearchResults.jsp");
        }
        
        if(operazione.equals("selectItinerary")) {
        	int id = Integer.parseInt(request.getParameter("idItinerary"));
         	ItinerarySearchResults results = (ItinerarySearchResults)session.getAttribute("itineraryResults");
         	Itinerary it = results.getElencoItinerari().get(id);
         	request.setAttribute("itinerario", it);
        	forward(request, response, "/viewItinerary.jsp");
        }
        
        
        if(operazione.equals("manageItinerary")) {
        	if (utenteDecorato == null)
                utenteDecorato = DecoratorUser.decora(proxy.getUser());
        	managementController = new ManagementController(utenteDecorato);
        	session.setAttribute("managementController", managementController);
        	forward(request, response, "/manageItinerary.jsp");
        }
        

        
        if(operazione.equals("confermaBasicInfo")) {
        	String nome = request.getParameter("nome");
        	String descrizione = request.getParameter("descrizione");
        	String categoria = request.getParameter("categoria");
        	System.out.println("Utente itinerario: "+managementController.getCurrentUser().getUsername());
        	managementController.createItinerary(nome, descrizione, categoria);
        	forward(request, response, "/creaItinerario.jsp");
        }
        
        /*
         * Restituisce tutti gli StayTemplate dell'agenzia
         */
        if(operazione.equals("searchtStayTemplate")) {
        	StaySearchResults results = SearchController.searchStayTemplate();
        	System.out.println("ssss: "+results.get(0).toString());
        	session.setAttribute("stayResults", results);
        	forward(request, response, "/selectStayTemplate.jsp");
        }
        
        
        if(operazione.equals("configureStayParameter")) {
        	//recupero l'id dello StayTemplate nell'arrayList
        	//Recupero le attività standard per lo stayTemplate scelto + quelle personalizzate
        	//utenteDecorato.searchActivityStayTemplate(utenteDecorato.getStay(id).getId());
        	//Recupero i leaf dello stayTemplate
        	//Recupero le opzioni dei leaf
        	int id = Integer.parseInt(request.getParameter("id"));
        	StaySearchResults results = (StaySearchResults)session.getAttribute("stayResults");
        	StayTemplate st = results.get(id);
        	managementController.setStay(st);
        	//TO-DO: inviare il controllo alla jsp che visualizza il tutto
        	
        	forward(request, response, "/configureStayParameter.jsp");
        }
        
        
        //Visualizza opzioni per un leaf con i valori possibili
        if (operazione.equals("viewOptionValues")) {
        	int id = Integer.parseInt(request.getParameter("idLeaf"));
        	request.setAttribute("idLeaf", id);   
        	forward(request, response, "/selectOptionValue.jsp");
        }
        
        
      //Recupera il valore per l'opzione selezionato(radioButton) e lo imposta a Option, da il controllo a configureStayParameter
        if (operazione.equals("selectValue")) {
        	int idLeaf = Integer.parseInt(request.getParameter("idLeaf"));
        	int idOption = Integer.parseInt(request.getParameter("idOption"));
        	int idOptValue = Integer.parseInt(request.getParameter("optValue"));
        	managementController.setOptionValue(idOption, idOptValue, idLeaf);
        	forward(request, response, "/selectOptionValue.jsp");
        }
        
        
        //Visualizzo tutte le attività dell'agenzia data una Località
        if (operazione.equals("searchActivity")) {
        	session.setAttribute("activityResults", SearchController.searchActivity());
        	forward(request, response, "/viewActivitySearchResults.jsp");
        }
        
        if (operazione.equals("addActivity")) {
        	int idActivity = Integer.parseInt(request.getParameter("id"));
        	ActivitySearchResults results = (ActivitySearchResults)session.getAttribute("activityResults");
        	managementController.addActivity(results.get(idActivity));
        	forward(request, response, "/configureStayParameter.jsp");
        	/*
        	 * Decidere come proseguire per il controllo di un attività compatibile
        	 * fare un metodo boolean in managementcontroller che ritorna true o false
        	 * in caso di false come segnali l'errore? bisognerebbe chiamare una jsp di errore->pessima soluzione
        	 * meglio: controllo js che in chiama il metodo del managementocntroller sull onclick e 
        	 * visualizza un popup seganalndo l'errora senza dare la possibilità di proseguire.
        	 */
        }
        
        if (operazione.equals("addStay")) {
        	//viene aggiunta la tappa che l'utente si sta configurando al suo itinerario
        	managementController.addStay();
        	forward(request, response, "/creaItinerario.jsp");
        }

        
        //TO-DO!!
        if(operazione.equals("addTransferStay")) {
        	int idStay = Integer.parseInt(request.getParameter("idTappa"));
        	request.setAttribute("idTappa", idStay);
        }
   
        if (operazione.equals("deleteStay")) {
        	int idStay = Integer.parseInt(request.getParameter("idTappa"));
        	managementController.deleteStay(idStay);
        	forward(request, response, "/creaItinerario.jsp");
        }
   
        //TO-DO!!
        if (operazione.equals("modificaTappa")) {
        	int idStay = Integer.parseInt(request.getParameter("idTappa"));
        	managementController.modificaTappa(idStay);
        	forward(request, response, "/configureStayParameter.jsp");
        }
        
        /*
         * Restituisce gli StayTemplate di tipo Transport, date una startLoc e un endLoc
         */
        
		
        
        

        
        
        

	}//Fine processRequest
}//Fine Controller
