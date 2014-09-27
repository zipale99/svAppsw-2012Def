package resources;

import java.util.ArrayList;
import java.util.List;

import composite.StayTemplate;

public class ActivitySearchResults {

    private List<Activity> elencoAttivita;    
    
    /**
     * senza argomenti come da regola dei Bean
     **/
    public ActivitySearchResults() {
		elencoAttivita = new ArrayList<Activity>();
	}
    
    
    public List<Activity> getElencoAttivita(){
		return elencoAttivita;
	}
    
    
    /**
     * aggiunge al bean elencoAttivita, un oggetto attivita settandovi i parametri
     * @param id
     * @param t
     * @param city
     * @param desc
     * @param durata
     * @param prezzo
     */
    
    public void aggiungi(int id,String t,String city,String desc, int durata,double prezzo){
		Activity att = new Activity();
		att.setIdActivity(id);
		att.setType(t);
		att.setLocation(city);
		att.setDesc(desc);
		att.setDurata(durata);
		att.setPrice(prezzo);
		elencoAttivita.add(att);
	}
    
    public void add(Activity a) {
    	elencoAttivita.add(a);
    }
    
    public int size() {
    	return elencoAttivita.size();
    }
    
    public Activity get(int index) {
    	return elencoAttivita.get(index);
    }
    
	
}
