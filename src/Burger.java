/*
 * TCSS 342- Assignment 1 
 */

/**
 * @author Vecheka Chhourn
 * @version 03/26/2018
 * A class that put the burger together according to the customers' requests.
 */
public class Burger {
	
	/** Burger's Bun.*/
	private static final String BUN = "Bun";
	/** Burger's order.*/
	private MyStack<String> myBurgerOrder;
	/** Patty class.*/
	private Patty myPatty;
	/** Veggies class.*/
	private Veggies myVeggies;
	/** Sauce class.*/
	private Sauces mySauce;
	/** Cheese class.*/
	private Cheese myCheese;
	
	
	/**
	 * Constructor to initialize the burger.
	 * @param theWorks boolean to determine burger type.
	 */
	public Burger(final boolean theWorks) {
		myBurgerOrder = new MyStack<>();
		myPatty = new Patty();
		myVeggies = new Veggies();
		mySauce = new Sauces();
		myCheese = new Cheese();
		if (theWorks) {
			// Baron burger
			baronBurger();
		} else {
			// burger with customized order
			myBurgerOrder.push(BUN);
			myBurgerOrder.push(myPatty.getBeef());
			myBurgerOrder.push(BUN);
		}
	}
	
	// helper method
	/** 
	 * Put together Baron burger.
	 */
	private void baronBurger() {
		
		myBurgerOrder.push(BUN);
		myBurgerOrder.push(mySauce.getKetchup());
		myBurgerOrder.push(mySauce.getMustard());
		myBurgerOrder.push(myVeggies.getMushrooms());
		myBurgerOrder.push(myPatty.getBeef());
		myBurgerOrder.push(myCheese.getCheddar());
		myBurgerOrder.push(myCheese.getMozzarella());
		myBurgerOrder.push(myCheese.getPepperjack());
		myBurgerOrder.push(myVeggies.getOnions());
		myBurgerOrder.push(myVeggies.getTomato());
		myBurgerOrder.push(myVeggies.getLettuce());
		myBurgerOrder.push(mySauce.getBaronSauce());
		myBurgerOrder.push(mySauce.getMayonnaise());
		myBurgerOrder.push(BUN);
		myBurgerOrder.push(myVeggies.getPickle());
		
	}

	/**
	 * Change patties in the burger to specified patty type.
	 * @param pattyType type of patty
	 */
	public void changePatties(final String pattyType) {
		final MyStack<String> tempStack = new MyStack<>();
		
		while (!myBurgerOrder.isEmpty()) {
			final String temp = myBurgerOrder.pop();
			if (myPatty.isPatty(temp)) {
				tempStack.push(pattyType);
			} else {
				tempStack.push(temp);
			}
		}
		

		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
	}
	
