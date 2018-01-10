import java.util.Iterator;

public class ImmutableStack<T extends Comparable<T>> {
	/*************
	 * Fields
	 *************/
	private final ImmutableSinglyLinkedList<T> stackLinkedList; // immutable singly linked list instance

	/**************************************
	 * Constructor: Initialize empty stack
	 **************************************/
	public ImmutableStack() {
		this.stackLinkedList = new ImmutableSinglyLinkedList<T>(); // initialize empty immutable list
	}

	/**********************************************
	 * Constructor: Initialize a stack with T data
	 **********************************************/
	public ImmutableStack(T[] objectArray) {
		this.stackLinkedList = new ImmutableSinglyLinkedList<T>(objectArray); // initialize immutable stack from T[]
	}

	/*****************************************************
	 * Constructor: Initialize a stack from another stack
	 *****************************************************/
	public ImmutableStack(ImmutableSinglyLinkedList<T> src) {
		this.stackLinkedList = src; // initialize immutable list from another immutable list
	}

	/**************************************************
	 * Add element of type T to the front of the stack
	 **************************************************/
	public final ImmutableStack<T> push(T element) {
		return new ImmutableStack<T>(this.stackLinkedList.addToFrontOfList(element)); // return a new immutable stack with the element added to the front (FILO)
	}

	/*********************************************
	 * Remove element from the front of the stack
	 *********************************************/
	public final ImmutableStack<T> pop() {
		if (this.stackLinkedList.getSize() == 0) {
			return new ImmutableStack<T>(); // nothing to pop
			// should throw an exception?
		}

		else {
			return new ImmutableStack<T>(this.stackLinkedList.deleteFromFrontOfList()); // return a new immutable stack with the first element removed (FILO)
		}
	}

	/**********************************
	 * Remove an element at position x
	 **********************************/
	public final ImmutableStack<T> removeAtIndex(int x) {
		if (x < 0 || x > this.stackLinkedList.getSize()) { // tried to delete something out of range
			return this;
		}

		if (x == 0) {
			this.stackLinkedList.deleteFromFrontOfList(); // if it's 0, delete the first element of the list
		}

		return new ImmutableStack<T>(this.stackLinkedList.deleteAtIndex(x));
	}

	/***********************************************************************************
	 * Return element of type T that was the first element in the stack w/o removing it
	 ***********************************************************************************/
	public final T peek() {
		return this.stackLinkedList.getFirstElement(); // return the top most element of the immutable stack
	}

	/**********************************************************************
	 * Return the element of type T that was at index "x" w/o removing it
	 **********************************************************************/
	public final T atIndex(int x) {
		if (x < 0 || x > this.stackLinkedList.getSize()) { // make sure the index is within range
			return null;
		}

		return this.stackLinkedList.getAtIndex(x); // return the element at position x in the immutable stack
	}

	/*************************
	 * Get size of the stack
	 *************************/
	public final int size() {
		return this.stackLinkedList.getSize(); // get the size of the immutable stack
	}

	/****************************************
	 * Checks to see if the stacks are equal
	 ****************************************/
	public final boolean isEqual(ImmutableStack<T> otherImmutableStack) {
		return this.stackLinkedList.isEqual(otherImmutableStack.stackLinkedList); // check to see if the immutable stacks are equal
	}

	/*************************************
	 * Check to see if the stack is empty
	 *************************************/
	public final boolean isEmpty() {
		return this.stackLinkedList.isEmpty(); // check to see if the immutable stack is empty
	}

	/******************
	 * Empty the stack
	 ******************/
	public final ImmutableStack<T> empty() {
		return new ImmutableStack<T>(); // empty the stack by returning a new immutable stack
	}

	/************************************
	 * Implements the iterable interface
	 ************************************/
	public final Iterator<T> iterator() {
		return this.stackLinkedList.iterator(); // create an iterator for the immutable stack
	}

	/*****************************
	 * Stack Linked List toString
	 *****************************/
	public final String toString() {
		return this.stackLinkedList.toString(); // immutable stack to string representation
	}
}
