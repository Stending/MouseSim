package engine;
import java.util.ArrayList;

import mouse.FattyMouse;
import mouse.MoriartyMouse;
import mouse.Mouse;
import mouse.SherlockMouse;

import utilities.PathFinding;
import utilities.Point;
import utilities.Random;

import griddata.Grid;
import griddata.GridParameters;
import griddata.ObstacleSquare;
import griddata.PraticableSquare;
import griddata.Square;

/**
 * This class initializes the grid's settings for the first time.
 * @author Matthieu
 *
 */
public class Generation {
	
	private Grid grid;
	
	private int sizex;
	private int sizey;
	private int numMouse;
	private float foodSourceDensity;
	private float obstacleDensity;
	private boolean enableSpecialMice = true;
	private int nbSquares;

	/**
	 * The parameters of the grid which will be generated must be specified in the argument.
	 * @param params The parameters of the grid.
	 */
	public Generation(GridParameters params) {
		this.sizex = params.getSizex();
		this.sizey = params.getSizey();
		this.numMouse = params.getNumMouse();
		this.foodSourceDensity = params.getfoodSourceDensity();
		this.obstacleDensity = params.getObstacleDensity();
		this.enableSpecialMice = params.isEnableSpecialMice();
		this.nbSquares = sizex*sizey;
		
		initGrid();
		generateFoodsources();
		generateObstacles();
		generateMice();
		grid.setGridParams(params);
	}
	
	/**
	 * This method initializes the grid.
	 */
	public void initGrid(){
		this.grid = new Grid(sizex, sizey);
	}
	
	/**
	 * This method places food sources in the grid according to the food source density.
	 */
	public void generateFoodsources() {		
		
		int nbFoodToCreate = (int)((float)nbSquares  * (foodSourceDensity / (float)100));
		
		ArrayList<Point> foodPoints = new ArrayList<Point>();
		
		int i;
		for(i=0;i<nbFoodToCreate;i++){
			Point p;
			do{
				p = new Point(Random.randomInt(sizex), Random.randomInt(sizey));		
			}while(foodPoints.contains(p) || !grid.getSquare(p).isPraticable());
			
			((PraticableSquare)grid.getSquare(p)).getFoodSource().setQuantity(10);
			
			foodPoints.add(p);
		}
		
	}	
	
	/**
	 * This method places obstacles in the grid according to the obstacle density.
	 */
	public void generateObstacles(){
		int nbObstacleToCreate = (int)((float)nbSquares  * (obstacleDensity / (float)100));
		
		PathFinding pathFinding = new PathFinding(sizex, sizey);
		ArrayList<Point> obstaclePoints = new ArrayList<Point>();
		ArrayList<Point> impossiblePoints = new ArrayList<Point>();
		
		int i;
		for(i=0;i<nbObstacleToCreate;i++){
			Point p;
			boolean goodObstacle;
			do{
				p = new Point(Random.randomInt(sizex), Random.randomInt(sizey));
				goodObstacle = true;
				if(obstaclePoints.contains(p) || !grid.getSquare(p).isPraticable()){
					goodObstacle = false;
				}else{
					ArrayList<Point> aroundSquares = new ArrayList<Point>();
					Point pt;
					pt= new Point(p.getX()-1, p.getY());
					if(grid.getSquare(pt) != null && grid.getSquare(pt).isPraticable())
						aroundSquares.add(pt);
					
					pt= new Point(p.getX(), p.getY()-1);
					if(grid.getSquare(pt) != null && grid.getSquare(pt).isPraticable())
						aroundSquares.add(pt);
					
					pt= new Point(p.getX()+1, p.getY());
					if(grid.getSquare(pt) != null && grid.getSquare(pt).isPraticable())
						aroundSquares.add(pt);
					
					pt= new Point(p.getX(), p.getY()+1);
					if(grid.getSquare(pt) != null && grid.getSquare(pt).isPraticable())
						aroundSquares.add(pt);
					
					Square tempSqr = grid.getSquare(p);
					grid.setSquare(p, new ObstacleSquare());
					for(int j=0;j<aroundSquares.size();j++){
						for(int k=j+1;k<aroundSquares.size();k++){
							
							if(pathFinding.calculatePath(aroundSquares.get(j), aroundSquares.get(k), grid.getGrid()) == null){
								goodObstacle = false;
								impossiblePoints.add(p);
								break;
							}
						}
					}
					grid.setSquare(p, tempSqr);
					
				}
				
			}while(!goodObstacle);
			
			grid.setSquare(p, new ObstacleSquare());
			
			obstaclePoints.add(p);
		}
	}
	
	/**
	 * This method creates a certain number of mice in the grid. Those mice can be special or not.
	 */
	public void generateMice(){
		Mouse m;
		for(int i=0; i<numMouse;i++){
			Point p;
			do{
				p = new Point (Random.randomInt(sizex),Random.randomInt(sizey));
			}while(!grid.getSquare(p).isPraticable());
			if(enableSpecialMice) {
				int rand = Random.randomInt(10);
				if(rand == 7)
					m = new FattyMouse(p);
				else if(rand == 8)
					m = new SherlockMouse(p);
				else if(rand == 9)
					m = new MoriartyMouse(p);
				else
					m = new Mouse(p);
			} else 
				m = new Mouse(p);
			grid.getMice().add(m);
		}
		
		
	}
	
	/**
	 * Returns the grid.
	 * @return The grid.
	 */
	public Grid getGrid(){
		return grid;
	}
}
