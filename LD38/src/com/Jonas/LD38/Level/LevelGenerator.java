package com.Jonas.LD38.Level;

import java.util.Random;

import com.Jonas.LD38.Level.Tile.*;

public class LevelGenerator {
	
	public static void generateLevel(int width, int height, Level level) {
		Random rand = new Random();
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				level.placeTile(new Water(), x, y);
			}
		}
		
		for (int y = 1; y < height-1; y++) {
			for (int x = 1; x < width-1; x++) {
				
				if ((x < 15 || y < 15 || x >= width-15 || y >= height-15) && rand.nextInt(100)>95)
					level.placeTile(new Water(), x, y);
				else
					level.placeTile(new Sand(), x, y);
			}
		}
		
		double chanceOfSurrSand = 97.8;

		int tries = 0;
		while (tries < 100) {
			tries++;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					
					if (level.getTile(x,y) instanceof Water) {
						Tile n = null;
						Tile s = null;
						Tile e = null;
						Tile w = null;
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrSand) {
							n = new Water();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrSand) {
							s = new Water();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrSand) {
							e = new Water();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrSand) {
							w = new Water();
						}
						
						if (n != null && y+1 < height) level.placeTile(n, x, y+1);
						if (s != null && y-1 >= 0) level.placeTile(s, x, y-1);
						if (e != null && x+1 < width) level.placeTile(e, x+1, y);
						if (w != null && x-1 >= 0) level.placeTile(w, x-1, y);
					}
				}
			}
		}
		
		for (int y = 1; y < height-1; y++) {
			for (int x = 1; x < width-1; x++) {
				int sand = 0;
				
				Tile n = level.getTile(x,(y+1));
				Tile s = level.getTile(x,(y-1));
				Tile e = level.getTile((x+1),y);
				Tile w = level.getTile((x-1),y);

				if (n instanceof Sand) sand++;
				if (s instanceof Sand) sand++;
				if (e instanceof Sand) sand++;
				if (w instanceof Sand) sand++;
				
				if (sand < 1) level.placeTile(new Water(), x, y);
			}
		}

		for (int y = 5; y < height-5; y++) {
			for (int x = 5; x < width-5; x++) {
				
				if (!(level.getTile(x,y) instanceof Sand)) continue;

				if (!isTileInRadius(Water.class, 5, x, y, width, height, level)) level.placeTile(new Grass(), x, y);
			}
		}
		
		level.placeTile(new Tree(), width/4, height/2);
		
		for (int y = 1; y < height-1; y++) {
			for (int x = 1; x < width-1; x++) {
				
				if (level.getTile(x,y) instanceof Grass && rand.nextDouble()*100.0>99.8)
					level.placeTile(new Tree(), x, y);
			}
		}
		
		double chanceOfSurrTree = 95;
		
		tries = 0;
		while (tries < 100) {
			tries++;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					
					if (level.getTile(x,y) instanceof Tree) {
						Tile n = null;
						Tile s = null;
						Tile e = null;
						Tile w = null;
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrTree) {
							n = new Tree();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrTree) {
							s = new Tree();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrTree) {
							e = new Tree();
						}
						
						if (rand.nextDouble()*100.0 >= chanceOfSurrTree) {
							w = new Tree();
						}
						
						if (n != null && y+1 < height && level.getTile(x,(y+1)) instanceof Grass) level.placeTile(n, x, y+1);
						if (s != null && y-1 >= 0 && level.getTile(x,(y-1)) instanceof Grass) level.placeTile(s, x, y-1);
						if (e != null && x+1 < width && level.getTile((x+1),y) instanceof Grass) level.placeTile(e, x+1, y);
						if (w != null && x-1 >= 0 && level.getTile((x-1),y) instanceof Grass) level.placeTile(w, x-1, y);
					}
				}
			}
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (level.getTile(x,y) instanceof Tree) {
					if (level.getTile(x,(y-3)) instanceof Tree) level.placeTile(new Grass(), x, y-3);
					if (level.getTile(x,(y-2)) instanceof Tree) level.placeTile(new Grass(), x, y-2);
					if (level.getTile(x,(y-1)) instanceof Tree) level.placeTile(new Grass(), x, y-1);
					if (level.getTile(x,(y+3)) instanceof Tree) level.placeTile(new Grass(), x, y+3);
					if (level.getTile(x,(y+2)) instanceof Tree) level.placeTile(new Grass(), x, y+2);
					if (level.getTile(x,(y+1)) instanceof Tree) level.placeTile(new Grass(), x, y+1);
					if (level.getTile((x+1),(y-3)) instanceof Tree) level.placeTile(new Grass(), x+1, y-3);
					if (level.getTile((x+1),(y-2)) instanceof Tree) level.placeTile(new Grass(), x+1, y-2);
					if (level.getTile((x+1),(y-1)) instanceof Tree) level.placeTile(new Grass(), x+1, y-1);
					if (level.getTile((x+1),(y+3)) instanceof Tree) level.placeTile(new Grass(), x+1, y+3);
					if (level.getTile((x+1),(y+2)) instanceof Tree) level.placeTile(new Grass(), x+1, y+2);
					if (level.getTile((x+1),(y+1)) instanceof Tree) level.placeTile(new Grass(), x+1, y+1);
					if (level.getTile((x-1),(y-3)) instanceof Tree) level.placeTile(new Grass(), x-1, y-3);
					if (level.getTile((x-1),(y-2)) instanceof Tree) level.placeTile(new Grass(), x-1, y-2);
					if (level.getTile((x-1),(y-1)) instanceof Tree) level.placeTile(new Grass(), x-1, y-1);
					if (level.getTile((x-1),(y+3)) instanceof Tree) level.placeTile(new Grass(), x-1, y+3);
					if (level.getTile((x-1),(y+2)) instanceof Tree) level.placeTile(new Grass(), x-1, y+2);
					if (level.getTile((x-1),(y+1)) instanceof Tree) level.placeTile(new Grass(), x-1, y+1);
					if (level.getTile((x+2),(y-2)) instanceof Tree) level.placeTile(new Grass(), x+2, y-2);
					if (level.getTile((x+2),(y-1)) instanceof Tree) level.placeTile(new Grass(), x+2, y-1);
					if (level.getTile((x+2),(y+2)) instanceof Tree) level.placeTile(new Grass(), x+2, y+2);
					if (level.getTile((x+2),(y+1)) instanceof Tree) level.placeTile(new Grass(), x+2, y+1);
					if (level.getTile((x-2),(y-2)) instanceof Tree) level.placeTile(new Grass(), x-2, y-2);
					if (level.getTile((x-2),(y-1)) instanceof Tree) level.placeTile(new Grass(), x-2, y-1);
					if (level.getTile((x-2),(y+2)) instanceof Tree) level.placeTile(new Grass(), x-2, y+2);
					if (level.getTile((x-2),(y+1)) instanceof Tree) level.placeTile(new Grass(), x-2, y+1);
					if (level.getTile((x-2),(y-1)) instanceof Tree) level.placeTile(new Grass(), x-3, y-1);
					if (level.getTile((x-2),(y+1)) instanceof Tree) level.placeTile(new Grass(), x+3, y+1);
					if (level.getTile((x-3),y) instanceof Tree) level.placeTile(new Grass(), x-3, y);
					if (level.getTile((x-2),y) instanceof Tree) level.placeTile(new Grass(), x-2, y);
					if (level.getTile((x-1),y) instanceof Tree) level.placeTile(new Grass(), x-1, y);
					if (level.getTile((x+3),y) instanceof Tree) level.placeTile(new Grass(), x+3, y);
					if (level.getTile((x+2),y) instanceof Tree) level.placeTile(new Grass(), x+2, y);
					if (level.getTile((x+1),y) instanceof Tree) level.placeTile(new Grass(), x+1, y);
				}
			}
		}
	}
	
	private static boolean isTileInRadius(Class<?> t, int r, int x, int y, int width, int height, Level level) {
		for (int yy = -r; yy < r; yy++) {
			for (int xx = -r; xx < r; xx++) {
				
				if (xx*xx+yy*yy>=r*r) continue;
				
				int x0 = xx+x;
				int y0 = yy+y;
				if (level.getTile(x0, y0).getClass().equals(t)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
