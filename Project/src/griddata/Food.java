package griddata;

import engine.Statistics;

/**
 * This class is contained in each square. It contains an amount of food which is an integer.
 * @author Zinedine
 *
 */
public class Food {

	private int quantity;
	
	/**
	 * The amount of food must be specified in the constructor.
	 * @param quantity The quantity of food the source will contain.
	 */
	public Food(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * The method "consume" without parameters decrements the quantity of food if the source is not empty.
	 * @return True if the consumption is done. False if the quantity is insufficient.
	 */
	public boolean consume(){
		if(!empty()){
			quantity--;
			if(this.empty())
				Statistics.globalStatistics.setFoodSources(Statistics.globalStatistics.getFoodSources()-1);
			return true;
		} else
			return false;
	}
	
	/**
	 * The method "consume" with one parameter substracts the number specified to the quantity if it contains enough food.
	 * @param number The amount of food which will be eaten.
	 * @return True if the consumption is done. False if the quantity is insufficient.
	 */
	public boolean consume(int number){
		if(quantity >= number){
			quantity -= number;
			return true;
		} else
			return false;
	}
	
	/**
	 * This method consumes all the food contained is the source.
	 * @return True if the consumption is done. False if the quantity is insufficient.
	 */
	public boolean clean(){
		if(!empty()){
			quantity = 0;
			return true;
		} else
			return false;
	}
	
	/**
	 * Check if the source is empty or not.
	 * @return True if the source is empty. False if the source is not empty.
	 */
	public boolean empty(){
		return quantity==0;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * This method used without parameters increments the amount of food.
	 */
	public void addFood(){
		addFood(1);
	}
	
	/**
	 * This method used with one parameter adds to the amount of food the quantity specified.
	 * @param quantity
	 */
	public void addFood(int quantity){
		this.quantity += quantity;
	}
	
	@Override
	public String toString(){
		if(!empty())
			return "The food source contains " + quantity + " unities.";
		else
			return "The food source is empty.";
	}
}
