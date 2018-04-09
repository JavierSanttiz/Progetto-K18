package eu.unipv.projectk.functions.arithmetic;

import eu.unipv.projectk.functions.BinaryMathFunction;
import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.NumberMathFunction;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) * g(x)
 */
public final class Mlt implements MathFunction {
    private final MathFunction mlt;

    /**
     * z(f0(x), f1(x), ... , fN(x)) = f0(x) * f1(x) * ... * fN(x);
     * @param arg0  first argument
     * @param arg1  second argument
     * @param args  all other optional arguments
     */
    public Mlt(MathFunction arg0, MathFunction arg1, MathFunction... args) {
        BinaryMultiplication multiplication = new BinaryMultiplication(arg0, arg1);

        for (MathFunction arg : args) {
            multiplication = new BinaryMultiplication(multiplication, arg);
        }

        mlt =  multiplication;
    }

    /**
     * z(k, f1(x), ... , fN(x)) = k * f1(x) * ... * fN(x);
     * @param number    fist argument
     * @param arg0      second argument
     * @param args      all other optional arguments
     */
    public Mlt(double number, MathFunction arg0, MathFunction... args) {
            mlt = new NumberMultiplication(number, new Mlt(arg0, args[0], Arrays.copyOfRange(args, 1, args.length)));
    }

    @Override
    public double evaluate(double x) {
        return mlt.evaluate(x);
    }

    @Override
    public MathFunction derivative() {
        return mlt.derivative();
    }

    @Override
    public String toString() {
        return mlt.toString();
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) * g(x)
     */
    private static final class BinaryMultiplication extends BinaryMathFunction {
        private static final BiFunction<Double, Double, Double> f = (x, y) -> x * y;

        BinaryMultiplication(MathFunction arg1, MathFunction arg2) {
            super(f, arg1, arg2, "*");
        }

        @Override
        public MathFunction derivative() {
            return new Add(new BinaryMultiplication(arg1.derivative(), arg2), new BinaryMultiplication(arg1, arg2.derivative()));
        }
    }

    /**
     * Represents the following function: z(k, f(x)) = k * f(x)
     */
    private static final class NumberMultiplication extends NumberMathFunction {

        NumberMultiplication(double number, MathFunction arg) {
            super(number, arg, "*");
        }

        @Override
        public double evaluate(double x) {
            return number * arg.evaluate(x);
        }

        @Override
        public MathFunction derivative() {
            return new Mlt(number, arg.derivative());
        }
    }
}
