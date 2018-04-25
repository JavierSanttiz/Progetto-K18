package eu.unipv.projectk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FunctionSlot extends HBox {
    private Label functionPrompt;
    private TextField functionInput;
    private HBox derivative;
    private Button derivativeButton;
    private TextField derivativeInput;

    public FunctionSlot() {
        functionPrompt = new Label("f(x) = ");
        functionInput = new TextField();
        functionInput.setPrefWidth(120);
        functionInput.setStyle("-fx-background-color: none;");

        derivative = new HBox();
        derivativeButton = new Button("Dⁿf n =");
        derivativeInput = new TextField();
        derivativeInput.setPrefWidth(40);
        derivativeInput.setStyle("-fx-background-color: none;");
        derivative.getChildren().addAll(derivativeButton, derivativeInput);

        setBorder(
                new Border(
                        new BorderStroke(
                                Color.LIGHTGRAY,
                                Color.LIGHTGRAY,
                                Color.LIGHTGRAY,
                                Color.LIGHTGRAY,
                                BorderStrokeStyle.NONE,
                                BorderStrokeStyle.NONE,
                                BorderStrokeStyle.SOLID,
                                BorderStrokeStyle.NONE,
                                CornerRadii.EMPTY,
                                new BorderWidths(2),
                                new Insets(0))));

        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 0, 0, 2));
        getChildren().addAll(functionPrompt, functionInput, derivative);
    }
}
