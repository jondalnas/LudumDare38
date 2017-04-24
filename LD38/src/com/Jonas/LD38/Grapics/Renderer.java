package com.Jonas.LD38.Grapics;

import com.Jonas.LD38.Game;
import com.Jonas.LD38.Resources;
import com.Jonas.LD38.Jobs.Job;
import com.Jonas.LD38.Jobs.JobContainer;
import com.Jonas.LD38.Level.Tile.Zones;

public class Renderer extends Bitmap {
	public Game game;
	
	private int foodTimer;
	
	public Renderer(int width, int height) {
		super(width, height);
	}
	
	public void render() {
		clear(0x0078F8);
		
		if (!game.started) {
			draw(Loader.Sprites, width/2-32/2-1, 10, 12*8, 0, 32, 32);

			draw("Start", width/2-(5*4)/2, height/2-6);

			draw("Exit", width/2-(4*4)/2, height/2+6);
		} else {
			game.level.render(this);
			
			if (game.selected) drawRect(0xF8F8F8, game.selectedBox.x-1, game.selectedBox.y-1, game.selectedBox.width+1, game.selectedBox.height+1);
			
			if (game.showZoneMenu) {
				draw(Loader.Sprites, width/2-16, height-17, 0, 40, 32, 16);
				
				if (game.zoneMenuOpened) {
					//TODO: Add animation
					
					for (int i = 0; i < Zones.size; i++) {
						draw(Loader.Sprites, width/2-(Zones.size/2*17)+(i*17), height-17-17, i*16, 56, 16, 16);
					}
				}
			}
			
			draw(Loader.Sprites, 1, height-57, 0, 72, 32, 56);
			
			for (int i = 0; i <= 6; i++) {
				if (i >= game.manager.joblist.size()) break;
				
				JobContainer jc = game.manager.joblist.get(i);
				
				String name = jc.job.name;
				
				while (name.length() < (7-(new String("x"+jc.quantity).length()))) {
					name += " ";
				}
				
				if (jc.job.isPossible)
					draw(name+"x"+jc.quantity, 3, height-41+i*6);
				else
					drawRed(name+"x"+jc.quantity, 3, height-41+i*6);
			}
			
			if (game.joblistMenuOpened) {
				for (int i = 0; i < Job.size; i++) {
					Job job = Job.values()[i];
					
					draw(Loader.Sprites, 34+(job.category?0:2)+(i/9*33), height-56+4-((Job.size>10?10:Job.size)*10/2)+((i%9)*10), 32, 128-(job.category?23:32), job.category?32:24, 9);
					
					draw(job.name, 34+2+(4*(job.category?7:5)-Job.values()[i].name.length()*4)/2+(job.category?0:2)+(i/9*33), height-56+4-((Job.size>10?10:Job.size)*10/2)+((i%9)*10)+2);
				}
				
				if (game.selectedJob != null) {
					draw(Loader.Sprites, width/2-22/2, height/2-14/2, 32, height-6, 22, 14);
					
					drawWideNumbers(game.cursor<0?0:game.cursor, width/2-2, height/2);
				}
			}
			
			fill(0, 0, 0, width, 10);
			
			for (int i = 0; i < Resources.size; i++) {
				Resources res = Resources.values()[i];
				
				draw(Loader.Sprites, (width+50)/(Resources.size+1)*(i+1)-4-(res.name.length()*4/2)-39, 1, res.icon%16*8, res.icon/16*8, 8, 8);
				draw(res.name, (width+50)/(Resources.size+1)*(i+1)-4-(res.name.length()*4/2)+9-39, 2);
				if (res.name.equalsIgnoreCase("FOOD") && game.starving > 0 && foodTimer++%90 >= 45)
					drawRed(res.quantity+"", (width+50)/(Resources.size+1)*(i+1)-4+(res.name.length()*4/2)+9-39+3, 2);
				else
					draw(res.quantity+"", (width+50)/(Resources.size+1)*(i+1)-4+(res.name.length()*4/2)+9-39+3, 2);
			}
		}
	}
}