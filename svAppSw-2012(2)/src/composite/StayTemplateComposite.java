/**
 * Questa classe rappresenta il Composite, ossia l'aggregazione di oggetti di tipo StayTemplate.
 * Pu� contenere oggetti di tipo StayTemplateLeaf oppure altri oggetti composti di tipo StayTemplateComposite.
 * 
 * Poich� a priori, quando andiamo a recuperare i leaf di uno stay template, non sappiamo se saranno uno o pi�,
 * trattiano ogni staytemplate come un composite. Nel caso di tappa singola, questo sar� composto da un solo leaf.
 */
package composite;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import resources.Activity;



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
	
	public List<Activity> getActivityList() {
		return activityList;
	}
	
	public void add(StayTemplate st) {
		tree.add(st);
	}
	
	public void addAc(Activity ac) {
		activityList.add(ac);
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
		String result = "";
		Iterator<StayTemplate> it = tree.iterator();
		while(it.hasNext()) {
			result = result + it.next().toString();
		}
		return result;
	}
	
	public void add (Activity act) {
		activityList.add(act);
	}	
	
	
	
}
