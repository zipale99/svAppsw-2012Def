/**
 * OptionStandard contiene le informazioni sulle opzioni
 * OptionList contiene i valori possibili per le opzioni standard
 * OprionPers contiene il riferimento all'opzione standard e un riferimento alla personalizzazione scelta nella tabella optList
 */
package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * @author utente
 *
 */
public class Option {

	int id;
	String name;
	String desc;
	OptionValue value;
	private List<OptionValue> possibleValue;

	public Option(int id, String name, String desc, OptionValue value) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.value = value;
		possibleValue = new ArrayList<OptionValue>();
	}
	
	public Option() {
		// TODO Auto-generated constructor stub
	}

	public void add(OptionValue valore) {
        possibleValue.add(valore);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public OptionValue getValue() {
		return value;
	}

	public void setValue(OptionValue value) {
		this.value = value;
	}

	public List<OptionValue> getPossibleValue() {
		return possibleValue;
	}
	
	public OptionValue getOptionValue(int index) {
		return possibleValue.get(index);
	}
	
	public void setOptionValue(int index, OptionValue optionValue) {
		possibleValue.set(index, optionValue);
	}
	
	public int getPossibleValueSize() {
		return possibleValue.size();
	}
	
	public String toString() {
		return "Name: " + name + "; Desc: " + desc + "; Value: " + value.getValue()+"[+"+value.getPrice()+" euro]"+"";
	}

	public void setPossibleValue(List<OptionValue> possibleValue) {
		this.possibleValue = possibleValue;
		
	}
		
	
	
}
