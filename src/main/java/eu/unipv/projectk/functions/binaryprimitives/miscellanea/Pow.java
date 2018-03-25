package eu.unipv.projectk.functions.binaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.number.Number;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Ln;

/**
 * <pre>
 * Represents the following functions:
 * z(n, f(x)) = n ^ f(x)
 * z(f(x), n) = f(x) ^ n
 * z(f(x), g(y)) = f(x) ^ g(x)
 * </pre>
 */
public final class Pow extends BinaryMathFunction {

    public Pow(MathFunction argument1, MathFunction argument2) {
        super(Math::pow, argument1, argument2, "^");
    }

    @Override
    public MathFunction derivative() {
        if (arg1 instanceof MathFunction && arg2.getClass().equals(Number.class)) {
            Number n = NumberFactory.valueOf(((Number) arg2).getValue() - 1);

            return Mult.chain(arg2, new Pow(arg1, n), arg1.derivative());
        } else if (arg1.getClass().equals(Number.class) && arg2 instanceof MathFunction) {
            return Mult.chain(new Pow(arg1, arg2), new Ln(arg1), arg2.derivative());
        } else {
            return new Mult(new Pow(arg1, arg2), new Add(new Mult(arg2.derivative(), new Ln(arg1)), new Div(new Mult(arg2, arg1.derivative()), arg1)));
        }
    }
}
