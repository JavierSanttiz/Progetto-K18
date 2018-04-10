package eu.unipv.projectk.functions;

public abstract class NumberMathFunction implements MathFunction {
    protected final double number;
    protected final MathFunction arg;

    protected final String symbol;

    public NumberMathFunction(double number, MathFunction arg, String symbol) {
        this.number = number;
        this.arg = arg;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "(" + number + " " + symbol + " " + "(" + arg + ")" + ")";
    }
}
