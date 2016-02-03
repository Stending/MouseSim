package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mouse.Mouse;

/**
 * This class will draw and display a mouse's face, depending of its genre, its type, 
 * its trust and its reliability.
 */
public class MousePicture extends JPanel{

	private static final long serialVersionUID = 1L;
	private Mouse mouse;
	
    private BufferedImage mouseHead;
    private BufferedImage fattyHead;
    private BufferedImage bowTie;
    private BufferedImage sherlockHat;
    private BufferedImage[] eyes = new BufferedImage[6];
    private BufferedImage[] mouths = new BufferedImage[6];
    
    /**
     * This constructor will load every pictures
     */
    public MousePicture() {
       try {                
          mouseHead = ImageIO.read(new File("./pictures/mouseface/mouseface.png"));
          fattyHead = ImageIO.read(new File("./pictures/mouseface/fatty_mouseface.png"));
          bowTie = ImageIO.read(new File("./pictures/mouseface/bow_tie.png"));
          sherlockHat = ImageIO.read(new File("./pictures/mouseface/hat.png"));
          eyes[0] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes00.png"));
          eyes[1] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes01.png"));
          eyes[2] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes02.png"));
          eyes[3] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes03.png"));
          eyes[4] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes04.png"));
          eyes[5] = ImageIO.read(new File("./pictures/mouseface/yeuxThomas/Eyes05.png"));
          
          mouths[0] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth00.png"));
          mouths[1] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth01.png"));
          mouths[2] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth02.png"));
          mouths[3] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth03.png"));
          mouths[4] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth04.png"));
          mouths[5] = ImageIO.read(new File("./pictures/mouseface/bouchesThomas/Mouth05.png"));
          
       } catch (IOException ex) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mouse.getClass().getName().equals("mouse.FattyMouse")){
        	g.drawImage(fattyHead, this.getPreferredSize().width/20, this.getPreferredSize().height/10, 160, 160, null); // see javadoc for more info on the parameters
        }else{
        	g.drawImage(mouseHead, this.getPreferredSize().width/20, this.getPreferredSize().height/10, 160, 160, null); // see javadoc for more info on the parameters
        }
        if(mouse.getGender().equals("Female")){
        	g.drawImage(bowTie, 108, 30, 60, 50, null); // see javadoc for more info on the parameters
        }
        g.drawImage(eyes[mouse.getListenMode().getTrust()], 37, 70, 110, 55, null);
        g.drawImage(mouths[mouse.getSpeakMode().getReliability()], 50, 110, 80, 40, null);
        
        if(mouse.getClass().getName().equals("mouse.SherlockMouse")){
        	g.drawImage(sherlockHat,35, 0,100, 100,null);
        }
        
    }

	public void setTrust(int trust) {
	}


	public void setReliability(int reliability) {
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
    
    

}
