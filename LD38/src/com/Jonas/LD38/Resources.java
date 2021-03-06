package com.Jonas.LD38;

public enum Resources {
	WOOD("Wood", 4+6*16),
	FOOD("FOOD", 5+6*16),
	ROOM("ROOM", 6+6*16);
	
	public String name;
	public int icon;
	public int quantity;
	
	public static int size = Resources.values().length;
	
	Resources(String name, int icon) {
		this. name = name;
		this.icon = icon;
	}
	
	public void add(int quantity) {
		this.quantity += quantity;
	}
	
	public void remove(int quantity) {
		this.quantity -= quantity;
	}
}