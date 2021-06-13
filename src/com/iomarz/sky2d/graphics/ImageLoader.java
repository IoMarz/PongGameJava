package com.iomarz.sky2d.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String p) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(p));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load image with path " + p);
			System.exit(1);
		}
		return null;
	}

}
