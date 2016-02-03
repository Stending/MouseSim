package griddata;

import java.util.ArrayList;

import utilities.Point;

import griddata.Square;
import mouse.Mouse;

/**
 * This class contains the grid where the simulation will operate.
 * @author Zinedine
 *
 */
public class Grid {

	private Square[][] grid;
	private ArrayList<Mouse> mice = new ArrayList<Mouse>();
	private GridParameters gridParams = new GridParameters(20,20,5,10,10,5, true);
	/**
	 * You must specify the size of the grid when you instantiate it.
	 * @param sizex The width of the grid.
	 * @param sizey The height of the grid.
	 */
	public Grid(int sizex, int sizey) {
		grid = new Square[sizex][sizey];
		gridParams = new GridParameters(sizex, sizey, 0, 0, 0, 5, true);
		for (int i=0;i<sizex; i++){
			for(int j=0;j<sizey;j++){
				grid[i][j] = new PraticableSquare();
			}
		}
		mice = new ArrayList<Mouse>();
	}

	/**
	 * Returns the square at specified position.
	 * @param p The position of the square you want.
	 * @return The square at the specified position.
	 */
	public Square getSquare(Point p){
		
		return getSquare(p.getX(), p.getY());
	}
	
	/**
	 * Returns the square at specified position.
	 * @param x The abscissa of the square you want.
	 * @param y The ordinate of the square you want.
	 * @return The square at the specified position. 
	 */
	public Square getSquare(int x, int y) {
		if((x < 0 || x >= grid.length) || (y < 0 || y >= grid[0].length)){
			return null;
		}else{
			return grid[x][y];
		}
	}
	
	/**
	 * This method is used to initialize a square in the grid.
	 * @param x The x position of the square.
	 * @param y The y position of the square.
	 * @param s The square.
	 */
	public void setSquare(int x, int y, Square s){
		grid[x][y] = s;
	}
	
	/**
	 * This method is used to initialize a square in the grid.
	 * @param p The position of the square.
	 * @param s The square.
	 */
	public void setSquare(Point p, Square s){
		setSquare(p.getX(),p.getY(), s);
	}
	
	/**
	 * This method returns an ArrayList which contains all mice present in the grid.
	 * @return An ArrayList of mice.
	 */
	public ArrayList<Mouse> getMice() {
		return mice;
	}
	
	
	/**
	 * Returns the mouse at specified index.
	 * @param i The index of the mouse.
	 * @return The mouse.
	 */
	public Mouse getMouse(int i){
		return mice.get(i);
	}
	
	/**
	 * Returns the square where a mouse is located.
	 * @param m The mouse.
	 * @return A square.
	 */
	public Square getMouseSquare(Mouse m) {
		return getSquare(m.getPosition());
	}
	
	/**
	 * This method returns an ArrayList which contains all mice present in the square at
	 * the specified position.
	 * @param p The position.
	 * @return An ArrayList of mice.
	 */
	public ArrayList<Mouse> getMiceAtPosition(Point p) {
		ArrayList<Mouse> al = new ArrayList<Mouse>();
		for(Mouse m : mice)
			if(m.getPosition().equals(p))
				al.add(m);
		return al;
	}
	
	/**
	 * Returns an ArrayList which contains positions of mice around a certain position.
	 * @param p The position.
	 * @return An ArrayList of mice.
	 */
	public ArrayList<Mouse> getMiceAroundPosition(Point p) {
		ArrayList<Mouse> al = getMiceAtPosition(p);
		if(p.getX()-1>=0)
			al = mergeLists(al,getMiceAtPosition(new Point(p.getX()-1,p.getY())));
		if(p.getX()+1<gridParams.getSizex())
			al = mergeLists(al,getMiceAtPosition(new Point(p.getX()+1,p.getY())));
		if(p.getY()+1<gridParams.getSizey())
			al = mergeLists(al,getMiceAtPosition(new Point(p.getX(),p.getY()+1)));
		if(p.getY()-1>=0)
			al = mergeLists(al,getMiceAtPosition(new Point(p.getX(),p.getY()-1)));
		return al;
	}
	
	/**
	 * This method merges two ArrayLists of mice.
	 * @param al1 First ArrayList.
	 * @param al2 Second ArrayList.
	 * @return Merged ArrayList.
	 */
	public ArrayList<Mouse> mergeLists(ArrayList<Mouse> al1, ArrayList<Mouse> al2) {
		for(Mouse m : al2)
			al1.add(m);
		return al1;
	}
	
	/**
	 * This method checks if a female mouse is present at the specified position. It is used
	 * for the reproduction.
	 * @param p The position.
	 * @return "true" if a female is present. Or "false".
	 */
	public boolean isThereFemaleMouseAtPosition(Point p) {
		for(Mouse m : getMiceAtPosition(p))
			if(m.getGender().equals("Female"))
				return true;
		return false;
	}
	
	/**
	 * Returns a female mouse present at the specified position.
	 * @param p The position.
	 * @return A female mouse.
	 */
	public Mouse getFemaleMouseAtPosition(Point p) {
		for(Mouse m : getMiceAtPosition(p))
			if(m.getGender().equals("Female"))
				return m;
		return null;
	}
	
	/**
	 * This method checks if a square at specified position contains more than one mouse.
	 * @param p The position.
	 * @return "true" if the square contains more than one mouse. Or "false".
	 */
	public boolean areThereOtherMiceAtPosition(Point p) {
		return getMiceAtPosition(p).size()>1;
	}
	
	/**
	 * Returns a mouse present at the specified position excepted the mouse we are handling.
	 * @param p The position.
	 * @param m The mouse we are handling.
	 * @return Another mouse.
	 */
	public Mouse getOtherMouseAtPosition(Point p, Mouse m) {
		for(Mouse mo : getMiceAtPosition(p))
			if(!mo.equals(m))
				return mo;
		return null;
	}

	/**
	 * Adds a mouse in the grid.
	 * @param m The mouse.
	 */
	public void addMouse(Mouse m) {
		mice.add(m);
	}
	
	/**
	 * Deletes a mouse from the grid.
	 * @param m The mouse.
	 */
	public void deleteMouse(Mouse m) {
		for(int i=0; i<mice.size();i++)
			if(m.equals(mice.get(i)))
				mice.remove(i);
	}

	public Square[][] getGrid() {
		return grid;
	}

	public void setGrid(Square[][] grid) {
		this.grid = grid;
	}

	public GridParameters getGridParams() {
		return gridParams;
	}

	public void setGridParams(GridParameters gridParams) {
		this.gridParams = gridParams;
	}

}
