package eu.unipv.projectk;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.arithmetic.Sub;
import eu.unipv.projectk.functions.identity.Identity;

public class Main {

    public static void main(String[] args) {
        MathFunction f = new Sub(Identity.X, Identity.X);
        System.out.println(f);
    }
}
