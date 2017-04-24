package com.Jonas.LD38.Level.Tile;

import com.Jonas.LD38.Grapics.Loader;
import com.Jonas.LD38.Grapics.Renderer;

public class House extends Tile {
	public House() {
		color = 0xA81000;
		weight = 2;
	}
	
	public void render(Renderer renderer) {
		renderer.draw(Loader.Sprites, x-2, y-4, 3, 4*8, 5, 5);
	}
}
