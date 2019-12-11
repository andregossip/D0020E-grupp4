/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

package store.time;

public class StoreTime {

	private ExponentialRandomStream customerArrivedGenerator;
	private UniformRandomStream customerPickGenerator, customerCheckOutGenerator;

	/**
	 * Construct a new Time Object
	 * 
	 * @param lambda
	 * @param seed
	 * @param minPick 
	 * @param maxPick
	 * @param minCheckOut
	 * @param maxCheckOut
	 * 
	 */

	public StoreTime(double lambda, long seed, double minPick,double maxPick, double minCheckOut, double maxCheckOut) {
		this.customerArrivedGenerator = new ExponentialRandomStream(lambda, seed);
		this.customerPickGenerator = new UniformRandomStream(minPick,maxPick, seed);
		this.customerCheckOutGenerator = new UniformRandomStream(minCheckOut,maxCheckOut, seed);

	}

	/**
	 * Returns time for next customer to arrive
	 * 
	 * @Return time for next customer
	 */
	public double timeNextCustomer() {
		return customerArrivedGenerator.next();

	}

	/**
	 * Returns time duration for a pick event
	 * 
	 * @return duration for a pick event
	 */
	public double timeCustomerPick() {
		return customerPickGenerator.next();

	}

	/**
	 * Returns time duration for a checkout event
	 * 
	 * @return time duration for a checkout event
	 */
	public double timeCustomerCheckOut() {
		return customerCheckOutGenerator.next();
	}

	
}
