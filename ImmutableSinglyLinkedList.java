import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

final class ImmutableSinglyLinkedList<T extends Comparable<T>> implements Iterable<T>{
	/*************
	 * Fields
	 *************/
	private final SNode<T> head; // immutable head node for the singly linked list
	private final int size; // immutable list size for the singly linked list

	/*******************************
	 * Initialize empty linked list
	 *******************************/
	public ImmutableSinglyLinkedList() {// initialize an empty list
		this.head = new SNode<T>(null, null); // initialize head node: data = null, next = null
		this.size = 0; // size is 0, since it's empty
	}

	/********************************************
	 * Initialize linked list from a single node
	 ********************************************/
	public ImmutableSinglyLinkedList(T input) {// initialize a list from a single input
		this.head = new SNode<T>(null, new SNode<T>(input, null)); // initialize head node: data = null, next = some node: data = input, next = null
		this.size = 1; // size is 1 since there's only 1 node
	}

	/***************************************
	 * Initialize linked list from an array
	 ***************************************/
	public ImmutableSinglyLinkedList(T[] srcArray) {
		this.size = srcArray.length; // size is the length of the T array
		this.head = new SNode<T>(null, cHelper(srcArray)); // paradigm shift, create a new immutable list with the elements in the array
		// head node: data = null, next = recursive function to create all the nodes all the way to the end of the array
	}

	/****************************
	 * Constructor Helper Method
	 ****************************/
	public final SNode<T> cHelper(T[] srcArray) {// recursive helper method
		if (srcArray.length == 1) {// terminal condition
			return new SNode<T>(srcArray[0], null); // when there's only 1 element in the array initialize the node: input = element in the array, next = null
		}

		else {
			return new SNode<T>(srcArray[0], cHelper(Arrays.copyOfRange(srcArray, 1, srcArray.length)));// recursive function call that removes the first element of the array each time it's called
			// Arrays.copyOfRange(what array, starting where, where to end)
		}
	}

	/***********************************
	 * Gets the size of the linked list
	 ***********************************/
	public final int getSize() {
		return this.size; // get size of immutable list
	}

	/****************************************
	 * Checks to see if linked list is empty
	 ****************************************/
	public final boolean isEmpty() {
		return this.size == 0; // check to see if the immutable list is empty
	}

	/******************************************
	 * Get first element in the list (not head)
	 ******************************************/
	public final T getFirstElement() {
		return this.head.getNext().getValue(); // get the first element in the immutable list
	}

	/*****************************************
	 * Get last element in the list (not tail)
	 *****************************************/
	public final T getLastElement() { // get last element in the immutable list
		SNode<T> currentIndex = this.head.getNext(); // starting position

		for (int i = 0; i < this.size - 1; i += 1) {// go to the end of the list 0-> (n-1)
			currentIndex = currentIndex.getNext(); // change reference to the next node
		}

		return currentIndex.getValue(); // return the value of the last node in the list
	}

	/*************************
	 * Get a node at index "x"
	 *************************/
	public final T getAtIndex(int x) {// get an element at position x
		Iterator<T> myIterator = this.iterator(); // list iterator

		for (int i = 0; i < this.size; i += 1) {
			T myValue = myIterator.next();

			if (i == x) { // if the current index matches return value at that index
				return myValue;
			}
		}

		return null; // no value found
	}

	/************************************************
	 * Insert a node to the front of the linked list
	 ************************************************/
	public final ImmutableSinglyLinkedList<T> addToFrontOfList(T toAdd) { // add two lists together and create a single list
		ImmutableSinglyLinkedList<T> list2 = new ImmutableSinglyLinkedList<T>(toAdd); // create the first list with the added input

		if (this.size == 0) { // check to see if the list you want to add to is empty, if it is return the newly created single list
			return list2;
		}

		else { // if not concatenate the two lists
			return list2.combineList(this); // (this list) will be appended to list2
		}
	}

	/**********************************************
	 * Insert a node to the end of the linked list
	 **********************************************/
	public final ImmutableSinglyLinkedList<T> addToEndOfList(T toAdd) { // add two lists together
		ImmutableSinglyLinkedList<T> list2 = new ImmutableSinglyLinkedList<T>(toAdd); // create the first list with the added input

		if (this.size == 0) { // check to see if the list you want to add to is empty, if it is return the newly created single list
			return list2;
		}

		else {
			return this.combineList(list2); // (list will be appended to (this list)
		}
	}

	/**************************************************
	 * Delete a node from the front of the linked list
	 **************************************************/
	public final ImmutableSinglyLinkedList<T> deleteFromFrontOfList() { // remove the first element of the list
		return new ImmutableSinglyLinkedList<T>(
				Arrays.copyOfRange(ImmutableSinglyLinkedList.toArray(this), 1, this.getSize())); // convert the list to an array and remove the first element of that array and return a newly created list with the array
	}

	/*******************************
	 * Delete a node at a index "x"
	 *******************************/
	public final ImmutableSinglyLinkedList<T> deleteAtIndex(int x) {
		T[] firstHalf = Arrays.copyOfRange(ImmutableSinglyLinkedList.toArray(this), 0, x); // 0 to x
		T[] secondHalf = Arrays.copyOfRange(ImmutableSinglyLinkedList.toArray(this), x + 1, this.getSize()); // x+1 to end
		T[] removedArr = concatenateArrays(firstHalf, secondHalf, this); // combine the halves

		return new ImmutableSinglyLinkedList<T>(removedArr); // return new list
	}

