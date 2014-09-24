package resources;

import java.util.ArrayList;
import java.util.List;

import composite.*;

public class StaySearchResults {

	 private List<StayTemplate> elencoStayTemplate;    
	    
	    /**
	     * senza argomenti come da regola dei Bean
	     **/
	    public StaySearchResults() {
			elencoStayTemplate = new ArrayList<StayTemplate>();
		}
	 
	    
	    
	    public void aggiungiComposite(int id, String user,String startLoc,String endLoc, String nome, int durata,String transport, double prezzo){
	    	List<Activity> activityList = new ArrayList<Activity>();
			StayTemplate st = new StayTemplateComposite(startLoc, endLoc, durata, nome, prezzo, activityList);
			st.setId(id);
		}
	    
	    //public void aggiungiLeaf()
	    
	   
	  
	    public List<StayTemplate> getElencoStayTemplate(){
			return elencoStayTemplate;
		}
	    
	    public void add(StayTemplate st) {
	    	elencoStayTemplate.add(st);
	    }
	    
	    public void addLeaf(StayTemplateLeaf stl) {
	    	elencoStayTemplate.add(stl);
	    }
	
}
