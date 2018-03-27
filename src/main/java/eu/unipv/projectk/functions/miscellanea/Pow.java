package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Ln;

import java.util.function.BiFunction;

/**
 * Represents the following functions:
 * <ol>
 * <li>z(n, f(x)) = n ^ f(x)
 * <li>z(f(x), n) = f(x) ^ n
 * <li>z(f(x), g(x)) = f(x) ^ g(x)
 * </ol>
 */
public final class Pow implements MathFunction {
    private static final BiFunction<Double, Double, Double> f = Math::pow;
    private static final String symbol = "^";

    private final MathFunction mathFunction;

    /**
     * Represents the following function: z(n, f(x)) = n ^ f(x)
     */
    private final class NPowF implements MathFunction {
        private final double n;
        private final MathFunction arg;

        NPowF(double n, MathFunction arg) {
            this.n = n;
            this.arg = arg;
        }

        @Override
        public double evaluate(double x) {
            return f.apply(n, arg.evaluate(x));
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new KFPlusH(Math.log(n), new Pow(n, arg)), arg.derivative());
        }

        @Override
        public String toString() {
            return n + " " + symbol + " " + arg.toString();
        }
    }

    /**
     * Represents the following function: z(f(x), n) = f(x) ^ n
     */
    private final class FPowN implements MathFunction {
        private final MathFunction arg;
        private final double n;

        FPowN(MathFunction arg, double n) {
            this.arg = arg;
            this.n = n;
        }

        @Override
        public double evaluate(double x) {
            return f.apply(arg.evaluate(x), n);
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new KFPlusH(n, new Pow(arg, n -1)), arg.derivative());
        }

        @Override
        public String toString() {
            return arg + " " + symbol + " " + n;
        }
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) ^ g(x)
     */
    private final class FPowF extends BinaryMathFunction {

        FPowF(MathFunction arg1, MathFunction arg2) {
            super(Math::pow, arg1, arg2, symbol);
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new Pow(arg1, arg2), new Add(new Mult(arg2.derivative(), new Ln(arg1)), new Div(new Mult(arg2, arg1.derivative()), arg1)));
        }
    }

    /**
     * f(x) = n ^ g(x)
     * @param n     constant number
     * @param arg   g(x)
     */
    public Pow(double n, MathFunction arg) {
        mathFunction = new NPowF(n, arg);
    }

    /**
     * f(x) = g(x) ^ n
     * @param arg   g(x)
     * @param n     constant number
     */
    public Pow(MathFunction arg, double n) {
        mathFunction = new FPowN(arg, n);
    }

    /**
     * z(f(x), g(x)) = f(x) ^ g(x)
     * @param arg1  f(x)
     * @param arg2  g(x)
     */
    public Pow(MathFunction arg1, MathFunction arg2) {
        mathFunction = new FPowF(arg1, arg2);
    }

    @Override
    public double evaluate(double x) {
        return mathFunction.evaluate(x);
    }

    @Override
    public MathFunction derivative() {
        return mathFunction.derivative();
    }

    @Override
    public String toString() {
        return mathFunction.toString();
    }
}