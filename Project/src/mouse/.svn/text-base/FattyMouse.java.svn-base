package mouse;

import griddata.Food;
import gui.Log;
import utilities.Point;

/**
 * This special mouse has the capacity to empty every food source it eat.
 */
public class FattyMouse extends Mouse {

	public FattyMouse(Point p, String gender) {
		super(p,gender);
		super.setName("Fatty " + super.getName());
	}
	
	public FattyMouse(Point p) {
		super(p);
		super.setName("Fatty " + super.getName());
	}
	
	@Override
	public void eat(Food f) {
		Log.write(this, "fattyeat");
		f.clean();
		super.heal(20);
	}
	
}
