/**
 * Implementation of lists, using doubly linked elements, and dummy nodes.
 * Starter class for "9.10 Laboratory: Lists with Dummy Nodes"
 * Please modify this code by following the directions in  on page 216 of
 * Java Structures sqrt(7) edition by Duane Bailey.
 */

import structure5.*;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E> {

	// Use these variables inherited from DoublyLinkedList
	// Do not uncomment this!  Just use the variables as if they were uncommented
	/**
	* Number of elements within the list.
	*	protected int count;
	*/

	/**
	* Reference to head of the list.
	*
	protected DoublyLinkedNode<E> head;
	*/

	/**
	* Reference to tail of the list.
	*
	protected DoublyLinkedNode<E> tail;
	*/


	/**
	* Constructs an empty list.
	*
	* @post constructs an empty list
	* @big-o O(1).
	*/
	public LinkedList() {
		head = new DoublyLinkedNode<E>(null);
		tail = new DoublyLinkedNode<E>(null);
		head.setPrevious(null);
		tail.setNext(null);
		clear();
	}

	/**
	* Determine the number of elements in the list.
	*
	* @post returns the number of elements in list
	* @big-o O(1).
	* @return The number of elements found in the list.
	*/
	public int size() {
		return count;
	}

	/**
	* Determine if the list is empty.
	*
	* @post returns true iff the list has no elements.
	* @big-o O(1).
	* @return True iff list has no values.
	*/
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	* Remove all values from list.
	* @big-o O(1).
	* @post removes all the elements from the list
	*/
	public void clear() {
		count = 0;
		head.setNext(tail);
		tail.setPrevious(head);
	}

	/**
	* A private routine to add an element after a node.
	* @param value the value to be added
	* @param previous the node the come before the one holding value
	* @pre previous is non null
	* @big-o O(1).
	* @post list contains a node following previous that contains value
	*/
	protected void insertAfter(E value, DoublyLinkedNode<E> previous) {
		DoublyLinkedNode<E> valueNode = new DoublyLinkedNode<E>(value);
		DoublyLinkedNode<E> holder = previous.next();
		previous.setNext(valueNode);
		valueNode.setPrevious(previous);
		valueNode.setNext(holder);
		holder.setPrevious(valueNode);
		count++;
	}

	/**
	* A private routine to remove a node.
	* @param node the node to be removed
	* @pre node is non null
	* @post node node is removed from the list
	* @post returns the value of the node removed
	* @big-o O(1).
	* @return the value of the node removed
	*/
	protected E remove(DoublyLinkedNode<E> node) {
		node.previous().setNext(node.next());  //setting next reference of previous node to next reference of node.
		node.next().setPrevious(node.previous()); //setting previous reference of next node to previous reference of node.
		count--;
		return node.value();
	}


	/**
	* Add a value to the head of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds element to head of list
	* @big-o O(1).
	*/
	public void addFirst(E value) {
		Assert.pre(value != null, "Value is null");
		insertAfter(value, head);   //insert after head, which is 1st position
	}

	/**
	* Add a value to the tail of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds new value to tail of list
	* @big-o O(1).
	*/
	public void addLast(E value) {
		Assert.pre(value != null, "Value is null");
		insertAfter(value, tail.previous()); //insert node after the previous value to tail.
	}

	/**
	* Remove a value from the head of the list.
	* Value is returned.
	*
	* @pre list is not empty
	* @post removes first value from list
	* @big-o O(1).
	* @post Returns the value removed from the list.
	* @return The value removed from the list.
	*/
	public E removeFirst() {
		Assert.pre(!isEmpty(), "The list is empty");
		return remove(head.next());  //remove next node after head, which is 1st value.
	}

	/**
	* Remove a value from the tail of the list.
	*
	* @pre list is not empty
	* @post removes value from tail of list
	* @post Returns the value removed from the list.
	* @big-o O(1).
	* @return The value removed from the list.
	*/
	public E removeLast() {
		Assert.pre(!isEmpty(), "The list is empty");
		return remove(tail.previous()); //remove value before tail, which is last value.
	}

	/**
	* Get a copy of the first value found in the list.
	*
	* @pre list is not empty
	* @post returns first value in list.
	* @big-o O(1).
	* @return A reference to first value in list.
	*/
	public E getFirst() {
		Assert.pre(!isEmpty(), "The list is empty");
		return head.next().value(); //return value after head, which is 1st value.
	}

	/**
	* Get a copy of the last value found in the list.
	*
	* @pre list is not empty
	* @post returns last value in list.
	* @big-o O(1).
	* @return A reference to the last value in the list.
	*/
	public E getLast() {
		Assert.pre(!isEmpty(), "The list is empty");
		return tail.previous().value(); //return value before taill, which is last value.
	}

	/**
	* Insert the value at location.
	* @big-o O(n).
	* @pre 0 <= i <= size()
	* @post adds the ith entry of the list to value o
	* @param i the index of this new value
	* @param o the the value to be stored
	*/
	public void add(int i, E o) {
		Assert.pre((i >= -1) && (i <= size()), "Index out of range.");
		if (i == 0) {
			insertAfter(o, head);   //incase index is 0, insert after head.
		} else {
			DoublyLinkedNode<E> first = head.next();
			for (int j = 0; j <= i; j++) {	 //iterate over elements within range of index
				if (j == i - 1) {
					insertAfter(o, first);   //insert node after "first" node
				}
				first = first.next();        //move to next index.
			}
		}

	}

	/**
	* Remove and return the value at location i.
	*
	* @pre 0 <= i < size()
	* @post removes and returns the object found at that location.
	* @big-o O(n).
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E remove(int i) {
		Assert.pre((i >= -1) && (i <= size()), "Index out of range.");
		if (i == 0) {
			return removeFirst();
		} else if (i > 0 && i < size()) {
			DoublyLinkedNode<E> first = head.next();
			for (int j = 0; j <= i; j++) {	 //iterate over elements within range of index
				if (j == i - 1) {
					remove(first.next());   //remove node after "first" node
				}
				first = first.next();        //move to next index.
			}
		}
		return null;
	}

	/**
	* Get the value at location i.
	*
	* @pre 0 <= i < size()
	* @post returns the object found at that location.
	* @big-o O(n).
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E get(int i) {
		Assert.pre((i >= -1) && (i <= size()), "Index out of range.");
		if (i == 0) {
			return getFirst();
		} else if (i > 0 && i < size()) {
			DoublyLinkedNode<E> first = head.next();
			for (int j = 0; j <= i; j++) {	 //iterate over elements within range of index
				if (j == i - 1) {
					return first.next().value();  //return node after "first" node
				}
				first = first.next();        //move to next index.
			}
		}
		return null;
	}

	/**
	* Set the value stored at location i to object o, returning the old value.
	* @big-o O(n).
	* @pre 0 <= i < size()
	* @post sets the ith entry of the list to value o, returns the old value.
	* @param i the location of the entry to be changed.
	* @param o the new value
	* @return the former value of the ith entry of the list.
	*/
	public E set(int i, E o) {
		Assert.pre((i >= -1) && (i <= size()), "Index out of range.");
		E removed = remove(i);
		add(i, o);                //adding value O at location i.
		return removed;   //removing old element at location i.;	     
	}

	/**
	* Determine the first location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	*
	* @param value The value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int indexOf(E value) {
		Assert.pre(value != null, "Value is null");
		for (int i = 0; i < size(); i++) {	 //iterate over elements within size of lsit.
			if (get(i).equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	* Determine the last location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	* @big-o O(n).
	* @param value the value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int lastIndexOf(E value) {
		Assert.pre(value != null, "Value is null");
		for (int i = size() - 1; i >= 0; i--) {	 //iterate over elements within size of list
			if (get(i).equals(value)) {
				return i;      //return index.
			}
		}
		return -1;
	}

	/**
	* Check to see if a value is within the list.
	*
	* @pre value not null
	* @post returns true iff value is in the list
	* @big-o O(n).
	* @param value A value to be found in the list.
	* @return True if value is in list.
	*/
	public boolean contains(E value) {
		Assert.pre(value != null, "Value is null");
		if (indexOf(value) < size() && indexOf(value) >= 0) {
			return true;
		}
		return false;
	}
	/**
	* Remove a value from the list.  At most one value is removed.
	* Any duplicates remain.  Because comparison is done with "equals,"
	* the actual value removed is returned for inspection.
	*
	* @pre value is not null.  List can be empty.
	* @post first element matching value is removed from list
	* @big-o O(n).
	* @param value The value to be removed.
	* @return The value actually removed.
	*/
	public E remove(E value) {
		Assert.pre(value != null, "Value is null");
		if (contains(value)) {
			return remove(indexOf(value));
		}
		return null;
	}

		
	



	/**
	* Construct an iterator to traverse the list.
	*
	* @post returns iterator that allows the traversal of list.
	*
	* @return An iterator that traverses the list from head to tail.
	*/
	public Iterator<E> iterator() {
		/**
		 * into your list implementation, please toggle the
		 * comments below. To understand why the lines below
		 * must be swapped, please consult the structure5
		 * source code for DoublyLinkedListIterator class.
		 */

		return new DoublyLinkedListIterator<E>(head, tail);
	}

	/**
	* Construct a string representation of the list.
	*
	* @post returns a string representing list
	*
	* @return A string representing the elements of the list.
	*/
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("<LinkedList (" + count + "):");

		Iterator<E> li = iterator();
		while (li.hasNext()) {
			s.append(" " + li.next());
		}
		s.append(">");

		return s.toString();
	}
}
