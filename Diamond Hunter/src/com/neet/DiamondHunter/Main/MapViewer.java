//This Class is to for the main control in declaring and loading the map
package com.neet.DiamondHunter.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.neet.DiamondHunter.Entity.Item;
import com.neet.DiamondHunter.TileMap.TileMap;

@SuppressWarnings("serial")
public class MapViewer  extends JPanel implements  Runnable {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 640;
	
	private Thread thread; 
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000 / FPS;
	
	private Item axe,boat;
	
	private TileMap tilemap;
	
	public MapViewer(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		
		init();
		//System.out.println("lol");
		long startTime;
		long urdTime;
		long waitTime;
		
		while(running){
			
			startTime = System.nanoTime();
			
			update();
			render();
			draw();
			
			urdTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - urdTime;
			
			try{
				Thread.sleep(waitTime);
			}
			catch(Exception e){
				
			}
			
		}
		
	}
	
	private void init(){
		
		running = true;
		
		image = new BufferedImage( WIDTH , HEIGHT, BufferedImage.TYPE_INT_RGB );
		g = (Graphics2D) image.getGraphics();
		
		
		tilemap = new TileMap(16);
		tilemap.loadMap("/Maps/testmap.map");
		tilemap.loadTiles("/Tilesets/testtileset.gif");
		
		populateItems();
	}
	
	private void populateItems() {
		
		axe = new Item(tilemap);
		axe.setType(Item.AXE);
		axe.setTilePosition(26, 37);
		
		boat = new Item(tilemap);
		boat.setType(Item.BOAT);
		boat.setTilePosition(12, 4);
	
		
	}	
	
	private void update() {
		
		tilemap.update();
		
	}
	
	private void render(){
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		tilemap.drawMapViewer(g);
		axe.draw(g);
		boat.draw(g);
		
	}
	
	private void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}	
	
}
