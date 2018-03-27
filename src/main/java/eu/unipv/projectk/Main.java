package eu.unipv.projectk;

import eu.unipv.projectk.functions.miscellanea.Combo;
import eu.unipv.projectk.functions.Identity;
import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.unaryprimitives.trigonometric.Sin;

public class Main {

    public static void main(String[] args) {
        MathFunction mf = new Combo(1, new Sin(Identity.X));

        System.out.print(mf);
    }
}
