/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

/**
 * In this class, the customer id will be returned every time the code is run. 
 */
package store.state;

public class Customer {
	/**
	 * This class describes an customer
	 */
	private int id;
	/**
	 * Constructor for the class Customer.
	 * Creates an customer with an id.
	 * @param id
	 */
	public Customer(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Customer id
	 */
	public String toString() {
		return Integer.toString(id);
	}

}
