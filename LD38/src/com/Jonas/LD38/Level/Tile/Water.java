package com.Jonas.LD38.Level.Tile;

import com.Jonas.LD38.Entity.Entity;

public class Water extends Tile {

	public Water() {
		color = 0x0078F8;
	}
	
	public boolean collides(Entity e) {
		return true;
	}
	
	public void setZone(Zones zone) {
	}
}