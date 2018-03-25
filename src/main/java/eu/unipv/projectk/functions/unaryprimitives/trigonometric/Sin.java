package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

public final class Sin extends UnaryMathFunction {

    public Sin(MathFunction argument) {
        super(Math::sin, argument, "sin");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Cos(arg), arg.derivative());
    }

}
