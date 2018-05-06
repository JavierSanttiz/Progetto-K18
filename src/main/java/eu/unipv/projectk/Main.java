package eu.unipv.projectk;

import javax.script.ScriptException;

public class Main {

    public static void main(String[] args) throws ScriptException {
        // Testing parser
        Parser p = new Parser();
        FooMathFunction f;

        f = p.parse("log(x)");

        System.out.println(f.apply(1));
    }
}
