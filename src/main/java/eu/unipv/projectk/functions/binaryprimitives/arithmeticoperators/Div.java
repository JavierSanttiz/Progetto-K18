package eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) / g(x)
 */
public final class Div extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, y) -> x / y;

    /**
     * z(f(x), g(x)) = f(x) / g(x)
     * @param arg1 f(x)
     * @param arg2 g(x)
     */
    public Div(MathFunction arg1, MathFunction arg2) {
        super(f, arg1, arg2, "/");
    }

    /**
     * Divide an indefinite number of functions with a single method invocation
     * @param args  a variable number of MathFunction(s)
     * @return      the division of the passed arguments
     */
    public static Div chain(MathFunction... args) {
        if (args.length < 2) {
            throw new AssertionError("Minimum number of parameters required: 2");
        } else if (args.length == 2) {
            return new Div(args[0], args[1]);
        } else {
            Div div = new Div(args[0], args[1]);

            for (int i = 2; i < args.length; i++) {
                div = new Div(div, args[i]);
            }

            return div;
        }
    }

    @Override
    public MathFunction derivative() {
        return new Div(new Subtr(new Mult(arg1.derivative(), arg2), new Mult(arg1, arg2.derivative())), new Pow2(arg2));
    }
}