	/**
	 * Add patty to the Burger up to 3 maximum.
	 */
	public void addPatty() {
		final MyStack<String> tempStack = new MyStack<>();
		boolean noCheeseFound = true;
		while (!myBurgerOrder.isEmpty()) {
			final String temp = myBurgerOrder.pop();
			if (myCheese.isCheese(temp)) {
				noCheeseFound = false;
				tempStack.push(myPatty.getBeef());
				tempStack.push(temp);
				break;
			} else {
				tempStack.push(temp);
			}
			
		}
		
		// no cheese found in the burger, add patty next to patty
		if (noCheeseFound) {
			while (!tempStack.isEmpty()) {
				final String temp = tempStack.pop();
				if (myPatty.isPatty(temp)) {
					myBurgerOrder.push(myPatty.getBeef());
					myBurgerOrder.push(temp);
					break;
				} else {
					myBurgerOrder.push(temp);
				}
			}
		}
		
		
		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
		
	}
	
	
	/**
	 * Add all items of the type to the Burger in the right locations.
	 * @param type type of category.
	 */
	public void addCategory(final String type) {
		final MyStack<String> tempStack = new MyStack<>(); 
		if (myVeggies.isVeggie(type)) {
			
			addVeggiesCategory(tempStack);
			
			
		} else if (myCheese.isCheese(type)) {
			while (!myBurgerOrder.isEmpty()) {
				final String temp = myBurgerOrder.pop();
				if (myPatty.isPatty(temp)) {
					tempStack.push(myCheese.getPepperjack());
					tempStack.push(myCheese.getMozzarella());
					tempStack.push(myCheese.getCheddar());
					tempStack.push(temp);
					break;
				} else {
					tempStack.push(temp);
				}
			}
		} else if (mySauce.isSauce(type)) {
			addSauceCategory(tempStack);
		}
		
		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
	}
	
	
	// helper method
	/**
	 * To add Sauce category to the burger.
	 * @param theTempStack stack to store all ingredients temporarily
	 */
	private void addSauceCategory(MyStack<String> theTempStack) {
		while (!myBurgerOrder.isEmpty()) {
			
			final String temp = myBurgerOrder.pop();
			if (temp.equals(BUN)) {
				theTempStack.push(temp);
				theTempStack.push(mySauce.getMayonnaise());
				theTempStack.push(mySauce.getBaronSauce());
				break;
			} else {
				theTempStack.push(temp);
			}
		}
		// iterate till the last bun in the burger.
		while (myBurgerOrder.size() > 1) {
			theTempStack.push(myBurgerOrder.pop());
		}
		theTempStack.push(mySauce.getMustard());
		theTempStack.push(mySauce.getKetchup());
		
	}

	
	// helper method
	/**
	 * To add Veggies category to the burger.
	 * @param theTempStack stack to store all ingredients temporarily
	 */
	private void addVeggiesCategory(MyStack<String> theTempStack) {
		myBurgerOrder.pop(); // bun
		theTempStack.push(myVeggies.getPickle());
		theTempStack.push(BUN);
		
		// check for sauce before adding lettuce, tomato, and onions
		while (mySauce.isSauce(myBurgerOrder.peek())) {
			theTempStack.push(myBurgerOrder.pop());
		}
		theTempStack.push(myVeggies.getLettuce());
		theTempStack.push(myVeggies.getTomato());
		theTempStack.push(myVeggies.getOnions());
		
		// adding mushrooms after the last beef
		while (!myBurgerOrder.isEmpty()) {
			final String temp = myBurgerOrder.pop();
			if (myPatty.isPatty(temp)) {
				theTempStack.push(temp);
				theTempStack.push(myVeggies.getMushrooms());
				break;
			} else {
				theTempStack.push(temp);
			}
		}
		
		
	}

	/**
	 * Remove all items of the type from the Burger.
	 * @param type type of category.
	 */
	public void removeCategory(final String type) {
		final MyStack<String> tempStack = new MyStack<>();
		
		while (!myBurgerOrder.isEmpty()) {
			final String temp = myBurgerOrder.pop();
			if (myCheese.isCheese(type) &&
					!myCheese.isCheese(temp)) {
				
				tempStack.push(temp);
				
			} else if (myVeggies.isVeggie(type) &&
					!myVeggies.isVeggie(temp)) {

				tempStack.push(temp);
				
			} else if (mySauce.isSauce(type) &&
					!mySauce.isSauce(temp)) {
				tempStack.push(temp);		
			}
			
		}
		
		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
		
	}
	
	
	// helper method
	/**
	 * Re arrange ingredients to their proper locations from the order.
	 * @param theTempStack stack that store the ingredients in reverse orders.
	 */
	private void reArrangeIngredients(final MyStack<String> theTempStack) {
		while (!theTempStack.isEmpty()) {
			myBurgerOrder.push(theTempStack.pop());
		}
		
	}

