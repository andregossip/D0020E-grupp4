/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */

package store.time;

import java.util.Random;

/**
 * This class is used for computing the time of an event.
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;
	
	/**
	 * 
	 * @param lower lowest time for an event
	 * @param upper highest time for an event
	 * @param seed
	 */

	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * 
	 * @param lower lowest time for an event
	 * @param upper highest time for an event
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * 
	 * @return the double value that is needed for the events
	 */
	public double next() {
		return lower + rand.nextDouble() * width;
	}
	

	

}
