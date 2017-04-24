package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Resources;
import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Level.Level;
import com.Jonas.LD38.Level.Tile.Grass;

public class ChopAction extends JobAction {
	private int choppingTimer;
	
	public boolean start(int x, int y, Level level, Human human) {
		if (choppingTimer%60 == 0) human.attacking = true;
		
		if (choppingTimer++ >= 600) {
			
			level.placeTile(new Grass(), x, y);
			
			Resources.WOOD.add(20);
			
			choppingTimer = 0;
			return true;
		}
		
		return false;
	}
}