	/**************************************************
	 * Delete a node from the end of the linked list
	 **************************************************/
	public final ImmutableSinglyLinkedList<T> deleteFromEndOfList() { // remove the last element of the list
		return new ImmutableSinglyLinkedList<T>(
				Arrays.copyOfRange(ImmutableSinglyLinkedList.toArray(this), 0, this.getSize() - 1));// convert the list to an array and remove the last element (n-1) of that array and return a newly created list with the array
	}

	/************************
	 * Concatenate two lists
	 ************************/
	public final ImmutableSinglyLinkedList<T> combineList(ImmutableSinglyLinkedList<T> otherList) {
		T[] firstArr = ImmutableSinglyLinkedList.toArray(this); // static method call to create a list out of immutable singly linked list
		T[] secondArr = ImmutableSinglyLinkedList.toArray(otherList); // static method call to create a list out of the other immutable singly linked list

		return new ImmutableSinglyLinkedList<T>(concatenateArrays(firstArr, secondArr, otherList)); // method call to concatenate both lists
	}

	/*************************
	 * Concatenate two Arrays
	 *************************/
	@SuppressWarnings("unchecked")
	public final T[] concatenateArrays(T[] arr1, T[] arr2, ImmutableSinglyLinkedList<T> otherList) { // concatenate the arrays, you need the 3rd parameter to determine the type of array that has to be created
		int mergedArrSize = arr1.length + arr2.length; // the size of the new array is the length of both arrays
		T[] mergedArr = (T[]) Array.newInstance(otherList.getFirstElement().getClass(), mergedArrSize); // create a new array of the type from otherList and newly made size

		System.arraycopy(arr1, 0, mergedArr, 0, arr1.length); // populate the newly created array with the first array
		// System.arraycopy(what array, starting from where in the previous array, to what array, starting position of the dest. array, to where in the dest. array)
		System.arraycopy(arr2, 0, mergedArr, arr1.length, arr2.length); // populate the newly created array with the second array starting where the first array finished in the newly created array

		return mergedArr;
	}

	/*********************************************
	 * Helper Method convert linked list to Array
	 *********************************************/
	@SuppressWarnings("unchecked")
	public static final <T extends Comparable<T>> T[] toArray(ImmutableSinglyLinkedList<T> myList) { // convert the immutable singly linked list to an array
		T[] resultArray = (T[]) Array.newInstance(myList.getFirstElement().getClass(), myList.getSize()); // create an array of the type from the first element in the immutable singly linked list of the size of the immutable singly linked list

		Iterator<T> myIterator = myList.iterator(); // create an iterator for otherList

		for (int i = 0; i < myList.getSize(); i += 1) { // iterate to the size of the immutable list
			T myValue = myIterator.next();
			resultArray[i] = myValue; // copy the values from otherList to the resultArray
		}

		return resultArray;
	}

	/************************************************
	 * Checks to see if the queue's values are equal
	 ************************************************/
	public final boolean isEqual(ImmutableSinglyLinkedList<T> otherList) { // check to see if the lists are equal
		Iterator<T> thisIterator = this.iterator(); // create an iterator for this immutable list
		Iterator<T> otherIterator = otherList.iterator(); // create an iterator for the other immutable list

		if (this.getSize() != otherList.getSize()) // if the sizes aren't equal, the lists aren't equal
			return false;

		for (int i = 0; i < this.getSize(); i += 1) { // loop for the size of the list
			T t1 = thisIterator.next(); // assign the current node's value and set the current position of the node to the next
			T t2 = otherIterator.next(); // assign the current node's value and set the current position of the node to the next

			if (t1.compareTo(t2) != 0) // find the first mismatch, if there is, they're not equal
				return false;
		}

		return true; // everything was equal if it gets to here
	}

	/************************************
	 * Implements the Iterable interface
	 ************************************/
	@Override
	public final Iterator<T> iterator() {
		return new Iterator<T>() { // Anonymous Class
			SNode<T> currentIndex = head.getNext(); // starting position is the first element following the head

			@Override
			public boolean hasNext() {
				return currentIndex.hasNext(); // check to see if it has a next reference using the node's hasNext method
			}

			@Override
			public T next() {
				T data = currentIndex.getValue(); // get current node's value

				if (hasNext()) { // if it has a next reference
					currentIndex = currentIndex.getNext(); // change current node to the next node

					return data; // return the current node's value
				}

				else {
					// next reference is null, so don't change the current index
					return data; // return the current node's value
				}
			}
		};
	}

	/************************
	 * Node's Value toString
	 ************************/
	@Override
	public final String toString() {
		String result = ""; // empty string
		Iterator<T> myIterator = this.iterator(); // create an iterator for this list

		for (int i = 0; i < this.getSize(); i += 1) { // loop through the whole list
			T myValue = myIterator.next(); // get the next node if there is one

			result += myValue; // add the value to the string
			result += " -> ";
		}

		result += "End of list"; // end of the list

		return result; // return the string
	}
}
