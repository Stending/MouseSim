package griddata;

import griddata.Food;
import griddata.Square;

/**
 * This class designates a square of a grid which is practicable.
 * @author Rova
 *
 */

public class PraticableSquare extends Square {
	
	private Food foodSource;
	
	/**
	 * This constructor initializes the square as a non-food square.
	 */
	public PraticableSquare(){
		this(0);
	}
	
	/**
	 * This constructor initializes the square with a certain amount of food.
	 * @param quantity Quantity of food.
	 */
	public PraticableSquare (int quantity) {
		foodSource = new Food(quantity);
	}
	
	/**
	 * This constructor initializes the square by copying another square.
	 * @param copy The square to copy.
	 */
	public PraticableSquare(PraticableSquare copy){
		this.foodSource = copy.getFoodSource();
	}
	
	public boolean isPraticable() {
		return true;
	}
	
	public boolean containsFood() {
		return(!foodSource.empty());
	}
	
	public int getFoodQuantity() {
		return(foodSource.getQuantity());
	}

	public Food getFoodSource() {
		return foodSource;
	}
}
