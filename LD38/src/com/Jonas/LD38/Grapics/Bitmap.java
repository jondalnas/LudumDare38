package com.Jonas.LD38.Grapics;

public class Bitmap {
	public final int width, height;
	public int[] pixels;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width*height];
	}
	
	public void clear(int color) {
		fill(color, 0, 0, width, height);
	}
	
	public void draw(Bitmap texture, int x, int y, int x0, int y0, int x1, int y1) {
		for (int yy = 0; yy < y1; yy++) {
			int yPix = yy+y0;
			if (yPix < 0 || yPix >= texture.height) continue;
			
			int ys = yy+y;
			if (ys < 0 || ys >= height) continue;
			
			for (int xx = 0; xx < x1; xx++) {
				int xPix = xx+x0;
				if (xPix < 0 || xPix >= texture.width) continue;
				
				int xs = xx+x;
				if (xs < 0 || xs >= width) continue;
				
				if (texture.getPixel(xPix, yPix) == 0xff00ff) continue;
				
				setPixel(texture.getPixel(xPix, yPix), xs, ys);
			}
		}
	}
	
	public void drawFlip(Bitmap texture, int x, int y, int x0, int y0, int x1, int y1) {
		for (int yy = 0; yy < y1; yy++) {
			int yPix = yy+y0;
			if (yPix < 0 || yPix >= texture.height) continue;
			
			int ys = yy+y;
			if (ys < 0 || ys >= height) continue;
			
			for (int xx = 0; xx < x1; xx++) {
				int xPix = (x1-xx-1)+x0;
				if (xPix < 0 || xPix >= texture.width) continue;
				
				int xs = xx+x;
				if (xs < 0 || xs >= width) continue;
				
				if (texture.getPixel(xPix, yPix) == 0xff00ff) continue;
				
				setPixel(texture.getPixel(xPix, yPix), xs, ys);
			}
		}
	}
	
	String str = "ABCDEFGHIJ" +
				 "KLMNOPQRST" +
				 "UVWXYZ1234" +
				 "567890    ";
	
	public void draw(String msg, int x, int y) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			char chr = msg.charAt(i);
			int pos = str.indexOf(chr);
			
			draw(Loader.Sprites, x+i*4, y, pos%10*3+64, pos/10*5+88, 3, 5);
		}
	}

	String numb = "12345678" +
				  "90      ";
	
	public void drawWideNumbers(int num, int x, int y) {
		String msg = (num+"").toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			char chr = msg.charAt(i);
			int pos = numb.indexOf(chr);
			
			draw(Loader.Sprites, x+i*4, y, pos%8*4+64, pos/8*5+108, 4, 5);
		}
	}
	
	public void drawRed(String msg, int x, int y) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			char chr = msg.charAt(i);
			int pos = str.indexOf(chr);
			
			draw(Loader.Sprites, x+i*4, y, pos%10*3+96, pos/10*5+88, 3, 5);
		}
	}
	
	public void drawWideNumbersRed(int num, int x, int y) {
		String msg = (num+"").toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			char chr = msg.charAt(i);
			int pos = numb.indexOf(chr);
			
			draw(Loader.Sprites, x+i*4, y, pos%8*4+96, pos/8*5+108, 4, 5);
		}
	}
	
	public int getPixel(int x, int y) {
		return pixels[x+y*width];
	}
	
	public void setPixel(int color, int x, int y) {
		pixels[x+y*width] = color;
	}
	
	public void fill(int color, int x, int y, int width, int height) {
		for (int yy = y; yy < height; yy++) {
			for (int xx = x; xx < width; xx++) {
				setPixel(color, xx, yy);
			}
		}
	}

	public void drawRect(int color, int x, int y, int w, int h) {
		for (int y0 = 0; y0 <= h; y0++) {
			int yy = y0 + y;
			
			if (yy < 0 || yy >= height) continue;
			
			for (int x0 = 0; x0 <= w; x0++) {
				int xx = x0 + x;
				
				if (xx < 0 || xx >= width) continue;
				
				if (x0 == 0 || x0 == w || y0 == 0 || y0 == h)
					setPixel(color, xx, yy);
			}
		}
	}
}