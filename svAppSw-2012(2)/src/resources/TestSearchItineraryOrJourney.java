package resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSearchItineraryOrJourney {

	@Test
	public void testSearchItinerary() {
		//fail("Not yet implemented");
		/**
		 * asserzione : ci� che � vero nel caso non ci siano errori nel metodo; in tal caso la barra
		 * sar� verde e non ci saranno errori.
		 */
		//sar� verificato se nella tabella itinerario ci sar� una localit� di partenza corrispondente a torino
		assertEquals("torino", ServiceDB.searchItinerary("torino","roma",5,"","").getElencoItinerari().get(0).getStartLoc());
		
		//sar� verificato se nella tabella itinerario ci sar� una localit� di destinazione corrispondente a roma
		assertEquals("roma", ServiceDB.searchItinerary("torino","roma",5,"","").getElencoItinerari().get(0).getEndLoc());
		
		//sar� verificato se nella tabella itinerario ci sar� una durata=5 di tinerario da torino a roma
		assertEquals(5, ServiceDB.searchItinerary("torino","roma",-1,"","").getElencoItinerari().get(0).getDurata());
	
	}
	
	
	
	

}
