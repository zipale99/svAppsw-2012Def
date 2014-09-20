package controllerMVC;

import decorator.*;
import composite.*;
import controller.*;

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
		  
        ProxyUser proxy = null;
        DecoratorUser utenteDecorato = null;
        ManagementController managementController = null;
              
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
        else { //Se l'operazione � diversa da null recupero dalla sessione utente gli oggetti proxy e decoratorUser
        	System.out.println("Operazione diversa da null");
        	proxy = (ProxyUser)session.getAttribute("proxy");
            utenteDecorato = (DecoratorUser)session.getAttribute("utenteDecorato");
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
        	SearchController.searchMyItinerary(proxy.getUser());
        	session.setAttribute("proxy", proxy);
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
        	SearchController.searchItinerary(proxy.getUser(), startLoc, endLoc, durata, nome, cat);
        	session.setAttribute("proxy", proxy);
        	forward(request, response, "/viewItineraryOrJourneySearchResults.jsp");
        }
        
        
        if(operazione.equals("manageItinerary")) {
        	if (utenteDecorato == null)
                utenteDecorato = DecoratorUser.decora(proxy.getUser());
        	managementController = new ManagementController(utenteDecorato);
        	session.setAttribute("managementController", managementController);
        	forward(request, response, "/manageItinerary.jsp");
        }
        
        /*
         * Dato l'id di uno StayTemplate, mostra le Attivit� di default ad esso associate
         */
        
        /*
         * Dato l'id di una Stay mostra le Attivit�(personalizzate) di quella Stay
         */
        
        /*
         * Dato l'id di un leaf mostra le opzioni ad esso associate
         */
        
        
        /*
         * Restituisce tutte le attivit� dell'agenzia
         */
        
        /*
         * Dato l'id di uno StayTemplate, restituisce tutti i leaf che compongono quello StayTemplate
         */
        
        /*
         * Dato l'id di una Stay, restituisce tutti i leaf che compongono quella Stay
         */
        
        /*
         * Dato l'id di un opzione associata ad un leaf, restituisce tutti i valori possibili per quell'opzione
         */
        
        /*
         * Restituisce gli StayTemplate di tipo Transport, date una startLoc e un endLoc
         */
        
        
        /*
         * Crea un itinerario
         */
		

        
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
        	//searchController.searchStayTemplate(utenteDecorato);
        	forward(request, response, "/selectStayTemplate.jsp");
        }
        
        
        if(operazione.equals("configureStayParameter")) {
        	//recupero l'id dello StayTemplate nell'arrayList
        	int id = Integer.parseInt(request.getParameter("id"));
        	//Recupero le attivit� standard per lo stayTemplate scelto + quelle personalizzate
        	//utenteDecorato.searchActivityStayTemplate(utenteDecorato.getStay(id).getId());
        	//Recupero i leaf dello stayTemplate
        	
        	//Recupero le opzioni dei leaf
        	
        	
        	
        	//TO-DO: inviare il controllo alla jsp che visualizza tutto questo
        	forward(request, response, "/configureStayParameter.jsp");
        }
        
        
        //Visualizza opzioni per un leaf con i valori possibili
        if (operazione.equals("viewOptionValues")) {
        	int id = Integer.parseInt(request.getParameter("idLeaf"));
        	//utenteDecorato.searchOptionValue();
        	//passo l'indice dell'optionList scelta
        	request.setAttribute("idLeaf", id);
        	//invio il controllo a viewOptionValue   
        	forward(request, response, "/selectOptionValue.jsp");
        }
        
        
      //Recupera il valore per l'opzione selezionato(radioButton) e lo imposta a Option, da il controllo a configureStayParameter
        if (operazione.equals("selectValue")) {
        	int idOption = Integer.parseInt(request.getParameter("idOption"));
        	String optValue = request.getParameter("optValue");
        	//price da recuperare da form
        	//idLeaf da recuperare da form
        	OptionValue optValueObj = new OptionValue(optValue, 0);
        	//utenteDecorato.setOptionValue(int posizioneDelLeaf, idOption, optValueObj);
        	
        }
        
        
        //Visualizzo tutte le attivit� dell'agenzia data una Localit�
        if (operazione.equals("addActivity")) {
        	String loc = request.getParameter("loc");
        	/*
        	 * uso un bean per visualizzare tutte le attivit�
        	 * recupero l'attivit� scelta
        	 * controllo se la localit� � compatibile
        	 * rinvio il controllo alla jsp chiamante(configureStayParameter)
        	 */
        	forward(request, response, "/viewActivitySearchResults.jsp");
        }
        
        
        
        

        
        
        

	}//Fine processRequest
}//Fine Controller
