package com.Jonas.LD38.Entity.Animal;

import java.awt.Rectangle;

public class Llama extends Animal {

	public Llama(int x, int y) {
		super(x, y, 2+2*16, 10);
		
		collider = new Rectangle(0, 1, 8, 7);
	}
}