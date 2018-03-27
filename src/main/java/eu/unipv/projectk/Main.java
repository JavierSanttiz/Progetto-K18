package eu.unipv.projectk;

import eu.unipv.projectk.functions.Identity;
import eu.unipv.projectk.functions.miscellanea.KOnFPlusH;

public class Main {

    public static void main(String[] args) {
        KOnFPlusH f = new KOnFPlusH(-1, Identity.X);

        System.out.println(f);
    }
}
