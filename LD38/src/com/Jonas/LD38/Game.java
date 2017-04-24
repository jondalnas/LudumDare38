package com.Jonas.LD38;

import java.awt.Rectangle;

import com.Jonas.LD38.Jobs.Job;
import com.Jonas.LD38.Jobs.JobManager;
import com.Jonas.LD38.Level.Level;
import com.Jonas.LD38.Level.Tile.Zones;

public class Game {
	public int width, height;
	
	public boolean started;
	
	public Level level;
	public Listener listener;
	public JobManager manager;
	
	public boolean showZones;
	public boolean showZoneMenu;
	public boolean zoneMenuOpened;
	public boolean joblistMenuOpened;
	public Job selectedJob;
	public int cursor = -1;
	
	public int[] selectedCorners = new int[2];
	public Rectangle selectedBox;
	public boolean selected;

	private boolean left;
	private boolean right;

	public int starving;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		level = new Level(width, height);
		level.game = this;
		manager = new JobManager();

		Resources.FOOD.quantity = 100;
		Resources.WOOD.quantity = 100;
		
		selectedCorners[0] = -1;
		selectedCorners[1] = -1;
	}
	
	public void tick() {
		
		if (!started) {
			if ((listener.mouseButtons&0b1)==1 && !left) {
				left = true;
				if (listener.mousePosX > width/2-(5*4)/2 && listener.mousePosX < width/2+(5*4)/2 && listener.mousePosY > height/2-6 && listener.mousePosY < height/2-1) {
					started = true;
				} else if (listener.mousePosX > width/2-(4*4)/2 && listener.mousePosX < width/2+(4*4)/2 && listener.mousePosY > height/2+6 && listener.mousePosY < height/2+11) {
					System.exit(0);
				}
			} else {
				left = false;
			}
		} else {
			level.tick();
			
			if ((listener.mouseButtons&0b1)==1 && !left) {
				if (selectedJob != null) {
					if (listener.mousePosX > width/2-22/2 && listener.mousePosX < width/2+22/2 && listener.mousePosY > height/2-9/2 && listener.mousePosY < height/2+9/2) {
						cursor = (listener.mousePosX-width/2+18/2)/2+1;
						if (cursor > 9) cursor = 9;
					}
				} else if (listener.mousePosX > width/2-16 && listener.mousePosX < width/2+16 && listener.mousePosY > height-17) {
					left = true;
					zoneMenuOpened = !zoneMenuOpened;
				} else if (listener.mousePosX < 33 && listener.mousePosY > 62) {
					if (listener.mousePosX > 25 && listener.mousePosX < 25+6 && listener.mousePosY > height-56 && listener.mousePosY < height-56+6) {
						joblistMenuOpened = true;
					}
				} else if (joblistMenuOpened && (listener.mousePosX > 33 && listener.mousePosX < 34+33+32 && listener.mousePosY > height-56+3-(Job.values().length*10)/2 && listener.mousePosY < height-56+3+(Job.values().length*10)/2) && !left) {
					for (int i = 0; i < Job.size; i++) {
						if (listener.mousePosY < height-56+3-((Job.size>10?10:Job.size)*10)/2+((i%9)*10)+10 && listener.mousePosY > height-56+3-((Job.size>10?10:Job.size)*10)/2+((i%9)*10) && listener.mousePosX > 34+(i/9*33) && listener.mousePosX < 34+(i/9*33)+32) {
							if (Job.values()[i].category) break;
							selectedJob = Job.values()[i];
							break;
						}
					}
				} else if (zoneMenuOpened && !left) {
					left = true;
					if (listener.mousePosY > height-17-17) {
						for (int i = 0; i < Zones.size; i++) {
							if (listener.mousePosX>width/2-(Zones.size/2*17)+(i*17) && listener.mousePosX<width/2-(Zones.size/2*17)+(i*17+16)) {
								level.setRectZone(selectedBox, Zones.values()[i]);
							}
						}
					}
				} else if (!selected || selectedCorners[0] == -1 || selectedCorners[0] == -1) {
					showZoneMenu = false;
					zoneMenuOpened = false;
					
					selectedCorners[0] = listener.mousePosX;
					selectedCorners[1] = listener.mousePosY;
				}
			} else if (((listener.mouseButtons>>2)&0b1)==1) {
				if (!right && listener.mousePosX > 3 && listener.mousePosX < 31 && listener.mousePosY > height-57 && listener.mousePosY < height-3) {
					right = true;
					for (int i = 0; i < manager.joblist.size(); i++) {
						if (listener.mousePosY > height-41+i*6 && listener.mousePosY < height-41+i*6+5) {
							manager.joblist.remove(i);
						}
					}
				} else {
					selected = false;
					joblistMenuOpened = false;
					selectedJob = null;
					selectedCorners[0] = -1;
					selectedCorners[1] = -1;
				}
			} else if (listener.mouseButtons == 0) {
				selectedCorners[0] = -1;
				selectedCorners[1] = -1;
				
				if (selected) showZoneMenu = true;
				
				if (cursor != -1) {
					manager.add(selectedJob, cursor);
					
					cursor = -1;
					
					selectedJob = null;
				}
			}
	
			if ((listener.mouseButtons&0b1)!=1) left = false;
			if (((listener.mouseButtons>>2)&0b1)!=1) right = false;
			
			
			if (selected)
				showZones = true;
			else {
				showZones = false;
				
				showZoneMenu = false;
				zoneMenuOpened = false;
			}
	
			if (selectedCorners[0] != -1 && selectedCorners[1] != -1) {
				int w = (listener.mousePosX > selectedCorners[0] ? listener.mousePosX-selectedCorners[0]:selectedCorners[0]-listener.mousePosX)+1;
				int h = (listener.mousePosY > selectedCorners[1] ? listener.mousePosY-selectedCorners[1]:selectedCorners[1]-listener.mousePosY)+1;
				
				selectedBox = new Rectangle(listener.mousePosX > selectedCorners[0] ? selectedCorners[0] : listener.mousePosX, listener.mousePosY > selectedCorners[1] ? selectedCorners[1] : listener.mousePosY, w, h);
				
				selected = true;
			}
		}
	}
}