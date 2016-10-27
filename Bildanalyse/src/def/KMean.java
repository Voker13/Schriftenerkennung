package def;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class KMean {
    public static int NUM_CLUSTERS;
    public static int TOTAL_DATA;

    public static ArrayList<Data> dataSet = new ArrayList<Data>();
    public static ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    public static ArrayList<RGB> pixels;

    public static void initialize(int k, ArrayList<RGB> pix) {
	NUM_CLUSTERS = k;
	TOTAL_DATA = pix.size();
	pixels = pix;
	// Setze 4 glechm‰ﬂig verteilte Punkte (nur auf 50x50)
	centroids.add(new Centroid(15,15));
	centroids.add(new Centroid(15,35));
	centroids.add(new Centroid(35,15));
	centroids.add(new Centroid(35,35));
    }
    
    public static ArrayList<Centroid> kMeanCluster(int k, BufferedImage bi) {
	
	RGB[][] pixel = new RGB[bi.getWidth()][bi.getHeight()];
	
	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		pixel[x][y] = new RGB(bi.getRaster().getPixel(x, y, new int[4]), x, y, bi.getWidth() * y + x);
	    }
	}
	
	ArrayList<RGB> dataPoints = new ArrayList<>();
	for (int y = 0; y < bi.getHeight(); y++) {
	    for (int x = 0; x < bi.getWidth(); x++) {
		if (pixel[x][y].isBlack()) {
		    dataPoints.add(pixel[x][y]);
		}
	    }
	}
	
	return kMeanCluster(k,dataPoints);
    }

    public static ArrayList<Centroid> kMeanCluster(int k, ArrayList<RGB> pix) {
	initialize(k,pix);
	
	final double bigNumber = Math.pow(10, 10);
	double minimum = bigNumber;
	double distance = 0.0;
	int sampleNumber = 0;
	int cluster = 0;
	boolean isStillMoving = true;
	Data newData = null;

	while (dataSet.size() < TOTAL_DATA) {
	    newData = new Data(pixels.get(sampleNumber).getX(), pixels.get(sampleNumber).getY());
	    dataSet.add(newData);
	    minimum = bigNumber;
	    for (int i = 0; i < NUM_CLUSTERS; i++) {
		distance = dist(newData, centroids.get(i));
		if (distance < minimum) {
		    minimum = distance;
		    cluster = i;
		}
	    }
	    newData.cluster(cluster);

	    for (int i = 0; i < NUM_CLUSTERS; i++) {
		int totalX = 0;
		int totalY = 0;
		int totalInCluster = 0;
		for (int j = 0; j < dataSet.size(); j++) {
		    if (dataSet.get(j).cluster() == i) {
			totalX += dataSet.get(j).X();
			totalY += dataSet.get(j).Y();
			totalInCluster++;
		    }
		}
		if (totalInCluster > 0) {
		    centroids.get(i).X(totalX / totalInCluster);
		    centroids.get(i).Y(totalY / totalInCluster);
		}
	    }
	    sampleNumber++;
	}

	while (isStillMoving) {
	    for (int i = 0; i < NUM_CLUSTERS; i++) {
		int totalX = 0;
		int totalY = 0;
		int totalInCluster = 0;
		for (int j = 0; j < dataSet.size(); j++) {
		    if (dataSet.get(j).cluster() == i) {
			totalX += dataSet.get(j).X();
			totalY += dataSet.get(j).Y();
			totalInCluster++;
		    }
		}
		if (totalInCluster > 0) {
		    centroids.get(i).X(totalX / totalInCluster);
		    centroids.get(i).Y(totalY / totalInCluster);
		}
	    }

	    isStillMoving = false;

	    for (int i = 0; i < dataSet.size(); i++) {
		Data tempData = dataSet.get(i);
		minimum = bigNumber;
		for (int j = 0; j < NUM_CLUSTERS; j++) {
		    distance = dist(tempData, centroids.get(j));
		    if (distance < minimum) {
			minimum = distance;
			cluster = j;
		    }
		}
		tempData.cluster(cluster);
		if (tempData.cluster() != cluster) {
		    tempData.cluster(cluster);
		    isStillMoving = true;
		}
	    }
	}
	return centroids;
    }

    private static double dist(Data d, Centroid c) {
	return Math.sqrt(Math.pow((c.Y() - d.Y()), 2) + Math.pow((c.X() - d.X()), 2));
    }

    static class Data {
	private double mX = 0;
	private double mY = 0;
	private int mCluster = 0;

	public Data() {
	    return;
	}

	public Data(double x, double y) {
	    this.X(x);
	    this.Y(y);
	    return;
	}

	public void X(double x) {
	    this.mX = x;
	    return;
	}

	public double X() {
	    return this.mX;
	}

	public void Y(double y) {
	    this.mY = y;
	    return;
	}

	public double Y() {
	    return this.mY;
	}

	public void cluster(int clusterNumber) {
	    this.mCluster = clusterNumber;
	    return;
	}

	public int cluster() {
	    return this.mCluster;
	}
    }
}