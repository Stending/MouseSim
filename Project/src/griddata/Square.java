package griddata;

import griddata.Food;

/**
 * This class designates a square of which the grid is composed.
 * @author Rova
 *
 */

public abstract class Square {

	public abstract boolean isPraticable();
	
	public abstract boolean containsFood();
	
	public abstract int getFoodQuantity();
	
	public abstract Food getFoodSource();
	
}
