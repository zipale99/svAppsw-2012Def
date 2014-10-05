package decorator;

import resources.ServiceDB;

import composite.HandMadeStay;
import composite.StayTemplate;
import composite.*;

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
		StayTemplateComposite stc = new StayTemplateComposite();
		StayTemplate hms = new HandMadeStay(startLoc, endLoc, nome);
		stc.add(hms);
		ServiceDB.createHMS(hms);
		this.stay = stc;
		this.itinerary.add(stc);
	}
	
}

