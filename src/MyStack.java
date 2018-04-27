import java.util.NoSuchElementException;

/*
 * TCSS 342- Assignment 1 
 */

/**
 * @author Vecheka Chhourn
 * @version 03/26/2018
 * A class that build linked data structure from scratch
 */
public class MyStack<E> {
	
	/** Top element in the stack.*/
	private StackNode<E> top;
	/** Keep count of stack's size.*/
	private int size;
	
	
	/**
	 * Empty constructor
	 */
	public MyStack() {
		top = null;
	}
	
	/**
	 * Check to see if stack is empty.
	 * @return true if stack is empty.
	 */
	public boolean isEmpty() {
		
		return (top == null);
	}
	
	/**
	 * Adds item to the top of the list.
	 */
	public void push(E item) {
		if (isEmpty()) {
			top = new StackNode<>(item);
			top.next = null;
			size++;
		} else {
			StackNode<E> newTop = new StackNode<>(item);
			newTop.next = top;
			top = newTop;
			size++;
			
		}
		
	}
	
	/**
	 * Removes and returns the item on the top of the stack.
	 * @return top item of the stack
	 */
	public E pop() {
		E item;
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty!");
		} else {
			StackNode<E> current = top;
			item = current.data;
			current = current.next;
			top = current;
			size--;
			
		}
		return item;
	}
	
	
	/**
	 * Returns the item on the top of the stack.
	 * @return top item of the stack
	 */
	public E peek() {
		E item;
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty!");
		} else {
			item = top.data;
		}
		return item;
	}
	
	/**
	 * Get the size of the stack.
	 * @return size of the stack
	 */
	public int size() {
		return size;
	}
	
	
	
	/**
	 * Get a string representation of the stack.
	 * @return string representation
	 */
	@Override
	public String toString() {
		String stackString = "";
		StackNode<E> current = top;
		if (isEmpty()) { 
			stackString = "[]";
		} else {
			stackString = "[" + current.data;
			current = current.next;
			while (current != null) {
				stackString += ", " + current.data;
				current = current.next;
			}
			stackString += "]";
		}
		return stackString;
	}
	
}



/**
 * @author Vecheka Chhourn
 * @version 03/26/2018
 * A class that creates a node to link elements together.
 * @param <E> generic data
 */
class StackNode<E> {
	
	/** Stack elements.*/
	protected E data;
	/** Pointer to next element in the stack.*/
	protected StackNode<E> next;
	
	
	/**
	 * Constructor to add next element to the top of the stack.
	 * @param theData element to add to the top of the stack
	 */
	public StackNode(final E theData) {
		this.data = theData;
	}
}

