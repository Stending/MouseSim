package gui;

import griddata.Grid;
import griddata.ObstacleSquare;
import griddata.ObstacleType;
import griddata.Square;
import griddata.PraticableSquare;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mouse.Mouse;

import utilities.Direction;
import utilities.Point;

/**
 * This class is used to display the grid.
 * @author Zinedine
 *
 */
public class GridGUI extends JPanel {

	private static final long serialVersionUID = -8894601442689885332L;
	public int squareSize = 50;
	private String displayMode = "Global";
	private Grid grid;
	private Mouse mouse;
	private Point selectedSquare = null;
	private BufferedImage maleMouseSpriteSheet;
	private BufferedImage femaleMouseSpriteSheet;
	private BufferedImage floorSprite;
	private BufferedImage foodSprite;
	private BufferedImage targetSprite;
	private HashMap<String, BufferedImage> obstacles = new HashMap<String, BufferedImage>();
	private BufferedImage mouseParquet;
	private BufferedImage mouseObstacle;
	private BufferedImage mouseFood;

	/**
	 * The constructor initializes all the pictures used by the grid.
	 */
	public GridGUI() {
		try {
			maleMouseSpriteSheet = ImageIO.read(new File("./pictures/sprites/MaleMouseSprite.png"));
			femaleMouseSpriteSheet = ImageIO.read(new File("./pictures/sprites/FemaleMouseSprite.png"));
			floorSprite = ImageIO.read(new File("./pictures/sprites/Parquet.png"));
			foodSprite = ImageIO.read(new File("./pictures/sprites/food.png"));
			targetSprite = ImageIO.read(new File("./pictures/sprites/target.png"));
			obstacles.put("Wood",ImageIO.read(new File("./pictures/sprites/wood.png")));
			obstacles.put("Table",ImageIO.read(new File("./pictures/sprites/table.png")));
			obstacles.put("Barrel",ImageIO.read(new File("./pictures/sprites/barrel.png")));
			obstacles.put("Box",ImageIO.read(new File("./pictures/sprites/box.png")));
			obstacles.put("Hole",ImageIO.read(new File("./pictures/sprites/hole.png")));
			mouseParquet = ImageIO.read(new File("./pictures/sprites/NegaParquet.png"));
			mouseObstacle = ImageIO.read(new File("./pictures/sprites/negawood.png"));
			mouseFood = ImageIO.read(new File("./pictures/sprites/NegaFood.png"));
		} catch (IOException e) {
			System.err.println("Image could not be read");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (displayMode.equals("Global")) {
			paintGlobalGrid(g);
		} else if (displayMode.equals("Memory")) {
			paintMemoryGrid(g);
		} else if(displayMode.equals("Outline")){
			paintOutlineGrid(g);
		}

	}

	/**
	 * Paints the grid in normal mode.
	 * @param g The graphics.
	 */
	public void paintGlobalGrid(Graphics g) {

		for (int i = 0; i < grid.getGridParams().getSizey(); i++) {
			for (int j = 0; j < grid.getGridParams().getSizex(); j++) {
				Square s = grid.getSquare(j, i);
				drawFloor(new Point(j, i), g);
				if (!s.isPraticable()) {
					drawObstacle(new Point(j, i), g,
							((ObstacleSquare) s).getType());
				} else if (s.containsFood()) {
					drawFood(new Point(j, i), ((PraticableSquare) s)
							.getFoodSource().getQuantity(), g);
				}
			}
		}

		for (Mouse m : grid.getMice()) {
			drawMouse(m, g);
		}

		if (selectedSquare != null) {
			highLightSquare(selectedSquare, g);
		}

	}

	/**
	 * Paints the grid in memory mode.
	 * @param g The graphics.
	 */
	public void paintMemoryGrid(Graphics g) {
		for (int i = 0; i < grid.getGridParams().getSizey(); i++) {
			for (int j = 0; j < grid.getGridParams().getSizex(); j++) {
				Point p = new Point(j, i);
				if (!mouse.getMemory().getGrid().containsKey(p)) {
					drawUnknown(p, g);
				} else {
					drawFloor(p, g, true);
					if (!mouse.getMemory().getGrid().get(p).isPraticable()) {
						drawObstacle(p, g, true);
					} else if (mouse.getMemory().getGrid().get(p).containsFood()) {
						drawFood(p, g, true);
					}
					
				}
			}
		}		
		if(grid.getMice().contains(mouse)) {
		drawMouse(mouse, g);
			if ((mouse.getTarget()!=null))
				drawTarget(mouse.getTarget(), g);
				drawPath(mouse.getPosition(), mouse.getPath(), g);
		}
		else {
			displayMode = "Global";
			JOptionPane.showMessageDialog(this, "The mouse is dead...");
		}
		drawVision(mouse.getPosition(), mouse.getVision(), g);
	}
	
	/**
	 * Paints the grid in schematic mode.
	 * @param g The graphics.
	 */
	public void paintOutlineGrid(Graphics g){
		for (int i = 0; i < grid.getGridParams().getSizey(); i++) {
			for (int j = 0; j < grid.getGridParams().getSizex(); j++) {
				Square s = grid.getSquare(j, i);
				if (s.isPraticable()) {
					drawSquare(new Point(j,i), g, 1, Color.lightGray);
					if (s.containsFood()) {
						drawCircle(new Point(j,i), g, 0.8f, Color.yellow);
					}
				}else{
					drawSquare(new Point(j, i), g, 1, Color.blue);
				}
			}
		}

		for (Mouse m : grid.getMice()) {
			drawCircle(m.getPosition(), g, 0.5f, Color.red);
		}

		if (selectedSquare != null) {
			highLightSquare(selectedSquare, g);
		}

	}
	
	/**
	 * Draws a tile of floor.
	 * @param p The position of the tile.
	 * @param g The graphics.
	 */
	public void drawFloor(Point p, Graphics g) {
		g.drawImage(floorSprite, p.getX() * squareSize, p.getY() * squareSize,
				squareSize, squareSize, null);
	}
	
	/**
	 * Draws a tile of floor in normal or memory mode.
	 * @param p The position of the tile.
	 * @param g	The graphics.
	 * @param b true : memory mode, false : normal mode.
	 */
	public void drawFloor(Point p, Graphics g, boolean b) {
		if(b)
			g.drawImage(mouseParquet, p.getX() * squareSize, p.getY() * squareSize,
					squareSize, squareSize, null);
		else
			drawFloor(p, g);
	}

	/**
	 * Draws a black tile.
	 * @param p The position of the tile.
	 * @param g The graphics.
	 */
	public void drawUnknown(Point p, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(p.getX() * squareSize, p.getY() * squareSize, squareSize,
				squareSize);
	}
	
	/**
	 * Draws the vision of a mouse when the selected mode is memory.
	 * @param mp The center of the vision.
	 * @param vis The vision size.
	 * @param g The graphics.
	 */
	public void drawVision(Point mp, int vis, Graphics g){
		int i, j = 0;
		for(i = -vis;i<0;i++){
			if(i == -vis)
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), true, true, false, true, g);
			else
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), true, true, false, false, g);
			j--;
		}
		j = -vis;
		for(i = 0;i< vis;i++){
			if(j == -vis)
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), true, true, true, false, g);
			else
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), false, true, true, false, g);
			j++;
		}
		
		for(i = vis;i>0;i--){
			if(i == vis)
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), false, true, true, true, g);
			else
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), false, false, true, true, g);
			j++;
		}
		
		for(i = 0;i > -vis;i--){
			if(j == vis)
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), true, false, true, true, g);
			else
				drawSquareBorder(new Point(mp.getX()+i,mp.getY()+j), true, false, false, true, g);
			j--;
		}
		
		
	}
	
	/**
	 * Draws a square of the mouse vision.
	 * @param p The position of the square.
	 * @param left Is the left border present ?
	 * @param top Is the top border present ?
	 * @param right Is the right border present ?
	 * @param bottom Is the bottom border present ?
	 * @param g The graphics.
	 */
	public void drawSquareBorder(Point p, boolean left, boolean top, boolean right, boolean bottom, Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.pink);
		if(left)
			g2.drawLine(p.getX()*squareSize, p.getY()*squareSize, p.getX()*squareSize, p.getY()*squareSize + squareSize);
		if(right)
			g2.drawLine(p.getX()*squareSize+squareSize, p.getY()*squareSize, p.getX()*squareSize+squareSize, p.getY()*squareSize + squareSize);
		if(top)
			g2.drawLine(p.getX()*squareSize, p.getY()*squareSize, p.getX()*squareSize+squareSize, p.getY()*squareSize);
		if(bottom)
			g2.drawLine(p.getX()*squareSize, p.getY()*squareSize + squareSize, p.getX()*squareSize+squareSize, p.getY()*squareSize + squareSize);
		
		
	}
	
	/**
	 * Draws a square.
	 * @param p Position.
	 * @param g Graphics.
	 * @param size Size.
	 * @param c Color.
	 */
	public void drawSquare(Point p, Graphics g, float size, Color c){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		g2.fillRect((int)(p.getX() * squareSize + (squareSize/2) - ((squareSize*size)/2)), (int)(p.getY() * squareSize + (squareSize/2) - ((squareSize*size)/2)), (int)(squareSize*size), (int)(squareSize*size));
	}
	
	/**
	 * Draws a circle.
	 * @param p Position.
	 * @param g Graphics.
	 * @param size Size.
	 * @param c Color.
	 */
	public void drawCircle(Point p, Graphics g, float size, Color c){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		g2.fillOval((int)(p.getX() * squareSize + (squareSize/2) - ((squareSize*size)/2)), (int)(p.getY() * squareSize + (squareSize/2) - ((squareSize*size)/2)), (int)(squareSize*size), (int)(squareSize*size));
	}
	
	/**
	 * Draws a cross on a square.
	 * @param p Position.
	 * @param g Graphics.
	 */
	public void drawCross(Point p, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.red);
		g2.drawLine(p.getX() * squareSize, p.getY() * squareSize, (p.getX()+1) * squareSize, (p.getY()+1) * squareSize);
		g2.drawLine((p.getX()+1) * squareSize, p.getY() * squareSize, p.getX() * squareSize, (p.getY()+1) * squareSize);
	}

	/**
	 * Draw a target on a square.
	 * @param p Position.
	 * @param g Graphics.
	 */
	public void drawTarget(Point p, Graphics g){
		g.drawImage(targetSprite, p.getX() * squareSize, p.getY() * squareSize,
				squareSize, squareSize, null);
	}
	

	/**
	 * Draws an obstacle of a certain type.
	 * @param p Position.
	 * @param g Graphics.
	 * @param type Type.
	 */
