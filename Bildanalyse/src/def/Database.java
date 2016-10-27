package def;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Database {

    static int k = 4;

    static DatabaseEntry A;
    static DatabaseEntry B;
    static DatabaseEntry C;
    static DatabaseEntry H;
    static DatabaseEntry L;
    static DatabaseEntry O;
    static DatabaseEntry Q;

    static ArrayList<DatabaseEntry> database;

    public static void initialize() throws IOException {
	A = new DatabaseEntry("A", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	B = new DatabaseEntry("B", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	C = new DatabaseEntry("C", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	H = new DatabaseEntry("H", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	L = new DatabaseEntry("L", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	O = new DatabaseEntry("O", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	Q = new DatabaseEntry("Q", KMean.kMeanCluster(k, ImageIO.read(Main.class.getResourceAsStream("/res/H.png"))));
	
	database = new ArrayList<>(Arrays.asList(A, B, C, H, L, O, Q));
    }

    public static String getCharacter(ArrayList<Centroid> centroids) {
	double min = 10000;
	String string = "";
	for (int i = 0; i < database.size(); i++) {
	    if (distance(database.get(i).getCentroids(), centroids) < min) {
		min = distance(database.get(i).getCentroids(), centroids);
		string = database.get(i).getCharacter();
	    }
	}
	System.out.println("Distance: " + min);
	return string;
    }

    private static double distance(ArrayList<Centroid> centroids, ArrayList<Centroid> centroids2) {
	double distance = 0;
	for (int i = 0; i < centroids.size(); i++) {
	    Centroid centroid1 = centroids.get(i);
	    Centroid centroid2 = centroids2.get(i);
	    distance += Math.sqrt((centroid1.X() - centroid2.X()) * (centroid1.X() - centroid2.X()) + (centroid1.Y() - centroid2.Y()) * (centroid1.Y() - centroid2.Y()));
	}
	return distance;
    }

}