	/**
	 * Add ingredient type to the Burger in the right locations.
	 * @param type type of ingredient.
	 */
	public void addIngredient(final String type) {
		final MyStack<String> tempStack = new MyStack<>();
		if (myVeggies.isVeggie(type)) {
			addVeggiesIngredient(tempStack, type);
			
		} else if (mySauce.isSauce(type)) {
			addSauceIngredients(tempStack, type);
		
		} else if (myCheese.isCheese(type)) {
			addCheeseIngredients(tempStack, type);
		}
		
		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
	}
	
	
	// helper method
	/**
	 * Add cheese' ingredients to the burger.
	 * @param theTempStack stack to temporarily store burger's order
	 * @param theType type of cheese 
	 */
	private void addCheeseIngredients(MyStack<String> theTempStack, String theType) {
		while (!myBurgerOrder.isEmpty()) {
			final String temp = myBurgerOrder.pop();
			if (myPatty.isPatty(temp)) {
				// Pepperjack is always before other cheese type
				// Mozzarella comes after that, and Cheddar is the last.
				if (theType.equals(myCheese.getPepperjack())
						&& myCheese.isCheese(theTempStack.peek())) {
					final String anotherCheese = theTempStack.pop();
					theTempStack.push(theType);
					theTempStack.push(anotherCheese);
					theTempStack.push(temp);
				} else if(theType.equals(myCheese.getMozzarella())) {
					if (theTempStack.peek().equals(myCheese.getCheddar())) {
						final String anotherCheese = theTempStack.pop();
						theTempStack.push(theType);
						theTempStack.push(anotherCheese);
						theTempStack.push(temp);
					} else {
						theTempStack.push(theType);
						theTempStack.push(temp);
					}
				} else {
					theTempStack.push(theType);
					theTempStack.push(temp);
				}
				break;
			} else {
				theTempStack.push(temp);
			}
		}
		
	}

	// helper method
	/**
	 * Add sauce' ingredients to the burger.
	 * @param theTempStack stack to temporarily store burger's order
	 * @param theType type of sauce 
	 */
	private void addSauceIngredients(final MyStack<String> theTempStack, final String theType) {
		if (theType.equals(mySauce.getMayonnaise())
				|| theType.equals(mySauce.getBaronSauce())) {

			while (!myBurgerOrder.isEmpty()) {
				final String temp = myBurgerOrder.pop();
				if (temp.equals(BUN)) {
					// Baron-Sauce always comes after Mayonnaise
					if (theType.equals(mySauce.getBaronSauce()) &&
							myBurgerOrder.peek().equals(mySauce.getMayonnaise())) {
						theTempStack.push(temp);
						theTempStack.push(myBurgerOrder.pop());
						theTempStack.push(theType);
						
					} else {
						theTempStack.push(temp);
						theTempStack.push(theType);
					}
					break;
				} else {
					theTempStack.push(temp);
				}
			}
			
		} else { // mustard or ketchup
			
			while (myBurgerOrder.size() > 2) {
				theTempStack.push(myBurgerOrder.pop());
			}
			// check if there is another sauce already in the burger
			// if the type is Ketchup always add after Mustard if found in the burger
			if (mySauce.isSauce(myBurgerOrder.peek())) {
				if (theType.equals(mySauce.getKetchup())) {
					theTempStack.push(myBurgerOrder.pop());
					theTempStack.push(theType);
				} else {
					theTempStack.push(theType);
					theTempStack.push(myBurgerOrder.pop());	
				}
			} else {
				theTempStack.push(myBurgerOrder.pop());
				theTempStack.push(theType);	
			}
			// add Bun to the burger
			theTempStack.push(myBurgerOrder.pop());
		}
		
	}

