package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(base, f(x)) = log(base, f(x))
 */
public final class Log implements MathFunction {
    private static final BiFunction<Double, Double, Double> f = (base, n) -> Math.log(n) / Math.log(base);

    private double base;
    private MathFunction arg;

    /**
     * f(x)) = log(base, f(x))
     * @param base  logarithm base
     * @param arg   logarithm argument
     */
    public Log(double base, MathFunction arg) {
        this.base = base;
        this.arg = arg;
    }

    @Override
    public double evaluate(double x) {
        return f.apply(base, x);
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg.derivative(), new KFPlusH(Math.log(base), arg));
    }

    @Override
    public String toString() {
        return "log(" + base + ", " + arg.toString() + ")";
    }
}
