package com.Jonas.LD38.Entity.Animal;

import java.awt.Rectangle;

import com.Jonas.LD38.Entity.Animal.Animal;

public class Turkey extends Animal {

	public Turkey(int x, int y) {
		super(x, y, 3+2*16, 5);
		
		collider = new Rectangle(1, 2, 6, 6);
	}
}