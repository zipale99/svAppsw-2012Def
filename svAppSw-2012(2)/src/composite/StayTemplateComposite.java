/**
 * Questa classe rappresenta il Composite, ossia l'aggregazione di oggetti di tipo StayTemplate.
 * Pu� contenere oggetti di tipo StayTemplateLeaf oppure altri oggetti composti di tipo StayTemplateComposite.
 * 
 * Poich� a priori, quando andiamo a recuperare i leaf di uno stay template, non sappiamo se saranno uno o pi�,
 * trattiano ogni staytemplate come un composite. Nel caso di tappa singola, questo sar� composto da un solo leaf.
 */
package composite;

import java.util.List;
import java.util.ArrayList;

import resources.Activity;
import resources.Option;



/**
 * @author utente
 *
 */
public class StayTemplateComposite extends StayTemplate {
	
	List<StayTemplate> tree;
	List<Activity> activityList;
	
	
	public StayTemplateComposite(String startLoc, String endLoc, int durata, String nome, double price, List<Activity> activityList) {
		super(startLoc, endLoc, durata, nome, price, -1);
		tree = new ArrayList<StayTemplate>();
		this.activityList = activityList;
	}

	public StayTemplateComposite() {
		tree = new ArrayList<StayTemplate>();
	}
	
	public void add (StayTemplate st) {
		tree.add(st);
	}
	
	public void remove (StayTemplate st) {
		tree.remove(st);
	}
	
	public StayTemplate getStayTemplate(int i) {
		return tree.get(i);
	}
	
	public int getSize() {
		return tree.size();
	}
	
	@Override
	public String toString() { 
		return super.toString();
	}
	
	public List<Option> getOptionList() {
		return null;
	}
	

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
	public Option getOption(int index) {
		return null;
	}
	
	public int getOptionListSize() {
		return -1;
	}
	
	
	
	
	
}
