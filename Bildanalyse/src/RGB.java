
public class RGB {
    
    private int red;
    private int green;
    private int blue;
    private int x;
    private int y;
    private int absolutePosition;

    public RGB() {
	
    }
    
    public RGB(int red, int green, int blue) {
	this.red = red;
	this.green = green;
	this.blue = blue;
    }
    
    public RGB(int[] rgb, int x, int y, int absolutePosition) {
	this.red = rgb[0];
	this.green = rgb[1];
	this.blue = rgb[2];
	this.x = x;
	this.y = y;
	this.absolutePosition = absolutePosition;
    }
    
    public boolean isBlack() {
	if (red < 50 && green < 50 && blue < 50) {
	    return true;
	}
	else return false;
    }
    
    public String toString() {
	return "Red: " + red + " Green: " + green + " Blue: " + blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAbsolutePosition() {
        return absolutePosition;
    }

    public void setAbsolutePosition(int absolutePosition) {
        this.absolutePosition = absolutePosition;
    }
    
    

}
