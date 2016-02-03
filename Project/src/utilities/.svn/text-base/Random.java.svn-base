package utilities;

/**
 * This class contains several methods to generate random numbers or booleans.
 * It can't be instantiated.
 * @author Zinedine
 *
 */
public class Random {

	/**
	 * This method used with one parameter returns a random integer between 0 and max (excluded).
	 * @param max The maximum value the method can return (excluded).
	 * @return The random integer.
	 */
	public static int randomInt(int max) {
		return (int)(Math.random() * max);
	}
	
	/**
	 * This method used with two parameters returns a random integer between 0 and max 
	 * included if "included" is true, or excluded if "included" is false.
	 * @param max The maximum value the method can return.
	 * @param included "true" if the maximum should be included.
	 * @return The random integer.
	 */
	public static int randomInt(int max, boolean included) {
		if(included)
			return (int)(Math.random() * (max + 1));
		else
			return (int)(Math.random() * max);
	}
	
	/**
	 * This method used without parameters returns randomly true or false.
	 * @return A random boolean.
	 */
	public static boolean randomBool() {
		return (Math.random()<0.5);
	}
	
	/**
	 * This method used with one parameter returns a random boolean weighted by n.
	 * @param n An integer between 0 and 10. Closer to 10 will be n, more often the method
	 * will return "true".
	 * @return A random boolean.
	 */
	public static boolean randomBool(int n) {
		return (Math.random()<(float)n/10);
	}
}