final class SNode<T extends Comparable<T>> {
	/*********
	 * Fields
	 *********/
	final T data; // immutable data for some node
	final SNode<T> nextNode; // immutable next reference for some node

	/*************************************
	 * Constructor: Create a single node
	 *************************************/
	public SNode(T inputData) {
		this(inputData, null); // constructor to initialize some node: data = inputData, next = null
	}

	/*************************************
	 * Constructor: Create a single node with specific next node
	 *************************************/
	public SNode(T inputData, SNode<T> next) {
		this.data = inputData; // initialize node: data = inputData
		this.nextNode = next; // initialize next reference: nextNode = next
	}

	/*************************************
	 * Get node's value
	 *************************************/
	public final T getValue() {
		return this.data; // return the node's data
	}

	/*************************************
	 * Get next node in sequence
	 *************************************/
	public final SNode<T> getNext() {
		return this.nextNode; // return the next reference to the current node
	}

	/*************************************
	 * Checks the status of the next node
	 *************************************/
	public final Boolean hasNext() {
		return (this.nextNode != null); // check to see if the node has a next node
	}
}
