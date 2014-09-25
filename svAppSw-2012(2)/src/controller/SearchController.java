package controller;

import resources.ActivitySearchResults;
import resources.ItinerarySearchResults;
import resources.ServiceDB;
import resources.StaySearchResults;
import decorator.User;


public class SearchController {
	
	/**
	 * riempie un ArrayList contenente tutti gli itinerari presenti nel DB
	 * @param startLoc
	 * @param endLoc
	 * @return
	 */
	public static ItinerarySearchResults searchItinerary(String startLoc, String endLoc, int durata, String nome, String cat) {
			return ServiceDB.searchItinerary(startLoc, endLoc, durata, nome, cat);
	}

	
	/**
	 * riempie un ArrayList contenente tutti gli stayTemplate presenti nel DB
	 * @param startLoc
	 * @param endLoc
	 * @return
	 */
	public static StaySearchResults searchStayTemplate() {
		return ServiceDB.searchStayTemplate();
	}
	
	/*
	 * restituisce i leaf appartenenti a uno stayTemplate composto
	 */
	public static StaySearchResults searchLeafStayTemplate(int idStayTemplate) {
		return ServiceDB.searchLeafStayTemplate(idStayTemplate);
	}
	
	/*
	 * Restituisce le attività associato ad uno StayTemplate
	 */
	public static ActivitySearchResults searchActivityStayTemplate(int idStayTemplate) {
		return ServiceDB.searchActivityStayTemplate(idStayTemplate);
	}
	
	
	
}
