package mouse;

import utilities.Random;

/**
 * This class contains a mouse listening mode.
 * @author Zinedine
 *
 */
public class Listening {

	private int trust;
	private boolean deaf;
	
	/**
	 * The constructor initializes randomly the fields "trust" and "deaf".
	 */
	public Listening() {
		trust = Random.randomInt(5,true);
		deaf = Random.randomBool(2);
	}

	public void setDeaf(boolean deaf) {
		this.deaf = deaf;
	}

	public int getTrust() {
		return trust;
	}

	public boolean isDeaf() {
		return deaf;
	}

	@Override
	public String toString() {
		return "Listening [trust=" + trust + ", deaf=" + deaf + "]";
	}
	
}
