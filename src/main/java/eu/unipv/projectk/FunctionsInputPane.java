package eu.unipv.projectk;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FunctionsInputPane extends VBox {
    private HBox optionsPanel;
    private Button addFunction, edit;

    public FunctionsInputPane() {
        optionsPanel = new HBox();
        optionsPanel.setSpacing(15);
        optionsPanel.setPadding(new Insets(5));
        optionsPanel.setBorder(
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

        addFunction = new Button("+");
        addFunction.setOnAction(e -> getChildren().add(new FunctionSlot()));

        edit = new Button("Edit");

        optionsPanel.getChildren().addAll(addFunction, edit);

        setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                Color.BLACK,
                                Color.BLACK,
                                Color.BLACK,
                                BorderStrokeStyle.NONE,
                                BorderStrokeStyle.SOLID,
                                BorderStrokeStyle.NONE,
                                BorderStrokeStyle.NONE,
                                CornerRadii.EMPTY,
                                new BorderWidths(1),
                                null)));

        setPrefWidth(300);
        setMaxWidth(300);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        getChildren().addAll(optionsPanel);
    }
}
