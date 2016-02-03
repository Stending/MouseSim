package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import engine.Statistics;

public class StatsGUI extends JPanel implements ActionListener {
	/**
	 * This class displays different statistics of the simulation.
	 * 
	 * @Rova
	 */
	private static final long serialVersionUID = 1L;
	

	protected JPanel mainPan = new JPanel();
	protected JPanel statsPan = new JPanel();
	protected JScrollPane jsPan = new JScrollPane();
	
	protected JLabel totalMiceLabel = new JLabel("Total mice :");
	protected JLabel totalMaleMiceLabel = new JLabel("Total male mice :");
	protected JLabel totalFemaleMiceLabel = new JLabel("Total female mice :");
	protected JLabel aliveMiceLabel = new JLabel("Alive mice :");
	protected JLabel aliveMaleMiceLabel = new JLabel("Alive male mice :");
	protected JLabel aliveFemaleMiceLabel = new JLabel("Alive female Mice :");
	protected JLabel deadMiceLabel = new JLabel("Dead mice :");
	protected JLabel deadMaleMiceLabel = new JLabel("Dead male Mice :");
	protected JLabel deadFemaleMiceLabel = new JLabel("Dead female Mice :");
	protected JLabel maximumMiceLabel = new JLabel("Maximum mice :");
	protected JLabel averageAliveMiceLabel = new JLabel("Average alive Mice :");
	protected JLabel gridSizexLabel = new JLabel("Grid size x :");
	protected JLabel gridSizeyLabel = new JLabel("Grid size y :");
	protected JLabel startingMiceLabel = new JLabel("Starting mice");
	protected JLabel foodRegenSpeedLabel = new JLabel("Food regeneration speed :");
	protected JLabel obstacleDensityLabel = new JLabel("Obstacle density :");
	protected JLabel foodDensityLabel = new JLabel("Food density :");
	protected JLabel foodSourcesLabel = new JLabel("Food sources :");
	protected JLabel totalFoodQuantityLabel = new JLabel("Total food quantity :");
	protected JLabel couplingsLabel = new JLabel("Couplings :");
	protected JLabel impregnateMiceLabel = new JLabel("Impregnate mice :");
	protected JLabel birthsLabel = new JLabel("Births :");
	protected JLabel duplicationsLabel = new JLabel("Duplications :");
	protected JLabel sterileMiceLabel = new JLabel("Total sterile mice :");
	protected JLabel truthsLabel = new JLabel("Truths :");
	protected JLabel liesLabel = new JLabel("Lies :");
	protected JLabel agreedInfosLabel = new JLabel("Agreed infos :");
	protected JLabel refusedInfosLabel = new JLabel("Refused infos :");
	
	protected JButton moreStatsButton = new JButton("More stats");

