package pane;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.input.KeyEvent;

public class GolfCourse extends Canvas {
	public GolfCourse() {
		super(800, 565);
		this.setVisible(true);
		addListerner();
	}

//	public void addListerner() {
//		System.out.println("pls work");
//		this.setOnKeyPressed((KeyEvent event) ->{
//			System.out.println("asdad");
//			InputUtility.setKeyPressed(event.getCode(), true);
//		});
//	
//		this.setOnKeyReleased((KeyEvent event) -> {
//			InputUtility.setKeyPressed(event.getCode(), false);
//		});
//
//		this.setOnMousePressed((MouseEvent event) -> {
//			if (event.getButton() == MouseButton.PRIMARY)
//				InputUtility.mouseLeftDown();
//		});
//
//		this.setOnMouseReleased((MouseEvent event) -> {
//			if (event.getButton() == MouseButton.PRIMARY)
//				InputUtility.mouseLeftRelease();
//		});
//
//		this.setOnMouseEntered((MouseEvent event) -> {
//			InputUtility.mouseOnScreen = true;
//		});
//
//		this.setOnMouseExited((MouseEvent event) -> {
//			InputUtility.mouseOnScreen = false;
//		});
//
//		this.setOnMouseMoved((MouseEvent event) -> {
//			if (InputUtility.mouseOnScreen) {
//				InputUtility.mouseX = event.getX();
//				InputUtility.mouseY = event.getY();
//			}
//		});
//
//		this.setOnMouseDragged((MouseEvent event) -> {
//			if (InputUtility.mouseOnScreen) {
//				InputUtility.mouseX = event.getX();
//				InputUtility.mouseY = event.getY();
//			}
//		});
//		
//		this.setOnDragDetected((MouseEvent event)->{
//			if(InputUtility.mouseOnScreen) {				
//				System.out.println("Mouse Draging");
//				InputUtility.isDrag = true ; 
//				InputUtility.mouseX = event.getX();
//				InputUtility.mouseY = event.getY();
//				InputUtility.mousePosX = event.getX();
//				InputUtility.mousePosY = event.getY();
//				System.out.println(event.getX()+" " + event.getY());
//			}
//		});

//	}

	public void addListerner() {
		System.out.println("detect");
		this.setOnKeyPressed((KeyEvent event) -> {
			System.out.println("asdawdad");
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				InputUtility.isDrag = true;
				InputUtility.mouseLeftDown();
				InputUtility.mouseRelease = false ; 
			}
			System.out.println("Pressed " + InputUtility.isDrag);
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				InputUtility.mouseRelease = true ; 
				InputUtility.mouseLeftRelease();
				InputUtility.isDrag = false;
			}
			System.out.println("Released " + InputUtility.isDrag);
//			System.out.println(InputUtility.mousePosX + " " + InputUtility.mousePosY);

		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
//				System.out.println("dragging");
				InputUtility.mouseY = event.getY();
				InputUtility.mousePosX = event.getX();
				InputUtility.mousePosY = event.getY();
				InputUtility.isDrag = true;
				System.out.println(InputUtility.isDrag);
			}
		});
	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}
