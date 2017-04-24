package com.Jonas.LD38.Grapics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Loader {
	public static Bitmap Sprites = loadTexture("/tex/SpriteSheet");
	
	private static Bitmap loadTexture(String path) {
		path += ".png";
		
		BufferedImage bi;
		try {
			bi = ImageIO.read(Loader.class.getResource(path));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		Bitmap bitmap = new Bitmap(bi.getWidth(), bi.getHeight());
		
		bi.getRGB(0, 0, bitmap.width, bitmap.height, bitmap.pixels, 0, bitmap.width);
		
		for (int i = 0; i < bitmap.pixels.length; i++) {
			bitmap.pixels[i] = bitmap.pixels[i] & 0xffffff;
		}
		
		return bitmap;
	}
}