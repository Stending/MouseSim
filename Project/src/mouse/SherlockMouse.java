package mouse;

import gui.Log;
import utilities.Point;

/**
 * This special mouse has the capacity to always know the truth. It can't be wrong.
 */
public class SherlockMouse extends Mouse {

	public SherlockMouse(Point p, String gender) {
		super(p,gender);
		super.getListenMode().setDeaf(false);
	}
	
	public SherlockMouse(Point p) {
		super(p);
		super.getListenMode().setDeaf(false);
	}
	
	@Override
	public Mouse copy() {
		Mouse m = new SherlockMouse(super.getPosition(),super.getGender());
		m.setListenMode(super.getListenMode());
		m.setSpeakMode(super.getSpeakMode());
		m.setSterile(super.isSterile());
		super.setDuplicated(true);
		return m;
	}
	
	@Override
	public void listen(Point p, Mouse giver, boolean truth) {
		if(truth) {
			if(super.getMemory().getGrid().containsKey(p))
				Log.write(this, "knew");
			else {
				Log.write(this, p, "sherlock");
				memorize(new MemorySquare(true, true, giver), p);
			}
		}
		else {
			Log.write(this, "sherlockdeny");
		}
	}
	
}
