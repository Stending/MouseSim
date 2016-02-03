package utilities;

import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * This class contains some drawing methods used in other classes.
 */
public class GUIDrawing {

	
	public static void drawCenteredString(String s, int px, int py, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, px + x, py + y);
	}
}