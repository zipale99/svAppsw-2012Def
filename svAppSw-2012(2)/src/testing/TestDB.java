package testing;

import resources.*;
import composite.*;
import decorator.User;

public class TestDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		User utente = new User("antonio", "", "");
		/*
		utente.recuperaMyItinerary();
		System.out.println(utente.getItineraryList().get(0).getStartLoc());
		*/
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		
		
		StaySearchResults st = ServiceDB.searchStayTemplate();
		System.out.println(st.get(0).getActivityList().toString());
		
		System.out.println(st.get(0).getStayTemplate(0).toString());
		System.out.println(st.get(0).getStayTemplate(1).toString());
		System.out.println(st.get(0).getStayTemplate(2).toString());
		System.out.println(st.get(0).getStayTemplate(3).toString()+"\n");
		
		//Recupero le opzioni e i possibili valori per il leaf2
		//OptionSearchResults optList = ServiceDB.getOptionLeaff(3,2);
		//stampo le opzioni associate al leaf2 e i possibili valori
		//System.out.println(optList.getElencoOptions().get(0).toString()+"\n");
		/*
		System.out.println(optList.getElencoOptions().get(0).getPossibleValue().toString()+"\n");
		System.out.println(optList.getElencoOptions().get(1).toString()+"\n");
		System.out.println(optList.getElencoOptions().get(1).getPossibleValue().toString());
		*/
		
		
		ItinerarySearchResults sir = ServiceDB.searchItinerary(null, null, 0, "", "");
			Itinerary it = sir.getElencoItinerari().get(0);
			ServiceDB.deleteItinerary(it);
		
		
		
	}

}
