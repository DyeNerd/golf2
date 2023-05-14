package component;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import logic.CollidableEntity;

public class GolfBall extends CollidableEntity {
	private final int radius = 10;
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
//		return Math.sqrt(Math.pow(x - InputUtility.mousePosX, 2) + Math.pow(y - InputUtility.mousePosY, 2));
		double dx = x - InputUtility.mousePosX;
		double dy = y - InputUtility.mousePosY;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double speed = distance / 10.0; // Adjust this value as necessary
	    return Math.min(speed, 10.0); // Limit the speed to a maximum of 10.0 units per frame
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
//			gc.setStroke(Color.RED);
//			gc.setLineWidth(2.0);
//			gc.strokeLine(x, y, Math.max(0, 2 * x - InputUtility.mousePosX),
			double dx = x - InputUtility.mousePosX;
			double dy = y - InputUtility.mousePosY;
			double distance = Math.sqrt(dx * dx + dy * dy); // Calculate the distance between the two points
			double arrowLength = Math.min(distance, 200); // Set arrowLength to the distance, but no more than 20.0
			double arrowWidth = 20;
			// Calculate the angle between the line and the x-axis
			double angle = Math.atan2(dy, dx);

			// Calculate the coordinates of the arrowhead
			double arrowEndX = x + arrowLength * Math.cos(angle);
			double arrowEndY = y + arrowLength * Math.sin(angle);
			double arrowTip1X = arrowEndX + arrowWidth * Math.cos(angle + Math.toRadians(135));
			double arrowTip1Y = arrowEndY + arrowWidth * Math.sin(angle + Math.toRadians(135));
			double arrowTip2X = arrowEndX + arrowWidth * Math.cos(angle - Math.toRadians(135));
			double arrowTip2Y = arrowEndY + arrowWidth * Math.sin(angle - Math.toRadians(135));

			// Draw the line with an arrowhead at the end
			gc.setStroke(Color.RED);
			gc.setLineWidth(4.0);
			gc.strokeLine(x, y, arrowEndX, arrowEndY);
			gc.strokeLine(arrowEndX, arrowEndY, arrowTip1X, arrowTip1Y);
			gc.strokeLine(arrowEndX, arrowEndY, arrowTip2X, arrowTip2Y);
		}

	}

}
