package component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Hole extends Circle {
	private int x;
	private int y;
	private final int radius = 10;
	public Hole(int x, int y) {
		this.setRadius(radius);
		this.setFill(Color.BLACK);
		this.setX(x);
		this.setY(y);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
