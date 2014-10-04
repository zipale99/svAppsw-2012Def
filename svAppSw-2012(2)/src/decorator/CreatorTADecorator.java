package decorator;

import resources.ServiceDB;

import composite.HandMadeStay;
import composite.StayTemplate;

public class CreatorTADecorator extends DecoratorUser {

	public CreatorTADecorator(AbstractUserComponent user) { //Decoro con la funzionalità di creator
		super(user);
	}
	
	public String getRuolo() {
		//super.getRuolo(); per conoscere il ruolo di prima
		return "CreatorTA";
	}
	
	/*
	 * public String getRuolo() {
		//super.getRuolo(); per conoscere il ruolo di prima
		return "Creator" + super.getRuolo();
	}
	 */
	
	@Override
	public void addHMS(String startLoc, String endLoc, String nome) {
		StayTemplate hms = new HandMadeStay(startLoc, endLoc, nome);
		ServiceDB.createHMS(hms);
		this.itinerary.add(hms);
	}
	
}

