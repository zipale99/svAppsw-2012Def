//import java.util.ArrayList;

import composite.Itinerary;

//import controller.*;
import decorator.*;
import resources.*;

public class TestMethod {

	public static void main(String[] args) {

		System.out.println("eseguo metodo per ricerca itinerari generico");
		
		User user = new User("","gio","TA");
		System.out.println("creato oggetto user....");
		Integer i = 10;
		for (Itinerary it : ServiceDB.riempiItDaDB(user)) {    
			it.toString();
		}
		System.out.println("chiamato metodo searchItinerary");
		System.out.println("chiamo metodo di serviceDB...");
		
		for (Itinerary it : ServiceDB.searchItinerary("torino", "roma", i, "buh", "df")) {    
			it.toString();
		}
		
		
		
	}

}
