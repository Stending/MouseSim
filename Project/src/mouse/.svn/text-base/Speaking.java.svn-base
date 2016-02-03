package mouse;

import utilities.Random;

/**
 * This class contains a mouse speaking mode.
 * @author Zinedine
 *
 */
public class Speaking {

	private int reliability;
	private boolean mute;
	
	/**
	 * The constructor initializes randomly the fields "reliability" and "mute".
	 */
	public Speaking() {
		reliability = Random.randomInt(5,true);
		mute = Random.randomBool(2);
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public int getReliability() {
		return reliability;
	}

	public boolean isMute() {
		return mute;
	}

	@Override
	public String toString() {
		return "Speaking [reliability=" + reliability + ", mute=" + mute + "]";
	}
	
}
