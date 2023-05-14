package pane;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class WelcomePage extends VBox {
    private Button startButton;
    private Button exitButton;
    
    public WelcomePage(Stage stage) {
        // Create a label to display the title of the game
        Label titleLabel = new Label("My Mini Golf Game");
        titleLabel.setStyle("-fx-font-size: 36px;");
        
        // Create a button to start the game
        startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 18px;");
        
        // Create a button to exit the game
        exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-size: 18px;");
        
        // Add event handlers to the buttons
        startButton.setOnAction(event -> {
            // Handle the start button click event
            // (e.g. start the game)
        	GameLogic logic = new GameLogic();
        	GolfCourse golfCourse = new GolfCourse();
        	RootPane rootPane = new RootPane(golfCourse);
        	Scene scene = new Scene(rootPane, 800, 640);
        	stage.setScene(scene);
    		stage.setTitle("MiniGolf");
        	
        	stage.setResizable(false);
        	
        	golfCourse.requestFocus();
        	stage.show();
        	
        	AnimationTimer animation = new AnimationTimer() {
    		public void handle(long now) {
				golfCourse.paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
    		}
    	};
    	animation.start();
        	
        });
        exitButton.setOnAction(event -> {
        	
        });
        
        // Add the components to the pane
        setSpacing(20);
        setPadding(new Insets(40));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, startButton, exitButton);
    }
    
    // Getter methods for the start and exit buttons
    
    public Button getStartButton() {
        return startButton;
    }
    
    public Button getExitButton() {
        return exitButton;
    }
}