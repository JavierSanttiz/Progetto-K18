package eu.unipv.projectk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CartesianPlane plane = new CartesianPlane(new FunctionManager(), -5, 5, -5, 5);
        Scene scene = new Scene(plane, 600, 600);

        plane.addFunction(Math::sin);
        plane.plot();

        primaryStage.setTitle("Plotter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
