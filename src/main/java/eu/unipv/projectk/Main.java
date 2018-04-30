package eu.unipv.projectk;

import eu.unipv.projectk.functions.FooMathFunction;
import eu.unipv.projectk.ui.Plotter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Plotter root = new Plotter(new FunctionManager(), -5, 5, -5, 5);

        FooMathFunction f = Math::sin;
        root.plot(f);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
