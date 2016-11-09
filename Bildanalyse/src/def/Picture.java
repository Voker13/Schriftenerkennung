package def;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
	
	private static int savePictureCounter = 1;

	private static int getRowHeight(BufferedImage bi) {
		int height = 0;

		RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];

		for (int y = 0; y < bi.getHeight(); y++) {
			for (int x = 0; x < bi.getWidth(); x++) {
				pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
			}
		}

		height = getMaxHeight(pixel, bi.getWidth(), bi.getHeight())
				- getMinHeight(pixel, bi.getWidth(), bi.getHeight());

		return height;
	}

	private static int getMinHeight(RGB[][] pixel, int w, int h) {
		int height = 100000;

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (pixel[x][y].getY() < height && pixel[x][y].isBlack()) {
					height = pixel[x][y].getY();
				}
			}
		}

		System.out.println("min height: " + height);

		return height;
	}

	private static int getMaxHeight(RGB[][] pixel, int w, int h) {
		int height = 0;

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (pixel[x][y].getY() > height && pixel[x][y].isBlack()) {
					height = pixel[x][y].getY();
				}
			}
		}

		System.out.println("max height: " + height);

		return height;

	}

	private static int getCharacterMinX(RGB[][] pixel, int w, int h) {
		
		int c = 0; //zählt die weißen spalten
		for (int x=0; x<w; x++) {
			if (columnIsPartialBlack(pixel,h,x)) {
				break;
			}
			c++; 
		}
		return c;
	}

	private static int getCharacterWidth(RGB[][] pixel, int w, int h, int xStart) {
		
		int c = 0; //zählt die schwarzen spalten
		for (int x=xStart; x<w; x++) {
			if (!columnIsPartialBlack(pixel,h,x)) {
				break;
			}
			c++;
		}
		return c;
	}

	private static int getCharacterMinY(RGB[][] pixel, int w, int h, int xStart, int xEnd) {
		
		int c = 0; //zählt die weißen reihen
		for (int y=0; y<h; y++) {
			if (rowIsPartialBlack(pixel,xStart+xEnd,y)) {
				break;
			}
			c++;
		}
		return c;
	}
	
	/*
	 * zählt die schwarzen reihen von startY bis h
	 */
	private static int getCharacterHeight(RGB[][] pixel, int w, int h, int xStart, int xEnd, int yStart) {
		
		int c = 0; //zählt die schwarzen reihen
		for (int y=yStart; y<h; y++) {
			if (!rowIsPartialBlack(pixel,xStart+xEnd,y)) {
				break;
			}
			c++;
		}
		return c;
	}
	
	/*
	 * gibt an ob eine spalte einen schwarzen pixel enthält
	 */
	private static boolean columnIsPartialBlack(RGB[][] pixel, int h, int x) {
		for (int i=0; i<h; i++) {
			if (pixel[x][i].isBlack()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * gibt an ob eine reihe einen schwarzen pixel enthält
	 * @param pixel = RGB[][]
	 * @param w die breite in der gesucht werden soll (von 0 bis w)
	 * @param y die reihe in pixel
	 * @return
	 */
	private static boolean rowIsPartialBlack(RGB[][] pixel, int w, int y) {
		for (int i=0; i<w; i++) {
			if (pixel[i][y].isBlack()) {
				return true;
			}
		}
		return false;
	}

	

	public static BufferedImage getCharacter(BufferedImage oldbi, int offset) {
		
		BufferedImage bi = oldbi.getSubimage(offset, 0, oldbi.getWidth()-offset, oldbi.getHeight());
		
		RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];

		for (int y = 0; y < bi.getHeight(); y++) {
			for (int x = 0; x < bi.getWidth(); x++) {
				pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
			}
		}

		int minX = getCharacterMinX(pixel, bi.getWidth(), bi.getHeight());
		
		int width = getCharacterWidth(pixel, bi.getWidth(), bi.getHeight(), minX);
		
		int minY = getCharacterMinY(pixel, bi.getWidth(), bi.getHeight(), minX, width);
		
		int height = getCharacterHeight(pixel, bi.getWidth(), bi.getHeight(), minX, width, minY);
		
		System.out.println("minX: "+minX+" minY: "+minY+" width: "+width+" height: "+height);
		
		Main.setOffset(Main.getOffset()+minX+width);
		
		return bi.getSubimage(minX, minY, width, height);
		
	}

	public static void savePictureToFile(BufferedImage bi) {
		File outputfile = new File("test"+savePictureCounter+".png");
		try {
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		savePictureCounter++;
	}

	public static BufferedImage getScaledImage(Image srcImg, double targetHeight) {

		double scale = targetHeight / srcImg.getHeight(null);
		int w = (int) (srcImg.getWidth(null) * scale);
		int h = (int) (srcImg.getHeight(null) * scale);

		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

}
