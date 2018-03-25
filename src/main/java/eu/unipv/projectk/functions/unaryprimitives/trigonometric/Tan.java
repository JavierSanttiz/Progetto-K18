package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

public final class Tan extends UnaryMathFunction {

    public Tan(MathFunction argument) {
        super(Math::tan, argument, "tan");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Add(NumberFactory.ONE, new Pow2(new Tan(arg))), arg.derivative());
    }
}
