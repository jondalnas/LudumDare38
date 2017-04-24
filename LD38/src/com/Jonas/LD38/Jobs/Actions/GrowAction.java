package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Level.Level;
import com.Jonas.LD38.Level.Tile.Tile;
import com.Jonas.LD38.Level.Tile.Wheet;
import com.Jonas.LD38.Level.Tile.Zones;

public class GrowAction extends JobAction {
	
	public GrowAction() {
		cost.set(1, 0, 0);
	}

	public boolean start(int x, int y, Level level, Human human) {
		level.placeTile(new Wheet(), x, y);
		if (!human.action.cost.sufficient()) return true;
		human.action.cost.buy();
		Tile tile = level.getClosestTileWithZone(Zones.growing, x, y);
		human.goingTo = tile;
		human.here = false;
		
		
		if (human.goingTo == null) return true;
		tile.workingOn = true;
		return false;
	}
}