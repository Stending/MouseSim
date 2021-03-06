package engine;

import utilities.Direction;
import engine.Statistics;
import utilities.PathFinding;
import utilities.Point;
import utilities.Random;
import mouse.FattyMouse;
import mouse.MoriartyMouse;
import mouse.Mouse;
import mouse.SherlockMouse;
import griddata.Grid;
import griddata.GridParameters;
import griddata.Square;
import gui.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class manages the whole simulation
 * 
 * @author Matthieu
 * 
 */

public class Simulation {
	private Grid grid;
	private int foodRegenCounter = 10;
	private int turnCounter = 0;
	private int numMouse = 0;
	
	private ArrayList<Mouse> miceToKill = new ArrayList<Mouse>();
	private ArrayList<Mouse> miceToAdd = new ArrayList<Mouse>();
	private int breedRate = 50;
	private int cloneRate = 50;
	private PathFinding pathFinding = new PathFinding(); 
	private boolean enableSpecialMice = true;
	
	
	public static ArrayList<String> boyNames = new ArrayList<String>();
	public static ArrayList<String> girlNames = new ArrayList<String>();
	public static int otherNamesCounter = 0;

	/**
	 * When the simulation is launched, the mice names are collected.
	 */
	public Simulation() {
		fillNamesLists();
		
	}

	/**
	 * This method will generate a grid depending on different parameters
	 * 
	 * @param params
	 *            contain every parameters needed to generate the grid
	 * 
	 */
	public void generateGrid(GridParameters params) {
		Statistics.globalStatistics = new Statistics();
		fillNamesLists();
		
		Generation gen = new Generation(params);
		pathFinding.setSizeX(params.getSizex());
		pathFinding.setSizeY(params.getSizex());
		Statistics.globalStatistics.setGridSizex(params.getSizex());
		Statistics.globalStatistics.setGridSizey(params.getSizey());
		Statistics.globalStatistics.setStartingMice(params.getNumMouse());
		Statistics.globalStatistics.setFoodSourceSpawnSpeed(params.getFoodRegenFrequency());
		Statistics.globalStatistics.setFoodSourceDensity(params.getfoodSourceDensity());
		Statistics.globalStatistics.setObstacleDensity(params.getObstacleDensity());
		Statistics.globalStatistics.setFoodSources((int)((float)params.getSizex()*params.getSizey()  * (params.getfoodSourceDensity() / (float)100)));	
		Statistics.globalStatistics.setTotalFoodQuantity((int)((float)params.getSizex()*params.getSizey()  * (params.getfoodSourceDensity() / (float)100))*10);	
		
		grid = gen.getGrid();
	}

	/**
	 * Launches the simulation
	 */
	public void launchSimulation() {
		
	}

	/**
	 * This method plays a new turn.
	 */
	public void simulateNextTurn() {
		turnCounter++;
		Log.writeLine(turnCounter);
		for (Mouse m : grid.getMice()) {
			if (!m.isDead())
				proceed(m);
		}
		killMice();
		addMice();
		numMouse += Statistics.globalStatistics.getAliveMice();
		Statistics.globalStatistics.setAverageMice(numMouse/turnCounter);
		if(Statistics.globalStatistics.getAliveMice()>Statistics.globalStatistics.getMaximumMice())
			Statistics.globalStatistics.setMaximumMice(Statistics.globalStatistics.getAliveMice());
		foodRegenCounter--;
		if(foodRegenCounter == 0){
			regenerateFood();
			foodRegenCounter = grid.getGridParams().getFoodRegenFrequency();
		}
		
	}

