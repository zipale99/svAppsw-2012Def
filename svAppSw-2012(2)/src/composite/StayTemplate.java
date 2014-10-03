/**
 * Questa classe astratta rappresenta il Component, è estesa da StayTemplateLeaf e da StayTemplateComposite
 */
package composite;

import java.util.Comparator;
import java.util.List;

import resources.Activity;
import resources.Option;

/**
 * @author utente
 *
 */
public abstract class StayTemplate implements Cloneable{
	
	int id;
	int idStay;
	String startLoc;
	String endLoc;
	int durata;
	String nome;
	double price;
	int timeOffset;

	
	/*
	 * Costruttore di default, viene chiamato da StayTemplateComposite quando 
	 * viene creato un composite per la realizzazione di un itinerario
	 */
	public StayTemplate() {
	}
	
	public StayTemplate(String startLoc, String endLoc, int durata, String nome, double price, int timeOffset) {
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.durata = durata;
		this.nome = nome;
		this.price = price;
		this.timeOffset = timeOffset;
	}


	
	@Override
	public String toString() {
		return  "; startLoc: " + startLoc +
				"; endLoc: " + endLoc +
				"; durata: " + durata +
				"; nnome: " + nome + 
				"; prezzo: " + price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartLoc() {
		return startLoc;
	}

	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}

	public String getEndLoc() {
		return endLoc;
	}

	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(int timeOffset) {
		this.timeOffset = timeOffset;
	}
	
	public int getOptionListSize() {
		return -1;
	}
	
	public StayTemplate getStayTemplate(int i) {
		return null;
	}
	
	public int getSize() {
		return -1;
	}
	
	public List<Activity> getActivityList() {
		return null;
	}

	public void setActivityList(List<Activity> activityList) {}
	
	public String print() {
		return null;
	}
	
	@Override
	public Object clone() { 
		try { 
			StayTemplate stl = (StayTemplate)super.clone(); 
			return stl;
			} catch (CloneNotSupportedException e) { 
				e.printStackTrace(); 
				return null; 
				} 
		}

	
	public String getTypeLeaf() {
		return null;
	}
	
	public void add(StayTemplate st){}
	public void remove(int index){}
	
	public List<Option> getOptionList() {
		return null;
	}

	public void setOptionList(List<Option> optionList) {}
	
	public int getSizeOption() {
		return -1;
	}

	public void setTypeLeaf(String typeLeaf) {}
	
	public Option getOption(int index) {
		return null;
	}
	
	public void setOption(int index, Option option) {}

	public int getIdStay() {
		return idStay;
	}

	public void setIdStay(int idStay) {
		this.idStay = idStay;
	}
	
	public boolean verificaCompatibilita(String location) {
		return false;
	}
	
	 
	
		
}
