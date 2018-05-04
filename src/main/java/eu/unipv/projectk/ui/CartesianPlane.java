package eu.unipv.projectk.ui;

import eu.unipv.projectk.FunctionManager;
import eu.unipv.projectk.functions.FooMathFunction;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class CartesianPlane extends Pane {
    private static final int STD_POINT_DENSITY = 200;
    private static final double STD_TICK_DENSITY = 10;
    private static int CHECK_TRESHOLD = 10;
    private static double MAX_ANGLE = Math.atan(Double.MIN_VALUE);

    private final FunctionManager functionManager;
    private Group sheet;

    private final NumberAxis xAxis;
    private final NumberAxis yAxis;

    private double axisTickDensity;
    private int pointDensity;

    public CartesianPlane(FunctionManager functionManager, double xLow, double xHi, double yLow, double yHi) {
        this(functionManager, xLow, xHi, yLow, yHi, STD_TICK_DENSITY, STD_POINT_DENSITY);
    }

    public CartesianPlane(FunctionManager functionManager, double xLow, double xHi, double yLow, double yHi, double axisTickDensity, int pointsDensity) {
        if ((xLow > xHi) || (yLow > yHi)) {
            throw new AssertionError("Low(s) must be less than Hi(s)");
        }

        this.functionManager = functionManager;
        sheet = new Group();

        this.axisTickDensity = axisTickDensity;
        this.pointDensity = pointsDensity;

        xAxis = new NumberAxis(xLow, xHi, (xHi - xLow) / this.axisTickDensity);
        xAxis.setSide(Side.BOTTOM);

        yAxis = new NumberAxis(yLow, yHi, (yHi - yLow) / this.axisTickDensity);
        yAxis.setSide(Side.RIGHT);

        // Axis binding to pane
        xAxis.layoutYProperty().bind(heightProperty().divide(2));
        xAxis.prefWidthProperty().bind(widthProperty());
        yAxis.layoutXProperty().bind(widthProperty().divide(2));
        yAxis.prefHeightProperty().bind(heightProperty());

        getChildren().addAll(xAxis, yAxis, sheet);

        // TODO: implement zoom
        // TODO: implement drag
    }

    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        sheetRefresh();
    }

    public void addFunction(FooMathFunction f) {
        functionManager.add(f);
    }

    public void plot(double x, double y) {
        Circle circle = new Circle(mapToPaneX(x), mapToPaneY(y), 4, Color.GREEN);

        sheet.getChildren().add(circle);
    }

    public void plot() {
        double step = (Math.abs(xAxis.getUpperBound()) + Math.abs(xAxis.getLowerBound())) / pointDensity;

        for (FooMathFunction f : functionManager.getFunctions()) {
            if (f != null) {
                double previousX = xAxis.getLowerBound();
                double previousY = f.apply(previousX);

                for (double x = previousX + step; x <= xAxis.getUpperBound(); x += step) {
                    if (!isVertical(previousX, previousY, x, f.apply(x))) {
                        plotSegment(previousX, previousY, x, f.apply(x), Color.RED);
                    } else {

                        for (int i = 0; i < CHECK_TRESHOLD; i++) {
                            double[] midPoints = splitSegment(previousX, x, i + 2);

                            if (!areVertical(f, midPoints)) {
                                // Probably not a discontinuity
                                plot(f, midPoints, Color.RED);
                                break;
                            }

                            // May be a discontinuity! Don't plot the segment
                        }
                    }

                    previousX = x;
                    previousY = f.apply(x);
                }
            }
        }
    }

    private void plot(FooMathFunction f, double[] points, Color color) {
        if (points.length < 2) {
            throw new AssertionError("At least 2 points");
        }

        for (int i = 0; i < points.length - 1; i++) {
            plotSegment(points[i], f.apply(points[i]), points[i + 1], f.apply(points[i + 1]), color);
        }

    }

    private double[] splitSegment(double x0, double x1, int splits) {
        if (x0 >= x1) {
            throw new AssertionError("x0 should be less than x1");
        }

        double[] points = new double[splits];
        double step = (x1 - x0) / splits;

        for (int i = 0; i < splits; i++) {
            points[i] = x0 + step * (i + 1);
        }

        return points;
    }

    private void plotSegment(double x0, double y0, double x1, double y1, Color color) {
        Line line = new Line(mapToPaneX(x0), mapToPaneY(y0), mapToPaneX(x1), mapToPaneY(y1));
        line.setStrokeWidth(2);
        line.setStroke(color);
        sheet.getChildren().add(line);
    }

    private boolean isVertical(double x0, double y0, double x1, double y1) {
        if (y0 == y1)
            return false;

        double angle = Math.abs(Math.atan((y1 - y0) / (x1 - x0)));

        // TODO: find a suitable proportional rule for angle offset
        double step = Math.abs(x1 - x0);
        double angleOffset = (step < 1? 1 / Math.pow(step, 2): Math.pow(step, 3));
        return !(MAX_ANGLE <= angle) || !(angle <= Math.atan(angleOffset));
    }

    private boolean areVertical(FooMathFunction f, double[] points) {
        double prevX = points[0];
        double prevY = f.apply(prevX);

        for (int i = 1; i < points.length; i++) {
            if (isVertical(prevX, prevY, points[i], f.apply(points[i]))) {
                return true;
            }

            prevX = points[i];
            prevY = f.apply(prevX);
        }

        return false;
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

    private void sheetRefresh() {
        sheet.getChildren().clear();
        plot();
    }
}
