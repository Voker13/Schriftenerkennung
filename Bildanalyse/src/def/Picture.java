package def;

import java.awt.image.BufferedImage;

public class Picture {

    private static int getRowHeight(BufferedImage bi) {
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

    private static int getCharacterStartX(RGB[][] pixel, int w, int h) {
	int width = 10000000;

	for (int y = 0; y < h; y++) {
	    for (int x = 0; x < w; x++) {
		if (pixel[x][y].getX() < width && pixel[x][y].isBlack()) {
		    width = pixel[x][y].getX();
		}
	    }
	}

	return width;
    }

    private static int getCharacterEndX(RGB[][] pixel, int w, int h, int i) {
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

    private static int getCharacterStartYinX(RGB[][] pixel, int w, int h, int xFrom, int xTo) {
	int yStart = 1000000;

	for (int y = 0; y < h; y++) {
	    for (int x = xFrom; x < xTo; x++) {
		if (pixel[x][y].getY() < yStart && pixel[x][y].isBlack()) {
		    yStart = pixel[x][y].getY();
		}
	    }
	}

	return yStart;
    }

    private static int getCharacterEndYinX(RGB[][] pixel, int w, int h, int xFrom, int xTo) {
	int yStart = 0;

	for (int y = 0; y < h; y++) {
	    for (int x = xFrom; x < xTo; x++) {
		if (pixel[x][y].getY() > yStart && pixel[x][y].isBlack()) {
		    yStart = pixel[x][y].getY();
		}
	    }
	}

	return yStart;
    }

    public static BufferedImage getCharacter(BufferedImage bi) {
	RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];

	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
	    }
	}

	int xStart = getCharacterStartX(pixel, bi.getWidth(), bi.getHeight());
	int xEnd = getCharacterEndX(pixel, bi.getWidth(), bi.getHeight(), xStart);
	int yStart = getCharacterStartYinX(pixel, bi.getWidth(), bi.getHeight(), xStart, xEnd);
	int yEnd = getCharacterEndYinX(pixel, bi.getWidth(), bi.getHeight(), xStart, xEnd);

	return bi.getSubimage(xStart, yStart, xEnd - xStart, yEnd - yStart);
    }

}
