package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// TODO: fix error loading stylesheet
public class FunctionsInputPane extends VBox {
    private HBox optionsBar;
    private JFXButton addFunction, edit;
    private VBox functions;
    private ScrollPane functionsScrollPane;

    public FunctionsInputPane() {
        optionsBar = new HBox();
        optionsBar.getStyleClass().add("options-bar");
        optionsBar.setStyle(
                    "-fx-pref-height: 45px;" +
                        "-fx-background-color: #d3d3d3;" +
                        "-fx-spacing: 15;" +
                        "-fx-alignment: center-left;" +
                        "-fx-padding: 5;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #bebebe;"
        );

        addFunction = new JFXButton("+");
        addFunction.getStyleClass().add("option-button");
        addFunction.setStyle(
                "-fx-background-color: #a4a4a4;"
        );
        addFunction.setOnAction(e -> functions.getChildren().add(new FunctionSlot()));

        edit = new JFXButton("Edit");
        edit.getStyleClass().add("option-button");
        edit.setStyle(
                "-fx-background-color: #a4a4a4;"
        );

        optionsBar.getChildren().addAll(addFunction, edit);

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
}
