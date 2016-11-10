package def;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

	private static int k = 2; 
	private static double maxDistance = 30; //für kmean ->  if x < maxD then buchstabe irrelevant
	
	private static double targetHeight = 50.0;
	private static double minWidthOfImage = 9; //abbruchbedingung wenn das Image zu schmal wird (img.width < minWidth)
	public static double leerzeichenOffset = 10; // in px
	private static int offset;

	public static void main(String[] args) throws IOException {
		Database.initialize(k);
		
		ArrayList<String> finalChars = new ArrayList<String>();

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(Main.class.getResourceAsStream("/res/hi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		offset = 0;
		
		while (true) {
			
			System.out.println("offset: "+offset);
			
			if (bi.getWidth() - offset <= minWidthOfImage) { // Zeile ist zuende
				System.out.println("--> break (w="+bi.getWidth()+"px) (offset="+offset+"px)");
				break;
			}
			
			BufferedImage character = Picture.getCharacter(bi,offset);
			
			Picture.savePictureToFile(character);
		
			BufferedImage scaledCharacter = Picture.getScaledImage(character, targetHeight);
			
			Picture.savePictureToFile(scaledCharacter);
			
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
			if (finalCharacter.getDistance() < maxDistance) { // Buchstaben gefunden
				
				System.out.println("--> Character");
				finalChars.add(finalCharacter.getString());
				
			} else { // nichts gefunden
				
				System.out.println("--> nothing found");
				finalChars.add("?");
				
			}
//			offset+= character.getWidth(); //-minX WIRD ZURZEIT IN GETCHARACTER ÜBER GETTER UND SETTER NEU GESETZT
			System.out.println(" ");
			
		}
		
		System.out.println("");
		System.out.println("*** RESULT ***");
		for (int i=0; i<finalChars.size(); i++) {
			System.err.print(finalChars.get(i));
		}
		
	}

	public static int getOffset() {
		return offset;
	}

	public static void setOffset(int offset) {
		Main.offset = offset;
	}

}
