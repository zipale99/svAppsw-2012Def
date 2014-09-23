package resources;

import java.util.List;

public class ElencoOptionBean {
	
	private List<Option> elencoOptions;

	public List<Option> getElencoOptions() {
		return elencoOptions;
	}

	public void setElencoOptions(List<Option> elencoOptions) {
		this.elencoOptions = elencoOptions;
	}    

	public void add(Option op){
		elencoOptions.add(op);
	}
	
	public void addOPV(OptionValue opv) {
		elencoOptions.add(opv);
	}
	
}
