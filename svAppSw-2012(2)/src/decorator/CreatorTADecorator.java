package decorator;

public class CreatorTADecorator extends DecoratorUser {

	public CreatorTADecorator(AbstractUserComponent user) { //Decoro con la funzionalit� di creator
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
}

