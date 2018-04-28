package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

// TODO: fix error loading stylesheet
public class FunctionSlot extends HBox {
    private Label prompt;
    private JFXTextField functionInput;
    private String derivativeText;
    private JFXTextField derivativeInput;
    private JFXButton derivative;
    private JFXButton delete;

    public FunctionSlot() {
        prompt = new Label("f(x) = ");
        prompt.getStyleClass().add("prompt");
        prompt.setStyle(
                "-fx-font-size: 16px;"
        );

        functionInput = new JFXTextField();
        functionInput.getStyleClass().add("function-input");
        functionInput.setStyle(
                "-fx-pref-width: 165px;"
        );

        derivativeText = " = n | Dⁿf";

        derivativeInput = new JFXTextField("1");
        derivativeInput.getStyleClass().add("derivative-input");
        derivativeInput.setStyle(
                    "-fx-max-width: 20px;" +
                            "-fx-alignment: center-right"
        );

        derivative = new JFXButton(derivativeText, derivativeInput);
        derivative.getStyleClass().add("derivative");
        derivative.setStyle(
                "-fx-background-color: #00bc00;"
        );

        delete = new JFXButton("X");
        delete.getStyleClass().add("delete");
        delete.setStyle(
                "-fx-background-color: #bc0000;"
        );

        setStyle(
                "-fx-pref-height: 50px;" +
                        "-fx-alignment: center;" +
                        "-fx-padding: 0 0 0 2;" +
                        "-fx-spacing: 10px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: gray;" +
                        "-fx-border-width: 0px 0px 1px 0px;"
        );

        getChildren().addAll(prompt, functionInput, derivative, delete);
    }
}
