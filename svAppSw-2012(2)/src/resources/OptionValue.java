/**
 * Rappresent oggetti di tipo optionValue, specifica un determinato valore per un opzione con un prezzo associato.
 * Questa classe � in relazione solo con la classe Option, in particolare un oggetto di tipo Option pu� contenere 
 * zero o pi� oggetti di tipo optionValue
 */
package resources;

/**
 * @author utente
 *
 */
public class OptionValue {

		int id;
		String value;
		double price;
		
		
		public OptionValue(String value, double price) {
			this.value = value;
			this.price = price;
		}


		public OptionValue() {
			// TODO Auto-generated constructor stub
		}


		public String getValue() {
			return value;
		}


		public void setValue(String value) {
			this.value = value;
		}


		public double getPrice() {
			return price;
		}


		public void setPrice(double price) {
			this.price = price;
		}
		
		@Override
		public String toString() {
			return "Valore: " + value + "[Costo: " + price + "]";
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}
		
		
}
