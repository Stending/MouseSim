package test;

import java.util.ArrayList;

import utilities.Direction;
import utilities.PathFinding;
import utilities.Point;
import griddata.Grid;
import griddata.ObstacleSquare;
import junit.framework.TestCase;

public class PathFindingTest extends TestCase {

	public void testPathFinding(){
		Grid grid = new Grid(3, 3);
		grid.setSquare(new Point(1,1), new ObstacleSquare());
		grid.setSquare(new Point(1,2), new ObstacleSquare());
		PathFinding pf = new PathFinding(3,3);
		
		ArrayList<Direction> path = pf.calculatePath(
				new Point(0,1), new Point(2,1), grid.getGrid());
		
		assertEquals(path.get(0), Direction.Up);
		assertEquals(path.get(1), Direction.Right);
		assertEquals(path.get(2), Direction.Right);
		assertEquals(path.get(3), Direction.Down);
		
		grid.setSquare(new Point(1,0), new ObstacleSquare());
		
		
		path = pf.calculatePath(
				new Point(0,1), new Point(2,1), grid.getGrid());
		
		assertNull(path);
		
		path = pf.calculatePath(
				new Point(0,0), new Point(0,0), grid.getGrid());
	}
	
}
