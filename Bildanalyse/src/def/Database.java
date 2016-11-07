package def;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Database {

    static int k;

    static DatabaseEntry A;
    static DatabaseEntry B;
    static DatabaseEntry C;
    static DatabaseEntry H;
    static DatabaseEntry i;
    

    static ArrayList<DatabaseEntry> database;

    public static void initialize(int clusterNumber) throws IOException {
	k = clusterNumber;
	
	A = new DatabaseEntry("A", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/A.png"))));
	B = new DatabaseEntry("B", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/B.png"))));
	C = new DatabaseEntry("C", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/C.png"))));
	H = new DatabaseEntry("H", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/H.png"))));
	i = new DatabaseEntry("i", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/i.png"))));
	
	database = new ArrayList<>(Arrays.asList(A, B, C, H, i));
    }

    public static Character getCharacter(ArrayList<Centroid> centroids) {
	double min = 10000;
	String string = "";
	for (int i = 0; i < database.size(); i++) {
	    if (distance(database.get(i).getCentroids(), centroids) < min) {
		min = distance(database.get(i).getCentroids(), centroids);
		string = database.get(i).getCharacter();
	    }
	}
	System.out.println("Distance: " + min);
	return new Character(min, string);
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
