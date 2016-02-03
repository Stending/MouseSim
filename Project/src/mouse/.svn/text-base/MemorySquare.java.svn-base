package mouse;

import griddata.Square;

/**
 * This class contains some square informations that mice need to know in their memory.
 * @author Zinedine
 *
 */
public class MemorySquare {

	private boolean food;
	private boolean praticable;
	private Mouse giver;
	
	public MemorySquare(Square square){
		this(square.containsFood(), square.isPraticable(), null);
	}
	
	/**
	 * You must specify if the square contains food and if it is practicable when you
	 * instantiate a MemorySquare.
	 * @param food If the square contains food.
	 * @param praticable If the square is practicable.
	 */	
	public MemorySquare(boolean food, boolean praticable, Mouse giver) {
		this.food = food;
		this.praticable = praticable;
		this.giver = giver;
	}
	public Mouse getGiver() {
		return giver;
	}

	/**
	 * 
	 * @return true if the square contains food, or false.
	 */
	public boolean containsFood() {
		return food;
	}
	
	/**
	 * 
	 * @return true if the square is practicable, or false.
	 */
	public boolean isPraticable() {
		return praticable;
	}
	
	public String toString(){
		return "food : " + food + "   praticable : " + praticable; 
 	}
	
}
