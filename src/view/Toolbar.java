package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;

import javax.swing.*;

import controller.AddPlayer;
import controller.MakeBet;
import controller.MakeRoll;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Toolbar extends JToolBar{
	GameEngine gameEngine;
	Player player;
	MainWindow frame;
	JLabel currentPlayer;
	
	public Toolbar(MainWindow frame, GameEngine gameEngine, Player player)
	{
		this.frame = frame;
		this.gameEngine = gameEngine;
		this.player = player;
		addButtons(this);
		
		this.currentPlayer = new JLabel(frame.getCurrentPlayer().getPlayerName());
		currentPlayer.setFont(new Font("Sefif", Font.BOLD, 36));
		add(currentPlayer);
		setPreferredSize(new Dimension(450,130));
	}
	
	protected void addButtons(JToolBar toolbar)
	{
		JButton button = null;
		
//		button = makeButton("player", "Add Player", "Add new player to the game");
//		button.addActionListener(new AddPlayer(this.frame, this.gameEngine));
//		toolbar.add(button);
	
		button = makeButton("bet", "Place Bet", "Player can place bet");
		button.addActionListener(new MakeBet(this.frame, this.gameEngine, this.player));
		toolbar.add(button);
		
		button = makeButton("roll", "Player Roll", "Player roll the dice");
		button.addActionListener(new MakeRoll(this.gameEngine, this.player));
		toolbar.add(button);

	}
	protected JButton makeButton(String img, String action, String tooltip)
	{
		int btnH = 100;
		int btnW = 100;
		int imgH = 90;
		int imgW = 90;
		
		String imgLocation = "images/"+img+".png";
		URL imgURL = Toolbar.class.getResource(imgLocation);
		
		JButton button = new JButton();
		button.setMaximumSize(new Dimension(btnW, btnH));
		button.setPreferredSize(new Dimension(btnW,btnH));
		button.setActionCommand(action);
		button.setToolTipText(tooltip);
		ImageIcon icon = new ImageIcon(imgURL);
		Image iconImage = icon.getImage();
		Image newImage = iconImage.getScaledInstance(imgW, imgH, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		button.setIcon(newIcon);
		return button;
	}
	public void updateToolbar()
	{
		this.currentPlayer.setText(this.frame.getCurrentPlayer().getPlayerName());
		repaint();
	}
}
