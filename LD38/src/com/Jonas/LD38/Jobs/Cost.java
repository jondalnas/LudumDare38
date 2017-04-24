package com.Jonas.LD38.Jobs;

import com.Jonas.LD38.Resources;

public class Cost {
	public int[] cost = new int[3];
	
	public boolean sufficient() {
		if (cost[0] == 0 && cost[1] == 0 && cost[2] == 0) return true;
		
		if (Resources.WOOD.quantity < cost[0]) return false;
		if (Resources.FOOD.quantity < cost[1]) return false;
		if (Resources.ROOM.quantity < cost[2]) return false;
		
		return true;
	}

	public void set(int wood, int food, int room) {
		cost[0] = wood;
		cost[1] = food;
		cost[2] = room;
	}

	public void buy() {
		Resources.WOOD.remove(cost[0]);
		Resources.FOOD.remove(cost[1]);
		Resources.ROOM.remove(cost[2]);
	}
}