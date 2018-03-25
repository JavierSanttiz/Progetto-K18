package eu.unipv.projectk.functions;

public interface MathFunction  {

    double evaluate(double x);

    MathFunction derivative();
}
