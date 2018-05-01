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

        // Jump not detected
        //FooMathFunction f = x -> 0.5 * Math.abs(x) / x;
        //root.plot(f);

        // Jump detected
        //FooMathFunction g = x -> Math.abs(x) / x;
        //root.plot(g);

        // Discontinuity detected
        //FooMathFunction h = x -> 5 / (x);
        //root.plot(h);

        // Discontinuity detected
        FooMathFunction k = x -> Math.pow(Math.sin(2 * x), x);
        root.plot(k);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
