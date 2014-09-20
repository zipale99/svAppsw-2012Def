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
		
		
		
		utente.searchItinerary("", "", 5, "", "");
		System.out.println(utente.getItineraryList().toString());
		
		
		
	}

}
