package def;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

    private static int k = 2;
    private static double targetHeight = 50.0;

    public static void main(String[] args) throws IOException {
	Database.initialize(k);

	BufferedImage bi = null;
	try {
	    bi = ImageIO.read(Main.class.getResourceAsStream("/res/Hello_world.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	BufferedImage character = Picture.getCharacter(bi);

	
	double scale = 1 / (character.getHeight() / targetHeight);
	System.out.println(scale);
	int w = (int) (character.getWidth() * scale);
	int h = (int) (character.getHeight() * scale);
	
	character = Picture.getScaledImage(character, w, h);
	
	System.out.println("(Width: " + character.getWidth() + "px|Height: " + character.getHeight() + "px) of scaled character");

	RGB[][] pixel = new RGB[character.getWidth()][character.getHeight()];

	for (int y = 0; y < character.getHeight(); y++) {
	    for (int x = 0; x < character.getWidth(); x++) {
		pixel[x][y] = new RGB(character.getRaster().getPixel(x, y, new int[4]), x, y, character.getWidth() * y + x);
	    }
	}

	ArrayList<RGB> dataPoints = new ArrayList<>();
	for (int y = 0; y < character.getHeight(); y++) {
	    for (int x = 0; x < character.getWidth(); x++) {
		if (pixel[x][y].isBlack()) {
		    dataPoints.add(pixel[x][y]);
		}
	    }
	}

	ArrayList<Centroid> centroids = KMean.kMeanCluster(k, dataPoints, character.getWidth(), character.getHeight());

	System.out.println(Database.getCharacter(centroids));
    }

}
