

/*
 * TCSS 342- Assignment 1 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Vecheka Chhourn
 * @version 03/26/2018
 * Main class to execute the program.
 */
public class Main {

	/** To check if it is a Baron order.*/
	private static final String BARON = "Baron";
	/** To check if an order have omissions or additions.*/
	private static final String WITH = "with";
	/** To check if an order have exceptions.*/
	private static final String BUT = "but";
	/** To check if an order have exceptions specified with the word "no".*/
	private static final String NO = "no";
	/** To check if it is a Cheese type.*/
	private static final String CHEESE = "Cheese";
	/** To check if it is a Sauce type.*/
	private static final String SAUCE = "Sauce";
	/** To check if it is a Veggies type.*/
	private static final String VEGGIES = "Veggies";
	/** To check if it is a Chicken patty type.*/
	private static final String CHICKEN = "Chicken";
	/** To check if it is a Veggie patty type.*/
	private static final String VEGGIE = "Veggie";
	/** To check if it is double patty burger.*/
	private static final String DOUBLE = "Double";
	/** To check if it is triple patty burger.*/
	private static final String TRIPLE = "Triple";
	/** Burger class reference.*/
	private static Burger myBurger;
	/** Order queue number .*/
	private static int myOrderQueue;
	
	
	/**
	 * Main method to run the program.
	 * @param theArgs command line.
	 */
	public static void main(final String[] theArgs)  {
		
		// read and prepare customers' orders.
		Scanner inputFile;
		try {
			inputFile = new Scanner(new File("customer2.txt"));
			while (inputFile.hasNextLine()) {
				parseLine(inputFile.nextLine());
			}
		} catch (final FileNotFoundException theE) {
			
			theE.printStackTrace();
		}
		
		System.out.println("**********************************************************************************");
		// test MyStack class' methods
		System.out.println();
		System.out.println("Testing MyStack Class...");
		testMyStack();
		
		// test Burger class' methods
		System.out.println();
		System.out.println("Testing Burger Class...");
		testBurger();
		
	}

	/**
	 * Test Burger class' methods
	 */
	private static void testBurger() {
		System.out.println("\nTesting using customized Burger option not"
				+ " Baron Burger");
		
		final String tabIn = "\t";
		int testNumber = 1;
		System.out.println(testNumber + ".Testing addCategory()-->\n");
		myBurger = new Burger(false);
		System.out.println(tabIn +"Adding Veggies Only: ");
		myBurger.addCategory(VEGGIES);
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(false);
		System.out.println(tabIn + "Adding Cheese Only: ");
		myBurger.addCategory(CHEESE);
		System.out.println(tabIn + myBurger.toString());

		System.out.println();
		
		myBurger = new Burger(false);
		System.out.println(tabIn + "Adding Sauce Only: ");
		myBurger.addCategory(SAUCE);
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		testNumber++;
		System.out.println(testNumber +".Testing addIngredients()-->\n");
		
		myBurger = new Burger(false);
		System.out.println(tabIn + "Adding Onions Only: ");
		myBurger.addIngredient("Onions");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(false);
		System.out.println(tabIn + "Adding Mozzarrella Only: ");
		myBurger.addIngredient("Mozzarella");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(false);
		System.out.println(tabIn + "Adding Baron-Sauce Only: ");
		myBurger.addIngredient("Baron-Sauce");
		System.out.println(tabIn + myBurger.toString());
		
		
		System.out.println();
		System.out.println("\nTesting using Baron Burger option");
		testNumber++;
		System.out.println(testNumber +".Testing removeIngredients()-->\n");
		
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing Baron-Sauce Only: ");
		myBurger.removeIngredient("Baron-Sauce");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing Lettuce Only: ");
		myBurger.removeIngredient("Lettuce");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing Cheddar Only: ");
		myBurger.removeIngredient("Cheddar");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		testNumber++;
		System.out.println(testNumber +".Testing removeCategory()-->\n");
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing all Cheese: ");
		myBurger.removeCategory("Cheese");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing all Veggies: ");
		myBurger.removeCategory("Veggies");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Removing all Sauce: ");
		myBurger.removeCategory("Sauce");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		testNumber++;
		System.out.println(testNumber +".Testing addPatty()-->\n");
		
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Adding Double Patty: ");
		myBurger.addPatty();
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		myBurger = new Burger(true);
		System.out.println(tabIn + "Adding Triple Patty: ");
		myBurger.addPatty();
		myBurger.addPatty();
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		testNumber++;
		System.out.println(testNumber +".Testing changPatties()-->\n");
		
		System.out.println(tabIn + "Change to Chicken: ");
		myBurger.changePatties("Chicken");
		System.out.println(tabIn + myBurger.toString());
		
		System.out.println();
		
		System.out.println(tabIn + "Change to Veggie: ");
		myBurger.changePatties("Veggie");
		System.out.println(tabIn + myBurger.toString());
		
	}

