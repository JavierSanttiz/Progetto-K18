package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import eu.unipv.projectk.FunctionManager;
import eu.unipv.projectk.functions.FooMathFunction;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class Plotter extends StackPane {
    private final FunctionManager functionManager;
    private final FunctionsInputPane functionsInputPane;
    private final CartesianPlane cartesianPlane;

    private JFXButton show;

    public Plotter(FunctionManager functionManager, double xLow, double xHi, double yLow, double yHi) {
        this.functionManager = functionManager;
        functionsInputPane = new FunctionsInputPane();
        cartesianPlane = new CartesianPlane(functionManager, xLow, xHi, yLow, yHi);

        graphicInit();

        show.visibleProperty().bindBidirectional(functionsInputPane.hiddenProperty());
        show.setOnAction(e -> {
            functionsInputPane.hiddenProperty().set(false);
            functionsInputPane.setTranslateX(functionsInputPane.getMinWidth());
        });
    }

    public void plot(FooMathFunction f) {
        cartesianPlane.addFunction(Math::sin);
        cartesianPlane.plot();
    }

    private void graphicInit() {
        show = new JFXButton(">>");
        show.getStyleClass().add("show");
        show.setStyle(
                "-fx-background-color: #bfbfbf;" +
                        "-jfx-button-type: RAISED;"
        );
        StackPane.setAlignment(show, Pos.TOP_LEFT);
        show.setTranslateY(10);
        show.setTranslateX(10);

        StackPane.setAlignment(functionsInputPane, Pos.TOP_LEFT);

        getChildren().addAll(cartesianPlane, functionsInputPane, show);
    }
}
