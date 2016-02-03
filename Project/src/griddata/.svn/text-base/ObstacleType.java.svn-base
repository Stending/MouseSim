package griddata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utilities.Random;

/**
 * Enumerates the types of obstacle.
 * @author Zinedine
 *
 */
public enum ObstacleType {
	Table{
		public String toString() {
	        return "Table";
	    }
	}
	, Barrel{
		public String toString() {
	        return "Barrel";
	    }
	}
	, Box{
		public String toString() {
	        return "Box";
	    }
	}
	, Hole{
		public String toString() {
	        return "Hole";
	    }
	}
	, Wood{
		public String toString() {
	        return "Wood";
	    }
	};
	
	
	
	 private static final List<ObstacleType> VALUES =
			    Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
			  
			  /**
			   * Returns a random obstacle type.
			   * @return A random type.
			   */
			  public static ObstacleType randomType()  {
			    return VALUES.get(Random.randomInt(SIZE));
			  }
			  
			  public static ObstacleType getType(int i){
				return VALUES.get(i);  
			  }
			  public static ObstacleType getType(String s){
					for(int i=0;i<SIZE;i++){
						if(VALUES.get(i).toString().equals(s))
							return VALUES.get(i);
					}
						return null;
			  }
				  
			  
			  public static int getIndexOf(ObstacleType ot){
				  return VALUES.indexOf(ot);
			  }
			  
			  public static String[] getTypeNames(){
				  String[] arr = new String[SIZE];
				  for(int i=0;i<SIZE;i++){
					  arr[i] = getType(i).toString();
				  }
				  return arr;
				  
			  }
}