	/**
	 * Test MyStack class' methods.
	 */
	private static void testMyStack() {
		MyStack<Integer> testStack = new MyStack<Integer>();
		testStack.push(1);
		testStack.push(4);
		testStack.push(5);
		testStack.push(6);
		System.out.println("Stack: " + testStack.toString());
		
		System.out.println("Testing size(): size = " + testStack.size() + " --> Passed!");
		
		System.out.println("Testing pop(): pop's value = " + testStack.pop() + " --> Passed!");
		
		System.out.println("Testing peek(): peek's value = " + testStack.peek() + " --> Passed!");
		
		testStack.push(7);
		System.out.println("Testing push(7): New Stack = " + testStack.toString() + " --> Passed!");
		
	}


	/**
	 * Read customers' orders.
	 * @param theNextLine order line.
	 */
	private static void parseLine(String theNextLine) {
		
		final String[] temp = theNextLine.split(" ");
		final ArrayList<String> orderLine = new ArrayList<String>();
		for (final String w: temp) {
			orderLine.add(w);
		}
		
		if (orderLine.contains(BARON)) {
			myBurger = new Burger(true);
			
			addingRemovingIngredients(true, orderLine);
			
			addPatty(orderLine);
			changePatty(orderLine);
			
		} else {
			myBurger = new Burger(false);
			addingRemovingIngredients(false, orderLine);
			
			addPatty(orderLine);
			changePatty(orderLine);
			
		}
		// printing to console
		System.out.println("Order #" + myOrderQueue + ": " + theNextLine);
		System.out.println(myBurger.toString());
		System.out.println();
		myOrderQueue++;
	}

	
	// helper method
	/** 
	 * To add/remove ingredients or categories in the burger.
	 * @param theBurgerType type of burger to deal with.
	 * @param theOrderLine order 
	 */
	private static void addingRemovingIngredients(final boolean theBurgerType, 
														final ArrayList<String> theOrderLine) {
		if (theOrderLine.contains(WITH)) {
			if (theBurgerType) {
				int index = theOrderLine.indexOf(NO) + 1;
				while (index < theOrderLine.size() &&
						!BUT.equalsIgnoreCase(theOrderLine.get(index))) {
					final String type = theOrderLine.get(index);

					if (isCategory(type)) {
						myBurger.removeCategory(type);
					} else {
						myBurger.removeIngredient(type);
					}
					 
					index++;
				}
			} else {
				int index = theOrderLine.indexOf(WITH) + 1;
				while (index < theOrderLine.size() &&
						!BUT.equalsIgnoreCase(theOrderLine.get(index))) {
					final String type = theOrderLine.get(index);

					if (isCategory(type)) {
						myBurger.addCategory(type);
					} else {
						myBurger.addIngredient(type);
					}
					 
					index++;
				}
				
			}
		}
		if (theOrderLine.contains(BUT)) {
			if (theBurgerType) {
				int index = theOrderLine.indexOf(BUT) + 1;
				while (index < theOrderLine.size()) {
					myBurger.addIngredient(theOrderLine.get(index));
					index++;
				}
			} else {
				if (theOrderLine.contains(NO)) {
					int index = theOrderLine.indexOf(NO) + 1;
					while (index < theOrderLine.size()) {
						myBurger.removeIngredient(theOrderLine.get(index));
						index++;
					}
				} else {
					int index = theOrderLine.indexOf(BUT) + 1;
					while (index < theOrderLine.size()) {
						myBurger.removeIngredient(theOrderLine.get(index));
						index++;
					}
				}
			}
		}
		
	}

	
	// helper method
	/**
	 * Determine if it is a category of ingredients.
	 * @param theType type of categories
	 * @return true if it is one of the categories
	 */
	private static boolean isCategory(final String theType) {
		return (theType.equals(CHEESE) || 
				theType.equals(SAUCE) ||
				theType.equals(VEGGIES));
	}


	// helper method
	/**
	 * Change patties type.
	 * @param theOrderLine customers' orders
	 */
	private static void changePatty(final ArrayList<String> theOrderLine) {
		if (theOrderLine.contains(CHICKEN)) {
			myBurger.changePatties(CHICKEN);
		} else if (theOrderLine.contains(VEGGIE)) {
			myBurger.changePatties(VEGGIE);
		}
		
	}

	// helper method
	/** 
	 * Add patty if it a double or triple in the order request from the customers.
	 * @param theOrderLine customers' orders
	 */
	private static void addPatty(final ArrayList<String> theOrderLine) {
		if (theOrderLine.contains(DOUBLE)) {
			myBurger.addPatty();
		} else if (theOrderLine.contains(TRIPLE)) {
			myBurger.addPatty();
			myBurger.addPatty();
		}
		
	}

}
