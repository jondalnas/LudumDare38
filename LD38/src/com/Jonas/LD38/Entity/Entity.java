package com.Jonas.LD38.Entity;

import java.awt.Rectangle;

import com.Jonas.LD38.Grapics.Loader;
import com.Jonas.LD38.Grapics.Renderer;
import com.Jonas.LD38.Level.Level;

public class Entity {
	public int x, y;
	public int tex;
	public Rectangle collider = new Rectangle();
	
	public Level level;
	
	public Entity(int x, int y, int tex) {
		this.x = x;
		this.y = y;
		this.tex = tex;
	}
	
	public void tick() {
	}
	
	public void render(Renderer renderer) {
		renderer.draw(Loader.Sprites, x, y, (tex%16)*8, (tex/16)*8, 8, 8);
	}
}