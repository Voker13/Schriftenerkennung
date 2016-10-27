import java.util.ArrayList;


public class DatabaseEntry {
    
    private String character;
    private ArrayList<Centroid> centroids;
    
    public DatabaseEntry() {
	
    }
    
    public DatabaseEntry(String character, ArrayList<Centroid> centroids) {
	this.character = character;
	this.centroids = centroids;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public ArrayList<Centroid> getCentroids() {
        return centroids;
    }

    public void setCentroids(ArrayList<Centroid> centroids) {
        this.centroids = centroids;
    }
    
    

}
