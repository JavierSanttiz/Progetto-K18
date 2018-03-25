package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

public final class Atan extends UnaryMathFunction {

    public Atan(MathFunction argument) {
        super(Math::atan, argument, "atan");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(NumberFactory.ONE, new Add(NumberFactory.ONE, new Pow2(arg))), arg.derivative());
    }
}
