/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */

package simulator;

import java.util.ArrayList;

public class EventQueue {
	private ArrayList<Event> eventQueue;
	protected SimState state;

	/**
	 * Create a new instance of EventQueue
	 * 
	 * @param state
	 */
	public EventQueue() {
		this.eventQueue = new ArrayList<Event>();
	}

	public boolean getEventQueueIsEmpty() {
		return eventQueue.size() == 0;
	}

	/**
	 * Adds an event to the event queue
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		eventQueue.add(event);
	}

	public void removeEvent() {
		eventQueue.remove(0);
	}

	/**
	 * 
	 * @return the event first in line.
	 */
	public Event getEvent() {
		if (eventQueue.size() == 0) {
			throw new RuntimeException("Queue is empty");
		}
		// Sorts the current array via it's getExTime method.
		eventQueue = sortEvent(eventQueue);
		return eventQueue.get(0);
	}

	/**
	 * 
	 * @return the size of event queue array.
	 */
	public int getQueueSize() {
		return eventQueue.size();
	}

	/**
	 * TODO 
	 * @param list
	 * @return 
	 */
	private static ArrayList<Event> sortEvent(ArrayList<Event> list) {
		if (list.size() == 1) {
			return list;
		}
		int midPoint = list.size() / 2;
		int startIndex = 0;

		ArrayList<Event> tempLeft = new ArrayList<Event>();
		ArrayList<Event> tempRight = new ArrayList<Event>();

		while (startIndex < midPoint) {
			tempLeft.add(list.get(startIndex));
			startIndex++;
		}
		while (midPoint < list.size()) {
			tempRight.add(list.get(midPoint));
			midPoint++;
		}
		/// Blir lite dumt med namnen här men va fan, kommer på nå
		/// bättre ngn dag.
		tempLeft = sortEvent(tempLeft);
		tempRight = sortEvent(tempRight);

		return merge(tempLeft, tempRight);

	}

	
	private static ArrayList<Event> merge(ArrayList<Event> left, ArrayList<Event> right) {

		ArrayList<Event> resultingList = new ArrayList<Event>();

		int leftPointer, rightPointer;
		leftPointer = rightPointer = 0;

		while (leftPointer < left.size() || rightPointer < right.size()) {
			if (leftPointer < left.size() && rightPointer < right.size()) {
				if (left.get(leftPointer).getExTime() <= right.get(rightPointer).getExTime()) {
					resultingList.add(left.get(leftPointer));
					leftPointer++;
				} else {
					resultingList.add(right.get(rightPointer));
					rightPointer++;
				}
			}
			/// Getting events from the original array if there are any left.
			else if (leftPointer < left.size()) {
				resultingList.add(left.get(leftPointer));
				leftPointer++;
			} else if (rightPointer < right.size()) {
				resultingList.add(right.get(rightPointer));
				rightPointer++;

			}

		}

		return resultingList;

	}

}
