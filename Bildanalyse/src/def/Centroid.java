package def;
public class Centroid {

    private double mX = 0.0;
    private double mY = 0.0;

    public Centroid() {
	return;
    }

    public Centroid(double newX, double newY) {
	this.mX = newX;
	this.mY = newY;
	return;
    }
    
    public String toString() {
    	return "("+mX + "," + mY + ")";
    }

    public void X(double newX) {
	this.mX = newX;
	return;
    }

    public double X() {
	return this.mX;
    }

    public void Y(double newY) {
	this.mY = newY;
	return;
    }

    public double Y() {
	return this.mY;
    }

}
