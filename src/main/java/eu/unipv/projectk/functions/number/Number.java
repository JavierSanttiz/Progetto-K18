package eu.unipv.projectk.functions.number;

import eu.unipv.projectk.functions.MathFunction;

/**
 * Represents the following function: f(x) = n
 * <b>
 *     Mind yourself! Number should be used when a
 *     function like f(x) = n is needed. If you have to
 *     represent numbers in functions other than f(x) = n
 *     use the KFPlusH class.
 * <b/>
 */
public class Number implements MathFunction {
    private final double n;

    Number(double n) {
        this.n = n;
    }

    /**
     * Get the value of n without using evaluate(double x).
     * @return  the constant n
     */
    public double getValue() {
        return n;
    }

    @Override
    public double evaluate(double x) {
        return n;
    }

    @Override
    public MathFunction derivative() {
        return new Number(0);
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}
