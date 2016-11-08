package def;

public class Character {
	/*
	 * accuracy of kmeans algorithm
	 */
	private double distance;
	
	/*
	 * name (string)
	 */
	private String string;
	
	public Character (double distance, String string) {
		this.distance = distance;
		this.string = string;
	}
	
	public Character (Character c) {
		this.distance = c.distance;
		this.string = c.string;
	}
	
	public String toString() {
		return "String: "+this.string+" / Distance: "+this.distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getString() {
		return string;
	}

	public void setString(String s) {
		this.string = s;
	}
}
