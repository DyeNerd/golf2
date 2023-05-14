package component;

public abstract class Obstacle {
	private int bounciness = 0;
	private int x;
	private int y;
	public Obstacle(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public abstract int bounce();
	
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
	
	public int getBounciness() {
		return this.bounciness;
	}
}
