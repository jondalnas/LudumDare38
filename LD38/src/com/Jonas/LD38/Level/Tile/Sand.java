package com.Jonas.LD38.Level.Tile;

import com.Jonas.LD38.Entity.Entity;
import com.Jonas.LD38.Entity.Human;

public class Sand extends Tile {

	public Sand() {
		color = 0xF8D878;
	}

	public boolean collides(Entity e) {
		if (e instanceof Human) return false;
		
		return true;
	}
	
	public void setZone(Zones zone) {
	}
}