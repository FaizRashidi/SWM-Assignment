// The tile map class contains a loaded tile set
// and a 2d array of the map.
// Each index in the map corresponds to a specific tile.

package com.neet.DiamondHunter.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.neet.DiamondHunter.Game.GamePanel;

public class TileMap {
	
	// position
	private int x; //position of the player
	private int y; //position of the player
	private int xdest;
	private int ydest;
	private int speed; //speed of the map
	private boolean moving;
	
	// bounds for the camera scrolling
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	// map
	private int[][] map; //2d array which represents a tile from the tileset
	private int tileSize; //Size of a single tile in pixel
	private int numRows; //Number of rows in the map
	private int numCols; //Number of colummns in the map
	private int width; //dimension of the map in pixels
	private int height; //"
	
	// tileset
	private BufferedImage tileset; //ENTIRE TILESET IMAGE
	private int numTilesAcross; //"ID" number of the tiles
	private Tile[][] tiles; //each individual tiles were read, 
							//and stored in tiles array
	
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	//Dimension
	public void setHeight(){
		
	}
	
	// Tilesize is defined when the constructor is invoked
	// Rows and columns is defined from the game panel
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2; //Left & right border of the game
		numColsToDraw = GamePanel.WIDTH / tileSize + 2; //Top & bottom border of the game
		speed = 4;
	}
	
	// Read from the tileset.gif
	// Setting the tiles coordinate and assign the subimaged tile with it
	// so that the loadMap will be loaded based on the coordinate
	public void loadTiles(String tileSetFile) {
		
		try {

			tileset = ImageIO.read(
				getClass().getResourceAsStream(tileSetFile)
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			//tileset.getwidth = 320
			//tileSize = 16
			//numTileAcross = 20
			
			//System.out.println(tileset.getWidth());
			//System.out.println(tileSize);
			//System.out.println(numTilesAcross);
			
			//To read from the Tile set and reference it 
			//so that the tiles[][] array can be assigned into it
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				
				//Cut an image from the tileset
				subimage = tileset.getSubimage(
							col * tileSize, //x - axis of the upper left corner
							0, // y - axis "
							tileSize, //width of the specified rectangular shape
							tileSize //height "
						);		
				// NORMAL is non-blockable tileset 1,2,3
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				
				//Cut an image from the tileset
				subimage = tileset.getSubimage(
							col * tileSize,
							tileSize,
							tileSize,
							tileSize
						);	
				// BLOCKED is blockable tileset = 22,20,21
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Read the testMap.map file
	public void loadMap(String testMapFile) {
		
		try {
			
			//Read the file name
			InputStream in = getClass().getResourceAsStream(testMapFile);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine()); //read lines and get total map columns
			System.out.println(numCols);
			numRows = Integer.parseInt(br.readLine()); //read lines and get total map rows
			
			map = new int[numRows][numCols]; //declared map coordinate form rows/columns
			
			width = numCols * tileSize; // Width of the map in pixels
			height = numRows * tileSize; // height of the map in pixels
			
			//camera boundaries setting
			xmin = GamePanel.WIDTH - width;
			xmin = -width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymin = -height;
			ymax = 0;
			
			/*Reading the TestMap.map*/
			
			String delims = "\\s+"; //The "\\s" is to omit the whitespace each time the map is read
			
			for(int row = 0; row < numRows; row++) {
				
				String line = br.readLine(); //Reading one line of numbers (rows)
				String[] tokens = line.split(delims); //Splitting the lines by omitting the whitespace
				
				//While still in one row, multiple columns is assigned
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize() { return tileSize; }
	public int getx() { return x; }
	public int gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public int getIndex(int row, int col) {
		return map[row][col];
	}
	
	public boolean isMoving() { return moving; }
	
	public void setTile(int row, int col, int index) {
		map[row][col] = index;
	}
	
	public void replace(int i1, int i2) {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(map[row][col] == i1) map[row][col] = i2;
			}
		}
	}
	
	public void setPosition(int x, int y) {
		xdest = x;
		ydest = y;
	}
	
	//To set the initial position of the camera
	public void setPositionImmediately(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void update() {
		if(x < xdest) {
			x += speed;
			if(x > xdest) {
				x = xdest;
			}
		}
		if(x > xdest) {
			x -= speed;
			if(x < xdest) {
				x = xdest;
			}
		}
		if(y < ydest) {
			y += speed;
			if(y > ydest) {
				y = ydest;
			}
		}
		if(y > ydest) {
			y -= speed;
			if(y < ydest) {
				y = ydest;
			}
		}
		
		fixBounds();
		
		colOffset = -this.x / tileSize;
		rowOffset = -this.y / tileSize;
		
		if(x != xdest || y != ydest) moving = true;
		else moving = false;
		
	}
	
	//Making the map 
	public void draw(Graphics2D g) {
		
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
		
			if(row >= numRows) break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				
				//The numbers in the tilemap that have been read is translated into rc reference
				//So that new coordinates can be drawn from the map coordinates.
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(
					
					//Tiles is a numbered tileset.gif, and has been cropped
					tiles[r][c].getImage(),
					x + col * tileSize,
					y + row * tileSize,
					null
				);				
			}			
		}		
	}
}

