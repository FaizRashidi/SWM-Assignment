// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;

public class Game {

	public void runGame(String[] args) {
		
		JFrame window = new JFrame("Diamond Hunter");
		//window.setLayout(new GridLayout(1, 2));
			
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

	}
			
}
