package com.Jonas.LD38.Level.Tile;

public enum Zones {
	none(0xFFFFFF),
	growing(0x005800),
	building(0xBCBCBC),
	tree(0x503000);
	
	public static final int size = Zones.values().length;
	
	int color;
	Zones(int color) {
		this.color = color;
	}
}