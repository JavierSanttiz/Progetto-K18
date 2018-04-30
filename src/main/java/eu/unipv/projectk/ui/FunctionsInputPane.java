package eu.unipv.projectk.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
        hide.setOnAction(e -> {
            TranslateTransition t = new TranslateTransition(new Duration(250), this);
            t.setToX(-getMaxWidth());
            t.play();
            t.setOnFinished(e1 -> hidden.set(true));
        });
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
        //JFXScrollPane.smoothScrolling(functionsScrollPane);

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
        private DerivativeButton derivativeButton;
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
                    "-fx-font-size: 16px;" +
                            "-fx-font-style: oblique;"
            );

            functionInput = new JFXTextField();
            functionInput.getStyleClass().add("function-input");
            functionInput.setStyle(
                    "-fx-pref-width: 165px;"
            );

            derivativeButton = new DerivativeButton();

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
                            "-fx-spacing: 8px;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-color: gray;" +
                            "-fx-border-width: 0px 0px 1px 0px;"
            );

            getChildren().addAll(prompt, functionInput, derivativeButton, delete);
        }

        private class DerivativeButton extends HBox{
            private int index;
            private JFXButton increment, decrement;
            private VBox modifier;
            private JFXButton computeDerivative;
            private Label indexLabel;

            DerivativeButton() {
                derivativeButtonGraphicInit();

                increment.setOnAction(e -> indexLabel.setText(String.valueOf(++index)));
                decrement.setOnAction(e -> {
                    if (index <= 1) {
                        // Do Nothing
                    } else {
                        indexLabel.setText(String.valueOf(--index));
                    }
                });
            }

            void derivativeButtonGraphicInit() {
                index = 1;

                increment = new JFXButton("▴");
                increment.getStyleClass().add("increment");

                decrement = new JFXButton("▾");
                decrement.getStyleClass().add("decrement");

                modifier = new VBox(increment, decrement);
                modifier.getStyleClass().add("modifier");
                modifier.setStyle(
                        "-fx-alignment: center;" +
                                "-fx-border-color: darkgreen;" +
                                "-fx-border-style: solid;" +
                                "-fx-border-width: 0px, 0px, 0px, 1px;"
                );

                computeDerivative = new JFXButton("Dⁿf ");
                computeDerivative.getStyleClass().add("comput-derivative");
                computeDerivative.setStyle(
                        "-fx-pref-height: 50px;"
                );

                indexLabel = new Label(String.valueOf(index));
                indexLabel.getStyleClass().add("index-label");
                indexLabel.setStyle(
                        "-fx-font-weight: bold;"
                );

                getStyleClass().add("derivative-button");
                setStyle(
                        "-fx-alignment: center;" +
                                "-fx-background-color: green;" +
                                "-fx-border-radius: 9;" +
                                "-fx-border-width: 10px;" +
                                "-fx-border-color: white;" +
                                "-fx-background-radius: 20;"
                );

                getChildren().addAll(computeDerivative, indexLabel, modifier);
            }
        }
    }
}
