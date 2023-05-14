package pane;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BackButton extends StackPane {
	public BackButton() {
		Circle c1 = new Circle(20, Color.BLACK);
		Text t1 = new Text("<");
		t1.setFill(Color.WHITE);
		this.setOnMouseClicked(event -> {
			//go back to welcome page
		});
		this.getChildren().addAll(c1, t1);
	}
}
