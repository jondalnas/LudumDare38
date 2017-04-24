package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Resources;
import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Level.Level;
import com.Jonas.LD38.Level.Tile.House;

public class BuildAction extends JobAction {
	public int buildTimer;
	
	public BuildAction() {
		cost.set(100, 30, 0);
	}
	
	public boolean start(int x, int y, Level level, Human human) {
		if (buildTimer++ >= 900) {
			level.placeTile(new House(), x, y);
			
			Resources.ROOM.add(2);
			
			return true;
		}
		
		return false;
	}
}