package store.state;

import java.util.ArrayList;

public class FIFO {

	private ArrayList<Customer> fifoQueue;

	public FIFO() {
		this.fifoQueue = new ArrayList<Customer>();

	}

	public void add(Customer customer) {
		fifoQueue.add(customer);
	}

	/**
	 * 
	 *
	 * 
	 * @return first customer in queue.
	 */
	public Customer getFirst() {
		return fifoQueue.remove(0);
	}

	public String toString() {
		String result = "[";
		if (fifoQueue.isEmpty()) {
			result += "]";
		}else{
			for (Customer customer : fifoQueue) {
				result += customer + ",";
			}
			result = result.substring(0,result.length()-1) + "]";
		}
		return result;
	}

	public boolean isEmpty() {
		if (fifoQueue.size() == 0) {
			return true;
		}
		return false;
	}

	public int queueSize() {
		return fifoQueue.size();
	}

}