	// helper method
	/**
	 * Add veggies' ingredients to the burger.
	 * @param theTempStack stack to temporarily store burger's order 
	 * @param theType type of veggies
	 */
	private void addVeggiesIngredient(final MyStack<String> theTempStack, final String theType) {
		// add before cheese if exists, else add before patty
		// Onions have to be after Lettuce & Tomato
		// Tomato have to be after Lettuce
		if (theType.equals(myVeggies.getPickle())) { // pickle
			myBurgerOrder.push(myVeggies.getPickle());
		} else if(theType.equals(myVeggies.getMushrooms())) { // mushrooms
			while (!myBurgerOrder.isEmpty()) {
				final String temp = myBurgerOrder.pop();
				if (myPatty.isPatty(temp)) {
					theTempStack.push(temp);
					theTempStack.push(theType);
					break;
				} else {
					theTempStack.push(temp);
				}
			}
		} else {
		    // add Bun
			theTempStack.push(myBurgerOrder.pop());
			// check for Sauce before adding lettuce, tomato or onions
			while (mySauce.isSauce(myBurgerOrder.peek())) {
				theTempStack.push(myBurgerOrder.pop());
			}
			addOnionsTomato(theTempStack, theType);
			theTempStack.push(theType);
		}
		
	}

	
	// helper method
	/** 
	 * Add Onions or Tomato to the right location in the burger.
	 * @param theTempStack stack to store burger's ingredients temporarily
	 * @param theType type of Veggies
	 */
	private void addOnionsTomato(final MyStack<String> theTempStack, final String theType) {
		if (theType.equals(myVeggies.getOnions())) {
			while (myBurgerOrder.peek().equals(myVeggies.getLettuce()) ||
					myBurgerOrder.peek().equals(myVeggies.getTomato())) {
				theTempStack.push(myBurgerOrder.pop());
			}
		} else if (theType.equals(myVeggies.getTomato())) {
			if (myBurgerOrder.peek().equals(myVeggies.getLettuce())) {
				theTempStack.push(myBurgerOrder.pop());
			}
		}
		
	}

	/**
	 * Remove ingredient type from the Burger.
	 * @param type type of ingredient.
	 */
	public void removeIngredient(final String type) {
		final MyStack<String> tempStack = new MyStack<>();
		while (!myBurgerOrder.isEmpty()) {
			final String temp =  myBurgerOrder.pop();
			if (!temp.equals(type)) {
				tempStack.push(temp);
			}
		}
		// re arrange order of ingredients
		reArrangeIngredients(tempStack);
	}
	
	
	@Override
	public String toString() {
		return myBurgerOrder.toString();
	}
	
}


/** 
 * Cheese type class to represents each cheese types, and check if 
 * an ingredient is a cheese.
 */
class Cheese {
	
	/**Cheddar chesse.*/
	private String myCheddar;
	/**Mozzarrella chesse.*/
	private String myMozzarella;
	/**Pepperjack chesse.*/
	private String myPepperjack;
	
	/**
	 * Constructor to initialize chesse types.
	 */
	public Cheese() {
		myCheddar = "Cheddar";
		myMozzarella = "Mozzarella";
		myPepperjack = "Pepperjack";
		
	}
	
	/**
	 * Getter for cheddar.
	 * @return cheddar
	 */
	
	public String getCheddar() {
		return myCheddar;
	}
	
	/**
	 * Getter for Mozzarrella.
	 * @return Mozzarrella
	 */
	
	public String getMozzarella() {
		return myMozzarella;
	}
	
	/**
	 * Getter for Pepperjack.
	 * @return Pepperjack
	 */
	
	public String getPepperjack() {
		return myPepperjack;
	}
	
	/** 
	 * Check if it is a chesse.
	 * @param theType type of ingredients on the burger.
	 * @return true if it is a chesse.
	 */
	public boolean isCheese(final String theType) {
		return (theType.equals(myPepperjack)
				|| theType.equals(myMozzarella)
				|| theType.equals(myCheddar)
				|| theType.equals("Cheese"));
	}
	
}

/** 
 * Patty type class to represents each Patty types, and check if 
 * an ingredient is a Patty.
 */
class Patty {
	
	/** Beef.*/
	private String myBeef;
	/** Chicken.*/
	private String myChicken;
	/** Veggie.*/
	private String myVeggie;
	
	/**
	 * Constructor to initialize all patty types.
	 */
	public Patty() {
		myBeef = "Beef";
		myChicken = "Chicken";
		myVeggie = "Veggie";
	}
	
	/**
	 * Getter for beef.
	 * @return beef.
	 */
	public String getBeef() {
		return myBeef;
	}
	
