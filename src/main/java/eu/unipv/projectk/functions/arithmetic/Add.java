package eu.unipv.projectk.functions.arithmetic;

import eu.unipv.projectk.functions.BinaryMathFunction;
import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.NumberMathFunction;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) + g(x)
 */
public final class Add implements MathFunction {
    private final MathFunction add;

    /**
     * z(f0(x), f1(x), ... , fN(x)) = f0(x) + f1(x) + ... + fN(x);
     * @param arg0  first argument
     * @param arg1  second argument
     * @param args  all other optional arguments
     */
    public Add(MathFunction arg0, MathFunction arg1, MathFunction... args) {
        BinaryAddition addition = new BinaryAddition(arg0, arg1);

        for (MathFunction arg : args) {
            addition = new BinaryAddition(addition, arg);
        }

        add = addition;
    }

    /**
     * z(k, f0(x), ... , fN(x)) = k + f0(x) + ... + fN(x);
     * @param number    first argument
     * @param arg0      second argument
     * @param args      all other optional arguments
     */
    public Add(double number, MathFunction arg0, MathFunction... args) {
        if (args.length > 0) {
            add = new NumberAddition(number, new Add(arg0, args[0], Arrays.copyOfRange(args, 1, args.length)));
        } else {
            add = new NumberAddition(number, arg0);
        }
    }

    @Override
    public double evaluate(double x) {
        return add.evaluate(x);
    }

    @Override
    public MathFunction derivative() {
        return add.derivative();
    }

    @Override
    public String toString() {
        return add.toString();
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) + g(x)
     */
    private static final class BinaryAddition extends BinaryMathFunction {
        private static final BiFunction<Double, Double, Double> f = (x, y) -> x + y;

        BinaryAddition(MathFunction arg1, MathFunction arg2) {
            super(f, arg1, arg2, "+");
        }

        @Override
        public MathFunction derivative() {
            return new BinaryAddition(arg1.derivative(), arg2.derivative());
        }
    }

    /**
     * Represents the following function: z(k, f(x)) = k + f(x)
     */
    private static final class NumberAddition extends NumberMathFunction {

        NumberAddition(double number, MathFunction arg) {
            super(number, arg, "+");
        }

        @Override
        public double evaluate(double x) {
            return number + arg.evaluate(x);
        }

        @Override
        public MathFunction derivative() {
            return arg.derivative();
        }
    }
}
