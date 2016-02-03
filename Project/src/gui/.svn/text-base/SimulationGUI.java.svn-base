package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mouse.Mouse;

import utilities.Point;

import engine.Simulation;
import griddata.GridParameters;
import griddata.ObstacleSquare;
import griddata.ObstacleType;
import griddata.PraticableSquare;
import griddata.Square;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class is the main frame of the Graphic User Interface
 */
public class SimulationGUI extends JFrame implements Runnable, MouseListener {

	private static final long serialVersionUID = 1L;

	private int simulationSpeed = 500;

	private Simulation simulation;

	private boolean stop = true;

	private SimulationGUI instance = this;

	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD,
			20);
	private ImageIcon backgroundImage = new ImageIcon("./wood_background.png");
	private JScrollPane gridScrollPane;

	protected JLabel background;

	protected JPanel wholeFrame = new JPanel();
	JPanel controlPanel = new JPanel();

	protected JTabbedPane infosTabbedPane = new JTabbedPane();

	protected SimInfosPanel simInfosPanel = new SimInfosPanel();

	protected GridCreationPanel gridCreationPanel = new GridCreationPanel();
	
	protected InfosPanel infosPanel = new InfosPanel();

	protected Log logPanel = new Log();
	
	protected StatsGUI statsPanel;

	protected Chat chatPanel;


	protected JLabel speedLabel = new JLabel("");
	protected JLabel infosLabel = new JLabel("");
	protected JLabel zoomLabel = new JLabel("Zoom : ");


	protected CustomButton startButton = new CustomButton("Play");
	protected CustomButton nextTurnButton = new CustomButton("Next Turn");
	protected CustomButton speedUpButton = new CustomButton("►►");
	protected CustomButton speedDownButton = new CustomButton("◄◄");
	protected CustomButton divideButton = new CustomButton("[]->");

	protected JSlider zoomSlide = new JSlider(JSlider.HORIZONTAL, 10, 100, 39);

	protected GridGUI gridGUI = new GridGUI();
	
	protected ExternalGrid externalGrid = null;

	private boolean divided = false;
	
	
	ImageIcon cheeseIcon = new ImageIcon("./pictures/icons/generationIcon.png");
	ImageIcon catIcon = new ImageIcon("./pictures/icons/chat.png");
	ImageIcon infoIcon = new ImageIcon("./pictures/icons/infobulle.png");
	ImageIcon logIcon = new ImageIcon("./pictures/icons/logIcon.png");
	ImageIcon statsIcon = new ImageIcon("./pictures/icons/statsicon.png");
	ImageIcon simIcon = new ImageIcon("./pictures/icons/clef.png");

	/**
	 * This constructor will initiate all the frame, create a default simulation and a default grid.
	 * @param title Title of the window.
	 */
	public SimulationGUI(String title) {
		super(title);
		this.addMouseListener(this);
		simulation = new Simulation();
		simulation.generateGrid(new GridParameters(10, 10, 5, 15, 5, 5, true));
		chatPanel = new Chat(simulation.getGrid());
		statsPanel = new StatsGUI();
		gridGUI.setGrid(simulation.getGrid());
		gridGUI.addMouseListener(this);
		initActions();
		initStyle();
		init();

	}

	/**
	 * Initiate the different actions (inputs)
	 */
	protected void initActions() {
		startButton.addActionListener(new StartStopAction());
		nextTurnButton.addActionListener(new SimulateTurnAction());
		speedUpButton.addActionListener(new SpeedAction(">>"));
		speedDownButton.addActionListener(new SpeedAction("<<"));
		zoomSlide.addChangeListener(new ZoomAction());
		divideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!divided) {
					externalGrid = new ExternalGrid("Grid");
					externalGrid.setVisible(true);
					divideButton.setText("[]<-");
					instance.setMaximumSize(new Dimension (400,300));
					instance.pack();
					divided = true;
				}
				else {
					externalGrid.setVisible(false); 
					externalGrid.dispose();
					divided = false;
					divideButton.setText("[]->");
					GridBagConstraints cons = new GridBagConstraints();
					cons.gridx = 0;
					cons.gridy = 0;
					wholeFrame.add(gridScrollPane, cons);
					instance.pack();
					gridGUI.repaint();
				}
			}	
		});	
	}

	 /**
	 * Initiate the style of the different elements of the frame
	 */
	protected void initStyle() {
		
		speedLabel.setFont(LABEL_FONT);
		speedLabel.setText("x" + (float) simulationSpeed / 500);
		speedLabel.setHorizontalAlignment(JLabel.CENTER);
		speedLabel.setVerticalAlignment(JLabel.CENTER);

		Image image = backgroundImage.getImage();
		int newWidth = backgroundImage.getIconWidth()
				+ (backgroundImage.getIconWidth() / 2);
		int newHeight = backgroundImage.getIconHeight()
				+ (backgroundImage.getIconHeight() / 2);
		backgroundImage = new ImageIcon(image.getScaledInstance(newWidth,
				newHeight, java.awt.Image.SCALE_SMOOTH));

		background = new JLabel(backgroundImage);

	}

	/**
	 * Formats the window, placing each item in its place
	 */
	private void init() {

		Container contentPane = getContentPane();
		contentPane.add(wholeFrame);
		wholeFrame.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		gridGUI.setSquareSize(39);
		gridGUI.setPreferredSize(new Dimension(simulation.getGrid()
				.getGridParams().getSizex()
				* gridGUI.getSquareSize(), simulation.getGrid().getGridParams()
				.getSizey()
				* gridGUI.getSquareSize()));

		gridScrollPane = new JScrollPane(gridGUI,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gridScrollPane.setPreferredSize(new Dimension(400, 400));
				
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		wholeFrame.add(gridScrollPane, frameConstraints);

		
		controlPanel.setPreferredSize(new Dimension(simulation.getGrid()
				.getGridParams().getSizex()
				* (gridGUI.getSquareSize() + 1), 50));
		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.add(startButton);

		JPanel p1 = new JPanel(new GridLayout(2, 1));
		JPanel p2 = new JPanel(new GridLayout(1, 4));
		JPanel p3 = new JPanel(new GridLayout(1, 2));
		JPanel p4 = new JPanel(new GridLayout(2, 1));
		p2.add(speedDownButton);

		p2.add(speedLabel);
		p2.add(speedUpButton);
		p2.add(divideButton);
		p4.add(zoomLabel);
		p4.add(zoomSlide);

		nextTurnButton.setPreferredSize(new Dimension(225, 100));
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		p3.add(nextTurnButton);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		p3.add(p4);
		p3.setPreferredSize(new Dimension(225, 100));
		p1.add(p3);
		p1.add(p2);
		controlPanel.add(p1);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		wholeFrame.add(controlPanel, frameConstraints);

		infosTabbedPane.addTab("Log", logIcon, logPanel,
				"Console qui va afficher les informations");
		infosTabbedPane.addTab("Sim", simIcon, simInfosPanel, "Informations globales de la simulation");
		infosTabbedPane.addTab("Infos", infoIcon, infosPanel, "Infos");
		infosTabbedPane.addTab("Generation", cheeseIcon, gridCreationPanel,
				"Generation");
		infosTabbedPane.addTab("Stats", statsIcon, statsPanel,
				"Stats");
		infosTabbedPane.addTab("Chat", catIcon, chatPanel, "Chat");
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 2;
		wholeFrame.add(infosTabbedPane, frameConstraints);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}

	
	public void updateGrid() {
		gridGUI.updateUI();
		gridGUI.repaint();
	}

	@Override
	public void run() {
		while (!stop) {
			simulation.simulateNextTurn();
			infosPanel.updateInfos();
			logPanel.updateLog();
			statsPanel.updateStats();
			updateGrid();
			try {
				Thread.sleep(simulationSpeed);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	
	}


	/**
	 * Start or stop the simulation
	 */
	private class StartStopAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!stop) {
				stop = true;
				startButton.setText(" Play ");
			} else {
				stop = false;
				startButton.setText(" Pause ");
				simulation.launchSimulation();
				Thread simThread = new Thread(instance);
				simThread.start();
			}
		}
	}
	
	/**
	 * Play one turn 
	 */
	private class SimulateTurnAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (stop) {
				simulation.simulateNextTurn();
				infosPanel.updateInfos();
				logPanel.updateLog();
				statsPanel.updateStats();
				updateGrid();
			}
		}

	}

	/**
	 * Increment or decrement the simulation speed.
	 */
	private class SpeedAction implements ActionListener {

		private boolean sens;

		public SpeedAction(String s) {
			super();
			this.sens = (s.equals(">>"));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (sens) {
				simulationSpeed /= 2;
				if (simulationSpeed <= 125)
					simulationSpeed = 125;
			} else {
				simulationSpeed *= 2;
				if (simulationSpeed > 2000)
					simulationSpeed = 2000;
			}
			speedLabel.setText("x" + (float) 500 / simulationSpeed);
		}
	}

	/**
	 * Change the zoom value of the gridGUI
	 */
	private class ZoomAction implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			gridGUI.setSquareSize(((JSlider) e.getSource()).getValue());
			gridGUI.setPreferredSize(new Dimension(simulation.getGrid()
					.getGridParams().getSizex()
					* gridGUI.getSquareSize(), simulation.getGrid()
					.getGridParams().getSizey()
					* gridGUI.getSquareSize()));
			updateGrid();
			gridGUI.repaint();
		}
	}
	
	private class ExternalGrid extends JFrame {
		
		public ExternalGrid(String title) {
			super(title);
			gridGUI.setSquareSize(550 / simulation.getGrid().getGridParams()
					.getSizex());
			this.setMinimumSize(new Dimension(600, 600));
			this.add(gridScrollPane);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					divided = false;
					divideButton.setText("[]->");
					GridBagConstraints cons = new GridBagConstraints();
					cons.gridx = 0;
					cons.gridy = 0;
					wholeFrame.add(gridScrollPane, cons);
					instance.pack();
					gridGUI.repaint();
				}
			});
		}
		private static final long serialVersionUID = 1L;
		
	}

	public static void main(String[] args) throws IOException {

		new SimulationGUI("Mouseville");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getX() / gridGUI.getSquareSize(), e.getY()
				/ gridGUI.getSquareSize());
		gridGUI.setDisplayMode("Global");

		simInfosPanel.outlineVisionCheckBox.setSelected(false);
		if (e.getClickCount() == 1) /** && (p2.getX()<8) && (p2.getY()<8)) */{
			if (simulation.getGrid().getSquare(p) != null) {
				gridGUI.setSelectedSquare(p);
				infosPanel.updateInfos(p, simulation.getGrid().getSquare(p));
				repaint();
				infosTabbedPane.setSelectedIndex(2);
			}
		} else if(e.getClickCount()==2){
			infosTabbedPane.setSelectedIndex(2);
			gridGUI.setSelectedSquare(p);
			ArrayList<Mouse> mice = simulation.getGrid().getMiceAtPosition(p);
			if(!mice.isEmpty()){
				infosPanel.updateInfos(mice.get(0));
				gridGUI.selectMouse(mice.get(0));
			}
			repaint();
			
		}else{
			gridGUI.setSelectedSquare(null);
		}
	}

	/**
	 * This panel contains the parameters fields and informations to generate a new grid.
	 */
	
	private class GridCreationPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		protected Box mainBox = Box.createHorizontalBox();
		
		protected Box generationBox = Box.createVerticalBox();
		protected Box exportationBox = Box.createVerticalBox();
		protected Box sizesBox = Box.createHorizontalBox();
		protected JPanel sizeXPanel = new JPanel();
		protected JPanel sizeYPanel = new JPanel();
		protected JPanel obstacleDensityPanel = new JPanel();
		protected JPanel foodDensityPanel = new JPanel();
		protected JPanel numMicePanel = new JPanel();
		protected JPanel specialMicePanel = new JPanel();
		
		protected JPanel sizeXLabelPanel = new JPanel();
		protected JPanel sizeYLabelPanel = new JPanel();
		protected JLabel sizeXLabel = new JLabel("Size X :");
		protected JLabel sizeYLabel = new JLabel("Size Y :");
		protected JLabel obstacleDensityLabel = new JLabel("Obstacle Density : ");
		protected JLabel foodDensityLabel = new JLabel("Food Density : ");
		protected JLabel numMiceLabel = new JLabel("Num Mice : ");
		protected JLabel specialMiceLabel = new JLabel("Special Mice : ");
		
		protected JTextField sizeXField = new JTextField("10");
		protected JTextField sizeYField = new JTextField("10");
		protected JTextField obstacleDensityField = new JTextField("5");
		protected JTextField foodDensityField = new JTextField("15");
		protected JTextField numMiceField = new JTextField("5");
		
		protected JButton generateButton = new JButton("Generate");
		protected JCheckBox specialMiceCheckBox = new JCheckBox();
		
		
		public GridCreationPanel() {
			super();
			initActions();
			initStyle();
			init();
			this.add(mainBox);
		}

		public void initActions() {
			
			generateButton.addActionListener(new GenerateGrid());
			
		}

		public void initStyle(){
			generationBox.setPreferredSize(new Dimension (230, 200));
			sizeXField.setPreferredSize(new Dimension(40,25));
			sizeYField.setPreferredSize(new Dimension(40,25));
			obstacleDensityField.setPreferredSize(new Dimension(40,25));
			foodDensityField.setPreferredSize(new Dimension(40,25));
			numMiceField.setPreferredSize(new Dimension(40,25));
			generateButton.setPreferredSize(new Dimension(100,50));
		}
		
		public void init() {

			mainBox.add(generationBox);
			mainBox.add(exportationBox);
			
			generationBox.add(sizesBox);
			generationBox.add(obstacleDensityPanel);
			generationBox.add(foodDensityPanel);
			generationBox.add(numMicePanel);
			generationBox.add(specialMicePanel);
			generationBox.add(generateButton);
			
			sizesBox.add(sizeXPanel);
			sizesBox.add(new Label("  "));
			sizesBox.add(sizeYPanel);
			
			
			sizeXPanel.add(sizeXLabelPanel);
			sizeXPanel.add(sizeXField);
			sizeYPanel.add(sizeYLabelPanel);
			sizeYPanel.add(sizeYField);
			
			obstacleDensityPanel.add(obstacleDensityLabel);
			obstacleDensityPanel.add(obstacleDensityField);
			foodDensityPanel.add(foodDensityLabel);
			foodDensityPanel.add(foodDensityField);
			numMicePanel.add(numMiceLabel);
			numMicePanel.add(numMiceField);
			specialMicePanel.add(specialMiceLabel);
			specialMicePanel.add(specialMiceCheckBox);
			
			
			sizeXLabelPanel.add(sizeXLabel);
			sizeYLabelPanel.add(sizeYLabel);
			
			specialMiceCheckBox.setSelected(true);
			
			/*
			obstacleDensityPanel.add(obstacleDensityLabel);
			foodDensityPanel.add(foodDensityLabel);
			numMicePanel.add(numMiceLabel);*/

			
		}

		private class GenerateGrid implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				int sizeX = 10;
				try {
					if(Integer.parseInt(sizeXField.getText())<0 || Integer.parseInt(sizeXField.getText())>50)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 50 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						sizeX = Integer.parseInt(sizeXField.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				int sizeY = 10;
				try {
					if(Integer.parseInt(sizeYField.getText())<0 || Integer.parseInt(sizeYField.getText())>50)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 50 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						sizeY = Integer.parseInt(sizeYField.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				int numMice = 5;
				try {
					if(Integer.parseInt(numMiceField.getText())<0 || Integer.parseInt(numMiceField.getText())>50)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 50 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						numMice = Integer.parseInt(numMiceField.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				int foodDensity = 15;
				try {
					if(Integer.parseInt(foodDensityField.getText())<0 || Integer.parseInt(foodDensityField.getText())>100)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 100 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						foodDensity = Integer.parseInt(foodDensityField.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				int obstacleDensity = 5;
				try {
					if(Integer.parseInt(obstacleDensityField.getText())<0 || Integer.parseInt(obstacleDensityField.getText())>100)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 100 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						obstacleDensity = Integer.parseInt(obstacleDensityField.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				simulation.generateGrid(new GridParameters(sizeX, sizeY, numMice, foodDensity, obstacleDensity, 
						5,
						specialMiceCheckBox.isSelected()));
				gridGUI.setGrid(simulation.getGrid());
				gridGUI.setPreferredSize(new Dimension(simulation.getGrid()
						.getGridParams().getSizex()
						* gridGUI.getSquareSize(), simulation.getGrid()
						.getGridParams().getSizey()
						* gridGUI.getSquareSize()));
				updateGrid();
				stop = true;
				startButton.setText(" Play ");
				Log.clear();
			}
		}
	}
	
	/**
	 * This panel contains informations about a grid element (Square or mouse)
	 */
		
	private class InfosPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private Square square;
		private Point squarePoint;
		private ArrayList<Mouse> squareMice;
		private Mouse mouse;
		private String mode = "";

		protected JPanel pan = new JPanel();

		protected Box squareBox = Box.createVerticalBox();
		protected Box squareBox2 = Box.createHorizontalBox();
		protected Box squareInfosBox = Box.createVerticalBox();
		protected Box squareMouseInfosBox = Box.createVerticalBox();

		protected Box mouseBox = Box.createVerticalBox();
		protected Box mouseBox2 = Box.createHorizontalBox();
		protected Box mouseInfosBox = Box.createVerticalBox();
		protected Box mouseParametersBox = Box.createVerticalBox();

		protected JPanel squareNameLabelPanel = new JPanel();
		protected JPanel squareObstaclePanel = new JPanel();
		protected JPanel squareFoodPanel = new JPanel();
		protected JPanel squareUpdateButtonPanel = new JPanel();
		protected JPanel squareMiceListLabelPanel = new JPanel();
		protected JPanel squareMiceListPanel = new JPanel();

		protected JPanel mouseNameLabelPanel = new JPanel();
		protected JPanel ageLabelPanel = new JPanel();
		protected JPanel sexeLabelPanel = new JPanel();
		protected JPanel lifeLabelPanel = new JPanel();
		protected JPanel trustLabelPanel = new JPanel();
		protected JPanel reliabilityLabelPanel = new JPanel();
		protected JPanel visionLabelPanel = new JPanel();
		protected JPanel displayVisionPanel = new JPanel();
		protected MousePicture mousePicture = new MousePicture();

		protected JLabel obstacleLabel = new JLabel("Obstacle :");
		protected JLabel foodLabel = new JLabel("Food :");
		protected JLabel miceListLabel = new JLabel("Mice List");

		protected JLabel mouseNameLabel = new JLabel("Matthieu (0,0)");
		protected JLabel sexeLabel = new JLabel("Sexe : ♀");
		protected JLabel ageLabel = new JLabel("Age : 10");
		protected JLabel lifeLabel = new JLabel("Life : 50");
		protected JLabel trustLabel = new JLabel("Trust : 0/5");
		protected JLabel reliabilityLabel = new JLabel("Reliability : 0/5");
		protected JLabel visionLabel = new JLabel("Vision : 2");
		protected JLabel displayVisionLabel = new JLabel("Display Vision : ");

		protected JTextField foodField = new JTextField("");

		protected JCheckBox obstacleCheckBox = new JCheckBox("");

		protected JCheckBox displayVisionCheckBox = new JCheckBox("");

		protected JComboBox obstacleTypeComboBox = new JComboBox(ObstacleType.getTypeNames());
		protected JButton foodButton = new JButton("Update");

		protected JButton mouseButton = new JButton("Go to mouse");
		protected JLabel squareNameLabel = new JLabel("Square (0,0)");
		//protected JTextField foodField = new JTextField("30");
		protected JList miceList = new JList();

		protected GridBagLayout layout = new GridBagLayout();
		protected GridBagConstraints cons = new GridBagConstraints();
		protected JTextArea mouseInfos = new JTextArea("");
		public InfosPanel() {
			super();
			initActions();
			init();
			this.setLayout(layout);
			this.add(pan);
		}

		public void initActions() {
			foodButton.addActionListener(new ChangeFoodAction());
			mouseButton.addActionListener(new SelectMouseAction());
			foodField.setPreferredSize(new Dimension(30, 27));
			miceList.setPreferredSize(new Dimension(40, 40));
			miceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cons.fill = GridBagConstraints.BOTH;
			cons.weightx = 1;
			cons.weighty = 1;

			displayVisionCheckBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						gridGUI.setDisplayMode("Memory");
						gridGUI.repaint();
					} else {
						gridGUI.setDisplayMode("Global");
						gridGUI.repaint();
					}
				}
			});

			obstacleCheckBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						foodField.setEnabled(false);
						foodButton.setEnabled(false);
						miceList.setEnabled(false);
						foodLabel.setEnabled(false);
						obstacleTypeComboBox.setEnabled(true);
						if (square.isPraticable()) {
							Square ns = new ObstacleSquare(ObstacleType.getType(obstacleTypeComboBox.getSelectedIndex()));
							simulation.getGrid().setSquare(squarePoint, ns);
							square = ns;
							gridGUI.repaint();
							updateInfos();
						}
					} else {
						foodField.setEnabled(true);
						foodButton.setEnabled(true);
						miceList.setEnabled(true);
						foodLabel.setEnabled(true);
						obstacleTypeComboBox.setEnabled(false);
						if (!square.isPraticable()) {
							Square ns = new PraticableSquare();
							simulation.getGrid().setSquare(squarePoint, ns);
							square = ns;
							gridGUI.repaint();
							updateInfos();
						}
					}
				}
			});
			
			obstacleTypeComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(!square.isPraticable()){
						ObstacleSquare os = (ObstacleSquare)square;
						os.setType(ObstacleType.getType(obstacleTypeComboBox.getSelectedIndex()));
						gridGUI.repaint();
					}
				}
			
			});

			miceList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {
						mouse = (Mouse) miceList.getSelectedValue();
						infosPanel.updateInfos(mouse);
						gridGUI.selectMouse(mouse);
					} else if (evt.getClickCount() == 3) {
					}
				}
			});
		}

		public void init() {
			
			squareBox.add(squareNameLabelPanel);
			squareBox.add(squareBox2);
			squareBox2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			squareBox2.add(squareInfosBox);
			squareBox2.add(new JLabel("           "));
			squareBox2.add(squareMouseInfosBox);
			squareInfosBox.add(squareObstaclePanel);
			squareInfosBox.add(squareFoodPanel);
			squareInfosBox.add(squareUpdateButtonPanel);
			squareMouseInfosBox.add(squareMiceListLabelPanel);
			squareMouseInfosBox.add(squareMiceListPanel);

			mouseBox.add(mouseNameLabelPanel);
			mouseBox.add(mouseBox2);
			mouseBox2.setPreferredSize(new Dimension(600,200));
			mouseBox2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mouseBox2.add(mousePicture);
			mousePicture.setPreferredSize(new Dimension(180, 180));
			mouseBox2.add(mouseInfosBox);
			mouseBox2.add(mouseParametersBox);
			mouseInfosBox.add(sexeLabelPanel);
			mouseInfosBox.add(ageLabelPanel);
			mouseInfosBox.add(lifeLabelPanel);
			mouseInfosBox.add(trustLabelPanel);
			mouseInfosBox.add(reliabilityLabelPanel);
			mouseInfosBox.add(visionLabelPanel);
			mouseParametersBox.add(displayVisionPanel);

			mouseNameLabelPanel.add(mouseNameLabel);
			squareNameLabelPanel.add(squareNameLabel);
			squareObstaclePanel.add(obstacleLabel);
			squareObstaclePanel.add(obstacleCheckBox);
			squareObstaclePanel.add(obstacleTypeComboBox);
			obstacleTypeComboBox.setEnabled(false);
			squareFoodPanel.add(foodLabel);
			squareFoodPanel.add(foodField);
			foodField.setPreferredSize(new Dimension(50, 25));
			squareUpdateButtonPanel.add(foodButton);
			squareMiceListLabelPanel.add(miceListLabel);
			squareMiceListPanel.add(miceList);
			miceList.setPreferredSize(new Dimension(100, 50));

			sexeLabelPanel.add(sexeLabel);
			ageLabelPanel.add(ageLabel);
			lifeLabelPanel.add(lifeLabel);
			trustLabelPanel.add(trustLabel);
			reliabilityLabelPanel.add(reliabilityLabel);
			visionLabelPanel.add(visionLabel);
			displayVisionPanel.add(displayVisionLabel);
			displayVisionPanel.add(displayVisionCheckBox);

		}

		public void updateInfos(Point p, Square s) {

			this.squarePoint = p;
			this.square = s;
			this.mouse = null;
			if (!this.mode.equals("Square")) {
				this.removeAll();
				this.add(squareBox);
				this.mode = "Square";
			}
			updateInfos();

		}

		public void updateInfos(Mouse m) {
			this.square = null;
			this.squareMice = null;
			this.mouse = m;
			this.displayVisionCheckBox.setSelected(false);
			if (!this.mode.equals("Mouse")) {
				this.removeAll();
				this.add(mouseBox);
				this.mode = "Mouse";
			}
			updateInfos();
		}

		public void updateInfos() {
			if (mode.equals("Mouse")
					&& !simulation.getGrid().getMice().contains(mouse)) {
				updateInfos(mouse.getPosition(), simulation.getGrid()
						.getSquare(mouse.getPosition()));
				return;
			}
			Border blackline = BorderFactory.createLineBorder(Color.black);
			this.setBorder(blackline);
			if (mode.equals("Square")) {
				squareMice = simulation.getGrid()
						.getMiceAtPosition(squarePoint);

				if (!square.isPraticable()) {
					squareNameLabel.setText("Obstacle "
							+ squarePoint.toString());
				} else if (square.containsFood()) {
					squareNameLabel.setText("Food Source "
							+ squarePoint.toString());
				} else {
					squareNameLabel.setText("Empty Square"
							+ squarePoint.toString());
				}

				if (!square.isPraticable()) {
					obstacleCheckBox.setSelected(true);
					obstacleTypeComboBox.setSelectedItem(((ObstacleSquare)square).getType().toString());
				} else {
					obstacleCheckBox.setSelected(false);
					foodField.setText("" + square.getFoodQuantity());
				}
				if (!squareMice.isEmpty()) {
					miceList.setListData(squareMice.toArray());
				} else {
					miceList.setListData(new String[0]);
				}
			} else if (mode.equals("Mouse")) {
				mouseNameLabel.setText(mouse.getName() + " "
						+ mouse.getPosition().toString());
				sexeLabel.setText("Sexe : " + mouse.getGender());
				ageLabel.setText("Age : " + mouse.getAge());
				lifeLabel.setText("Life : " + mouse.getLifeTime());
				trustLabel.setText("Trust : "
						+ (mouse.getClass().getName().contains("Sherlock")?"Sherlock":mouse.getListenMode().getTrust()));
				reliabilityLabel.setText("Reliability : "
						+ (mouse.getClass().getName().contains("Moriarty")?"Moriarty":mouse.getSpeakMode().getReliability()));
				visionLabel.setText("Vision : " + mouse.getVision());
				if (mouse.getLifeTime() == 0)
					mouseInfos.setText(mouse.getName() + " est morte^^");
				mousePicture.setTrust(mouse.getListenMode().getTrust());
				mousePicture.setMouse(mouse);
				mousePicture.repaint();
				gridGUI.setSelectedSquare(mouse.getPosition());
			}
			this.revalidate();
		}

		private class SelectMouseAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!miceList.isSelectionEmpty()) {
					mouse = (Mouse) miceList.getSelectedValue();
					mode = "Mouse";
					infosPanel.updateInfos();
					gridGUI.selectMouse(mouse);
				} else
					JOptionPane.showMessageDialog(infosPanel,
							"No mouse is selected in the list !", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}

		private class ChangeFoodAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				square.getFoodSource().setQuantity(
						Integer.parseInt(foodField.getText()));
				gridGUI.repaint();
			}
		}
	}
	
	/**
	 * This class enable the user to change some parameters like the outline vision, the food regeneration speed, the breed rate or the clone rate.
	 * @author Rova
	 *
	 */
	private class SimInfosPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		protected GridBagLayout layout = new GridBagLayout();
				
		protected Box mainBox = Box.createVerticalBox();
		protected Box outlineVisionBox = Box.createHorizontalBox();
		protected Box simParamsBox = Box.createHorizontalBox();
		protected Box updateButtonBox = Box.createHorizontalBox();
		
		protected JPanel outlinePanel = new JPanel();
		protected JPanel outlineLabelPanel = new JPanel();
		protected JPanel foodRegenCounterLabelPanel = new JPanel();
		protected JPanel breedRateLabelPanel = new JPanel();
		protected JPanel cloneRateLabelPanel = new JPanel();
		protected JPanel foodRegenCounterFieldPanel = new JPanel();
		protected JPanel breedRateFieldPanel = new JPanel();
		protected JPanel cloneRateFieldPanel = new JPanel();
		protected JPanel updateButtonPanel = new JPanel();
		protected JCheckBox outlineVisionCheckBox = new JCheckBox();
		
		protected JLabel outlineLabel = new JLabel("Outline Vision :");
		protected JLabel foodRegenCounterLabel = new JLabel("Food Regeneration time : ");
		protected JLabel breedRateLabel = new JLabel("Breed Rate : ");
		protected JLabel cloneRateLabel = new JLabel("Clone Rate : ");

		protected JTextField foodRegenCounterField = new JTextField(10);
		protected JTextField breedRateField = new JTextField(10);
		protected JTextField cloneRateField = new JTextField(10);

		protected JButton updateButton = new JButton("Apply");
				
		public SimInfosPanel() {
			super();
			initActions();
			init();
			this.setLayout(layout);
			this.add(mainBox);
		}

		/**
		 * When the "Apply" button is pressed, this method update the new simulation's parameters.
		 * @author Rova
		 *
		 */
		private class UpdateSimInfosAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulation.getGrid().getGridParams().setFoodRegenFrequency(Integer.parseInt(foodRegenCounterField.getText()));
				try {
					if(Integer.parseInt(breedRateField.getText())<0 || Integer.parseInt(breedRateField.getText())>100)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 100 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						simulation.setBreedRate(Integer.parseInt(breedRateField.getText()));
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					if(Integer.parseInt(cloneRateField.getText())<0 || Integer.parseInt(cloneRateField.getText())>100)
						JOptionPane.showMessageDialog(instance, "You must enter a number between 0 and 100 !", "Error", JOptionPane.ERROR_MESSAGE);
					else
						simulation.setCloneRate(Integer.parseInt(cloneRateField.getText()));
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(instance, "You must enter a number !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		/**
		 * This method enable the user to change the vision mode with the "Outline vision" checkbox.
		 */
		public void initActions() {		
			updateButton.addActionListener(new UpdateSimInfosAction());
			outlineVisionCheckBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						gridGUI.setDisplayMode("Outline");
						gridGUI.repaint();
					}
					else {
						gridGUI.setDisplayMode("Global");
						gridGUI.repaint();
					}
				}
			});
		}
		
		public void init() {
			foodRegenCounterField.setText("5");
			breedRateField.setText("50");
			cloneRateField.setText("50");
			
			outlineLabelPanel.add(outlineLabel);
			outlinePanel.add(outlineLabelPanel);
			outlinePanel.add(outlineVisionCheckBox);
			outlineVisionBox.add(outlinePanel);
			
			foodRegenCounterLabelPanel.add(foodRegenCounterLabel);
			foodRegenCounterFieldPanel.add(foodRegenCounterField);
			simParamsBox.add(foodRegenCounterLabelPanel);
			simParamsBox.add(foodRegenCounterFieldPanel);
			
			breedRateLabelPanel.add(breedRateLabel);
			breedRateFieldPanel.add(breedRateField);
			simParamsBox.add(breedRateLabelPanel);
			simParamsBox.add(breedRateFieldPanel);
			
			
			cloneRateLabelPanel.add(cloneRateLabel);
			cloneRateFieldPanel.add(cloneRateField);
			simParamsBox.add(cloneRateLabelPanel);
			simParamsBox.add(cloneRateFieldPanel);
			
			updateButton.setPreferredSize(new Dimension(140,40));
			updateButtonPanel.add(updateButton);
			updateButtonBox.add(updateButtonPanel);
			
			mainBox.add(outlineVisionBox);
			mainBox.add(simParamsBox);
			mainBox.add(updateButtonBox);
		}
	}
}
