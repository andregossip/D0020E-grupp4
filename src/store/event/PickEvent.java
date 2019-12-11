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
public class PickEvent extends Event {

	private Customer customer;
	/**
	 * Constructs a new Pickevent
	 * @param state
	 * @param time
	 * @param customer
	 */
	public PickEvent(StoreState state, double time, Customer customer) {
		super(state);
		super.eventDescription = "Pick";
		super.eventUserDescription = customer.toString();
		super.executeTime = time;
		this.customer = customer;
	}
	/**
	 * This method executes a pick event for a customer
	 */
	@Override
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.updateState(this);
		if (s.getRegistersOpen() > 0) {
			s.closeOneRegister();
			eventQueue.addEvent(new CheckOutEvent(s, s.getElapsedTime() + s.getTimeNextCustomerCheckout(), customer));
		} else {

			s.addToCheckoutQueue(customer);
		}
	}
}
