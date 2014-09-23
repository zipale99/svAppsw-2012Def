//import java.util.ArrayList;

import java.awt.List;
import java.util.ArrayList;

import resources.*;
import composite.Itinerary;
import composite.StayTemplate;
import composite.StayTemplateComposite;
//import controller.*;
import decorator.*;

public class TestMethod {

	public static void main(String[] args) {

		System.out.println("eseguo metodo per ricerca itinerari attinenti ad un utente...");		
		User user = new User("filomena","p3","customer");
		System.out.println("creato oggetto user....");
		System.out.println();
		for (Itinerary it : ServiceDB.riempiItDaDB(user))    
			System.out.println(it.toString());
		System.out.println();
		
		System.out.println("eseguo metodo per ricercare tutti gli itinerari con certi parametri del form...");		
		for (Itinerary it : ServiceDB.searchItinerary("torino", "roma", 2, "buh", "df"))    
			System.out.println(it.toString());
		System.out.println();
		
		System.out.println("eseguo metodo per stampare gli stayTemplate presenti nel DB...");
		StayTemplateComposite stc = ServiceDB.searchStayTemplate();
		//int size = stc.getSize();
		StayTemplate st1 = stc.getStayTemplate(0);
		//while(size > 0) {
			System.out.println("prima localita' stayTemplate1 : "+st1.getStartLoc());
		//	size--;
		//}
			
		System.out.println("eseguo metodo per ottenere le attivita' associate allo stayTemplate1...");
		System.out.println("prima attivita' del primo stayTemplate : "+ServiceDB.searchActivityStayTemplate(1).get(0).getDesc());
		
		StayTemplateComposite stc2 = new StayTemplateComposite(); 
		ServiceDB.searchActivity(stc2);
		System.out.println("prima attivita' presente nel db : "+stc2.getActivityList().get(0).getDesc());
		
		
		
	}

}
