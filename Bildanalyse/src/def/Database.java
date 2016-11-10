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
    static DatabaseEntry d;
    static DatabaseEntry e;
    static DatabaseEntry H;
    static DatabaseEntry i;
    static DatabaseEntry l;
    static DatabaseEntry o;
    static DatabaseEntry r;
    static DatabaseEntry W;
    static DatabaseEntry exc;
    static DatabaseEntry leer;
    

    static ArrayList<DatabaseEntry> database;

    public static void initialize(int clusterNumber) throws IOException {
	k = clusterNumber;
	
	A = new DatabaseEntry("A", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/A.png"))));
	B = new DatabaseEntry("B", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/B.png"))));
	C = new DatabaseEntry("C", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/C.png"))));
	d = new DatabaseEntry("d", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/d.png"))));
	e = new DatabaseEntry("e", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/e.png"))));
	H = new DatabaseEntry("H", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/H.png"))));
	i = new DatabaseEntry("i", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/i.png"))));
	l = new DatabaseEntry("l", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/l.png"))));
	o = new DatabaseEntry("o", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/o.png"))));
	r = new DatabaseEntry("r", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/r.png"))));
	W = new DatabaseEntry("W", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/W.png"))));
	exc = new DatabaseEntry("!", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/exclamation_mark.png"))));
	leer = new DatabaseEntry("_", KMean.kMeanCluster(k, ImageIO.read(Database.class.getResourceAsStream("/res/leer.png"))));
	
	database = new ArrayList<>(Arrays.asList(A, B, C, d, e, H, i, l, o, r, W,exc,leer));
//	database = new ArrayList<>(Arrays.asList(H));
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
	return new Character(min, string);
    }

    private static double distance(ArrayList<Centroid> centroids, ArrayList<Centroid> centroids2) {
	double distance = 0;
	for (int i = 0; i < centroids.size(); i++) {
	    Centroid centroid1 = centroids.get(i);
	    Centroid centroid2 = centroids2.get(i);
	    double dist =  Math.sqrt((centroid1.X() - centroid2.X()) * (centroid1.X() - centroid2.X()) + (centroid1.Y() - centroid2.Y()) * (centroid1.Y() - centroid2.Y()));
	    if (dist > distance) {
		distance = dist;
	    }
	}
	return distance;
    }

}
