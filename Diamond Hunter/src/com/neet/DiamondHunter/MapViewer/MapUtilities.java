/*
 * MapUtilites.java  v2.0  22/12/16
 */
package com.neet.DiamondHunter.MapViewer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * @author Muhammad Faiz Bin Mohd Rashidi
 * 
 * This class reads the map from the external file "tilemap.map"
 * and loads image "tileset.gif"
 * 
 * This class also draws the map 
 */
public class MapUtilities {
	
	//Map image
    Image tileset;
    Image tiles[][];
    
    //For Position validation reference in the mapController class
    public static int newmaps[][];
    
    int maps[][];
    int tileSize = 16;
    int numCols,numRows;

    /**
     * @param tileSetFile is the path of the tileset.gif
     * This method reads the image, and cut in tiles.
     */
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

	/**
	 * @param testMapFile is the path of the TestMap.map file 
	 * This class reads and stores the lines inside the files.
	 */
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
			
			newmaps = maps;				//For references
		}
		catch(Exception e){}	
	}

	/**
	 * @param gc is the graphics context of the FXML canvas objects
	 * This method draws the map based from the position read from the tilemap 
	 * and load the position with the image obtained from loadTile method
	 */
	public void drawMapViewer(GraphicsContext gc) {

		for(int row = 0; row <numRows; row++){
			for( int col = 0; col <numCols; col++){
								
				int rc = maps[row][col];
				int r = rc / tiles[0].length;
				int c = rc % tiles[0].length;
				
				gc.drawImage(
						tiles[r][c],
						col * tileSize,
						row * tileSize
						);		
			}
		}
	}
}
