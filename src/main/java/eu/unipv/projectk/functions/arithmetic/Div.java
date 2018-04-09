package eu.unipv.projectk.functions.arithmetic;

import eu.unipv.projectk.functions.BinaryMathFunction;
import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.NumberMathFunction;
import eu.unipv.projectk.functions.miscellanea.Pow2;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) / g(x)
 */
public final class Div implements MathFunction {
    private final MathFunction div;

    /**
     * z(f0(x), f1(x), ... , fN(x)) = f0(x) / f1(x) / ... / fN(x);
     * @param arg0  first argument
     * @param arg1  second argument
     * @param args  all other optional arguments
     */
    public Div(MathFunction arg0, MathFunction arg1, MathFunction... args) {
        BinaryDivision division = new BinaryDivision(arg0, arg1);

        for (MathFunction arg : args) {
            division = new BinaryDivision(division, arg);
        }

        div = division;
    }

    /**
     * z(k, f(x)) = k / f(x)
     * @param number    first argument
     * @param arg       second argument
     */
    public Div(double number, MathFunction arg) {
        div = new NumberDivFunction(number, arg);
    }

    /**
     * z(f(x), k) = f(x) / k
     * @param arg       first argument
     * @param number    second argument
     */
    public Div(MathFunction arg, double number) {
        div = new FunctionDivNumber(arg, number);
    }

    @Override
    public double evaluate(double x) {
        return div.evaluate(x);
    }

    @Override
    public MathFunction derivative() {
        return div.derivative();
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) / g(x)
     */
    private static final class BinaryDivision extends BinaryMathFunction {
        private static final BiFunction<Double, Double, Double> f = (x, y) -> x / y;

        BinaryDivision(MathFunction arg1, MathFunction arg2) {
            super(f, arg1, arg2, "/");
        }

        @Override
        public MathFunction derivative() {
            return new BinaryDivision(new Sub(new Mlt(arg1.derivative(), arg2), new Mlt(arg1, arg2.derivative())), new Pow2(arg2));
        }
    }

    /**
     * Represents the following function: z(k, f(x)) = k / f(x)
     */
    private static final class NumberDivFunction extends NumberMathFunction {

        NumberDivFunction(double number, MathFunction arg) {
            super(number, arg, "/");
        }

        @Override
        public double evaluate(double x) {
            return number / arg.evaluate(x);
        }

        @Override
        public MathFunction derivative() {
            return new Div(new Mlt(-1, arg.derivative()), new Pow2(arg));
        }
    }

    /**
     * Represents the following function: z(f(x), k) = f(x) / k
     */
    private static final class FunctionDivNumber extends NumberMathFunction {

        public FunctionDivNumber(MathFunction arg, double number) {
            super(number, arg, "/");
        }

        @Override
        public double evaluate(double x) {
            return arg.evaluate(x) / number;
        }

        @Override
        public MathFunction derivative() {
            return new Div(arg.derivative(), number);
        }

        @Override
        public String toString() {
            return "(" + arg + " + " + "(" + number + ")" + ")";
        }
    }
}
