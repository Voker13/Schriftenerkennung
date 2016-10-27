package def;

import java.awt.image.BufferedImage;

public class Picture {

    public static int getRowHeight(BufferedImage bi) {
	int height = 0;

	RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];

	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
	    }
	}

	height = getMaxHeight(pixel, bi.getWidth(), bi.getHeight()) - getMinHeight(pixel, bi.getWidth(), bi.getHeight());

	return height;
    }

    public static int getMinHeight(RGB[][] pixel, int w, int h) {
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

    public static int getMaxHeight(RGB[][] pixel, int w, int h) {
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

    public static int getCharacterStartX(RGB[][] pixel, int w, int h) {
	int width = 1000000;

	for (int y = 0; y < h; y++) {
	    for (int x = 0; x < w; x++) {
		if (pixel[x][y].getX() < width && pixel[x][y].isBlack()) {
		    width = pixel[x][y].getX();
		}
	    }
	}

	return width;
    }

    public static int getCharacterEndX(RGB[][] pixel, int w, int h, int i) {
	int width = 0;

	for (int x = 0; x < w; x++) {
	    boolean black = false;
	    for (int y = 0; y < h; y++) {
		if (pixel[x + i][y].isBlack()) {
		    black = true;
		    break;
		}
	    }
	    if (!black) {
		return width + i;
	    } else {
		width++;
	    }
	}

	return width + i;
    }
    
    public static int getCharacterStartY() {
	return 0;
    }

    public static RGB[][] getCharacterPixels(RGB[][] pixel, int w, int h) {
	int firstPixelAt = getCharacterStartX(pixel, w, h);
	System.out.println("First pixel at: " + firstPixelAt);

	int lastPixelAt = getCharacterEndX(pixel, w, h, firstPixelAt);
	System.out.println("Last pixel at: " + lastPixelAt);

	RGB[][] character = new RGB[lastPixelAt - firstPixelAt][h];

	for (int y = 0; y < h; y++) {
	    for (int x = 0; x < lastPixelAt - firstPixelAt; x++) {
		character[x][y] = pixel[x + firstPixelAt][y];
	    }
	}

	return character;
    }

    public static BufferedImage getCharacterImage(BufferedImage bi, RGB[][] pixel) {
	int x_offset = getCharacterStartX(pixel, bi.getWidth(), bi.getHeight());
	int width = getCharacterEndX(pixel, bi.getWidth(), bi.getHeight(), x_offset);
	int y_offset = 0;
	int height = 0;

	return bi.getSubimage(x_offset, y_offset, width, height);
    }

}
