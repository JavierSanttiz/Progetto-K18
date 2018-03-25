package eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) * g(x)
 */
public final class Mult extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, y) -> x * y;

    public Mult(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "*");
    }

    /**
     * Multiply an indefinite number of functions with a single method invocation
     * @param args  a variable number of MathFunction(s)
     * @return      the addition of the passed arguments
     */
    public static Mult chain(MathFunction... args) {
        if (args.length < 2) {
            throw new AssertionError("Minimum number of parameters required: 2");
        } else if (args.length == 2) {
            return new Mult(args[0], args[1]);
        } else {
            Mult mult = new Mult(args[0], args[1]);

            for (int i = 2; i < args.length; i++) {
                mult = new Mult(mult, args[i]);
            }

            return mult;
        }
    }

    @Override
    public MathFunction derivative() {
        return new Add(new Mult(arg1.derivative(), arg2), new Mult(arg1, arg2.derivative()));
    }
}
