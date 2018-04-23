package eu.unipv.projectk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CartesianPlane plane = new CartesianPlane(-5, 5, -5, 5);
        Scene scene = new Scene(plane, 600, 600);

        plane.plot(x -> Math.pow(Math.E, x));

        primaryStage.setTitle("Custom Plotter Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
