package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Resources;
import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Level.Level;
import com.Jonas.LD38.Level.Tile.Grass;
import com.Jonas.LD38.Level.Tile.Tile;
import com.Jonas.LD38.Level.Tile.Wheet;

public class HarvestAction extends JobAction {
	public boolean start(int x, int y, Level level, Human human) {
		level.placeTile(new Grass(), x, y);
		Tile tile = level.getClosestTile(new Wheet(), x, y);
		human.goingTo = tile;
		human.here = false;
		
		Resources.FOOD.add(2);
		
		if (human.goingTo == null) return true;
		tile.workingOn = true;
		return false;
	}
}