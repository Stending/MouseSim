package mouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import utilities.Point;
import utilities.Random;


/**
 * This class manages mice' memory.
 * @author Zinedine
 *
 */
public class Memory {

	private LinkedHashMap<Point, MemorySquare> grid;
	private int size;
	private ArrayList<Mouse> liarMice;
	
	/**
	 * The constructor initializes the memory.
	 */
	public Memory() {
		grid = new LinkedHashMap<Point, MemorySquare>();
		size = 100;
		liarMice = new ArrayList<Mouse>();
	}
	
	/**
	 * This method adds a square to the mouse's memory.
	 * @param s The square to add.
	 * @param p The position of the square.
	 */
	public void addSquare(MemorySquare s, Point p) {
		if(grid.size() >= size)
			grid.remove(grid.entrySet().iterator().next().getKey());
		grid.put(p, s);
		
	}
	
	/**
	 * This methods checks if the memory is empty or not.
	 * @return true if the memory is empty, or false.
	 */
	public boolean isEmpty() {
		return grid.isEmpty();
	}

	/**
	 * 
	 * @return The maximum size of this mouse's memory.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * This method sets the maximum size of this mouse's memory.
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * This method returns a square which contains food according to this mouse's memory.
	 * @return A square with food in it.
	 */
	public Point getFoodPos() {
		for(Point p : grid.keySet()) {
			if(grid.get(p).containsFood())
				return p;
		}
		return null;
	}
	
	/**
	 * This methods returns a square which does not contains food according to this mouse's
	 * memory. (For liars)
	 * @return A square without food in it.
	 */
	public Point getWrongPos() {
		for(Point p : grid.keySet()) {
			if(!grid.get(p).containsFood() && grid.get(p).isPraticable())
				return p;
		}
		return null;
	}
	
	public Point getUnknownPos(int sizeX, int sizeY) {
		Point p = new Point();
		int counter = 0;
		do {
			p.setX(Random.randomInt(sizeX));
			p.setY(Random.randomInt(sizeY));
			counter++;
			if(counter >= 100){
				return p;
			}
		} while(grid.containsKey(p));
		return p; 
	}
	
	/**
	 * Returns an ArrayList which contains positions of all the Food Sources memorized by the
	 * mouse.
	 * @return An ArrayList of positions.
	 */
	public ArrayList<Point> getListOfFoodPositionsMemorized() {
		ArrayList<Point> al = new ArrayList<Point>();
		Iterator<Point> it = grid.keySet().iterator();
		while(it.hasNext()) {
			Point p = it.next();
			if(grid.get(p).containsFood())
				al.add(p);
		}
		return al;
	}

	public HashMap<Point, MemorySquare> getGrid() {
		return grid;
	}

	public ArrayList<Mouse> getLiarMice() {
		return liarMice;
	}


	
	
}
