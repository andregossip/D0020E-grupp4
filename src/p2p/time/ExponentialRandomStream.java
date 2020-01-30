
package p2p.time;

import java.util.Random;

/**
 * 
 * This class is used for computing the next arrival time of an event.
 *
 */

public class ExponentialRandomStream {

	private Random rand;
	private double lambda;

	public ExponentialRandomStream(double lambda, long seed) {
		rand = new Random(seed);
		this.lambda = lambda;
	}

	public ExponentialRandomStream(double lambda) {
		rand = new Random();
		this.lambda = lambda;
	}
	
	/**
	 * 
	 * @return the double value that is needed for the event time.
	 */

	public double next() {
		return -Math.log(rand.nextDouble()) / lambda;
	}


}
