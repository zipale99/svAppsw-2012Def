/**
 * Allo stesso modo del ConcretComponent, anche la classe DecoratorUser implementa l'interfaccia AbstracUser, ha il compito di decorare l'oggetto User
 * con i ConcreteDecorator(Creator)
 * 
 * Per l'implementazione del singleton, è stato utilizzato un metodo statico (decora) che deve essere chiamato per restituire
 * l'istanza del decoratorUser. L'oggetto decoratorUser verrà istanziato solo la prima volta che il metodo è invocato.
 * Le volte successive sarà restituito un riferimento allo stesso oggetto.
 */
package decorator;


import java.util.List;

import resources.ServiceDB;
import composite.*;
import resources.*;

/**
 * @author Alessandro
 *
 */
public abstract class DecoratorUser extends AbstractUserComponent {
	
	
	protected AbstractUserComponent user; //Riferimento al user
	private Itinerary itinerary;
	private StayTemplate stay;
	
	private static DecoratorUser instance = null; //Riferimento a un istanza di se stessa
	
	//Questo costruttore viene utilizzato solo allo scopo di assegnare la decorazione al user
	public DecoratorUser(AbstractUserComponent user) { 
		this.user = user; //Assegno la decorazione al user
	}
	
	public static DecoratorUser decora(AbstractUserComponent user){
	        if(instance == null){
	        	System.out.println("Creo e restituisco una nuova instanza del DecoratorUser specifico");
	        	if (user.getRuolo().equals("Customer"))
	        		instance = new CreatorCustomerDecorator(user);
	        	if (user.getRuolo().equals("TA"))
	        		instance = new CreatorTADecorator(user);
	        }
	        return instance;
	}
	
	@Override
	public void invalida() {
		instance = null;
	}
	
	@Override
	public void createItinerary(){
		 itinerary = new Itinerary();
		 itinerary.setUser(this.getUsername());
	}
	
	@Override
	public void provideBasicInfo(String nome, String descrizione, String categoria){
		 itinerary = new Itinerary();
		 itinerary.setUser(this.getUsername());
		 itinerary.setNome(nome);
		 itinerary.setDesc(descrizione);
		 itinerary.setCategoria(categoria);
	 }
	
	@Override
	public void setOptionValue(int idOption, int idOptionValue, int idLeaf) {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+idOption);
		stay.getStayTemplate(idLeaf).getOptionList().get(idOption).setValue(stay.getStayTemplate(idLeaf).getOptionList().get(idOption).getPossibleValue().get(idOptionValue));
	}
	
	public String getRuolo() {
		return user.getRuolo();
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getPwd() {
		return user.getPwd();
	}
	
	public AbstractUserComponent getUser() {
		return user;
	}
	
	@Override
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	@Override
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	
	@Override
	public StayTemplate getStay() {
		return stay;
	}
	
	@Override
	public void setStay(StayTemplate stay) {
		this.stay = (StayTemplate)stay.clone();
	}
	
	public void setUsername(String username) {
		user.setUsername(username);
	}

	public void setPwd(String pwd) {
		user.setPwd(pwd);
	}

	public void setRuolo(String ruolo) {
		user.setRuolo(ruolo);
	}
	
	@Override
	public void myItinerary() {
		user.myItinerary();
	}
	
	@Override
	public List<Itinerary> getItineraryList() {
		return user.getItineraryList();
	}
	
	/*Prima volta che lo chiama decUser smista l'operazione e ritorna il decorator appropriato
	 * Quanod sarachiamato una seconda volta, viene invocato il metodo create del concrete dec appropriato
	 * che restituisce l'istanza creata in precedenza. Dunque è il costruttore del concrete dec che deve tenere traccia 
	 * dell'istanza ed eventualmente restituire l'istanza vecchia.
    */

}
