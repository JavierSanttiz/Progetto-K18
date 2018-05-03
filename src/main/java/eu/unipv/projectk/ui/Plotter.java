package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import eu.unipv.projectk.FunctionManager;
import eu.unipv.projectk.functions.FooMathFunction;
import eu.unipv.projectk.ui.functioninput.FunctionInput;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Plotter extends StackPane {
    private final FunctionManager functionManager;
    private final FunctionInput functionInput;
    private final CartesianPlane cartesianPlane;

    private JFXButton show;
    private Label functionTextPopup;

    public Plotter(FunctionManager functionManager, double xLow, double xHi, double yLow, double yHi) {
        this.functionManager = functionManager;
        functionInput = new FunctionInput();
        cartesianPlane = new CartesianPlane(functionManager, xLow, xHi, yLow, yHi);

        graphicInit();

        show.visibleProperty().bindBidirectional(functionInput.hiddenProperty());
        show.setOnAction(e -> {
            functionInput.hiddenProperty().set(false);
            TranslateTransition t = new TranslateTransition(new Duration(250), functionInput);
            t.setToX(functionInput.getMinWidth());
            t.play();
        });

        Timeline waiting = new Timeline(new KeyFrame(Duration.seconds(3)));
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), functionTextPopup);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        SequentialTransition fade = new SequentialTransition(functionTextPopup, waiting, fadeTransition);

        functionTextPopup.setVisible(false);
        functionTextPopup.textProperty().bind(functionInput.getSelectedSlotText());
        functionTextPopup.textProperty().addListener((a) -> {
            functionTextPopup.setVisible(true);
            fade.playFromStart();
        });
    }

    public void plot(FooMathFunction f) {
        cartesianPlane.addFunction(f);
        cartesianPlane.plot();
    }

    private void graphicInit() {
        show = new JFXButton(">>");
        show.setStyle(
                "-fx-background-color: #bfbfbf;" +
                        "-jfx-button-type: RAISED;"
        );
        StackPane.setAlignment(show, Pos.TOP_LEFT);
        show.setTranslateY(10);
        show.setTranslateX(10);

        functionTextPopup = new Label("f(x) = ");
        functionTextPopup.setStyle(
                "-fx-background-color: rgba(128,128,128,0.65);" +
                        "-fx-background-radius: 10;" +
                        "-fx-pref-height: 50px;" +
                        "-fx-min-width: 80px;" +
                        "-fx-padding: 15px;" +
                        "-fx-alignment: center;" +
                        "-fx-font-style: oblique;"
        );
        StackPane.setAlignment(functionTextPopup, Pos.BOTTOM_CENTER);
        functionTextPopup.setTranslateY(-30);

        StackPane.setAlignment(functionInput, Pos.TOP_LEFT);

        getChildren().addAll(cartesianPlane, functionInput, show, functionTextPopup);
    }
}
