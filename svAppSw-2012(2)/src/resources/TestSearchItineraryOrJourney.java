package resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSearchItineraryOrJourney {

	@Test
	public void testSearchItinerary() {
		//fail("Not yet implemented");
		/**
		 * asserzione : ciò che è vero nel caso non ci siano errori nel metodo; in tal caso la barra
		 * sarà verde e non ci saranno errori.
		 */
		//sarà verificato se nella tabella itinerario ci sarà una località di partenza corrispondente a torino
		assertEquals("torino", ServiceDB.searchItinerary("torino","roma",5,"","").getElencoItinerari().get(0).getStartLoc());
		
		//sarà verificato se nella tabella itinerario ci sarà una località di destinazione corrispondente a roma
		assertEquals("roma", ServiceDB.searchItinerary("torino","roma",5,"","").getElencoItinerari().get(0).getEndLoc());
		
		//sarà verificato se nella tabella itinerario ci sarà una durata=5 di tinerario da torino a roma
		assertEquals(5, ServiceDB.searchItinerary("torino","roma",-1,"","").getElencoItinerari().get(0).getDurata());
	
	}
	
	
	
	

}
