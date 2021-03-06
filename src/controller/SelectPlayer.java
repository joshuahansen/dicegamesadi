package controller;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainWindow;
import view.Toolbar;
/**
 * 
 * @author Joshua Hansen
 * ListSelectionListener for getting player selected from list
 */
public class SelectPlayer implements ListSelectionListener {
	private GameEngine gameEngine;
	private MainWindow frame;
	private DefaultListModel listModel;
	private JList list;
	
	//constructor passes in frame, gameEngine and list
	public SelectPlayer(MainWindow frame, GameEngine gameEngine, DefaultListModel<String> listModel, JList list)
	{
		this.frame = frame;
		this.gameEngine = gameEngine;
		this.listModel = listModel;
		this.list = list;
	}
	//get value from list and set as current player
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int playerIndex = list.getSelectedIndex();
		String player = (String) listModel.get(playerIndex);
		String[] splitString = player.split("-");
		frame.setCurrentPlayer(splitString[0]);
		
		//refresh main frame with current player details
		frame.refreshDice(gameEngine.getPlayer(frame.getCurrentPlayer()).getRollResult());
		frame.refreshFrame();
	}

}