	/**
	 * Getter for chicken.
	 * @return chicken.
	 */
	public String getChicken() {
		return myChicken;
	}
	
	/**
	 * Getter for veggie.
	 * @return veggie.
	 */
	public String getVeggie() {
		return myVeggie;
	}
	
	/** 
	 * Check if it is a patty.
	 * @param theType type of ingredients on the burger.
	 * @return true if it is a patty.
	 */
	public boolean isPatty(final String theType) {
		return (theType == myBeef 
				|| theType == myChicken
				|| theType == myVeggie);
	}
	
}

/** 
 * Sauce type class to represents each sauce types, and check if 
 * an ingredient is a sauce.
 */
class Sauces {
	
	/** Ketchup.*/
	private String myKetchup;
	/** Mustard.*/
	private String myMustard;
	/** Mayonnaise.*/
	private String myMayonnaise;
	/** Baron-Sauce.*/
	private String myBaronSauce;
	
	/** 
	 * Constructor to initialize all sauces type.
	 */
	public Sauces() {
		myKetchup = "Ketchup";
		myMustard = "Mustard";
		myMayonnaise = "Mayonnaise";
		myBaronSauce = "Baron-Sauce";
	}
	
	
	/**
	 * Getter for ketchup.
	 * @return ketchup
	 */
	public String getKetchup() {
		return myKetchup;
	}
	
	/**
	 * Getter for mustard.
	 * @return mustard
	 */
	public String getMustard() {
		return myMustard;
	}
	
	/**
	 * Getter for mayonnaise.
	 * @return mayonnaise
	 */
	public String getMayonnaise() {
		return myMayonnaise;
	}
	
	/**
	 * Getter for Baron Sauce.
	 * @return Baron Sauce
	 */
	public String getBaronSauce() {
		return myBaronSauce;
	}
	
	/** 
	 * Check if it is a sauce.
	 * @param theType type of ingredients on the burger.
	 * @return true if it is a sauce.
	 */
	public boolean isSauce(final String theType) {
		return (theType.equals(myKetchup)
				|| theType.equals(myMustard)
				|| theType.equals(myMayonnaise)
				|| theType.equals(myBaronSauce)
				|| theType.equals("Sauce"));
	}
	
}

/** 
 * Veggies type class to represents each veggies types, and check if 
 * an ingredient is a veggies.
 */
class Veggies {
	
	/** Lettuce.*/
	private String myLettuce;
	/** Tomato.*/
	private String myTomato;
	/** Onions.*/
	private String myOnions;
	/** Pickle.*/
	private String myPickle;
	/** Mushrooms.*/
	private String myMushroom;
	
	/**
	 * Constructor to initialize all veggies type.
	 */
	public Veggies() {
		myLettuce = "Lettuce";
		myTomato = "Tomato";
		myOnions = "Onions";
		myPickle = "Pickle";
		myMushroom = "Mushrooms";
	}
	
	/**
	 * Getter for lettuce.
	 * @return lettuce
	 */
	public String getLettuce() {
		return myLettuce;
	}
	
	/**
	 * Getter for onions.
	 * @return onions
	 */
	public String getOnions() {
		return myOnions;
	}
	
	/**
	 * Getter for tomato.
	 * @return tomato
	 */
	public String getTomato() {
		return myTomato;
	}
	
	/**
	 * Getter for pickle.
	 * @return pickle
	 */
	public String getPickle() {
		return myPickle;
	}
	
	/**
	 * Getter for mushrooms.
	 * @return mushrooms
	 */
	public String getMushrooms() {
		return myMushroom;
	}
	
	/** 
	 * Check if it is a veggie.
	 * @param theType type of ingredients on the burger.
	 * @return true if it is a veggie.
	 */
	public boolean isVeggie(final String theType) {
		return (theType.equals(myLettuce) 
				|| theType.equals(myTomato)
				|| theType.equals(myOnions)
				|| theType.equals(myPickle)
				|| theType.equals(myMushroom)
				|| theType.equalsIgnoreCase("Veggies"));
	}
	
}


