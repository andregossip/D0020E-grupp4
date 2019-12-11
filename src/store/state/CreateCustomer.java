/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

/**
 * In this class, a customer will be created each time the code is run.
 * We start with a customer who has 0 as an id 
 * and every time the class is run, a new customer with new id is created 
 */
package store.state;

public class CreateCustomer {
	/**
	 * This class creates new customers
	 */
	private int id;
	/**
	 * Constructs the first customer id
	 */
	public CreateCustomer() {
		id = 0;
	}

	/**
	 * 
	 * @return new customer with id++
	 */
	public Customer newCustomer() {
		return new Customer(id++);
	}

}