	public StatsGUI() {
		super();
		statsPan.setLayout(new GridLayout(4, 2));
		init();
		mainPan.setPreferredSize(new Dimension(600, 220));
		jsPan = new JScrollPane(statsPan,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsPan.setPreferredSize(new Dimension(500, 175));
		mainPan.add(jsPan);
		this.add(mainPan);
	}
	
	/**
	 * This method displays the basic statistics of the simulation.
	 * If the "More Stats" button is pressed, it open the "FullStats" window.
	 */
	public void init() {
		statsPan.add(aliveMiceLabel);
		statsPan.add(deadMiceLabel);
		statsPan.add(aliveMaleMiceLabel);
		statsPan.add(aliveFemaleMiceLabel);
		statsPan.add(foodSourcesLabel);
		statsPan.add(totalFoodQuantityLabel);
		statsPan.add(sterileMiceLabel);
		statsPan.add(impregnateMiceLabel);
		this.add(moreStatsButton);
		moreStatsButton.setPreferredSize(new Dimension(100,30));
		moreStatsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FullStats("Full stats").setVisible(true);
			
			}
		});
	}
	
	/**
	 * This method update the simulation's statistics at each turn.
	 * @param clear
	 */
	public void updateStats() {
		totalMiceLabel.setText("Total mice : "+Statistics.globalStatistics.getTotalMice());
		totalMaleMiceLabel.setText("Total male mice : "+Statistics.globalStatistics.getTotalMaleMice());
		totalFemaleMiceLabel.setText("Total female mice : "+Statistics.globalStatistics.getTotalFemaleMice());
		aliveMiceLabel.setText("Alive mice : "+Statistics.globalStatistics.getAliveMice());
		aliveMaleMiceLabel.setText("Alive male mice : "+Statistics.globalStatistics.getAliveMaleMice());
		aliveFemaleMiceLabel.setText("Alive female mice : " + Statistics.globalStatistics.getAliveFemaleMice());
		deadMiceLabel.setText("Dead mice : "+Statistics.globalStatistics.getDeadMice());
		deadMaleMiceLabel.setText("Dead male mice : "+Statistics.globalStatistics.getDeadMaleMice());
		deadFemaleMiceLabel.setText("Dead female mice : "+Statistics.globalStatistics.getDeadFemaleMice());
		maximumMiceLabel.setText("Maximum mice : "+Statistics.globalStatistics.getMaximumMice());
		averageAliveMiceLabel.setText("Average alive mice : "+Statistics.globalStatistics.getAverageMice());
		gridSizexLabel.setText("Grid size x : "+Statistics.globalStatistics.getGridSizex());
		gridSizeyLabel.setText("Grid size y : "+Statistics.globalStatistics.getGridSizey());
		startingMiceLabel.setText("Starting mice : "+Statistics.globalStatistics.getStartingMice());
		foodRegenSpeedLabel.setText("Food regeneration speed : "+Statistics.globalStatistics.getFoodSourceSpawnSpeed());
		obstacleDensityLabel.setText("Obstacle density : "+Statistics.globalStatistics.getObstacleDensity()+"%");
		foodDensityLabel.setText("Food density : "+Statistics.globalStatistics.getFoodSourceDensity()+"%");
		foodSourcesLabel.setText("Food sources : "+Statistics.globalStatistics.getFoodSources());
		totalFoodQuantityLabel.setText("Total food quantity : "+Statistics.globalStatistics.getTotalFoodQuantity());
		couplingsLabel.setText("Couplings : "+Statistics.globalStatistics.getCouplings());
		impregnateMiceLabel.setText("Impregnate mice : "+Statistics.globalStatistics.getImpregnateMice());
		birthsLabel.setText("Births : "+Statistics.globalStatistics.getBirths());
		duplicationsLabel.setText("Duplications : "+Statistics.globalStatistics.getDuplications());
		sterileMiceLabel.setText("Total sterile mice : "+Statistics.globalStatistics.getSterileMice());
		truthsLabel.setText("Truths : "+Statistics.globalStatistics.getTruths());
		liesLabel.setText("Lies : "+Statistics.globalStatistics.getLies());
		agreedInfosLabel.setText("Agreed infos : "+Statistics.globalStatistics.getAgreedInfo());
		refusedInfosLabel.setText("Refused infos : "+Statistics.globalStatistics.getRefusedInfo());
		jsPan.repaint();
		mainPan.add(jsPan);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateStats();
	}

	/**
	 * This class displays all the simulation's statistics in another window.
	 * @author Rova
	 *
	 */
	public class FullStats extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected JPanel fullStatsPan = new JPanel();
		protected JScrollPane jsPan2 = new JScrollPane();
		
		public FullStats(String title) {
			super(title);
			this.setPreferredSize(new Dimension(550, 550));
			fullStatsPan.setLayout(new GridLayout(10, 2));
			init();
			jsPan2 = new JScrollPane(fullStatsPan,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jsPan2.setPreferredSize(new Dimension (200, 500));
			this.add(jsPan2);
			this.pack();
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					statsPan.add(aliveMiceLabel);
					statsPan.add(deadMiceLabel);
					statsPan.add(aliveMaleMiceLabel);
					statsPan.add(aliveFemaleMiceLabel);
					statsPan.add(foodSourcesLabel);
					statsPan.add(totalFoodQuantityLabel);
					statsPan.add(sterileMiceLabel);
					statsPan.add(impregnateMiceLabel);
					mainPan.add(jsPan);
				}				
			});			
		}
		
		public void init() {
			fullStatsPan.add(totalMiceLabel);
			fullStatsPan.add(totalMaleMiceLabel);
			fullStatsPan.add(totalFemaleMiceLabel);
			fullStatsPan.add(aliveMiceLabel);
			fullStatsPan.add(aliveMaleMiceLabel);
			fullStatsPan.add(aliveFemaleMiceLabel);
			fullStatsPan.add(deadMiceLabel);
			fullStatsPan.add(deadMaleMiceLabel);
			fullStatsPan.add(deadFemaleMiceLabel);
			fullStatsPan.add(maximumMiceLabel);
			fullStatsPan.add(averageAliveMiceLabel);
			fullStatsPan.add(gridSizexLabel);
			fullStatsPan.add(gridSizeyLabel);
			fullStatsPan.add(startingMiceLabel);
			fullStatsPan.add(foodRegenSpeedLabel);
			fullStatsPan.add(obstacleDensityLabel);
			fullStatsPan.add(foodDensityLabel);
			fullStatsPan.add(foodSourcesLabel);
			fullStatsPan.add(totalFoodQuantityLabel);
			fullStatsPan.add(couplingsLabel);
			fullStatsPan.add(impregnateMiceLabel);
			fullStatsPan.add(birthsLabel);
			fullStatsPan.add(duplicationsLabel);
			fullStatsPan.add(sterileMiceLabel);
			fullStatsPan.add(truthsLabel);
			fullStatsPan.add(liesLabel);
			fullStatsPan.add(agreedInfosLabel);
			fullStatsPan.add(refusedInfosLabel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			updateStats();
		}
	}
}
