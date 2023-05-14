package pane;

import javafx.scene.layout.VBox;

public class RootPane extends VBox {
	private static TopGamePane topGamePane;
	private static GolfCourse golfCourse ;

	public RootPane(GolfCourse golfcourse) {
		topGamePane = new TopGamePane();
		this.golfCourse = golfcourse ;
		this.getChildren().add(topGamePane);
		this.getChildren().add(golfCourse);
	}
}
