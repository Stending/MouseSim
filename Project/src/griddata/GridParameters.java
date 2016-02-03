package griddata;

/**
 * This class is used to stock informations about the grid.
 * @author Zinedine
 *
 */
public class GridParameters {
	
	
	private int sizex; 
	private int sizey;
	private int numMouse;
	private int foodSourceDensity;
	private float obstacleDensity;
	private int foodRegenFrequency;
	private boolean enableSpecialMice;
	
	/**
	 * This constructor initializes the parameters to default values.
	 */
	public GridParameters(){
		this(20,20,5,10,10,5, true);
	}
	
	/**
	 * This contructor initializes the parameters to specified values.
	 * @param sizex Grid width.
	 * @param sizey Grid height.
	 * @param numMouse Number of mice.
	 * @param foodSourceDensity Density of food sources.
	 * @param obstacleDensity Density of obstacles.
	 * @param foodRegenFrequency Frequency of food regeneration.
	 */
	public GridParameters(int sizex, int sizey, int numMouse, int foodSourceDensity, float obstacleDensity, int foodRegenFrequency, boolean enableSpecialMice) {
		this.sizex = sizex;
		this.sizey = sizey;
		this.numMouse = numMouse;
		this.foodSourceDensity = foodSourceDensity;
		this.obstacleDensity = obstacleDensity;
		this.foodRegenFrequency = foodRegenFrequency;
		this.enableSpecialMice = enableSpecialMice;
	}

	public int getSizex() {
		return sizex;
	}

	public void setSizex(int sizex) {
		this.sizex = sizex;
	}

	public int getSizey() {
		return sizey;
	}

	public void setSizey(int sizey) {
		this.sizey = sizey;
	}

	public int getNumMouse() {
		return numMouse;
	}

	public void setNumMouse(int numMouse) {
		this.numMouse = numMouse;
	}

	public int getfoodSourceDensity() {
		return foodSourceDensity;
	}

	public void setfoodSourceDensity(int foodSourceDensity) {
		this.foodSourceDensity = foodSourceDensity;
	}

	public float getObstacleDensity() {
		return obstacleDensity;
	}

	public void setObstacleDensity(float obstacleDensity) {
		this.obstacleDensity = obstacleDensity;
	}

	public int getFoodRegenFrequency() {
		return foodRegenFrequency;
	}

	public void setFoodRegenFrequency(int foodRegenFrequency) {
		this.foodRegenFrequency = foodRegenFrequency;
	}

	public boolean isEnableSpecialMice() {
		return enableSpecialMice;
	}

	public void setEnableSpecialMice(boolean enableSpecialMice) {
		this.enableSpecialMice = enableSpecialMice;
	}
	
	

}