	/**
	 * This method is called to create regularly new food sources in the grid.
	 */
	public void regenerateFood(){
		Point p;
		do{
			p = new Point(Random.randomInt(grid.getGridParams().getSizex()), Random.randomInt(grid.getGridParams().getSizey()));
		}while(!grid.getSquare(p).isPraticable());
		grid.getSquare(p).getFoodSource().addFood(5);
		Statistics.globalStatistics.setFoodSources(Statistics.globalStatistics.getFoodSources()+1);
		Statistics.globalStatistics.setTotalFoodQuantity(Statistics.globalStatistics.getTotalFoodQuantity()+5);
	}
	
	/**
	 * This method adds new mice which were born in the current turn.
	 */
	public void addMice() {
		for(Mouse m : miceToAdd)
			grid.addMouse(m);
		miceToAdd.clear();
	}
	
	/**
	 * This method deletes mice which where killed in the current turn.
	 */
	public void killMice() {
		for (Mouse m : miceToKill) {
			grid.deleteMouse(m);
			Statistics.globalStatistics.setDeadMice(Statistics.globalStatistics.getDeadMice()+1);
			Statistics.globalStatistics.setAliveMice(Statistics.globalStatistics.getAliveMice()-1);
			if (m.getGender() == "Male") {
				Statistics.globalStatistics.setDeadMaleMice(Statistics.globalStatistics.getDeadMaleMice()+1);
				Statistics.globalStatistics.setAliveMaleMice(Statistics.globalStatistics.getAliveMaleMice()-1);
			}
			else	{
				Statistics.globalStatistics.setDeadFemaleMice(Statistics.globalStatistics.getDeadFemaleMice()+1);
				Statistics.globalStatistics.setAliveFemaleMice(Statistics.globalStatistics.getAliveFemaleMice()-1);
			}
		}

		miceToKill.clear();
	}

	/**
	 * Make act a mouse.
	 * 
	 * @param m
	 *            The acting mouse.
	 */
	public void proceed(Mouse m) {

		lookAround(m);
		if(m.getLifeTime()>50 && !m.isDuplicated() && Random.randomInt(100, true) <= cloneRate) {
			duplicateMouse(m);
			Log.write(m,"duplicate");
		}
		else if(m.getGender()=="Male" && m.getLifeTime() > 15 && grid.isThereFemaleMouseAtPosition(m.getPosition()) && grid.getFemaleMouseAtPosition(m.getPosition()).getLifeTime() > 30 && !grid.getFemaleMouseAtPosition(m.getPosition()).isPregnant() && m.getAge()>20 && grid.getFemaleMouseAtPosition(m.getPosition()).getAge()>20 && Random.randomInt(100, true) <= breedRate) {
			mate(m,grid.getFemaleMouseAtPosition(m.getPosition()));
			Log.write(m,grid.getFemaleMouseAtPosition(m.getPosition()),"breed");
		}
		else if (m.getLifeTime() > 50) {
			moveRandomly(m);
			Log.write(m,"random");
		} else if (m.hasTarget()) {
			
			if (moveToTarget(m)) {
				Log.write(m, "target");
				m.eat(grid.getMouseSquare(m).getFoodSource());
				Statistics.globalStatistics.setTotalFoodQuantity(Statistics.globalStatistics.getTotalFoodQuantity()-1);
				if(!grid.getMouseSquare(m).containsFood()){
					Statistics.globalStatistics.setFoodSources(Statistics.globalStatistics.getFoodSources()-1);
				}
			} else {
				if(m.getClosestFoodPosition() != null && m.distanceWith(m.getClosestFoodPosition()) < m.distanceWith(m.getTarget())) {
					m.searchTarget();
					calculatePathToTarget(m);
					Log.write(m,"newtarget");
				}
				else
					Log.write(m,"totarget");
			}
		} else if (grid.getMouseSquare(m).containsFood()) {
			if (m.getLifeTime() < 10 || !condSpe(m)) {
				m.eat(grid.getMouseSquare(m).getFoodSource());
				Statistics.globalStatistics.setTotalFoodQuantity(Statistics.globalStatistics.getTotalFoodQuantity()-1);
				if(!grid.getMouseSquare(m).containsFood()){
					Statistics.globalStatistics.setFoodSources(Statistics.globalStatistics.getFoodSources()-1);
				}
			} else if (m.knowFoodSource()) {
				Log.write(m,"newtarget");
				m.searchNewTarget();
				calculatePathToTarget(m);
			} else {
				Log.write(m,"explore");
				m.searchUnknown(grid.getGridParams());
				calculatePathToTarget(m);
			}
		} else if (m.knowFoodSource()) {
			Log.write(m,"newtarget");
			m.searchTarget();
			calculatePathToTarget(m);
		} else {
			Log.write(m,"explore");
			m.searchUnknown(grid.getGridParams());
			calculatePathToTarget(m);
		}
		m.speak(grid.getMiceAroundPosition(m.getPosition()));
		m.loseLife();
		if (m.getLifeTime() == 0) {
			Log.write(m,"death");
			miceToKill.add(m);
		}
		if (m.isPregnant()) {
			m.decrementPregnancy();
			if (m.getPregnancyTime() == 0) {
				birth(m);
				Log.write(m,"birth");
			}
		}
		lookAround(m);
		m.birthday();
		if(m.getAge()>=200)
			miceToKill.add(m);
	}

