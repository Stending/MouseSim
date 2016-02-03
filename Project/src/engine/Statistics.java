package engine;

/**
 * This class manages the simulation's statistics.
 * @author Zinedine
 *
 */
public class Statistics {
	public static Statistics globalStatistics = new Statistics();
	
	private int totalMice = 0;
	private int totalMaleMice = 0;
	private int totalFemaleMice = 0;
	private int deadMice = 0;
	private int deadMaleMice = 0;
	private int deadFemaleMice = 0;
	private int aliveMice = 0;
	private int aliveMaleMice = 0;
	private int aliveFemaleMice = 0;
	private int averageMice = 0;
	private int maximumMice = 0;
	private int foodSources  = 0;
	private int totalFoodQuantity = 0;	
	
	private int gridSizex  = 0;
	private int gridSizey  = 0;
	private int StartingMice  = 0;
	private int foodSourceSpawnSpeed  = 0;
	private float obstacleDensity  = 0;
	private int foodSourceDensity  = 0;
	
	private int couplings = 0;
	private int impregnateMice = 0;
	private int sterileMice = 0;
	private int births = 0;
	private int duplications = 0;
	
	
	private int truths = 0;
	private int lies = 0;
	private int agreedInfo = 0;
	private int refusedInfo = 0;
	
	/**
	 * The constructor initializes all values to zero.
	 */
	public Statistics () {
		this.totalMice = 0;
		this.totalMaleMice = 0;
		this.totalFemaleMice = 0;
		this.deadMice = 0;
		this.deadMaleMice = 0;
		this.deadFemaleMice = 0;
		this.aliveMice = 0;
		this.aliveMaleMice = 0;
		this.aliveFemaleMice = 0;
		this.averageMice = 0;
		this.maximumMice = 0;
		this.foodSources  = 0;
		this.totalFoodQuantity = 0;	
		
		this.gridSizex  = 0;
		this.gridSizey  = 0;
		this.StartingMice  = 0;
		this.foodSourceSpawnSpeed  = 0;
		this.obstacleDensity  = 0;
		this.foodSourceDensity  = 0;
		
		this.couplings = 0;
		this.impregnateMice = 0;
		this.sterileMice = 0;
		this.births = 0;
		this.duplications = 0;
		
		this.truths = 0;
		this.lies = 0;
		this.agreedInfo = 0;
		this.refusedInfo = 0;
					
	}
		
	public static Statistics getGlobalStatistics() {
		return globalStatistics;
	}
	
	public static void setGlobalStatistics(Statistics globalStatistics) {
		Statistics.globalStatistics = globalStatistics;
	}
	
	public int getStartingMice() {
		return StartingMice;
	}
	public void setStartingMice(int StartingMice) {
		this.StartingMice = StartingMice;
	}
	public int getFoodSourceSpawnSpeed() {
		return foodSourceSpawnSpeed;
	}
	public void setFoodSourceSpawnSpeed(int foodSourceSpawnSpeed) {
		this.foodSourceSpawnSpeed = foodSourceSpawnSpeed;
	}
	public float getObstacleDensity() {
		return obstacleDensity;
	}
	public void setObstacleDensity(float obstacleDensity) {
		this.obstacleDensity = obstacleDensity;
	}
	public int getFoodSourceDensity() {
		return foodSourceDensity;
	}
	public void setFoodSourceDensity(int foodSourceDensity) {
		this.foodSourceDensity = foodSourceDensity;
	}
	public int getTotalMice() {
		return totalMice;
	}
	public void setTotalMice(int totalMice) {
		this.totalMice = totalMice;
	}
	public int getTotalMaleMice() {
		return totalMaleMice;
	}
	public void setTotalMaleMice(int totalMaleMice) {
		this.totalMaleMice = totalMaleMice;
	}
	public int getTotalFemaleMice() {
		return totalFemaleMice;
	}
	public void setTotalFemaleMice(int totalFemaleMice) {
		this.totalFemaleMice = totalFemaleMice;
	}
	public int getDeadMice() {
		return deadMice;
	}
	public void setDeadMice(int deadMice) {
		this.deadMice = deadMice;
	}
	public int getDeadMaleMice() {
		return deadMaleMice;
	}
	public void setDeadMaleMice(int deadMaleMice) {
		this.deadMaleMice = deadMaleMice;
	}
	public int getDeadFemaleMice() {
		return deadFemaleMice;
	}
	public void setDeadFemaleMice(int deadFemaleMice) {
		this.deadFemaleMice = deadFemaleMice;
	}
	public int getAliveMice() {
		return aliveMice;
	}
	public void setAliveMice(int aliveMice) {
		this.aliveMice = aliveMice;
	}
	public int getAliveMaleMice() {
		return aliveMaleMice;
	}
	public void setAliveMaleMice(int aliveMaleMice) {
		this.aliveMaleMice = aliveMaleMice;
	}
	public int getAliveFemaleMice() {
		return aliveFemaleMice;
	}
	public void setAliveFemaleMice(int aliveFemaleMice) {
		this.aliveFemaleMice = aliveFemaleMice;
	}
	public int getAverageMice() {
		return averageMice;
	}
	public void setAverageMice(int averageMice) {
		this.averageMice = averageMice;
	}
	public int getMaximumMice() {
		return maximumMice;
	}
	public void setMaximumMice(int maximumMice) {
		this.maximumMice = maximumMice;
	}
	public int getFoodSources() {
		return foodSources;
	}
	public void setFoodSources(int foodSources) {
		this.foodSources = foodSources;
	}
	public int getCouplings() {
		return couplings;
	}
	public void setCouplings(int couplings) {
		this.couplings = couplings;
	}
	public int getImpregnateMice() {
		return impregnateMice;
	}
	public void setImpregnateMice(int impregnateMice) {
		this.impregnateMice = impregnateMice;
	}
	public int getSterileMice() {
		return sterileMice;
	}
	public void setSterileMice(int sterileMice) {
		this.sterileMice = sterileMice;
	}
	public int getBirths() {
		return births;
	}
	public void setBirths(int births) {
		this.births = births;
	}
	public int getTruths() {
		return truths;
	}
	public void setTruths(int truths) {
		this.truths = truths;
	}
	public int getLies() {
		return lies;
	}
	public void setLies(int lies) {
		this.lies = lies;
	}
	public int getAgreedInfo() {
		return agreedInfo;
	}
	public void setAgreedInfo(int agreedInfo) {
		this.agreedInfo = agreedInfo;
	}
	public int getRefusedInfo() {
		return refusedInfo;
	}
	public void setRefusedInfo(int refusedInfo) {
		this.refusedInfo = refusedInfo;
	}
	
	public int getGridSizex() {
		return gridSizex;
	}

	public void setGridSizex(int gridSizex) {
		this.gridSizex = gridSizex;
	}

	public int getGridSizey() {
		return gridSizey;
	}

	public void setGridSizey(int gridSizey) {
		this.gridSizey = gridSizey;
	}

	public void setDuplications(int duplications) {
		this.duplications = duplications;
	}

	public int getDuplications() {
		return duplications;
	}

	public void setDuplication(int duplications) {
		this.duplications = duplications;
	}

	public int getTotalFoodQuantity() {
		return totalFoodQuantity;
	}

	public void setTotalFoodQuantity(int totalFoodQuantity) {
		this.totalFoodQuantity = totalFoodQuantity;
	}
		
}
