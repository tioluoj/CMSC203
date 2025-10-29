package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create a BorderPane layout
            BorderPane root = new BorderPane();

            // Adding a sample label to the center of the BorderPane
            Label label = new Label("Welcome to Property Management System");
            root.setCenter(label);

            // Set up the scene with BorderPane as root and apply the CSS
            Scene scene = new Scene(root, 400, 400);

            // Attempt to load the stylesheet and apply it
            String css = getClass().getResource("application.css") != null ?
                    getClass().getResource("application.css").toExternalForm() : null;

            if (css != null) {
                scene.getStylesheets().add(css);
            } else {
                System.out.println("Warning: 'application.css' file not found.");
            }

            // Set stage title and display
            primaryStage.setTitle("Property Management System");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}