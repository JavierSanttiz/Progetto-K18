package eu.unipv.projectk.ui.functioninput;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FunctionInput extends VBox {
    private SimpleBooleanProperty hidden;

    private JFXButton addFunction, edit, hide;
    private HBox leftOptionsBar;
    private HBox optionsBar;

    private FunctionSlotManager functionSlotManager;
    private ScrollPane functionsScrollPane;

    public FunctionInput() {
        init();

        addFunction.setOnAction(e -> functionSlotManager.newSlot());

        hide.setOnAction(e -> {
            TranslateTransition t = new TranslateTransition(new Duration(250), this);
            t.setToX(-getMaxWidth());
            t.play();
            t.setOnFinished(e1 -> hidden.set(true));
        });
    }

    public SimpleStringProperty getSelectedSlotText() {
        return functionSlotManager.selectedSlotTextPropertyProperty();
    }

    public boolean isHidden() {
        return hidden.get();
    }

    public SimpleBooleanProperty hiddenProperty() {
        return hidden;
    }

    private void init() {
        addFunction = new JFXButton("+");
        addFunction.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        edit = new JFXButton("Edit");
        edit.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        hidden = new SimpleBooleanProperty(false);
        hide = new JFXButton("<<");
        hide.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        leftOptionsBar = new HBox(addFunction, edit);
        leftOptionsBar.setStyle(
                "-fx-spacing: 15;" +
                        "-fx-alignment: center-left;" +
                        "-fx-padding: 10;"
        );

        optionsBar = new HBox(leftOptionsBar, hide);
        optionsBar.setStyle(
                "-fx-pref-height: 45px;" +
                        "-fx-background-color: #d3d3d3;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #bebebe;" +
                        "-fx-alignment: center-left;" +
                        "-fx-spacing: 250px;"
        );

        functionSlotManager = new FunctionSlotManager();

        functionsScrollPane = new ScrollPane(functionSlotManager);

        setStyle(
                "-fx-max-width: 410px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #bebebe;" +
                        "-fx-border-width: 0px 2px 0px 0px;" +
                        "-fx-background-color: #ffffff;"
        );

        getChildren().addAll(optionsBar, functionsScrollPane);
    }
}