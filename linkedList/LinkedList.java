/*
Assignment number: 8.6
File name: LinkedList.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
package linkedList;

import java.util.NoSuchElementException;

/**
 * Represents a generic linked list, and list-oriented operations.
 * The list can hold objects of any type.
 */
public class LinkedList<E> {
	
	// The following constant is used for localizing the line separator character,
	// which may be different in different host platforms.
	static final String nl =  System.getProperty("line.separator");

	private Node<E> first;  // Points to the first node in the list (just after the dummy node)
	private Node<E> last;   // Points to the last node in the list
	private int size;       // Number of list elements

	/** 
	 * Creates a list with 0 elements.
	 */
	public LinkedList() {
		// Starts with a dummy node, to avoid handling empty lists.
		Node<E> dummy = new Node<E>(null);
		this.first =  dummy;
		this.last = first;
		this.size = 0;
	}
	
	/** 
	 * Adds the given element to the end of this list.
	 * @param e  the node to add
	 */
	public void add(E e) {
	    Node<E> newNode = new Node<E>(e);     // create new Node that the user ask to add
		last.next = newNode;                  // add the Node to the list
		last = newNode;						  // update the "last" pointer.
		if (size == 0) {                  	  //check if it is the first element and if does update "first" pointer
			first.next = newNode;
		}
        size++;
	}

	/** 
	 * Adds the given element to the beginning of this list.
	 * @param e  the element to add to the list.
	 */
	public void addFirst(E e) {
		// Creates a Node object that holds the given data value
		Node <E> newNode = new Node<E>(e);
		// Makes the new node point to the first node in this list
		newNode.next = first.next;
		// Makes first point to the new node
		first.next = newNode;
		if (size == 0) {                     //check if it is the first element and if does update "last" pointer
			last = newNode;
		}
		size++;
	}
	
	/**
	 * Adds the given element at the n'th location of this list.
	 * The index of the list's first element is 0.
	 * The index of the list's last element is the list's size.
	 * @param  e the element to add
	 * @param  index the index
	 * @throws IllegalArgumentException if index is negative or larger than the list's size
	 */
	public void add(E e, int index) {

		try {                                                      // check if the index is legal

			if ((index < 0) || (index > size)) {
				throw new IllegalArgumentException();              // if not throw exception
			}
		}
		catch (IllegalArgumentException x) {						// handel the exception
			System.out.println(index + " is illegal index");
			return;
		}

		Node<E> newNode = new Node(e);
		Node<E> current = first;
		if (index == this.size){						// if the Index is the end add with regular add and done
			this.add(e);
			return;
		}
		for (int i = 0; i < index; i++){               // advance until the right index
			current = current.next;
		}
		newNode.next = current.next;                   // add the Node
		current.next = newNode;
		size++;


	}
	
	/** 
	 * Returns the index of the given element in this list, or -1 if not found.
	 * @param  e the returned index will be the index of e.
	 * @return the index of the given element in this list
	 */
	public int indexOf (E e) {

		ListIterator<E> l1 = this.iterator();
		int index = 0;

		while (l1.current != null) {
			if (l1.current.e == e){
				return index;
			}
			index ++;
			l1.next();
		}
		return -1;
	}

	/** 
	 * If the given element exists in this list, removes it and returns true.
	 *  Otherwise, returns false.
	 * @param  e the element to remove.
	 * @return if the given element exists in this list, removes it and
	 * returns true. Otherwise, returns false.
	 */
	 public boolean remove(E e) {
		 Node<E> current = this.first.next;
		 Node<E> prevNode = this.first;

		 do {
			 if (current.e == e) {                    // Check if the current Node is the one that we want to remove.
				 if (current.next == null) {          // Check if this is the last node.
					 prevNode.next = null;
					 last = prevNode;                 // Update the last.
				 } else {
					 prevNode.next = current.next;    // remove the Node.
				 }
				 size--;
				 return true;
			 }
			 prevNode = current;                      // advance
			 current = current.next;                  // advance
		 }while (current != null);

		 return false;
	}

	/** 
	 * Returns the first element in this list.
	 * @return the first element in this list.
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getFirst() {

		try {                                            // check if the list isn't empty
			if (size == 0) {
				throw new NoSuchElementException();      // if does throw an exception
			}
			return first.next.e;

		}
		catch (NoSuchElementException e) {               // handel the exception.
			System.out.println("The list is empty");
		}
		return null;
	}

	
	/** 
	 * Returns the last element in this list.
	 * @return the last element in this list.
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getLast() {
		try {											// check if the list isn't empty
			if (size == 0) {
				throw new NoSuchElementException();		// if does throw an exception
			}
			return last.e;
		}
		catch (NoSuchElementException e) {				// handel the exception.
			System.out.println("The list is empty");
		}
		return null;
	}
	
	/** 
	 * Returns the size of this list.
	 * @return the size of this list.
	 */
	public int size() {
	    return size;
	}
	
	/** 
	 * Returns an itertaor on this list.
	 * @return an itertaor on this list.
	 */
	public ListIterator<E> iterator() {
	    return new ListIterator<E>(first.next);
	}
	
	/**
	 * A textual representation of the elements of this list.
	 * Each element is displayed in a separate line.
	 */
	public String toString() {
	    if (size == 0) return "()";
	    StringBuilder str = new StringBuilder();
	    ListIterator<E> itr = this.iterator();
	    while (itr.hasNext()) {
	        str.append(itr.next().toString() + nl);
	    }
	    return str.toString();
	}
	
	// The main method of this class can be used for testing the
	// LinkedList methods. Clients of the class will normally not use it.
	public static void main(String[] args) {
		// Creates a list of Integer objects, add some elements, and prints the list.
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		list.add(3);
//		list.add(7);
//		list.add(9);
//		System.out.println(list);
//		list.remove(3);
//		System.out.println(list);
//		System.out.println(list.getFirst());



		
		// As you implement the LinkedList class methods, write your testing 
		// code below. If you want, you can use private testing methods.
		
		 testExceptions();
	}
	
	// Exception testing method.
	private static void testExceptions() {
	    // Creates a list of Integer objects
		LinkedList<Integer> list = new LinkedList<Integer>();
			
		// After you'll implement the getFirst() method, the statement below
		// should cause the program to crash.
		// To prevent it, wrap the method call with try/catch code. 
	    list.getLast(); // Tries to get an element from the list, which is empty
				
	    // Adds three elements to the list, and prints it
		list.add(3);
		list.add(7);
		list.add(9);
		System.out.println(list);


		// After you'll implement the add(e,index) method, the statement below
		// should cause the program to crash.
		// To prevent it, wrap the method call with try/catch code. 
		list.add(8,-2);	// Tries to insert an element in index -2.
					
		// After you'll implement the add(e,index) method, the statement below
		// should cause the program to crash.
		// To prevent it, wrap the method call with try/catch code. 
		list.add(8,10); // Tries to insert an element in index 10.
	}
}
