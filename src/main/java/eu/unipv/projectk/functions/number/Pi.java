package eu.unipv.projectk.functions.number;

/**
 * Represents the following function: f(x) = π
 */
public final class Pi extends Number {

    public Pi() {
        super(Math.PI);
    }

    @Override
    public String toString() {
        return "π";
    }
}
