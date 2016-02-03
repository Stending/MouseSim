package gui;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import utilities.GUIDrawing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

/**
 * This class redesigns the default Java Swing button.
 * @author Zinedine
 *
 */
public class CustomButton extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Dimension size = new Dimension(36, 36);
	protected Font font = new Font("MAIAN.TTF", Font.PLAIN, 20);
	protected Color color = Color.BLACK;
	protected String text = "";
	public Dimension dot = new Dimension((int) (size.width / 3),
			(int) (size.height / 3));
	public Dimension arc = new Dimension((int) Math.sqrt(size.width),
			(int) Math.sqrt(size.height));
	
	private Dimension cheeseSize;

	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

	public int val;
	public int pos;

	private boolean mouseEntered = false;
	private BufferedImage cheeseTexture;

	public CustomButton(String label) {
		super();
		try {
			cheeseTexture = ImageIO.read(new File("./pictures/cheese_texture.png"));
			cheeseSize = new Dimension(cheeseTexture.getWidth(), cheeseTexture.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		enableInputMethods(true);
		addMouseListener(this);
		
		setSize(size.width, size.height);
		setFocusable(true);
		
		this.text = label;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int lastWidth = getPreferredSize().width% cheeseSize.width;
		int lastHeight = getPreferredSize().height% cheeseSize.height;
		
		int i, j;
		
		for(i = 0; i<=getPreferredSize().width / cheeseSize.width;i++){
			for(j = 0; j<=getPreferredSize().height / cheeseSize.height;j++){
				if(i==getPreferredSize().width / cheeseSize.width){
					if(j==getPreferredSize().height / cheeseSize.height){
						g.drawImage(cheeseTexture, 
								i*cheeseSize.width, j*cheeseSize.height,  i*cheeseSize.width + lastWidth, j*cheeseSize.height + lastHeight, 
								0, 0, lastWidth, lastHeight,
								null
								);
						
					}else{
					g.drawImage(cheeseTexture, 
							i*cheeseSize.width, j*cheeseSize.height,  i*cheeseSize.width + lastWidth, j*cheeseSize.height + cheeseSize.height, 
							0, 0, lastWidth, cheeseSize.height,
							null
							);
					}
				}else if(j==getPreferredSize().height / cheeseSize.height){
					g.drawImage(cheeseTexture, 
							i*cheeseSize.width, j*cheeseSize.height,  i*cheeseSize.width + cheeseSize.width, j*cheeseSize.height + lastHeight, 
							0, 0, cheeseSize.width, lastHeight,
							null
							);
				}else{
					g.drawImage(cheeseTexture, i*cheeseSize.width, j*cheeseSize.height, null);
				}
				
			}
				
		}
		
		if (mouseEntered) {
			g.setColor(Color.DARK_GRAY);
		} else {
			g.setColor(Color.BLACK);
		}
		
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width,
				arc.height);

		g.setFont(font);
		g.setColor(color);
		GUIDrawing.drawCenteredString(text, 0 , 0, getPreferredSize().width, getPreferredSize().height, g);

	}

	public void roll() {
		int faces = 6;
		val = (int) (Math.random() * faces + 1);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		mouseEntered = true;
		if (val != -1) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		repaint();
	}

	public void mouseExited(MouseEvent e) {
		mouseEntered = false;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		notifyListeners(e);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		repaint();
	}

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	private void notifyListeners(MouseEvent e) {
		ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				new String(), e.getWhen(), e.getModifiers());
		synchronized (listeners) {
			for (int i = 0; i < listeners.size(); i++) {
				ActionListener tmp = listeners.get(i);
				tmp.actionPerformed(evt);
			}
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
