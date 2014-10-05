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
	
	
	public void addTransferStay(StayTemplate tr, int offset) {
		System.out.println("setTransferStay di Itinerary");
		super.tree.get(offset).setTimeOffset(offset);//perch� nella view offset parte da 0;pos parte da 1!!!
		position++;
		offset+=1;
		tr.setTimeOffset(offset);	
		super.add(tr);							
		int actual = offset;
		while(offset < position-1) {//potrei mettere la size di tree
			super.tree.get(offset).setTimeOffset(++actual);		
			offset++;
		}
		for(int i = 0; i < position; i++) {
			System.out.println("offset prima di sort:"+super.tree.get(i).getTimeOffset());
		}
		this.sort();
		for(int i = 0; i < position; i++) {
			System.out.println("offset dopo di sort:"+super.tree.get(i).getTimeOffset());
		}
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
	public boolean equals(Object obj) {
		System.out.println("metodo equals");
		Itinerary it = (Itinerary)obj;
		if (this.id == it.id) {
			System.out.println("metodo equals return true");
			return true;
		}
		else {
			System.out.println("metodo equals return false");
			return false;
		}
	}
	

	
	@Override
	public String toString() {
		return 	super.toString() + "<br>"
				+ "<b>CATEGORIA:</b> " + categoria
				+ "<br>"
				+ "<b>DESCRIZIONE:</b> " + desc
				+ "<br>"
				+ "<b>STATO:</b> " + stato;
	}


	






	
	
	
}

