package com.neet.DiamondHunter.MapViewer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class MapUtilities {
	
    Image tileset;
    Image tiles[][];
    int tileSize = 16;
    public static int newmaps[][];
    int maps[][];
    
    int numCols,numRows;
	private int x; //position of the player
	private int y; //position of the player

	public void loadTile(String tileSetFile) {

		try{
			
			tileset = new Image(tileSetFile);
			
			int numTilesAcross = (int) ((tileset.getWidth())/(tileSize));
			tiles = new Image[2][numTilesAcross];
			
		    PixelReader pixelreader = tileset.getPixelReader();
			
			for(int col = 0; col < numTilesAcross; col++){
				WritableImage newImage = new WritableImage(
						pixelreader,
						col * tileSize,
						0, 
						tileSize, 
						tileSize);
				
				tiles[0][col] = newImage;
				
				WritableImage newImage2 = new WritableImage(
						pixelreader,
						col * tileSize,
						tileSize, 
						tileSize, 
						tileSize);
				
				tiles[1][col] = newImage2;
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void loadMap(String testMapFile) {

		try{
			
			InputStream in = getClass().getResourceAsStream(testMapFile);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			maps = new int[numRows][numCols];
			
			String delimiters = "\\s+";
			
			for(int row = 0 ; row < numRows ; row++){
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				
				for(int col = 0; col < numCols; col++){
					maps[row][col] = Integer.parseInt(tokens[col]);
				}
			 }
			
			newmaps = maps;
		}
		catch(Exception e){}	
		
	}
	

	public void drawMapViewer(GraphicsContext gc) {

		for(int row = 0; row <numRows; row++){
			for( int col = 0; col <numCols; col++){
								
				int rc = maps[row][col];
				int r = rc / tiles[0].length;
				int c = rc % tiles[0].length;
				
				gc.drawImage(
						tiles[r][c],
						x + col * tileSize,
						y + row * tileSize
						);		
				
			}
		}
	
	}
	
}
