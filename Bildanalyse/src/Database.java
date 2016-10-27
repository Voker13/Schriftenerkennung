import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    
    static int k = 4;

    static DatabaseEntry A = new DatabaseEntry("A", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\A.png")));
    static DatabaseEntry B = new DatabaseEntry("B", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\B.png")));
    static DatabaseEntry C = new DatabaseEntry("C", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\C.png")));
    static DatabaseEntry H = new DatabaseEntry("H", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\H.png")));
    static DatabaseEntry L = new DatabaseEntry("L", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\L.png")));
    static DatabaseEntry O = new DatabaseEntry("O", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\O.png")));
    static DatabaseEntry Q = new DatabaseEntry("Q", KMean.kMeanCluster(k, new File("C:\\Users\\Tobias\\OneDrive\\Dokumente\\Q.png")));

    static ArrayList<DatabaseEntry> database = new ArrayList<>(Arrays.asList(A, B, C, H, L, O, Q));

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
