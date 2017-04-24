package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Resources;
import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Entity.Animal.Wolf;
import com.Jonas.LD38.Jobs.Actions.JobAction;
import com.Jonas.LD38.Level.Level;

public class HuntingAction extends JobAction {
	private int cooloff = 100;
	
	public boolean start(int x, int y, Level level, Human human) {
		if (cooloff++ >= 100) {
			cooloff = 0;
			human.attacking = true;
			human.hunting.damage();
			
			if (human.hunting.dead) {
				Resources.FOOD.add(50);
				return true;
			}
			
			if (human.hunting instanceof Wolf && ((Wolf) human.hunting).tamed) {
				return true;
			}
		}
		return false;
	}
}