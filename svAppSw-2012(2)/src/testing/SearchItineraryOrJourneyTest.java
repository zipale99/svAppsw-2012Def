package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import resources.ServiceDB;

public class SearchItineraryOrJourneyTest {

	@Test
	public void testSearchItinerary() {
		assertEquals("torino", ServiceDB.searchItinerary("torino", "", 0, "", "").getElencoItinerari().get(0).getStartLoc());
		assertEquals("torino", ServiceDB.searchItinerary("torino", "roma", 0, "", "").getElencoItinerari().get(0).getStartLoc());
	}

}
