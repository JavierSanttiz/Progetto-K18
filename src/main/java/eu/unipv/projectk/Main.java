package eu.unipv.projectk;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        CartesianPlane plane = new CartesianPlane(new FunctionManager(), -5, 5, -5, 5);
        FunctionsInputPane functionsInputPane = new FunctionsInputPane();
        StackPane.setAlignment(functionsInputPane, Pos.TOP_LEFT);

        root.getChildren().addAll(plane, functionsInputPane);

        plane.addFunction(Math::log);
        plane.plot();

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
