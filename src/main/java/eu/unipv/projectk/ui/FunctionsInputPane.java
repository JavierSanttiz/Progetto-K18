package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// TODO: fix error loading stylesheet
public class FunctionsInputPane extends VBox {
    private SimpleBooleanProperty hidden;
    private JFXButton addFunction, edit, hide;
    private HBox leftOptionsBar;
    private HBox optionsBar;
    private VBox functions;
    private ScrollPane functionsScrollPane;

    public FunctionsInputPane() {
        inputPaneGraphicInit();

        addFunction.setOnAction(e -> functions.getChildren().add(new FunctionSlot()));
        hide.setOnAction(e -> hidden.set(true));
    }

    public boolean isHidden() {
        return hidden.get();
    }

    public SimpleBooleanProperty hiddenProperty() {
        return hidden;
    }

    private void inputPaneGraphicInit() {
        addFunction = new JFXButton("+");
        addFunction.getStyleClass().add("option-button");
        addFunction.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        edit = new JFXButton("Edit");
        edit.getStyleClass().add("option-button");
        edit.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        hidden = new SimpleBooleanProperty(false);
        hide = new JFXButton("<<");
        hide.getStyleClass().add("hide");
        hide.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        leftOptionsBar = new HBox(addFunction, edit);
        leftOptionsBar.getStyleClass().add("left-options-bar");
        leftOptionsBar.setStyle(
                "-fx-spacing: 15;" +
                        "-fx-alignment: center-left;" +
                        "-fx-padding: 10;"
        );

        optionsBar = new HBox(leftOptionsBar, hide);
        optionsBar.getStyleClass().add("options-bar");
        optionsBar.setStyle(
                "-fx-pref-height: 45px;" +
                        "-fx-background-color: #d3d3d3;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #bebebe;" +
                        "-fx-alignment: center-left;" +
                        "-fx-spacing: 250px;"
        );

        functions = new VBox();
        functions.getStyleClass().add("functions");
        functions.setStyle(
                "-fx-pref-width: 385px;" +
                        "-fx-border-width: 0px;" +
                        "-fx-padding: 0px, 5px, 0px, 0px;" +
                        "-fx-background-color: none;"
        );

        functionsScrollPane = new ScrollPane(functions);
        functionsScrollPane.getStyleClass().add("functions-scroll-pane");
        JFXScrollPane.smoothScrolling(functionsScrollPane);

        setStyle(
                "-fx-max-width: 410px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #bebebe;" +
                        "-fx-border-width: 0px 2px 0px 0px;" +
                        "-fx-background-color: #ffffff;"
        );

        getChildren().addAll(optionsBar, functionsScrollPane);

        functions.getChildren().add(new FunctionSlot());
    }

    private class FunctionSlot extends HBox {
        private Label prompt;
        private JFXTextField functionInput;
        private String derivativeText;
        private JFXTextField derivativeInput;
        private JFXButton derivative;
        private JFXButton delete;

        FunctionSlot() {
            slotGraphicInit();

            delete.setOnAction(e -> {
                if (functions.getChildren().size() == 1) {
                    // Do nothing
                } else {
                    functions.getChildren().remove(this);
                }
            });
        }

        void slotGraphicInit() {
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
                            "-fx-alignment: center-right;"
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

            getStyleClass().add("function-slot");
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
}
