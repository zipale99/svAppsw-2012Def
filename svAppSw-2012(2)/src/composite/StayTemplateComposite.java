/**
 * Questa classe rappresenta il Composite, ossia l'aggregazione di oggetti di tipo StayTemplate.
 * Può contenere oggetti di tipo StayTemplateLeaf oppure altri oggetti composti di tipo StayTemplateComposite.
 * 
 * Poichè a priori, quando andiamo a recuperare i leaf di uno stay template, non sappiamo se saranno uno o più,
 * trattiano ogni staytemplate come un composite. Nel caso di tappa singola, questo sarà composto da un solo leaf.
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
	
	@Override
	public void add (StayTemplate st) {
		tree.add(st);
	}
	
	@Override
	public void remove (int index) {
		tree.remove(index);
	}
	
	@Override
	public StayTemplate getStayTemplate(int i) {
		return tree.get(i);
	}
	
	@Override
	public int getSize() {
		return tree.size();
	}
	
	@Override
	public String toString() { 
		return super.toString();
	}
	
	@Override
	public List<Activity> getActivityList() {
		return activityList;
	}
	
	@Override
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
	@Override
	public String print() {
		return tree.toString();
	}
	
	@Override
	public boolean verificaCompatibilita(String location) {
		List<String> locationLeaf = new ArrayList<String>();
		for (StayTemplate st : this.tree) {
			locationLeaf.add(st.getStartLoc());
			locationLeaf.add(st.getEndLoc());
		}
		return locationLeaf.contains(location);
	}
	
	
	
}
