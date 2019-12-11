package store.state;

import simulator.Event;
import simulator.EventQueue;
import store.time.StoreTime;

/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public class StoreState extends simulator.SimState {

	// Constants
	private final long TIME_SEED;
	private final int MAX_CUSTOMERS;
	private final int MAX_REGISTERS;
	private final double TIME_STORE_CLOSE;
	private final double ARRIVAL_SPEED;
	private final double MIN_PICKING_TIME;
	private final double MAX_PICKING_TIME;
	private final double MIN_CHECKOUT_TIME;
	private final double MAX_CHECKOUT_TIME;
	private final double TIME_SIM_STOP;

	// Customer statistics
	private int customersPayed;
	private int customersInStore;
	private int customersVisited;
	private int customersDeniedEntry;
	private int customersInQueueTotal;

	// Checkout statistics
	private int registersOpen;
	private double queueTime;
	private double checkOutFreeTime;
	private double specElapsedTime;

	// Event descriptions
	private String eventDescription;
	private String customerWhoPerformedEvent;

	private boolean storeIsOpen;

	private FIFO checkOutQueue;
	private StoreTime storeTime;
	private CreateCustomer customerSpawn;

	/**
	 * Construct an instance of StoreState
	 * 
	 * @param TIME_SEED         Seed to generate random number
	 * @param MAX_CUSTOMERS     Maximum number of costumers allowed in store at once
	 * @param MAX_REGISTERS     Maximum number of registers available in store
	 * @param TIME_STORE_CLOSE  At what time store closes
	 * @param ARRIVAL_SPEED     Speed of which costumers arrive at
	 * @param MIN_PICKING_TIME  Minimum time a costumer can pick items in
	 * @param MAX_PICKING_TIME  Maximum time a costumer can pick items in
	 * @param MIN_CHECKOUT_TIME Minimum time a costumer can checkout in
	 * @param MAX_CHECKOUT_TIME Maximum time a costumer can checkout in
	 */
	public StoreState(long TIME_SEED, int MAX_CUSTOMERS, int MAX_REGISTERS, double TIME_STORE_CLOSE,
			double ARRIVAL_SPEED, double MIN_PICKING_TIME, double MAX_PICKING_TIME, double MIN_CHECKOUT_TIME,
			double MAX_CHECKOUT_TIME, EventQueue eventQueue, double TIME_SIM_STOP) {
		super(eventQueue);

		this.storeTime = new StoreTime(ARRIVAL_SPEED, TIME_SEED, MIN_PICKING_TIME, MAX_PICKING_TIME, MIN_CHECKOUT_TIME,
				MAX_CHECKOUT_TIME);
		this.checkOutQueue = new FIFO();
		this.customerSpawn = new CreateCustomer();

		this.TIME_SIM_STOP = TIME_SIM_STOP;
		this.customersInQueueTotal = 0;
		this.registersOpen = MAX_REGISTERS;
		this.TIME_SEED = TIME_SEED;
		this.MAX_CUSTOMERS = MAX_CUSTOMERS;
		this.MAX_REGISTERS = MAX_REGISTERS;
		this.TIME_STORE_CLOSE = TIME_STORE_CLOSE;
		this.ARRIVAL_SPEED = ARRIVAL_SPEED;
		this.MIN_PICKING_TIME = MIN_PICKING_TIME;
		this.MAX_PICKING_TIME = MAX_PICKING_TIME;
		this.MIN_CHECKOUT_TIME = MIN_CHECKOUT_TIME;
		this.MAX_CHECKOUT_TIME = MAX_CHECKOUT_TIME;
	}

	/**
	 * Create a new customer
	 *
	 * @return newCustomer() method
	 */
	public Customer createNewCustomer() {
		return customerSpawn.newCustomer();
	}

	public double getTIME_STORE_CLOSE() {
		return TIME_STORE_CLOSE;
	}

	/**
	 * Open a new registers for payment.
	 *
	 * else If the highest number of registers is biggar than the open
	 * registers @throws OpenRegisterFailedException
	 */
	public void openNewRegister() {
		if (registersOpen < MAX_REGISTERS) {
			registersOpen++;
		} else {
			// TODO: throw new OpenRegisterFailedException()
			throw new RuntimeException("All registers are open!");
		}
	}

	/**
	 * close a register
	 *
	 * Else if all registers are close @throws CloseRegisterFailedException else.
	 */
	public void closeOneRegister() {
		if (registersOpen > 0) {
			registersOpen--;
		} else {
			// TODO: throw new CloseRegisterFailedException()
			throw new RuntimeException("All registers are close!");

		}
	}

	/**
	 * 
	 * @return the time when the sim is stop.
	 */
	public double getTIME_SIM_STOP() {
		return TIME_SIM_STOP;
	}

	/**
	 * The store is open and can accept customers.
	 *
	 * @return storeIsOpen
	 */
	public boolean storeIsOpen() {
		return storeIsOpen;
	}

	/**
	 * The store is closed and doesn't accept new customers. Change storeIsOpen to
	 * false.
	 */
	public void closeStore() {
		if (storeIsOpen) {
			storeIsOpen = false;
		}
	}

	/**
	 * To open the store.
	 *
	 * StoreIsOpen change to true.
	 */
	public void openStore() {
		storeIsOpen = true;
	}

	/**
	 * Increase the number of customers who couldn't enter the store by one.
	 */
	public void increaseCustomerDeniedByOne() {
		customersDeniedEntry++;
	}

	/**
	 * Increase the number of customers that have been visited the store by one.
	 */
	public void increaseCustomerVisitedByOne() {
		customersVisited++;
	}

	/**
	 * Increase the number of customers payed by one.
	 */
	public void increaseCustomerPayedByOne() {
		customersPayed++;
	}

	/**
	 * Increase the number of customers payed by one
	 */
	public void increaseCustomersInStoreByOne() {
		customersInStore++;
	}

	/**
	 * Decrease amount of customers currently in store
	 */
	public void decreaseCustomersInStoreByOne() {
		customersInStore--;
	}

	/**
	 * Get the number of all the customers who are in the store.
	 *
	 * @return customers In Store
	 */
	public int getCustomersInStore() {
		return customersInStore;
	}

	/**
	 * Get the number of the registers which are open and accepted payment.
	 *
	 * @return registersOpen
	 */
	public int getRegistersOpen() {
		return registersOpen;
	}

	/**
	 *
	 *
	 * @return isEmpty() method.
	 */
	public boolean checkOutQueueIsEmpty() {
		return checkOutQueue.isEmpty();
	}

	/**
	 * 
	 * @return check out queue.
	 */
	public FIFO getCheckoutQueue() {
		return checkOutQueue;
	}

	/**
	 * Get queue time between two customers.
	 *
	 * @return timeNextCustomer()
	 */
	public double getTimeNextCustomer() {
		return storeTime.timeNextCustomer();
	}

	/**
	 * Get check out time between two customers.
	 *
	 * @return timeCustomerCheckOut()
	 */
	public double getTimeNextCustomerCheckout() {
		return storeTime.timeCustomerCheckOut();
	}

	/**
	 * Get pick time for customers.
	 *
	 * @return timeCustomerPick()
	 */
	public double getTimeCustomerPick() {
		return storeTime.timeCustomerPick();
	}

	/**
	 * Add new customer in pay queue
	 *
	 * @param customer
	 */
	public void addToCheckoutQueue(Customer c) {
		checkOutQueue.add(c);
		customersInQueueTotal++;
	}

	/**
	 * Get amount of customers that have been in queue in total.
	 * 
	 * @return amount of customers that have been in queue
	 */
	public int getCustomersInQueueTotal() {
		return customersInQueueTotal;
	}

	/**
	 * Get first customer in checkout queue
	 * 
	 * @return first customer in checkout queue
	 */
	public Customer getFirstFromCheckoutQueue() {
		return checkOutQueue.getFirst();
	}

	/**
	 * Get seed time.
	 *
	 * @return TIME_SEED
	 */
	public long getTIME_SEED() {
		return TIME_SEED;
	}

	/**
	 * Get the max number of customers.
	 *
	 * @return MAX_CUSTOMERS
	 */
	public int getMAX_CUSTOMERS() {
		return MAX_CUSTOMERS;
	}

	/**
	 * Get max number of registers.
	 *
	 * @return MAX_REGISTERS
	 */
	public int getMAX_REGISTERS() {
		return MAX_REGISTERS;
	}

	/**
	 * Get the speed of arrival.
	 *
	 * @return ARRIVAL_SPEED
	 */
	public double getARRIVAL_SPEED() {
		return ARRIVAL_SPEED;
	}

	/**
	 * Get the minimum time of picking.
	 *
	 * @return MIN_PICKING_TIME
	 */
	public double getMIN_PICKING_TIME() {
		return MIN_PICKING_TIME;
	}

	/**
	 * Get the maximum time of picking.
	 *
	 * @return MAX_PICKING_TIME
	 */
	public double getMAX_PICKING_TIME() {
		return MAX_PICKING_TIME;
	}

	/**
	 * Get the minimum time of check out.
	 *
	 * @return MIN_CHECKOUT_TIME
	 */
	public double getMIN_CHECKOUT_TIME() {
		return MIN_CHECKOUT_TIME;
	}

	/**
	 * get the spec elapsed time.
	 * 
	 * @return specElapsedTime
	 */
	public double getSpecElapsedTime() {
		return specElapsedTime;
	}

	/**
	 *
	 * Get the maximum time of check out.
	 *
	 * @return MAX_CHECKOUT_TIME
	 */
	public double getMAX_CHECKOUT_TIME() {
		return MAX_CHECKOUT_TIME;
	}

	/**
	 * Get the number of customers who paid for their things
	 *
	 * @return customersPayed
	 */
	public int getCustomersPayed() {
		return customersPayed;
	}

	/**
	 * Get the number of customers who visited the store
	 *
	 * @return customersVisited
	 */
	public int getCustomersVisited() {
		return customersVisited;
	}

	/**
	 * Get the number of the customers who wait in queue.
	 *
	 * @return customersInQueue
	 */
	public int getCustomersInQueue() {
		return checkOutQueue.queueSize();
	}

	/**
	 * Get the number of customers who couldn't go in the store.
	 *
	 * @return customersDeniedEntry
	 */
	public int getCustomersDeniedEntry() {
		return customersDeniedEntry;
	}

	/**
	 * Get the time while the checkout was empty
	 *
	 * @return checkoutFreeTime
	 */

	public double getCheckOutFreeTime() {
		return checkOutFreeTime;
	}

	/**
	 * Update the time that spent in queue
	 *
	 * @param peopleInQueueTime
	 */
	public void uppdateCustomersInQueueTime(double peopleInQueueTime) {
		queueTime += peopleInQueueTime;
	}

	@Override
	public void runSim() {
		startSimulator();
	}

	/**
	 * 
	 * @return amount of time customers have been queueing in total
	 */
	public double getQueueTime() {
		return queueTime;
	}

	/**
	 * 
	 * @return current simulated time
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * 
	 * @return type of event
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * 
	 * @return the customer who did an event
	 */
	public String getCustomerWhoPerformedEvent() {
		return customerWhoPerformedEvent;
	}

	@Override
	public void updateState(Event event) {
		if (storeIsOpen || customersInStore > 0) {
			// Updates registers wasted time
			checkOutFreeTime += registersOpen * (event.getExTime() - elapsedTime);

			// Updates time that people have been standing in the queue
			queueTime += getCustomersInQueue() * (event.getExTime() - elapsedTime);

			// Temp time for results:
			specElapsedTime = event.getExTime();

		}

		// DESCRIPTION OF EVENT

		// Updates event that occured
		eventDescription = event.getEventDescription();

		// Updates event that occured
		eventDescription = event.getEventDescription();

		// Updates which customer who performed the event.
		customerWhoPerformedEvent = event.getEventUserDescription();

		// Sets time to be the time that the event was executed.
		elapsedTime = event.getExTime();

		setChanged();
		notifyObservers();
	}


}
