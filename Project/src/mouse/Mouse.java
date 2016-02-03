package mouse;

import java.util.ArrayList;

import engine.Simulation;
import engine.Statistics;
import utilities.Direction;
import utilities.Point;
import utilities.Random;

import griddata.Food;
import griddata.GridParameters;
import griddata.Square;
import gui.Log;

/**
 * This class represents each mouse present in the grid.
 * 
 * @author Zinedine
 * 
 */
public class Mouse {
	private String name;
	private String gender;
	private Point position;
	private int lifeTime;
	private int pregnancyTime;
	private Listening listenMode;
	private Speaking speakMode;
	private int vision;
	private boolean sterile;
	private Memory memory;
	private ArrayList<Direction> path;
	private Direction lastDirection;
	private Point target;
	private boolean dead;
	private int deceived;
	private int age;
	private boolean duplicated;

	/**
	 * You must specify the position of the mouse when you instantiate it.
	 * 
	 * @param p
	 *            The position of the mouse.
	 */
	
	public Mouse(Point p, String gender){
		this.gender = gender;
		name = generateName(gender);
		position = p;
		lifeTime = 20;
		listenMode = new Listening();
		speakMode = new Speaking();
		vision = Random.randomInt(4)+1;
		sterile = Random.randomBool(2);
		target = null;
		memory = new Memory();
		dead = false;
		lastDirection = Direction.Up;
		deceived = 0;
		path = new ArrayList<Direction>();
		age = 0;
		duplicated = false;
		Statistics.globalStatistics.setTotalMice(Statistics.globalStatistics.getTotalMice()+1);
		Statistics.globalStatistics.setAliveMice(Statistics.globalStatistics.getAliveMice()+1);
		if (gender.equals("Male")) {
			Statistics.globalStatistics.setTotalMaleMice(Statistics.globalStatistics.getTotalMaleMice()+1);
			Statistics.globalStatistics.setAliveMaleMice(Statistics.globalStatistics.getAliveMaleMice()+1);
		}
		else {
			Statistics.globalStatistics.setTotalFemaleMice(Statistics.globalStatistics.getTotalFemaleMice()+1);
			Statistics.globalStatistics.setAliveFemaleMice(Statistics.globalStatistics.getAliveFemaleMice()+1);
		}
		if (sterile)
			Statistics.globalStatistics.setSterileMice((Statistics.globalStatistics.getSterileMice()+1));
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Mouse(Point p) {
		this(p, (Random.randomBool() ? "Male" : "Female"));

		
	}

	

	public boolean knowFoodSource() {
		return memory.getListOfFoodPositionsMemorized().size() > 0;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setListenMode(Listening listenMode) {
		this.listenMode = listenMode;
	}

	public void setSpeakMode(Speaking speakMode) {
		this.speakMode = speakMode;
	}

	public void setSterile(boolean sterile) {
		this.sterile = sterile;
	}

	public boolean isDead() {
		return dead;
	}

	public Mouse copy() {
		Mouse m = new Mouse(this.position, this.gender);
		m.setListenMode(this.listenMode);
		m.setSpeakMode(this.speakMode);
		m.setSterile(this.sterile);
		this.duplicated = true;
		return m;
	}
	
	public void setDuplicated(boolean duplicated) {
		this.duplicated = duplicated;
	}

	public boolean isDuplicated() {
		return duplicated;
	}
	
	public void eat(Food f) {
		Log.write(this, "eat");
		f.consume();
		this.heal(6);
	}

	/**
	 * This method used without parameters increments the mouse life time.
	 */
	public void heal() {
		lifeTime++;
	}

	/**
	 * This method used with one parameter adds the specified number to the
	 * mouse life time.
	 * 
	 * @param n
	 *            The quantity to add.
	 */
	public void heal(int n) {
		lifeTime += n;
	}

	public void loseLife() {
		if (lifeTime > 0)
			lifeTime--;
	}

	public void loseLife(int n) {
		lifeTime -= n;
	}

	/**
	 * Checks if the mouse already has a target.
	 * 
	 * @return "true" if it has a target. Or "false".
	 */
	public boolean hasTarget() {
		return target != null;
	}

	/**
	 * Calculates the distance between the mouse and the specified position.
	 * 
	 * @param p
	 *            The position.
	 * @return A distance.
	 */

	public double distanceWith(Point p) {
		return this.position.distanceWith(p);
	}

	/**
	 * This method adds a square to the mouse memory.
	 * 
	 * @param s
	 *            The memory square to add.
	 * @param p
	 *            The position of the square.
	 */
	public void memorize(MemorySquare s, Point p) {
		memory.addSquare(s, p);
	}

	public void memorize(Square s, Point p) {
		memory.addSquare(new MemorySquare(s.containsFood(), s.isPraticable(), null),
				p);
	}

	/**
	 * This method is used when a mouse speak to another to give informations.
	 * The information given can be a lie if the mouse's reliability is low.
	 * 
	 * @param m
	 *            The mouse which will listen to this mouse.
	 */
	public void speak(ArrayList<Mouse> miceList) {
		Point p;
		if (!memory.isEmpty() && !speakMode.isMute()) {
			if (Random.randomInt(5, true) <= speakMode.getReliability())
				for(Mouse m : miceList) {
					if(!m.equals(this)) {
						Log.write(this,m,"indicate");
						Statistics.globalStatistics.setTruths(Statistics.globalStatistics.getTruths()+1);
						p = memory.getFoodPos();
						if(p != null)
							m.listen(p, this, true);
					}
				}
			else
				for(Mouse m : miceList) {
					if(!m.equals(this)) {
						Log.write(this, m, "lie");
						Statistics.globalStatistics.setLies(Statistics.globalStatistics.getLies()+1);
						m.listen(memory.getWrongPos(), this, false);
					}
				}
		}
		else
			Log.write(this, "mute");
	}

	public void listen(Point p, Mouse giver, boolean truth) {
		listen(p, giver);
	}
	
	/**
	 * This method is used when another mouse speaks to this mouse. The mouse
	 * can refuse the information if its trust is low.
	 * 
	 * @param p
	 *            The location designated by the other mouse.
	 */
	public void listen(Point p, Mouse giver) {
		if (!listenMode.isDeaf()) {
			if(memory.getGrid().containsKey(p) && memory.getGrid().get(p).containsFood())
				Log.write(this, "knew");
			else if (Random.randomInt(5, true) <= listenMode.getTrust()+this.deceived && isPotentialPosition(p) && !this.memory.getLiarMice().contains(giver)) {
				Log.write(this, p);
				memorize(new MemorySquare(true, true, giver), p);
				Statistics.globalStatistics.setAgreedInfo(Statistics.globalStatistics.getAgreedInfo()+1);
			}
			else {
				Log.write(this, "deny");
				Statistics.globalStatistics.setRefusedInfo(Statistics.globalStatistics.getRefusedInfo()+1);
			}
		}
		else
			Log.write(this, "deaf");
	}

	public Listening getListenMode() {
		return listenMode;
	}

	public Speaking getSpeakMode() {
		return speakMode;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public Memory getMemory() {
		return memory;
	}

	/**
	 * The mouse searches a target according to its memory.
	 */
	public void searchTarget() {
		target = getClosestFoodPosition();
		if(target == null){
			target = new Point(0,0);
		}
	}

	public void searchNewTarget() {
		target = getClosestFoodPositionExcepted(position);
		if(target == null){
			target = new Point(0,0);
		}
	}

	public void searchUnknown(GridParameters gp) {
		target = memory.getUnknownPos(gp.getSizex(), gp.getSizey());
	}


	public boolean isOnTarget() {
		return this.position.equals(target);
	}

	/**
	 * Return the closest food source position from the mouse.
	 * 
	 * @return A position.
	 */
	public Point getClosestFoodPosition() {
		ArrayList<Point> al = this.memory.getListOfFoodPositionsMemorized();
		if (!al.isEmpty()) {
			Point min;
			if(!al.get(0).equals(this.position))
				min = al.get(0);
			else if(al.size() > 1)
				min = al.get(1);
			else
				return null;
			
			for (Point p : al)
				if (this.distanceWith(p) < this.distanceWith(min) && !this.position.equals(p))
					min = p;
			return min;
		} else
			return null;
	}

	public Point getClosestFoodPositionExcepted(Point po) {
		ArrayList<Point> al = this.memory.getListOfFoodPositionsMemorized();
		if (!al.isEmpty()) {
			Point min;
			if(!al.get(0).equals(this.position))
				min = al.get(0);
			else if(al.get(1) != null)
				min = al.get(1);
			else
				return null;
			
			for (Point p : al)
				if (this.distanceWith(p) < this.distanceWith(min)
						&& !p.equals(po))
					min = p;
			return min;
		} else
			return null;
	}

	/**
	 * @return the first element of the Path ArrayList and remove it from the
	 *         ArrayList.
	 */
	public Direction popPath() {
		if (path.size() > 0) {
			Direction dir = path.get(0);
			path.remove(0);
			return dir;
		} else {
			return null;
		}
	}

	public void setTarget(Point target) {
		this.target = target;
	}

	public void setPregnancyTime(int time) {
		pregnancyTime = time;
	}

	public String getGender() {
		return gender;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getVision() {
		return vision;
	}

	public void setVision(int vision) {
		this.vision = vision;
	}

	public Direction getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(Direction lastDirection) {
		this.lastDirection = lastDirection;
	}

	public int getPregnancyTime() {
		return pregnancyTime;
	}

	public boolean isSterile() {
		return sterile;
	}

	public boolean isPregnant() {
		return pregnancyTime > 0;
	}

	public void decrementPregnancy() {
		pregnancyTime--;
	}

	@Override
	public String toString() {
		char c;
		if(this.gender.equals("Male"))
			c = '♂';
		else if(this.gender.equals("Female"))
			c = '♀';
		else
			c = '?';
		return name + " " + c + " (" + lifeTime + ")";
	
	}

	public String getName() {
		return name;
	}

	/**
	 * This method returns a name according to the gender specified.
	 * 
	 * @param gender
	 *            The gender of the mouse which name will be returned.
	 * @return The new mouse name.
	 */
	private String generateName(String gender) {
		ArrayList<String> names = new ArrayList<String>();

		if (gender.equals("Male"))
			names = Simulation.boyNames;
		else if (gender.equals("Female"))
			names = Simulation.girlNames;
		
		if(names.isEmpty()){
			Simulation.otherNamesCounter++;
			return "Numéro " + Simulation.otherNamesCounter;
		}else{
			int rand = Random.randomInt(names.size());
			String name = names.get(rand);
			names.remove(rand);
			return name;
		}
	}

	public Point getTarget() {
		return target;
	}

	public void setPath(ArrayList<Direction> path) {
		this.path = path;		
	}
	
	public boolean isPotentialPosition(Point p) {
		boolean b = true;
		if(memory.getGrid().containsKey(p)) {
			if(memory.getGrid().get(p).isPraticable()) {
				if(distanceWith(p) <= vision)
					b = false;
			}
			else
				b = false;
		}
		return b;
	}

	public ArrayList<Direction> getPath() {
		return path;
	}

	public void deceive(Mouse m) {
		deceived++;
		Log.write(this, "deceived");
		if(m != null && Random.randomBool()) {
			this.memory.getLiarMice().add(m);
			Log.write(this, m, "liar");
		}
	}
	
	public void birthday() {
		age++;
	}
	
	public int getAge() {
		return age;
	}
	
}