package utilities;

import java.util.ArrayList;
import java.util.HashMap;

import mouse.MemorySquare;

import griddata.Square;

/**
 * This class will calculate path from a point A to a point B
 */
public class PathFinding {
	
	private int sizeX;
	private int sizeY;
	
	public PathFinding(){
		this(10, 10);
	}
	
	public PathFinding(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	private class Node {

		private Node parent;
		private int cost;
		private Point p;
		

		public Node(Node parentNode, int cost, int x, int y) {

			this.parent = parentNode;
			this.cost = cost;
			this.p = new Point(x, y);

		}

		public boolean costLessThan(Node n) {
			return this.cost <= n.getCost();
		}

		public boolean isMatch(Node n) {
			return this.p.equals(n.getP());
		}

		public ArrayList<Node> getSuccessors() {
			ArrayList<Node> successors = new ArrayList<Node>();

			Point nextP;

			nextP = new Point(this.p.getX() - 1, this.p.getY());

			if (this.parent == null || !nextP.equals(this.parent.getP()))
				successors.add(new Node(this, this.cost + 1, nextP.getX(),
						nextP.getY()));
			nextP = new Point(this.p.getX() + 1, this.p.getY());
			if (this.parent == null || !nextP.equals(this.parent.getP()))
				successors.add(new Node(this, this.cost + 1, nextP.getX(),
						nextP.getY()));
			nextP = new Point(this.p.getX(), this.p.getY() - 1);
			if (this.parent == null || !nextP.equals(this.parent.getP()))
				successors.add(new Node(this, this.cost + 1, nextP.getX(),
						nextP.getY()));
			nextP = new Point(this.p.getX(), this.p.getY() + 1);
			if (this.parent == null || !nextP.equals(this.parent.getP()))
				successors.add(new Node(this, this.cost + 1, nextP.getX(),
						nextP.getY()));

			return successors;
		}

		public Direction directionFrom(Node n) {
			if (this.p.getX() > n.getP().getX())
				return Direction.Right;
			else if (this.p.getX() < n.getP().getX())
				return Direction.Left;
			else if (this.p.getY() < n.getP().getY())
				return Direction.Up;
			else
				return Direction.Down;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public int getCost() {
			return cost;
		}

		public Point getP() {
			return p;
		}

		public String toString() {
			return p.toString() + " cost : " + cost;
		}
	}

	private class SortedCostNodeList extends ArrayList<Node> {
		private static final long serialVersionUID = 1L;

		public void push(Node n) {
			int i = 0;
			while (i < this.size() && n.getCost() > this.get(i).getCost()) {
				i++;
			}
			this.add(i, n);
		}

		public Node pop() {
			Node n = this.get(0);
			this.remove(0);
			return n;
		}

		public int indexOf(Node n) {
			int i = 0;
			while (i < this.size() && !this.get(i).isMatch(n)) {
				i++;
			}
			if (i >= this.size()) {
				return -1;
			} else {
				return i;
			}
		}

	}

	public ArrayList<Direction> calculatePath(Point a, Point b,
			HashMap<Point, MemorySquare> memoryGrid) {

		ArrayList<Direction> solutionPathList = new ArrayList<Direction>();

		// Create a node containing the goal state node_goal
		Node node_goal = new Node(null, 1, b.getX(), b.getY());

		// Create a node containing the start state node_start
		Node node_start = new Node(null, 1, a.getX(), a.getY());

		// Create OPEN and CLOSED list
		SortedCostNodeList OPEN = new SortedCostNodeList();
		SortedCostNodeList CLOSED = new SortedCostNodeList();

		// Put node_start on the OPEN list
		OPEN.push(node_start);

		// while the OPEN list is not empty
		while (OPEN.size() > 0) {
			// Get the node off the open list
			// with the lowest f and call it node_current
			Node node_current = OPEN.pop();

			// if node_current is the same state as node_goal we
			// have found the solution;
			// break from the while loop;
			if (node_current.isMatch(node_goal)) {
				node_goal.setParent(node_current.getParent());
				break;
			}

			// Generate each state node_successor that can come after
			// node_current
			ArrayList<Node> successors = node_current.getSuccessors();
			// for each node_successor or node_current
			for (Node node_successor : successors) {
				// Set the cost of node_successor to be the cost of node_current
				// plus
				// the cost to get to node_successor from node_current
				// --> already set while we were getting successors

				// find node_successor on the OPEN list

				if (practicable(node_successor, memoryGrid)) {

					int oFound = OPEN.indexOf(node_successor);

					// if node_successor is on the OPEN list but the existing
					// one is
					// as good
					// or better then discard this successor and continue

					if (oFound > 0) {
						Node existing_node = OPEN.get(oFound);
						if (existing_node.costLessThan(node_current))
							continue;
					}

					// find node_successor on the CLOSED list
					int cFound = CLOSED.indexOf(node_successor);

					// if node_successor is on the CLOSED list
					// but the existing one is as good
					// or better then discard this successor and continue;
					if (cFound > 0) {
						Node existing_node = CLOSED.get(cFound);
						if (existing_node.costLessThan(node_current))
							continue;
					}

					// Remove occurences of node_successor from OPEN and CLOSED
					if (oFound != -1)
						OPEN.remove(oFound);
					if (cFound != -1)
						CLOSED.remove(cFound);

					// Set the parent of node_successor to node_current;
					// --> already set while we were getting successors

					// Set h to be the estimated distance to node_goal
					// (Using heuristic function)
					// --> already set while we were getting successors

					// Add node_successor to the OPEN list
					OPEN.push(node_successor);
				}
			}
			// Add node_current to the CLOSED list
			CLOSED.push(node_current);
		}

		Node p = node_goal;
		while (p.getParent() != null) {
			solutionPathList.add(0, p.directionFrom(p.getParent()));
			p = p.getParent();
		}
		if(solutionPathList.size() > 0){
			return solutionPathList;
		}else{
			return null;
		}
	}
		

	public ArrayList<Direction> calculatePath(Point a, Point b, Square[][] grid){
		HashMap<Point, MemorySquare> memoryGrid = new HashMap<Point, MemorySquare>();
		for(int i=0;i<grid[0].length;i++){
			for(int j=0;j<grid.length;j++){
				memoryGrid.put(new Point(j,i), new MemorySquare(grid[j][i]));
			}
		}
		return calculatePath(a,b, memoryGrid);
		
		
	}
	public boolean practicable(Node n, Square[][] grid) {
		Point p = n.getP();
		if (p.getX() < 0 || p.getX() >= grid.length)
			return false;
		else if (p.getY() < 0 || p.getY() >= grid[0].length) {
			return false;
		}

		if (grid[p.getX()][p.getY()].isPraticable()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean practicable(Node n, HashMap<Point, MemorySquare> memoryGrid) {
		Point p = n.getP();
		if (!memoryGrid.containsKey(p)){
			
			if ( p.getX() >= 0 && p.getX() < sizeX && p.getY() >= 0 && p.getY() < sizeY){
				return true;
			}else
				return false;
			
			
		}else if (memoryGrid.get(p).isPraticable()) {
			return true;
		} else {
			return false;
		}
	}

	
	
	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}



}
