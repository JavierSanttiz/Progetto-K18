package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = (k / g(x)) + Σ(h)
 */
public class KOnFPlusH implements MathFunction {
    private MathFunction arg;
    private double div;
    private double add;

    public KOnFPlusH(double div, MathFunction arg, double... add) {
        if (Double.compare(div, 0) == 0)
            throw new AssertionError("div = " + div);

        this.arg = arg;
        this.div = div;

        this.add = 0;
        for (double d : add)
            this.add += d;
    }

    @Override
    public double evaluate(double x) {
        return (div / arg.evaluate(x)) + add;
    }

    @Override
    public MathFunction derivative() {
        return new KFPlusH(-div, new Div(arg.derivative(), new Pow2(arg)));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean notAdded = Double.compare(add, 0) == 0;

        if (notAdded) {
            // Do nothing
        } else {
            sb.append("(");
        }
        sb.append("(").append(div).append(" / ").append(arg).append(")");
        if (notAdded) {
            // Do nothing
        } else {
            sb.append(" + ").append(add).append(")");
        }

        return sb.toString();
    }
}