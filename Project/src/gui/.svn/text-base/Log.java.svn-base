package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utilities.Point;

import mouse.Mouse;

/**
 * The log which is displayed in the simulation.
 * @author Zinedine
 *
 */
public class Log extends JPanel implements ChangeListener, ActionListener{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ArrayList<String> lines = new ArrayList<String>();
	
	protected JPanel pan = new JPanel();
	protected JTextArea log = new JTextArea();
	protected JScrollPane jsPan = new JScrollPane();
	protected JCheckBox displayFeeding = new JCheckBox("Feeding",true);
	protected JCheckBox displayBreeding = new JCheckBox("Breeding",true);
	protected JCheckBox displayCommunication = new JCheckBox("Communication",true);
	protected JCheckBox displayPathFinding = new JCheckBox("Pathfinding",true);
	protected JCheckBox displayLifeState = new JCheckBox("Life",true);
	protected JButton selectAll = new JButton("Select all");
	protected JButton clear = new JButton("Clear");
	protected JSpinner lineNb = new JSpinner();
	protected SpinnerModel model = new SpinnerNumberModel(50,1,500,1);
	
	/**
	 * The constructor initializes the listeners.
	 */
	public Log() {
		super();
		pan.setPreferredSize(new Dimension (600,220));
		jsPan = new JScrollPane(log,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsPan.setPreferredSize(new Dimension(500,175));
		log.setEditable(false);
		displayBreeding.addChangeListener(this);
		displayFeeding.addChangeListener(this);
		displayCommunication.addChangeListener(this);
		displayPathFinding.addChangeListener(this);
		displayLifeState.addChangeListener(this);
		selectAll.addActionListener(this);
		clear.addActionListener(this);
		lineNb.setModel(model);
		lineNb.addChangeListener(this);
		pan.add(displayCommunication);
		pan.add(displayBreeding);
		pan.add(displayFeeding);
		pan.add(displayPathFinding);
		pan.add(displayLifeState);
		pan.add(selectAll);
		pan.add(clear);
		pan.add(lineNb);
		pan.add(jsPan);
		this.add(pan);
	}
	
	/**
	 * Updates the log.
	 */
	public void updateLog() {
		log.setText("");
		pan.remove(jsPan);
		for(int i = Math.max(0, lines.size()-(Integer)lineNb.getValue()); i < lines.size(); i++) {
			if((lines.get(i).contains("<feed>") && displayFeeding.isSelected()) || (lines.get(i).contains("<breed>") && displayBreeding.isSelected()) || (lines.get(i).contains("<com>") && displayCommunication.isSelected()) || (lines.get(i).contains("<path>") && displayPathFinding.isSelected()) || (lines.get(i).contains("<life>") && displayLifeState.isSelected()))
				log.append("\n"+lines.get(i).substring(lines.get(i).lastIndexOf('>')+1));
			else if(!lines.get(i).contains("<") && !lines.get(i).contains(">"))
				log.append("\n"+lines.get(i));
		}
		jsPan.repaint();
		pan.add(jsPan);
		this.repaint();
	}

	/**
	 * Writes a separator in the log which displays the current turn.
	 * @param turn Current turn.
	 */
	public static void writeLine(int turn) {
		lines.add("---------------Tour "+turn+"------------------------");
	}
	
	/**
	 * Writes a message in the log according to one mouse.
	 * @param m A mouse.
	 * @param s "eat", "duplicate", "random", "target", "totarget", "newtarget", "explore", "death", "birth", "mute", "deny", "sherlockdeny", "deaf", "knew", "fattyeat", "deceived".
	 */
	public static void write(Mouse m, String s) {
		if(s.equals("eat"))
			lines.add("<feed>"+m.getName()+" mange. "+(m.getGender().equals("Male")?"Quel gourmand !":"Quelle gourmande !"));
		else if(s.equals("duplicate"))
			lines.add("<breed>"+m.getName()+" s'est "+(m.getGender().equals("Male")?"dupliqué !":"dupliquée !"));
		else if(s.equals("random"))
			lines.add("<path>"+m.getName()+" a beaucoup de vie, donc "+(m.getGender().equals("Male")?"il se déplace aléatoirement.":"elle se déplace aléatoirement."));
		else if(s.equals("target"))
			lines.add("<path>"+m.getName()+" est "+(m.getGender().equals("Male")?"arrivé à destination.":"arrivée à destination."));
		else if(s.equals("totarget"))
			lines.add("<path>"+m.getName()+" se déplace vers sa cible.");
		else if(s.equals("newtarget"))
			lines.add("<path>"+m.getName()+" change de cible");
		else if(s.equals("explore"))
			lines.add("<path>"+m.getName()+" décide d'aller explorer l'inconnu");
		else if(s.equals("death"))
			lines.add("<life>"+m.getName()+" n'a pas survécu...");
		else if(s.equals("birth"))
			lines.add("<breed>"+m.getName()+" a donné la vie, toutes nos félicitations !");
		else if(s.equals("mute"))
			lines.add("<com>"+m.getName()+" veut parler, mais "+(m.getGender().equals("Male")?"il est muet...":"elle est muette..."));
		else if(s.equals("deny"))
			lines.add("<com>"+m.getName()+" ne lui fait pas confiance.");
		else if(s.equals("sherlockdeny"))
			lines.add("<com>"+m.getName()+" a flairé le mensonge, bien joué Sherlock !");
		else if(s.equals("deaf"))
			lines.add("<com>Mais "+m.getName()+" est "+(m.getGender().equals("Male")?"sourd...":"sourde..."));
		else if(s.equals("knew"))
			lines.add("<com>Mais "+m.getName()+" était déjà au courant.");
		else if(s.equals("fattyeat"))
			lines.add("<feed>" + m.getName() + " a dévoré toute une source de nourriture.");
		else if(s.equals("deceived"))
			lines.add("<com>" + m.getName() + " vient de se rendre compte qu'" + (m.getGender().equals("Male")?"il s'est fait berné.":"elle s'est faite berner."));
	}
	
	/**
	 * Writes a message in the log according to two mice.
	 * @param m1 First mouse.
	 * @param m2 Second mouse.
	 * @param s "breed", "indicate", "lie", "liar".
	 */
	public static void write(Mouse m1, Mouse m2, String s) {
		if(s.equals("breed"))
			lines.add("<breed>"+m1.getName()+" et "+m2.getName()+" font crac-crac.");
		else if(s.equals("indicate"))
			lines.add("<com>"+m1.getName()+" donne une indication à "+m2.getName()+".");
		else if(s.equals("lie"))
			lines.add("<com>"+m1.getName()+" ment à "+m2.getName()+". Quelle perfidie !");
		else if(s.equals("liar"))
			lines.add("<com>" + m1.getName() + " a décidé de ne plus croire " + m2.getName() + ".");
	}
	
	/**
	 * Writes a message in the log when a mouse memorize a square.
	 * @param m Mouse.
	 * @param p Position of the square.
	 */
	public static void write(Mouse m, Point p) {
		lines.add("<com>"+m.getName()+" lui fait confiance et mémorise la case "+p.toString());
	}
	
	/**
	 * Writes a message in the log when a mouse that can be Sherlock memorize a square.
	 * @param m Mouse.
	 * @param p Position of the square.
	 * @param s "sherlock".
	 */
	public static void write(Mouse m, Point p, String s) {
		if(s.equals("sherlock"))
			lines.add("<com>"+m.getName()+" voit que c'est vrai et mémorise la case "+p.toString());
		else
			write(m,p);
	}
	
	/**
	 * Writes a message in the log when a mouse believe in another mouse's liar.
	 * @param m1 First mouse.
	 * @param m2 Second mouse.
	 * @param p The position of the square memorized.
	 */
	public static void write(Mouse m1, Mouse m2, Point p) {
		lines.add("<com>"+m1.getName()+ (m1.getGender().equals("Male")?" s'est fait avoir par "+ m2.getName() + " et mémorise la case " + p.toString():" s'est faite avoir par " + m2.getName() + " et mémorise la case " + p.toString()));
	}

	/**
	 * Clears the log.
	 */
	public static void clear() {
		lines.clear();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		updateLog();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(selectAll)) {
			displayBreeding.setSelected(true);
			displayCommunication.setSelected(true);
			displayFeeding.setSelected(true);
			displayLifeState.setSelected(true);
			displayPathFinding.setSelected(true);
			updateLog();
		}
		else if(e.getSource().equals(clear)) {
			lines.clear();
			updateLog();
		}
	}
	
}
