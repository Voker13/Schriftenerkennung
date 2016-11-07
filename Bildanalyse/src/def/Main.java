package def;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

	private static int k = 2;
	private static double targetHeight = 50.0;
	private static double maxDistance = 20; //für kmean ->  if x < maxD then buchstabe irrelevant

	public static void main(String[] args) throws IOException {
		Database.initialize(k);
		
		ArrayList<String> finalChars = new ArrayList<String>();

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(Main.class.getResourceAsStream("/res/Hello_world.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int offset = 0;
		
		while (true) {
	
			BufferedImage character = Picture.getCharacter(bi,offset);
			
			System.out.println("(Width: " + character.getWidth() + "px|Height: " + character.getHeight() + "px) of unscaled character");
	
			BufferedImage newCharacter = Picture.getScaledImage(character, targetHeight);
	
			System.out.println("(Width: " + newCharacter.getWidth() + "px|Height: " + newCharacter.getHeight() + "px) of scaled character");
	
			RGB[][] pixel = new RGB[newCharacter.getWidth()][newCharacter.getHeight()];
	
			for (int y = 0; y < newCharacter.getHeight(); y++) {
				for (int x = 0; x < newCharacter.getWidth(); x++) {
					pixel[x][y] = new RGB(newCharacter.getRaster().getPixel(x, y, new int[4]), x, y,
							newCharacter.getWidth() * y + x);
				}
			}
	
			ArrayList<RGB> dataPoints = new ArrayList<>();
			for (int y = 0; y < newCharacter.getHeight(); y++) {
				for (int x = 0; x < newCharacter.getWidth(); x++) {
					if (pixel[x][y].isBlack()) {
						dataPoints.add(pixel[x][y]);
					}
				}
			}
	
			ArrayList<Centroid> centroids = KMean.kMeanCluster(k, dataPoints, newCharacter.getWidth(), newCharacter.getHeight());
	
			Character ergCharacter = new Character(Database.getCharacter(centroids));
			
			// prüfen ob ein buchstabe gefunden wurde
			if (ergCharacter.getDistance() > maxDistance) { 
				break; // kein weiterer buchstabe in zeile
			} else {
				finalChars.add(ergCharacter.getString()); // neuer buchstabe in zeile in ergebnisliste addn
				offset += character.getWidth();
				System.out.println("string: "+ergCharacter.getString());
				System.out.println("offset: "+offset);
			}
			
		}
		
		System.out.println("");
		System.out.println("*** RESULT ***");
		for (int i=0; i<finalChars.size(); i++) {
			System.err.print(finalChars.get(i));
		}
		
	}

}
