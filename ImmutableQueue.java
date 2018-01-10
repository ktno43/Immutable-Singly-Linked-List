import java.util.Iterator;

public class ImmutableQueue<T extends Comparable<T>> {
	/*************
	 * Fields
	 *************/
	private final ImmutableSinglyLinkedList<T> queueLinkedList; // immutable singly linked list instance

	/**************************************
	 * Constructor: Initialize empty queue
	 **************************************/
	public ImmutableQueue() {
		this.queueLinkedList = new ImmutableSinglyLinkedList<T>(); // initialize empty immutable list
	}

	/**********************************************
	 * Constructor: Initialize a queue with T data
	 **********************************************/
	public ImmutableQueue(T[] objectArray) {
		this.queueLinkedList = new ImmutableSinglyLinkedList<T>(objectArray); // initialize immutable list from T[]
	}

	/*****************************************************
	 * Constructor: Initialize a queue from another stack
	 *****************************************************/
	public ImmutableQueue(ImmutableSinglyLinkedList<T> mahList) {
		this.queueLinkedList = mahList; // initialize list from another immutable list
	}

	/*************************************************
	 * Add element of type T to the end of the queue
	 *************************************************/
	public final ImmutableQueue<T> enqueue(T element) {
		return new ImmutableQueue<T>(this.queueLinkedList.addToEndOfList(element)); // return a new immutable queue with the element added to the end (FIFO)
	}

	/*********************************************
	 * Remove element from the front of the queue
	 *********************************************/
	public final ImmutableQueue<T> dequeue() {
		if (this.queueLinkedList.getSize() == 0) {
			return new ImmutableQueue<T>(); // nothing to dequeue so return an empty queue
			// should probably throw an exception?
		}

		else {
			return new ImmutableQueue<T>(this.queueLinkedList.deleteFromFrontOfList()); // return a new immutable queue with the first element removed (FIFO)
		}
	}

	/**********************************
	 * Remove an element at position x
	 **********************************/
	public final ImmutableQueue<T> removeAtIndex(int x) {
		if (x < 0 || x > this.queueLinkedList.getSize()) { // tried to delete something out of range
			return this;
		}

		if (x == 0) {
			this.queueLinkedList.deleteFromFrontOfList(); // if it's 0, delete the first element of the list
		}

		return new ImmutableQueue<T>(this.queueLinkedList.deleteAtIndex(x));
	}

	/***********************************************************************************
	 * Return element of type T that was the first element in the queue w/o removing it
	 ***********************************************************************************/
	public final T front() {
		return this.queueLinkedList.getFirstElement(); // return the first element in the immutable queue
	}

	/**********************************************************************************
	 * Return element of type T that was the last element in the queue w/o removing it
	 **********************************************************************************/
	public final T back() {
		return this.queueLinkedList.getLastElement(); // return the last element in the immutable queue
	}

	/**********************************************************************
	 * Return the element of type T that was at index "x" w/o removing it
	 **********************************************************************/
	public final T atIndex(int x) {
		if (x < 0 || x > this.queueLinkedList.getSize()) { // make sure the index is within range
			return null;
		}

		return this.queueLinkedList.getAtIndex(x); // return the element at position x in the immutable queue
	}

	/*************************
	 * Get size of the queue
	 *************************/
	public final int size() {
		return this.queueLinkedList.getSize(); // get the size of the immutable queue
	}

	/*****************************************
	 * Checks to see if the queues are equal
	 *****************************************/
	public final boolean isEqual(ImmutableQueue<T> otherImmutableQueue) {
		return this.queueLinkedList.isEqual(otherImmutableQueue.queueLinkedList); // check to see if the immutable queues are equal
	}

	/*************************************
	 * Check to see if the queue is empty
	 *************************************/
	public final boolean isEmpty() {
		return this.queueLinkedList.isEmpty(); // check to see if the immutable queue is empty
	}

	/******************
	 * Empty the queue
	 ******************/
	public final ImmutableQueue<T> empty() {
		return new ImmutableQueue<T>(); // empty the immutable queue by returning a new immutable queue
	}

	/************************************
	 * Implements the iterable interface
	 ************************************/
	public final Iterator<T> iterator() {
		return this.queueLinkedList.iterator(); // create an iterator for the immutable queue
	}

	/*****************************
	 * Queue Linked List toString
	 *****************************/
	public final String toString() {
		return this.queueLinkedList.toString(); // immutable queue to string representation
	}
}
