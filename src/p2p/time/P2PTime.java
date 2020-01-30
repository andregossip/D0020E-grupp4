

package p2p.time;

public class P2PTime {

	private ExponentialRandomStream nodeSendFile;
	//private UniformRandomStream customerPickGenerator, customerCheckOutGenerator;

	/**
	 * Construct a new Time Object
	 * 
	 * @param lambda
	 * @param seed
	 */

	public P2PTime(double lambda, long seed) {
		this.nodeSendFile = new ExponentialRandomStream(lambda, seed);
	}

	/**
	 * Returns time for next customer to arrive
	 * 
	 * @Return time for next customer
	 */
	public double timeSendNextFile() {
		return nodeSendFile.next();
	}
	
}
