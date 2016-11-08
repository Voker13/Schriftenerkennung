package def;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

	private static int k = 2;
	private static double targetHeight = 50.0;
	private static double maxDistance = 20; //für kmean ->  if x < maxD then buchstabe irrelevant
	private static double minWidthOfImage = 5; //abbruchbedingung wenn das Image zu schmal wird (img.width < minWidth)
	private static double leerzeichenOffset = 20; // in px

	public static void main(String[] args) throws IOException {
		Database.initialize(k);
		
		ArrayList<String> finalChars = new ArrayList<String>();

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(Main.class.getResourceAsStream("/res/Hello.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int offset = 0;
		
		while (true) {
	
			BufferedImage character = Picture.getCharacter(bi,offset);
			
			BufferedImage scaledCharacter = Picture.getScaledImage(character, targetHeight);
	
			RGB[][] pixel = new RGB[scaledCharacter.getWidth()][scaledCharacter.getHeight()];
	
			for (int y = 0; y < scaledCharacter.getHeight(); y++) {
				for (int x = 0; x < scaledCharacter.getWidth(); x++) {
					pixel[x][y] = new RGB(scaledCharacter.getRaster().getPixel(x, y, new int[4]), x, y,
							scaledCharacter.getWidth() * y + x);
				}
			}
	
			ArrayList<RGB> dataPoints = new ArrayList<>();
			for (int y = 0; y < scaledCharacter.getHeight(); y++) {
				for (int x = 0; x < scaledCharacter.getWidth(); x++) {
					if (pixel[x][y].isBlack()) {
						dataPoints.add(pixel[x][y]);
					}
				}
			}
	
			ArrayList<Centroid> centroids = KMean.kMeanCluster(k, dataPoints, scaledCharacter.getWidth(), scaledCharacter.getHeight());
	
			Character finalCharacter = new Character(Database.getCharacter(centroids));
			System.out.println(finalCharacter.toString());
			
			// prüfen ob ein buchstabe gefunden wurde
			if (scaledCharacter.getWidth() < minWidthOfImage) { // Zeile ist zuende
				
				System.out.println("--> break (w="+scaledCharacter.getWidth()+"px) (offset="+offset+"px)");
				break;
				
			} else if (finalCharacter.getDistance() > maxDistance) { // Leerzeichen gefunden
				
				System.out.println("--> Leerzeichen");
				finalChars.add(" ");
				offset+=leerzeichenOffset;
				
			} else {
				
				System.out.println("--> Character");
				finalChars.add(finalCharacter.getString()); // neuer buchstabe in zeile in ergebnisliste addn
				offset += character.getWidth();
				
			}
			System.out.println(" ");
			
		}
		
		System.out.println("");
		System.out.println("*** RESULT ***");
		for (int i=0; i<finalChars.size(); i++) {
			System.err.print(finalChars.get(i));
		}
		
	}

}
