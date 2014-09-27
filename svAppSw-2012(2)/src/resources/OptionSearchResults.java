package resources;

import java.util.ArrayList;
import java.util.List;

public class OptionSearchResults {
	
	private List<Option> elencoOptions;
	
	public OptionSearchResults() {
		elencoOptions = new ArrayList<Option>();
	}

	public List<Option> getElencoOptions() {
		return elencoOptions;
	}

	public void setElencoOptions(List<Option> elencoOptions) {
		this.elencoOptions = elencoOptions;
	}    

	public void add(Option op){
		elencoOptions.add(op);
	}
	
	//Aggiungo valori all'opzione presente in index
	public void addOPV(int index, OptionValue opv) {
		elencoOptions.get(index).add(opv);
	}
	
}