public void drawObstacle(Point p, Graphics g, ObstacleType type) {
		g.drawImage(obstacles.get(type.toString()), p.getX() * squareSize, p.getY()
				* squareSize, squareSize, squareSize, null);
	}
	
	/**
	 * Draws an obstacle in normal or memory view.
	 * @param p Position.
	 * @param g Graphics.
	 * @param b Is memory view ?
	 */
	public void drawObstacle(Point p, Graphics g, boolean b) {
		if(b)
			g.drawImage(mouseObstacle, p.getX() * squareSize, p.getY()
					* squareSize, squareSize, squareSize, null);
		else
			drawObstacle(p, g, ObstacleType.Wood);
	}

	/**
	 * Draws a food source.
	 * @param p Position.
	 * @param quantity Quantity of food.
	 * @param g Graphics.
	 */
	public void drawFood(Point p, int quantity, Graphics g) {
		g.setColor(Color.yellow);
		g.drawImage(foodSprite, p.getX() * squareSize, p.getY() * squareSize,
				squareSize, squareSize, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.red);

	}
	
	/**
	 * Draws a food source in normal or memory view.
	 * @param p Position.
	 * @param g Graphics.
	 * @param b Is memory view ?
	 */
	public void drawFood(Point p, Graphics g, boolean b) {
		if(b)
			g.drawImage(mouseFood, p.getX() * squareSize, p.getY() * squareSize,
					squareSize, squareSize, null);
		else
			drawFood(p,1,g);
	}

	/**
	 * Draws a mouse.
	 * @param m The mouse.
	 * @param g Graphics.
	 */
	public void drawMouse(Mouse m, Graphics g) {
		Graphics2D ga = (Graphics2D) g;
		ga.setPaint(Color.GRAY);
		int spritePos = 0;
		Direction dir = m.getLastDirection();
		if (dir == Direction.Left) {
			spritePos = 0;
		} else if (dir == Direction.Up) {
			spritePos = 32;
		} else if (dir == Direction.Right) {
			spritePos = 64;
		} else if (dir == Direction.Down) {
			spritePos = 96;
		}
		Point p = m.getPosition();
		BufferedImage mouseSpriteSheet;
		if(m.getGender().equals("Male"))
			mouseSpriteSheet = maleMouseSpriteSheet;
		else
			mouseSpriteSheet = femaleMouseSpriteSheet;
			
		ga.drawImage(mouseSpriteSheet, p.getX() * squareSize, p.getY()
				* squareSize, p.getX() * squareSize + squareSize, p.getY()
				* squareSize + squareSize, spritePos, 0, spritePos + 32, 32,
				null);
	}

	/**
	 * Highlights a square.
	 * @param p Position.
	 * @param g Graphics.
	 */
	public void highLightSquare(Point p, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		if(squareSize > 20)
			g2.setStroke(new BasicStroke(squareSize/20));
		else
			g2.setStroke(new BasicStroke(1));
		g2.drawRect(p.getX() * squareSize, p.getY() * squareSize, squareSize,
				squareSize);
	}

	/**
	 * Draws a path to a target.
	 * @param p Position.
	 * @param path Path.
	 * @param g Graphics.
	 */
	public void drawPath(Point p, ArrayList<Direction> path, Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(3));
		for(int i=0; i<path.size(); i++){
			Point p2 = new Point();
			if(path.get(i).equals(Direction.Left))
				p2 = new Point(p.getX()-1, p.getY());
			if(path.get(i).equals(Direction.Up))
				p2 = new Point(p.getX(), p.getY()-1);
			if(path.get(i).equals(Direction.Right))
				p2 = new Point(p.getX()+1, p.getY());
			if(path.get(i).equals(Direction.Down))
				p2 = new Point(p.getX(), p.getY()+1);
			
			g2.drawLine(p.getX() * squareSize + squareSize/2, p.getY() * squareSize  + squareSize/2, p2.getX() * squareSize + squareSize/2, p2.getY() * squareSize + squareSize/2);
			p = p2;
		}
		
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public Grid getGrid() {
		return this.grid;
	}

	public Point getSelectedSquare() {
		return selectedSquare;
	}

	public void setSelectedSquare(Point selectedSquare) {
		this.selectedSquare = selectedSquare;
	}

	public int getSquareSize() {
		return squareSize;
	}

	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public void selectMouse(Mouse m) {
		this.mouse = m;
	}
}
