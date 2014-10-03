/**
 * 
 */
package composite;
import java.util.*;


/**
 * @author utente
 *
 */
public class Itinerary extends StayTemplateComposite implements Comparator<StayTemplate> {
	
	private int position = 0;
	String user;
	String desc;
	String categoria;
	String stato;
	
	public Itinerary() {
	}
	
	
	public Itinerary(String user, String startLoc, String endLoc, int durata, String nome, String desc, String categoria, String stato, double price) {
		super(startLoc, endLoc, durata, nome, price, null);
		this.user = user;
		this.desc = desc;
		this.categoria = categoria;
		this.stato = stato;
		this.price = price;
	}
	
	public Itinerary(String user, String nome, String desc, String categoria) {
		this.user = user;
		this.nome = nome;
		this.desc = desc;
		this.categoria = categoria;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public void add(StayTemplate st) {
		position++;
		st.setTimeOffset(position);
		super.add(st);
	}
	
	public void addStay(StayTemplate st) {
		super.add(st);
	}
	
	public int compare(StayTemplate st1, StayTemplate st2) {
		 if ( st1.getTimeOffset() < st2.getTimeOffset() ) {
			 System.out.println("metodo compare, return -1");
		 	 return -1;
		 	}
		 	else if ( st1.getTimeOffset() > st2.getTimeOffset() ) {
		 		System.out.println("metodo compare, return 1");
		 		return 1;
		 	}
		 	System.out.println("metodo compare, return 0");
		 	return 0;
	} 
	
	
	public void sort() {
		Collections.sort(this.tree, this);
	}

	
	@Override
	public String toString() {
		return 	"NOME: " + nome +
				"; PARTENZA: " + startLoc + 
				"; ARRIVO: " + endLoc +
				"; DURATA(giorni): " + durata + 
				"; DESCRIZIONE: " + desc +
				"; PREZZO(euro): " + price +
				super.toString();
	}


	@Override
	public void setState() {
		System.out.println("metodo setState di Itinerary");
		System.out.println("stato itinerario : "+this.getStato());
		
		List <StayTemplate> tree = this.tree;
		
		System.out.println("citta finale primo leaf itinerario utente : "+tree.get(0).getEndLoc());
		System.out.println("stato attuale dell'itinerario : "+stato);
		int size = tree.size();
		System.out.println("hai inserito : " + size+" tappe");
		int i = 1;
		if(size > 2)
		  while(size > i) {
			if(!(tree.get(i-1).getEndLoc()).equals(tree.get(i).getStartLoc())) {
				System.out.println("entro nella condizione if");
				System.out.println("citta finale primo leaf itinerario utente : "+tree.get(1).getStartLoc());
				this.stato = "parziale non contiguo";
				System.out.println("asdfghjklò");
				System.out.println("stato attuale dell'itinerario : "+stato);
				return;
			}
			i++;
		  }
		this.stato = "completo contiguo";
		System.out.println("stato attuale dell'itinerario : "+stato);
	}

	






	
	
	
}

