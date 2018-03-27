package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.number.Number;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Ln;

import java.util.function.BiFunction;

/**
 * <pre>
 * Represents the following functions:
 * z(n, f(x)) = n ^ f(x)
 * z(f(x), n) = f(x) ^ n
 * z(f(x), g(x)) = f(x) ^ g(x)
 * </pre>
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

        public NPowF(double n, MathFunction arg) {
            this.n = n;
            this.arg = arg;
        }

        @Override
        public double evaluate(double x) {
            return f.apply(n, arg.evaluate(x));
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new Combo(Math.log(n), new Pow(n, arg)), arg.derivative());
        }
    }

    /**
     * Represents the following function: z(f(x), n) = f(x) ^ n
     */
    private final class FPowN implements MathFunction {
        private final MathFunction arg;
        private final double n;

        public FPowN(MathFunction arg, double n) {
            this.arg = arg;
            this.n = n;
        }

        @Override
        public double evaluate(double x) {
            return f.apply(arg.evaluate(x), n);
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new Combo(n, new Pow(arg, n -1)), arg.derivative());
        }
    }

    /**
     * Represents the following function: z(f(x), g(x)) = f(x) ^ g(x)
     */
    private final class FPowF extends BinaryMathFunction {

        public FPowF(MathFunction arg1, MathFunction arg2) {
            super(Math::pow, arg1, arg2, symbol);
        }

        @Override
        public MathFunction derivative() {
            return new Mult(new Pow(arg1, arg2), new Add(new Mult(arg2.derivative(), new Ln(arg1)), new Div(new Mult(arg2, arg1.derivative()), arg1)));
        }
    }

    public Pow(double n, MathFunction arg) {
        mathFunction = new NPowF(n, arg);
    }

    public Pow(MathFunction arg, double n) {
        mathFunction = new FPowN(arg, n);
    }

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
}