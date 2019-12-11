package store.event;

import simulator.Event;
import store.state.Customer;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class CustomerArrivedEvent extends Event {
	
	private Customer customer;
	/**
	 * Constructs a new Customer to arrive
	 * @param state
	 * @param time
	 */
	public CustomerArrivedEvent(StoreState state, double time) {
		super(state);
		super.eventDescription = "Arrived";
		this.customer = state.createNewCustomer();
		super.eventUserDescription = customer.toString();
		super.executeTime = time;

	}

	/**
	 * This method execute a new customer arriving to the store
	 * and letting the person in if the store is open and not full.
	 */
	@Override
	public void runEvent() {
		StoreState s = ((StoreState)state);
		s.updateState(this);
		
		if (s.storeIsOpen()) {
			s.increaseCustomerVisitedByOne();
			if(s.getCustomersInStore() >= s.getMAX_CUSTOMERS()) {
				s.increaseCustomerDeniedByOne();
			} else {
				s.increaseCustomersInStoreByOne();
				double pickTime = state.getElapsedTime() + s.getTimeCustomerPick();
				eventQueue.addEvent(new PickEvent(s, pickTime, customer));
			}
			double newTimeCustomer = s.getElapsedTime() + s.getTimeNextCustomer();
			eventQueue.addEvent(new CustomerArrivedEvent(s, newTimeCustomer));
		}
		
			
	}
}
