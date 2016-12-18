// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.neet.DiamondHunter.Main;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Game {
	
	static GamePanel gamepanel;
	static MapViewer mapviewer;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Diamond Hunter");
		window.setLayout(new GridLayout(1, 2));
		
		gamepanel = new GamePanel();
		mapviewer = new MapViewer();
		
		window.add(gamepanel);
		window.add(mapviewer);
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
			
}
