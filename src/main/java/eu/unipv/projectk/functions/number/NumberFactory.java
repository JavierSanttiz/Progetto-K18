package eu.unipv.projectk.functions.number;

import java.util.HashMap;

public class NumberFactory {
    public static final Number PI = new Pi();
    public static final Number E = new E();
    public static final Number ONE = new Number(1);
    public static final Number MINUS_ONE = new Number(-1);
    public static final Number ZERO = new Number(0);

    private static final HashMap<Double, Number> numberRegister = new HashMap<>();

    private NumberFactory() {}

    public static Number valueOf(double n) {
        if (Double.compare(n, Math.PI) == 0) {
            return PI;
        } else if (Double.compare(n, Math.E) == 0) {
            return E;
        } else if (Double.compare(n, 1) == 0) {
            return ONE;
        } else if (Double.compare(n, -1) == 0) {
            return MINUS_ONE;
        } else if (Double.compare(n, 0) == 0) {
            return ZERO;
        } else {
            Number number = numberRegister.get(n);

            if (number == null) {
                number = new Number(n);
                numberRegister.put(n, number);
            }

            return number;
        }
    }
}
