package griddata;

import griddata.Food;
import griddata.Square;

/**
 * This class designates a square of a grid which is not praticable.
 * @author Rova
 *
 */

public class ObstacleSquare extends Square {

	
	private ObstacleType type;
	
	/**
	 * This constructor initializes a random appearance for the obstacle.
	 */
	public ObstacleSquare () {
		this.type = ObstacleType.randomType();
	}
	
	/**
	 * This constructor initializes the obstacle to a certain appearance.
	 * @param type
	 */
	public ObstacleSquare(ObstacleType type) {
		this.type = type;
	}
	
	public boolean isPraticable() {
		return false;
	}

	public boolean containsFood() {
		return false;
	}
	
	public int getFoodQuantity() {
		return 0;
	}
	
	public Food getFoodSource() {
		return null;
	}
	
	public ObstacleType getType() {
		return type;
	}

	public void setType(ObstacleType type) {
		this.type = type;
	}
}
