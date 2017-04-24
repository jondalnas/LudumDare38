package com.Jonas.LD38.Level.Tile;

import com.Jonas.LD38.Grapics.Renderer;

public class Wheet extends Tile {
	public int growing;
	
	public Wheet() {
	}
	
	public void render(Renderer renderer) {
		renderer.setPixel(growing<5?0x503000:(growing<20?0x00B800:0xF8B800), x, y);
	}
}
