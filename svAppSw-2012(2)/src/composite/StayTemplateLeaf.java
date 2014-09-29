/**
 * Questa classe rappresenta un singolo componente Leaf generico che a sua volta può essere un Transpor, un Accomodation o un HMS (che la estendono).
 */
package composite;

import java.util.List;

import resources.Option;

/**
 * @author utente
 *
 */
public class StayTemplateLeaf extends StayTemplate {
	
	String typeLeaf;
	List<Option> optionList;
	
	public StayTemplateLeaf(String startLoc, String endLoc, int durata, String nome, String typeLeaf, 
							double price, int timeOffset, List<Option> optionValueList) {
		super(startLoc, endLoc, durata, nome, price, timeOffset);
		this.typeLeaf = typeLeaf;
		this.setOptionList(optionValueList);
	}
	
	@Override
	public String getTypeLeaf() {
		return typeLeaf;
	}
	
	@Override
	public List<Option> getOptionList() {
		return optionList;
	}
	
	@Override
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	
	@Override
	public int getSizeOption() {
		return optionList.size();
	}
	
	@Override
	public void setTypeLeaf(String typeLeaf) {
		this.typeLeaf = typeLeaf;
	}
	
	@Override
	public Option getOption(int index) {
		return optionList.get(index);
	}
	
	@Override
	public void setOption(int index, Option option) {
		optionList.set(index, option);
	}
	
	
	@Override
	public int getOptionListSize() {
		return optionList.size();
	}
	
	@Override
	public String toString() {
		return super.toString() +
				"\nTypeLeaf: " + typeLeaf;
	}
		
}
