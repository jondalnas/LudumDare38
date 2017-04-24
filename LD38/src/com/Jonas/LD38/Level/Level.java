package com.Jonas.LD38.Level;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Jonas.LD38.Game;
import com.Jonas.LD38.Entity.Entity;
import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Entity.Particle;
import com.Jonas.LD38.Entity.Animal.Animal;
import com.Jonas.LD38.Entity.Animal.Chicken;
import com.Jonas.LD38.Entity.Animal.Llama;
import com.Jonas.LD38.Entity.Animal.Turkey;
import com.Jonas.LD38.Entity.Animal.Wolf;
import com.Jonas.LD38.Grapics.Renderer;
import com.Jonas.LD38.Level.Tile.Sapling;
import com.Jonas.LD38.Level.Tile.Tile;
import com.Jonas.LD38.Level.Tile.Tree;
import com.Jonas.LD38.Level.Tile.Water;
import com.Jonas.LD38.Level.Tile.Wheet;
import com.Jonas.LD38.Level.Tile.Zones;

public class Level {
	public final int width, height;
	
	public Tile[] tiles;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Particle> particles = new ArrayList<Particle>();
	public Game game;
	private Random rand = new Random();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width*height];
		LevelGenerator.generateLevel(width, height, this);

		addEntity(new Human(width/2, height/2, true));
		addEntity(new Human(width/2-8, height/2, false));
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return new Water();
		
		return tiles[x+y*width];
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			e.tick();
			if (e instanceof Human && ((Human) e).dead) {
				entities.remove(e);
				i--;
			}
			if (e instanceof Animal && ((Animal) e).dead) {
				entities.remove(e);
				i--;
			}
		}
		
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.tick();
			if (p.dead) {
				particles.remove(p);
				i--;
			}
		}
		
		for (int i = 0; i < 100; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			Tile t = getTile(x, y);
			if (t instanceof Sapling) {
				((Sapling) t).growing++;
				if (((Sapling) t).growing > 25) placeTile(new Tree(), x, y);
			}
			if (t instanceof Wheet) {
				((Wheet) t).growing++;

				if (rand.nextInt(5000)==0) {
					int num = rand.nextInt(4);
					
					if (num==0) addEntity(new Chicken(x, y));
					else if (num==1) addEntity(new Turkey(x, y));
					else if (num==2) addEntity(new Wolf(x, y));
					else addEntity(new Llama(x, y));
				}
			}
		}
	}
	
	public void placeTile(Tile t, int x, int y) {
		t.level = this;
		t.x = x;
		t.y = y;
		
		if (tiles[x+y*width] != null) t.zone = tiles[x+y*width].zone;
		
		tiles[x+y*width] = t;
	}
	
	public void addEntity(Entity e) {
		e.level = this;
		entities.add(e);
	}
	
	public boolean collides(Entity e, int x, int y) {
		for (int yy = 0; yy < e.collider.height; yy++) {
			int y0 = yy + e.collider.y;
			for (int xx = 0; xx < e.collider.width; xx++) {
				int x0 = xx + e.collider.x;
				if (getTile(x0+x, y0+y).collides(e)) return true;
			}
		}
		
		return false;
	}
	
	public void setRectZone(Rectangle rect, Zones zone) {
		for (int yy = 0; yy < rect.height; yy++) {
			for (int xx = 0; xx < rect.width; xx++) {
				int x0 = xx+rect.x;
				int y0 = yy+rect.y;
				
				getTile(x0, y0).setZone(zone);
			}
		}
	}

	public Tile getClosestTileWithZone(Zones zone, int x, int y) {
		int dist = width+height;
		Tile best = null;
		
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				
				Tile t = getTile(xx, yy);
				if (t.matchZones(zone)) {
					int newDist = Math.abs(t.x-x)+Math.abs(t.y-y);
					if (newDist < dist) {
						dist = newDist;
						best = t;
					}
				}
			}
		}
		
		return best;
	}

	public Tile getClosestTile(Tile tile, int x, int y) {
		int dist = width+height;
		Tile best = null;
		
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				
				Tile t = getTile(xx, yy);
				
				if (tile.getClass().equals(t.getClass()) && tile.accept(t)) {
					int newDist = Math.abs(t.x-x)+Math.abs(t.y-y);
					if (newDist < dist) {
						dist = newDist;
						best = t;
					}
				}
			}
		}
		
		return best;
	}

	public Animal getClosestAnimal(int x, int y) {
		Animal bestAnimal = null;
		int best = width+height;
		for (Entity e : entities) {
			if (!(e instanceof Animal) || (e instanceof Wolf && ((Wolf) e).tamed)) continue;
			
			int xx = e.x-x;
			int yy = e.y-y;
			
			int dist = xx+yy;
			if (dist < best) {
				best = dist;
				bestAnimal = (Animal) e;
			}
		}
		
		return bestAnimal;
	}

	public boolean isUnder(Tile tile, Entity e) {
		for (int yy = 0; yy < e.collider.height+4; yy++) {
			int y0 = yy + e.collider.y-2;
			for (int xx = 0; xx < e.collider.width+4; xx++) {
				int x0 = xx + e.collider.x-2;
				
				if (getTile(x0+e.x, y0+e.y) == tile) return true;
			}
		}
		
		return false;
	}

	public boolean exists(Tile tile) {
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				Tile t = getTile(xx, yy);
				
				if (t == tile) return true;
			}
		}
		
		return false;
	}
	
	public void render(Renderer renderer) {
		List<Tile> special = new ArrayList<Tile>();
		for (Tile t : tiles) {
			if (t.weight > 1) special.add(t);
			else t.render(renderer);
		}
		
		for (Tile t : special) {
			t.render(renderer);
		}
		
		for (Entity e : entities) {
			e.render(renderer);
		}
		
		for (Particle p : particles) {
			p.render(renderer);
		}
	}
}