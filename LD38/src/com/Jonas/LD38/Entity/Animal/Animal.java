package com.Jonas.LD38.Entity.Animal;

import com.Jonas.LD38.Entity.Entity;
import com.Jonas.LD38.Entity.Particle;
import com.Jonas.LD38.Grapics.Loader;
import com.Jonas.LD38.Grapics.Renderer;

import java.util.Random;

public class Animal extends Entity {
	protected double fuzzX, fuzzY;
	
	protected double movementSpeed = 0.1;
	
	public boolean isFemale;
	
	Random rand = new Random();
	
	protected boolean moveing = false;
	protected int stillTime;
	private int cooldown = 120;
	protected double xd, yd;
	
	private int hp;
	private int stunned;
	public boolean dead;
	
	public Animal(int x, int y, int tex, int hp) {
		super(x, y, tex);
		
		this.hp = hp;
		
		isFemale = new Random().nextBoolean();
	}
	
	public void tick() {
		if (stunned-- > 0) return;
		
		if (!moveing && stillTime++ >= cooldown && rand.nextInt(150) == 0) {
			moveing = true;
			yd = (new Random().nextDouble()*2-1)*movementSpeed;
			xd = (new Random().nextDouble()*2-1)*movementSpeed;
			
			fuzzX = x;
			fuzzY = y;

			if (level.collides(this, (int) (fuzzX+xd), (int) fuzzY)) xd = -xd;
			if (level.collides(this, (int) fuzzX, (int) (fuzzY+yd))) yd = -yd;
		}
		
		if (moveing) {
			if (!level.collides(this, (int) (fuzzX+xd), (int) fuzzY)) fuzzX += xd;
			if (!level.collides(this, (int) fuzzX, (int) (fuzzY+yd))) fuzzY += yd;
			
			x = (int) fuzzX;
			y = (int) fuzzY;
			
			if (rand.nextInt(300) == 0) {
				moveing = false;
				stillTime = 0;
			}
		}
	}

	public void damage() {
		if (dead) return;
		
		stunned = 120;
		hp--;
		if (hp <= 0) {
			dead = true;
			for (int i = 0; i < 3; i++) {
				level.particles.add(new Particle(4+5*16, x, y, (rand.nextDouble()-0.5)*2, (rand.nextDouble()-0.5)*2, 120));
			}
		}
	}
	
	public void render(Renderer renderer) {
		if (xd > 0)
			renderer.draw(Loader.Sprites, x, y, (tex%16)*8, (tex/16+(isFemale?1:0))*8, 8, 8);
		else
			renderer.drawFlip(Loader.Sprites, x, y, (tex%16)*8, (tex/16+(isFemale?1:0))*8, 8, 8);
	}
}