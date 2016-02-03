package mouse;

import java.util.ArrayList;

import gui.Log;
import utilities.Point;

/**
 * This special mouse has the capacity to fool every mouse, by lying to the mmice 
 * who believe what it says, and by telling the truth to the mice who don't believe it.
 */
public class MoriartyMouse extends Mouse {

	public MoriartyMouse(Point p, String gender) {
		super(p,gender);
		super.getSpeakMode().setMute(false);
	}
	
	public MoriartyMouse(Point p) {
		super(p);
		super.getSpeakMode().setMute(false);
	}
	
	@Override
	public Mouse copy() {
		Mouse m = new MoriartyMouse(super.getPosition(),super.getGender());
		m.setListenMode(super.getListenMode());
		m.setSpeakMode(super.getSpeakMode());
		m.setSterile(super.isSterile());
		super.setDuplicated(true);
		return m;
	}
	
	@Override
	public void speak(ArrayList<Mouse> miceList) {
		Point p;
		for(Mouse m : miceList) {
			if(!m.equals(this)) {
				p = super.getMemory().getWrongPos();
				m.memorize(new MemorySquare(true, true, this), p);
				Log.write(m, this, p);
			}
		}
	}
	
}
