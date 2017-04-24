package com.Jonas.LD38.Jobs;

import com.Jonas.LD38.Entity.Animal.Animal;
import com.Jonas.LD38.Jobs.Actions.BabyAction;
import com.Jonas.LD38.Jobs.Actions.BuildAction;
import com.Jonas.LD38.Jobs.Actions.ChopAction;
import com.Jonas.LD38.Jobs.Actions.GrowAction;
import com.Jonas.LD38.Jobs.Actions.GrowTreeAction;
import com.Jonas.LD38.Jobs.Actions.HarvestAction;
import com.Jonas.LD38.Jobs.Actions.HuntingAction;
import com.Jonas.LD38.Jobs.Actions.JobAction;
import com.Jonas.LD38.Level.Tile.House;
import com.Jonas.LD38.Level.Tile.Tree;
import com.Jonas.LD38.Level.Tile.Wheet;
import com.Jonas.LD38.Level.Tile.Zones;

public enum Job {
	BUILDINGS("Houses"),
	BUILD(Zones.building, new BuildAction(), "Build"),
	TREES("Trees"),
	TREE(Zones.tree, new GrowTreeAction(), "Plant"),
	CHOP(new Tree(), new ChopAction(), "Chop"),
	WHEET("Wheet"),
	PLANT(Zones.growing, new GrowAction(), "Plant"),
	HARVEST(new Wheet(), new HarvestAction(), "Hrvst"),
	ANIMAL("Animal"),
	BABY(new House(), new BabyAction(), "Baby"),
	HANDEL(new Animal(0,0,0,0), new HuntingAction(), "Handl");
	
	public Object gotoObject;
	private JobAction action;
	public String name;
	public boolean category;
	public boolean isPossible = true;
	
	public static final int size = Job.values().length;
	
	Job(Object gotoObject, JobAction action, String name) {
		this.gotoObject = gotoObject;
		this.action = action;
		this.name = name;
	}
	
	Job(String name) {
		this.name = name;
		category = true;
	}
	
	public JobAction getAction() {
		return action;
	}
}
