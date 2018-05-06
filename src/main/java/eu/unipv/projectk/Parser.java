package eu.unipv.projectk;

import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String TOKEN_PREFIX = "Math.";

    private ScriptEngine engine;

    private enum Token {
        abs("abs"),
        acos("acos"),
        acosh("acosh"),
        asin("asin"),
        asinh("asinh"),
        atan("atan"),
        atanh("atanh"),
        cbrt("cbrt"),
        ceil("ceil"),
        cos("cos"),
        cosh("cosh"),
        exp("exp"),
        floor("floor"),
        log("log"),
        round("round"),
        sin("sin"),
        sinh("sinh"),
        sqrt("sqrt"),
        tan("tan"),
        tanh("tanh"),
        ;

        Token(String s) {
        }
    }

    public Parser() {
        engine = new NashornScriptEngineFactory().getScriptEngine();
    }

    public FooMathFunction parse(String s) throws ScriptException {
        for (Token t : Token.values()) {
            String strToken = t.toString();
            Pattern p = Pattern.compile(strToken);
            Matcher matcher = p.matcher(s);
            s = matcher.replaceAll(TOKEN_PREFIX + strToken);
        }

        AbstractJSObject JSFunction = (AbstractJSObject) engine.eval("function(x) { return (" + s + "); }");

        return x -> (Double) JSFunction.call(null, x);
    }
}
