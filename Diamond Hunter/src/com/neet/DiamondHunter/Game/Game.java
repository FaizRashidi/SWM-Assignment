/* 
 * The entry point of the game.
 * This class loads up a JFrame window and
 * puts a GamePanel into it.
 * 
 *	Game Source Code is taken from the Youtube user "Foreign Guy Mike"
 *	Credits to him
*/
package com.neet.DiamondHunter.Game;

import javax.swing.JFrame;

/*
 * New game
 */
public class Game {
	
	/**
	 * This method runs the game
	 */
	public void runGame(String[] args) {
		
		JFrame window = new JFrame("Diamond Hunter");
		
		window.add(new GamePanel());
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}		
}
