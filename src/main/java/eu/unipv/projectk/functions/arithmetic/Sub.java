package eu.unipv.projectk.functions.arithmetic;

import eu.unipv.projectk.functions.BinaryMathFunction;
import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.NumberMathFunction;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) - g(x)
 */
public final class Sub implements MathFunction {
    private final MathFunction sub;

    /**
     * z(f0(x), f1(x), ... , fN(x)) = f0(x) - f1(x) - ... - fN(x);
     * @param arg0  first argument
     * @param arg1  second argument
     * @param args  all other optional arguments
     */
    public Sub(MathFunction arg0, MathFunction arg1, MathFunction... args) {
        if (args.length > 0) {

            Add add = new Add(arg1, args[0]);

            for (int i = 1; i < args.length; i++) {
                add = new Add(add, args[i]);
            }

            sub = new BinarySubtraction(arg0, add);
        } else {
            sub = new BinarySubtraction(arg0, arg1);
        }
    }

    /**
     * z(k, f(x)) = k - f(x)
     * @param number    first argument
     * @param arg       second argument
     */
    public Sub(double number, MathFunction arg) {
        sub = new NumberSubFunction(number, arg);
    }

    /**
     * z(f(x), k) = f(x) - k
     * @param arg       first argument
     * @param number    second argument
     */
    public Sub(MathFunction arg, double number) {
        sub = new FunctionSubNumber(arg, number);
    }

    @Override
    public double evaluate(double x) {
        return sub.evaluate(x);
    }

    @Override
    public MathFunction derivative() {
        return sub.derivative();
    }

    @Override
    public String toString() {
        return sub.toString();
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) - g(x)
     */
    private static final class BinarySubtraction extends BinaryMathFunction {
        private static final BiFunction<Double, Double, Double> f = (x, y) -> x - y;

        BinarySubtraction(MathFunction arg1, MathFunction arg2) {
            super(f, arg1, arg2, "-");
        }

        @Override
        public MathFunction derivative() {
            return new BinarySubtraction(arg1.derivative(), arg2.derivative());
        }
    }

    /**
     * Represents the following function: z(k, f(x)) = k - f(x)
     */
    private static final class NumberSubFunction extends NumberMathFunction {

        NumberSubFunction(double number, MathFunction arg) {
            super(number, arg, "-");
        }

        @Override
        public double evaluate(double x) {
            return number - arg.evaluate(x);
        }

        @Override
        public MathFunction derivative() {
            return new Mlt(-1, arg.derivative());
        }
    }

    /**
     * Represents the following function: z(f(x), k) = f(x) - k
     */
    private static final class FunctionSubNumber extends NumberMathFunction {

        public FunctionSubNumber(MathFunction arg, double number) {
            super(number, arg, "-");
        }

        @Override
        public double evaluate(double x) {
            return arg.evaluate(x) - number;
        }

        @Override
        public MathFunction derivative() {
            return arg.derivative();
        }
    }
}
