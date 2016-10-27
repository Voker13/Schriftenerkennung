package def;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {
	Database.initialize();
	
	BufferedImage bi = null;
	try {
	    bi = ImageIO.read(Main.class.getResourceAsStream("/res/H.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];

	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
	    }
	}
	// Get subimage with perfect height
	int start = Picture.getMinHeight(pixel, bi.getWidth(), bi.getHeight());
	bi = bi.getSubimage(0, start, bi.getWidth(), Picture.getRowHeight(bi));

	// rescale pixel-array
	pixel = new RGB[bi.getWidth()][bi.getHeight()];
	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
	    }
	}
	RGB[][] firstCharacter = Picture.getCharacter(pixel, bi.getWidth(), bi.getHeight());
	
	ArrayList<RGB> dataPoints = new ArrayList<>();
	for (int y = 0; y < firstCharacter[0].length; y++) {
	    for (int x = 0; x < firstCharacter.length; x++) {
		if (pixel[x][y].isBlack()) {
		    dataPoints.add(firstCharacter[x][y]);
		}
	    }
	}
	
	int k = 4;

	KMean.kMeanCluster(k, dataPoints);

	System.out.println(Database.getCharacter(KMean.centroids));

	/*
	dataPoints = new ArrayList<>();
	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		if (pixel[x][y].isBlack()) {
		    dataPoints.add(pixel[x][y]);
		}
	    }
	}

	
	System.out.println("Zeilenhöhe: " + Picture.getRowHeight(bi));

	System.out.println(k + " " + numberItems);

	KMean.kMeanCluster(k, dataPoints);

	System.out.println(Database.getCharacter(KMean.centroids));
	*/

	// Print out centroid results.
	System.out.println("Centroids finalized at:");
	for (int i = 0; i < KMean.NUM_CLUSTERS; i++) {
	    System.out.println("     (" + KMean.centroids.get(i).X() + ", " + KMean.centroids.get(i).Y() + ")");
	}
	System.out.print("\n");

    }

}
