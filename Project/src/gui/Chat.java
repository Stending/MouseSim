package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import griddata.Grid;

public class Chat extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JPanel pan = new JPanel();
	protected JScrollPane jsPan = new JScrollPane();
	protected JLabel chatLabel = new JLabel("Chat:");
	protected JTextField chatField = new JTextField(30);
	protected JTextArea chatTextArea = new JTextArea();
	protected JButton sendButton = new JButton("Send");

	public Chat(Grid grid) {
		super();
		pan.setPreferredSize(new Dimension(510, 220));
		jsPan = new JScrollPane(chatTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsPan.setPreferredSize(new Dimension(500, 175));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		chatTextArea.setBorder(blackline);
		chatTextArea.setEditable(false);
		pan.add(jsPan);
		pan.add(chatLabel);
		pan.add(chatField);
		pan.add(sendButton);
		sendButton.addActionListener(this);
		this.add(pan);
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "This feature will be implemented in a future update", "Not available", JOptionPane.INFORMATION_MESSAGE);
	}
}
