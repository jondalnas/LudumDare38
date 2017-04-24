package com.Jonas.LD38.Entity.Animal;

import java.awt.Rectangle;

import com.Jonas.LD38.Entity.Animal.Animal;

public class Chicken extends Animal {
	public Chicken(int x, int y) {
		super(x, y, 1+2*16, 3);
		
		collider = new Rectangle(2, 3, 4, 5);
	}
}