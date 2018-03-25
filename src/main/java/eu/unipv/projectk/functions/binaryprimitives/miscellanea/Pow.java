package eu.unipv.projectk.functions.binaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Add;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.number.Number;

public final class Pow extends BinaryMathFunction {

    public Pow(MathFunction argument1, MathFunction argument2) {
        super(Math::pow, argument1, argument2, "^");
    }

    @Override
    public MathFunction derivative() {
        if (arg1 instanceof MathFunction && arg2.getClass().equals(Number.class)) {
            Number n = NumberFactory.valueOf(((Number) arg2).getValue() - 1);

            return new Mult(new Mult(arg2, new Pow(arg1, n)), arg1.derivative());
        } else if (arg1.getClass().equals(Number.class) && arg2 instanceof MathFunction) {
            return new Mult(new Pow(NumberFactory.E, new Mult(arg2, new Log(NumberFactory.E, arg1))), new Log(NumberFactory.E, arg1));
        } else {
            return new Mult(new Pow(arg1, arg2), new Add(new Mult(arg2.derivative(), new Log(NumberFactory.E, arg1)), new Mult(arg2, new Div(arg1.derivative(), arg1))));
        }
    }
}