	/**
	 * This method determines if a mouse wants to change the food source to consume.
	 * @param m The mouse.
	 * @return true if the mouse wants to change food source, or false.
	 */
	public boolean condSpe(Mouse m) {
		return grid.getMouseSquare(m).getFoodQuantity() < m.getMemory()
				.getListOfFoodPositionsMemorized().size();
	}

	/**
	 * This method makes a mouse see around it.
	 * @param m
	 */
	public void lookAround(Mouse m) {
		for (int i = -m.getVision(); i <= m.getVision(); i++) {
			int vert = Math.abs(Math.abs(i) - m.getVision());
			for (int j = -vert; j <= vert; j++) {
				int posX = m.getPosition().getX() + i;
				int posY = m.getPosition().getY() + j;
				Point pos = new Point(posX, posY);
				Square s = grid.getSquare(pos);
				if (s != null) {
					m.memorize(s, pos);
					if(m.getTarget() != null && m.getTarget().equals(pos)){

						if(s.getFoodQuantity() == 0){
							m.setTarget(null);
							m.deceive(m.getMemory().getGrid().get(pos).getGiver());
						}
					}
				}
			}
		}
	}

	/**
	 * This method makes a mouse move to a random direction.
	 * @param m The mouse.
	 * @return true if there is no problem, or false.
	 */
	public boolean moveRandomly(Mouse m) {
		return moveTo(m, Direction.randomDirection());
	}

	/**
	 * This method makes a mouse move to its target.
	 * @param m The mouse.
	 * @return true if the mouse arrives on its target, or false.
	 */
	public boolean moveToTarget(Mouse m) {
		Direction dir = m.popPath();
		if (m.isOnTarget()) {
			m.setTarget(null);
			return true;
		}
		if (dir != null) {
			m.setLastDirection(dir);

			boolean b = moveTo(m, dir);
			if (!b) {
				calculatePathToTarget(m);
				moveToTarget(m);
			}
		}

		return false;
	}

	/**
	 * This method makes a mouse move to a certain direction.
	 * @param m The mouse.
	 * @param dir The direction.
	 * @return true if there is no problem, or false.
	 */
	public boolean moveTo(Mouse m, Direction dir) {
		m.setLastDirection(dir);
		Point mPos = m.getPosition();
		Point nextPos;
		if (dir == Direction.Left)
			nextPos = new Point(mPos.getX() - 1, mPos.getY());
		else if (dir == Direction.Right)
			nextPos = new Point(mPos.getX() + 1, mPos.getY());
		else if (dir == Direction.Up)
			nextPos = new Point(mPos.getX(), mPos.getY() - 1);
		else
			nextPos = new Point(mPos.getX(), mPos.getY() + 1);

		if (grid.getSquare(nextPos) != null
				&& grid.getSquare(nextPos).isPraticable()) {
			m.setPosition(nextPos);
			return true;
		} else
			return false;
	}

