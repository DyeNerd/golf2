package component;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import logic.CollidableEntity;

public class GolfBall extends CollidableEntity {
	private final int radius = 20;
	private final double maxSpeed = 10;
	private int powerPercent;
	private double x, y, speed ,angle;
	private final double speedDecayRate = 0.25 ;

	public GolfBall(double x, double y) {
		this.setPowerPercent(0);
		this.setSpeed(0);
		this.setX(x);
		this.setY(y);
	}

	public void move() {
		this.x += Math.cos(angle) * speed;
		this.y -= Math.sin(angle) * speed;
	}

	public double calculatePower() {
		return Math.sqrt(Math.pow(x - InputUtility.mousePosX, 2) + Math.pow(y - InputUtility.mousePosY, 2));
	}

	public void update() {
		if (InputUtility.mouseRelease && this.getSpeed() == 0) {
			setSpeed(Math.min(maxSpeed,calculatePower()));
			InputUtility.mouseRelease = false ;
			angle = calculateAngle(); 
			System.out.println(angle);
//			System.out.println(this.getSpeed());
		}
		setSpeed(getSpeed()-speedDecayRate);
		move();
//		System.out.println(getSpeed());
	}

	public double calculateAngle() {
		double dx = this.x - InputUtility.mousePosX;
		double dy = -this.y + InputUtility.mousePosY;
		System.out.println(""+ dx + " " + dy + " "+ Math.atan2(dy	, dx) );
		return Math.atan2(dy, dx);
	}

	public int getPowerPercent() {
		return powerPercent;
	}

	public void setPowerPercent(int powerPercent) {
		this.powerPercent = powerPercent;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return this.x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return this.y;
	}

	public void setSpeed(double speed) {
		if (speed <= 0) {
			speed = 0;
		}
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLUE);
		gc.fillArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360, ArcType.OPEN);
		gc.translate(x, y);
		gc.translate(-x, -y);

		if (speed == 0 && InputUtility.isDrag) {
			gc.setStroke(Color.RED);
			gc.setLineWidth(2.0);
			gc.strokeLine(x, y, Math.max(0, 2 * x - InputUtility.mousePosX),
					Math.max(0, 2 * y - InputUtility.mousePosY));
			calculateAngle();
		}

	}

}
