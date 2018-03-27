package eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) - g(x)
 */
public final class Subtr extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, y) -> x - y;

    /**
     * z(f(x), g(x)) = f(x) - g(x)
     * @param arg1 f(x)
     * @param arg2 g(x)
     */
    public Subtr(MathFunction arg1, MathFunction arg2) {
        super(f, arg1, arg2, "-");
    }

    /**
     * Subtract an indefinite number of functions with a single method invocation
     * @param args  a variable number of MathFunction(s)
     * @return      the addition of the passed arguments
     */
    public static Subtr chain(MathFunction... args) {
        if (args.length < 2) {
            throw new AssertionError("Minimum number of parameters required: 2");
        } else if (args.length == 2) {
            return new Subtr(args[0], args[1]);
        } else {
            Add add = new Add(args[1], args[2]);

            for (int i = 3; i < args.length; i++) {
                add = new Add(add, args[i]);
            }

            Subtr subtr = new Subtr(args[0], add);

            return subtr;
        }
    }

    @Override
    public MathFunction derivative() {
        return new Subtr(arg1.derivative(), arg2.derivative());
    }
}