	/**
	 * This method makes a mouse calculate a path to its target.
	 * @param m The mouse.
	 * @return true if there is no problem, or false.
	 */
	public boolean calculatePathToTarget(Mouse m) {
		ArrayList<Direction> tempPath = pathFinding.calculatePath(
				m.getPosition(), m.getTarget(), m.getMemory().getGrid());

		if (tempPath != null) {
			m.setPath(tempPath);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This methods creates a clone of this mouse. It is used when the mouse is
	 * very healthy.
	 */
	public void duplicateMouse(Mouse m) {
		miceToAdd.add(m.copy());
		Statistics.globalStatistics.setDuplication(Statistics.globalStatistics.getDuplications()+1);
	}

	/**
	 * This method is used for the reproduction by a male mouse.
	 * 
	 * @param m
	 *            The female mouse to fertilize.
	 */
	public void mate(Mouse m, Mouse f) {
		if (!m.isSterile())
			fertilize(f);
			Statistics.globalStatistics.setCouplings(Statistics.globalStatistics.getCouplings()+1);
	}

	/**
	 * This method is used for the reproduction by a female mouse. It
	 * initializes the pregnancy count.
	 */
	public void fertilize(Mouse f) {
		if (!f.isSterile())
			f.setPregnancyTime(10);
			Statistics.globalStatistics.setImpregnateMice(Statistics.globalStatistics.getImpregnateMice()+1);
	}

	/**
	 * This method is used to create new mice at the end of the pregnancy time.
	 */
	public void birth(Mouse f) {
		Statistics.globalStatistics.setImpregnateMice(Statistics.globalStatistics.getImpregnateMice()-1);
		for (int i = 0; i < Random.randomInt(5, true); i++) {
			Mouse m;
			if(enableSpecialMice) {
				int rand = Random.randomInt(10);
				if(rand == 7)
					m = new FattyMouse(f.getPosition());
				else if(rand == 8)
					m = new SherlockMouse(f.getPosition());
				else if(rand == 9)
					m = new MoriartyMouse(f.getPosition());
				else
					m = new Mouse(f.getPosition());
			} else 
				m = new Mouse(f.getPosition());
			miceToAdd.add(m);
		}
		Statistics.globalStatistics.setBirths(Statistics.globalStatistics.getBirths()+1);
	}

	/**
	 * Returns the grid.
	 * @return the grid.
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Sets the grid.
	 * @param grid The grid.
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the number of turns since the beggining of the simulation.
	 * @return The number of turns.
	 */
	public int getTurnCounter() {
		return turnCounter;
	}
	
	/**
	 * Returns the mice which are waiting to be killed this turn.
	 * @return The mice to kill.
	 */
	public ArrayList<Mouse> getMiceToKill() {
		return miceToKill;
	}

	/**
	 * Returns the mice which are waiting to birth this turn.
	 * @return The mice to birth.
	 */
	public ArrayList<Mouse> getMiceToAdd() {
		return miceToAdd;
	}
	
	/**
	 * This method reads the file which contains all mice names and stocks them.
	 */
	public void fillNamesLists() {
		try {
			InputStream ips = new FileInputStream("micenames.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			boyNames.clear();
			girlNames.clear();
			while (!(ligne = br.readLine()).equals("---") && ligne != null) {
				boyNames.add(ligne);
			}
			if (ligne != null) {
				while ((ligne = br.readLine()) != null) {
					girlNames.add(ligne);
				}
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void setBreedRate(int breedRate) {
		this.breedRate = breedRate;
	}

	public void setCloneRate(int cloneRate) {
		this.cloneRate = cloneRate;
	}
}
