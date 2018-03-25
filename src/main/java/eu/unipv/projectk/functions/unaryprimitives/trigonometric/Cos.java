package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

public final class Cos extends UnaryMathFunction {

    public Cos(MathFunction argument) {
        super(Math::cos, argument, "cos");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(NumberFactory.MINUS_ONE, new Mult(new Sin(arg), arg.derivative()));
    }
}
