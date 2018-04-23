package eu.unipv.projectk;

import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.function.Function;

public class CartesianPlane extends Pane {
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;

    public CartesianPlane(double xLow, double xHi, double yLow, double yHi) {
        xAxis = new NumberAxis(-5, 5, 1);
        xAxis.setSide(Side.BOTTOM);

        yAxis = new NumberAxis(-5, 5, 1);
        yAxis.setSide(Side.RIGHT);

        // Axis binding to pane
        xAxis.layoutYProperty().bind(heightProperty().divide(2));
        xAxis.prefWidthProperty().bind(widthProperty());
        yAxis.layoutXProperty().bind(widthProperty().divide(2));
        yAxis.prefHeightProperty().bind(heightProperty());

        setOnMouseClicked(e -> {
            System.out.println(mapToCartesianX(e.getX()) + " " + mapToCartesianY(e.getY()));
        });

        getChildren().addAll(xAxis, yAxis);
    }

    public void plot(double x, double y) {
        Circle circle = new Circle(mapToPaneX(x), mapToPaneY(y), 4, Color.GREEN);

        getChildren().add(circle);
    }

    public void plot(Function<Double, Double> f) {
        int density = 50;
        double step = (xAxis.getUpperBound() - yAxis.getLowerBound()) / density;

        double previousX = xAxis.getLowerBound();
        double previousY = f.apply(previousX);

        for (double x = previousX + step; x <= xAxis.getUpperBound(); x += step) {
            plotSegment(previousX, previousY, x, f.apply(x), Color.RED);

            previousX = x;
            previousY = f.apply(x);
        }
    }

    private void plotSegment(double x0, double y0, double x1, double y1, Color color) {
        Line line = new Line(mapToPaneX(x0), mapToPaneY(y0), mapToPaneX(x1), mapToPaneY(y1));
        line.setStrokeWidth(2);
        line.setStroke(color);

        getChildren().add(line);
    }

    private double mapToPaneX(double cartesianX) {
        double convertedX = cartesianX / getAxisRange(xAxis) * getWidth();

        return convertedX + getWidth() / 2;
    }

    private double mapToPaneY(double cartesianY) {
        double convertedY = - (cartesianY / getAxisRange(yAxis) * getHeight());

        return convertedY + getHeight() / 2;
    }

    private double mapToCartesianX(double paneX) {
        double centeredX = paneX - getWidth() / 2;

        return centeredX / getWidth() * getAxisRange(xAxis);
    }

    private double mapToCartesianY(double paneY) {
        double centeredY = paneY - getHeight() / 2;

        return (centeredY / getHeight() * getAxisRange(yAxis));
    }

    private double getAxisRange(NumberAxis axis) {
        return axis.getUpperBound() - axis.getLowerBound();
    }
}
