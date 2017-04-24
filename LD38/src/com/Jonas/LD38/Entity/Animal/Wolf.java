package com.Jonas.LD38.Entity.Animal;

import java.awt.Rectangle;

import com.Jonas.LD38.Grapics.Loader;
import com.Jonas.LD38.Grapics.Renderer;
import com.Jonas.LD38.Level.Tile.Tile;

public class Wolf extends Animal {
	public boolean tamed;
	public boolean gettingTamed;
	
	private int cool;
	
	public Animal target;
	
	public Wolf(int x, int y) {
		super(x, y, 0+2*16, 20);
		
		fuzzX = x;
		fuzzY = y;
		
		collider = new Rectangle(0, 1, 8, 7);
	}
	
	public void tick() {
		if (gettingTamed || cool++ < 60) return;
		
		if (target != null && !target.dead) {
			directionToTile(level.getTile(target.x, target.y));
		} else {
			target = level.getClosestAnimal(x, y);
			if (target instanceof Wolf) target = null;
			if (target == null && Math.random()<0.05) {
				double xx = (Math.random()-0.5)*2;
				double yy = (Math.random()-0.5)*2;
				
				double length = Math.sqrt(xx*xx+yy*yy);

				xd = xx/length*0.3;
				yd = yy/length*0.3;
			}
		}
		
		if (!gettingTamed) {
			if (!level.collides(this, (int) (fuzzX+xd), (int) fuzzY)) fuzzX += xd;
			if (!level.collides(this, (int) fuzzX, (int) (fuzzY+yd))) fuzzY += yd;
			
			x = (int) fuzzX;
			y = (int) fuzzY;
			
			if (target == null) return;
			
			if (level.isUnder(level.getTile(target.x, target.y), this)) {
				target.damage();
				if (xd > 0.001) xd = xd / 1000;
				yd = 0;
			}
		}
	}
	
	private void directionToTile(Tile tile) {
		if (tile == null) return;
		
		double xx = tile.x-fuzzX;
		double yy = tile.y-fuzzY;
		
		double length = Math.sqrt(xx*xx+yy*yy);

		xd = xx/length*0.3;
		yd = yy/length*0.3;
	}
	
	public void damage() {
		gettingTamed = true;
		
		if (Math.random()<0.2) {
			gettingTamed = false;
			tamed = true;
		}
	}
	
	public void render(Renderer renderer) {
		if (xd > 0)
			renderer.draw(Loader.Sprites, x, y, (tex%16)*8+(tamed?4:0)*8, (tex/16+(isFemale?1:0))*8, 8, 8);
		else
			renderer.drawFlip(Loader.Sprites, x, y, (tex%16)*8+(tamed?4:0)*8, (tex/16+(isFemale?1:0))*8, 8, 8);
	}
}