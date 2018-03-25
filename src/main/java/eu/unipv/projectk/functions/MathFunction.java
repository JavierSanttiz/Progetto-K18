package eu.unipv.projectk.functions;

/**
 * Each class that represents a mathematical function
 * must implement this interface.
 */
public interface MathFunction  {

    /**
     * Evaluate a function for a given number
     * @param x the value passed to the function
     * @return  the function evaluated in x
     */
    double evaluate(double x);

    /**
     * Find the function's derivative
     * @return  the function's derivative
     */
    MathFunction derivative();
}
