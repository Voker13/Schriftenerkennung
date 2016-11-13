package def;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Database {

    static int kNum;

    static DatabaseEntry exc;
    
    static DatabaseEntry a;
    static DatabaseEntry b;
    static DatabaseEntry c;
    static DatabaseEntry d;
    static DatabaseEntry e;
    static DatabaseEntry f;
    static DatabaseEntry g;
    static DatabaseEntry h;
    static DatabaseEntry i;
    static DatabaseEntry j;
    static DatabaseEntry k;
    static DatabaseEntry l;
    static DatabaseEntry m;
    static DatabaseEntry n;
    static DatabaseEntry o;
    static DatabaseEntry p;
    static DatabaseEntry q;
    static DatabaseEntry r;
    static DatabaseEntry s;
    static DatabaseEntry t;
    static DatabaseEntry u;
    static DatabaseEntry v;
    static DatabaseEntry w;
    static DatabaseEntry x;
    static DatabaseEntry y;
    static DatabaseEntry z;
    
    static DatabaseEntry A;
    static DatabaseEntry B;
    static DatabaseEntry C;
    static DatabaseEntry D;
    static DatabaseEntry E;
    static DatabaseEntry F;
    static DatabaseEntry G;
    static DatabaseEntry H;
    static DatabaseEntry I;
    static DatabaseEntry J;
    static DatabaseEntry K;
    static DatabaseEntry L;
    static DatabaseEntry M;
    static DatabaseEntry N;
    static DatabaseEntry O;
    static DatabaseEntry P;
    static DatabaseEntry Q;
    static DatabaseEntry R;
    static DatabaseEntry S;
    static DatabaseEntry T;
    static DatabaseEntry U;
    static DatabaseEntry V;
    static DatabaseEntry W;
    static DatabaseEntry X;
    static DatabaseEntry Y;
    static DatabaseEntry Z;
    

    static ArrayList<DatabaseEntry> database;

    public static void initialize(int clusterNumber) throws IOException {
	kNum = clusterNumber;
	
	a = new DatabaseEntry("a", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/aLow.png"))));
	b = new DatabaseEntry("b", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/bLow.png"))));
	c = new DatabaseEntry("c", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/cLow.png"))));
	d = new DatabaseEntry("d", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/dLow.png"))));
	e = new DatabaseEntry("e", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/eLow.png"))));
	f = new DatabaseEntry("f", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/fLow.png"))));
	g = new DatabaseEntry("g", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/gLow.png"))));
	h = new DatabaseEntry("h", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/hLow.png"))));
	i = new DatabaseEntry("i", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/iLow.png"))));
	j = new DatabaseEntry("j", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/jLow.png"))));
	k = new DatabaseEntry("k", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/kLow.png"))));
	l = new DatabaseEntry("l", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/lLow.png"))));
	m = new DatabaseEntry("m", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/mLow.png"))));
	n = new DatabaseEntry("n", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/nLow.png"))));
	o = new DatabaseEntry("o", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/oLow.png"))));
	p = new DatabaseEntry("p", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/pLow.png"))));
	q = new DatabaseEntry("q", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/qLow.png"))));
	r = new DatabaseEntry("r", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/rLow.png"))));
	s = new DatabaseEntry("s", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/sLow.png"))));
	t = new DatabaseEntry("t", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/tLow.png"))));
	u = new DatabaseEntry("u", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/uLow.png"))));
	v = new DatabaseEntry("v", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/vLow.png"))));
	w = new DatabaseEntry("w", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/wLow.png"))));
	x = new DatabaseEntry("x", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/xLow.png"))));
	y = new DatabaseEntry("y", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/yLow.png"))));
	z = new DatabaseEntry("z", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/zLow.png"))));
	
	A = new DatabaseEntry("A", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/A.png"))));
	B = new DatabaseEntry("B", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/B.png"))));
	C = new DatabaseEntry("C", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/C.png"))));
	D = new DatabaseEntry("D", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/D.png"))));
	E = new DatabaseEntry("E", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/E.png"))));
	F = new DatabaseEntry("F", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/F.png"))));
	G = new DatabaseEntry("G", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/G.png"))));
	H = new DatabaseEntry("H", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/H.png"))));
	I = new DatabaseEntry("I", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/I.png"))));
	J = new DatabaseEntry("J", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/J.png"))));
	K = new DatabaseEntry("K", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/K.png"))));
	L = new DatabaseEntry("L", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/L.png"))));
	M = new DatabaseEntry("M", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/M.png"))));
	N = new DatabaseEntry("N", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/N.png"))));
	O = new DatabaseEntry("O", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/O.png"))));
	P = new DatabaseEntry("P", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/P.png"))));
	Q = new DatabaseEntry("Q", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/Q.png"))));
	R = new DatabaseEntry("R", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/R.png"))));
	S = new DatabaseEntry("S", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/S.png"))));
	T = new DatabaseEntry("T", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/T.png"))));
	U = new DatabaseEntry("U", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/U.png"))));
	V = new DatabaseEntry("V", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/V.png"))));
	W = new DatabaseEntry("W", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/W.png"))));
	X = new DatabaseEntry("X", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/X.png"))));
	Y = new DatabaseEntry("Y", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/Y.png"))));
	Z = new DatabaseEntry("Z", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/Z.png"))));
	
	exc = new DatabaseEntry("!", KMean.kMeanCluster(kNum, ImageIO.read(Database.class.getResourceAsStream("/res/exclamation_mark.png"))));
	
	database = new ArrayList<>(Arrays.asList( A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, exc));
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
