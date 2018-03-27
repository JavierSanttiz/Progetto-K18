package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;

/**
 * Represents the following function: f(x) = k * g(x) + Σ(h)
 */
public final class KFPlusH implements MathFunction {
    private MathFunction arg;
    private double mlt;
    private double add;

    /**
     * f(x) = k * g(x) + Σ(h)
     * @param mlt   k
     * @param arg   g(x)
     * @param add   Σ(h)
     */
    public KFPlusH(double mlt, MathFunction arg, double... add) {
        if (Double.compare(mlt, 0) == 0)
            throw new AssertionError("mlt = " + 0);

        this.mlt = mlt;
        this.arg = arg;

        this.add = 0;
        for (double d : add)
            this.add += d;
    }

    @Override
    public double evaluate(double x) {
        return mlt * arg.evaluate(x) + add;
    }

    @Override
    public MathFunction derivative() {
        return new KFPlusH(mlt, arg.derivative());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        if (Double.compare(mlt, 1) == 0) {
            // Do nothing
        } else {
            sb.append(mlt).append(" * ");
        }
        sb.append(arg.toString());
        if (Double.compare(add, 0) == 0) {
            // Do nothing
        } else {
            sb.append(" + ").append(add);
        }
        sb.append(")");

        return sb.toString();
    }
}