package com.Jonas.LD38.Jobs.Actions;

import com.Jonas.LD38.Entity.Human;
import com.Jonas.LD38.Jobs.Cost;
import com.Jonas.LD38.Level.Level;

public abstract class JobAction {
	public Cost cost = new Cost();
	
	public abstract boolean start(int x, int y, Level level, Human human);
}
