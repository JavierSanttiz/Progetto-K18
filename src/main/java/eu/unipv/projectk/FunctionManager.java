package eu.unipv.projectk;

import eu.unipv.projectk.functions.FooMathFunction;

import java.util.Arrays;

public class FunctionManager {
    private static final int STD_SIZE = 5;
    private FooMathFunction functions[];
    private int index, resizeCounter;

    public FunctionManager() {
        functions = new FooMathFunction[STD_SIZE];
        index = 0;
        resizeCounter = 1;
    }

    public void add(FooMathFunction f) {
        if (index >= STD_SIZE * resizeCounter) {
            functions = Arrays.copyOf(functions, functions.length + STD_SIZE);
            resizeCounter++;
        }

        functions[index++] = f;
    }

    public FooMathFunction[] getFunctions() {
        return functions;
    }
}
