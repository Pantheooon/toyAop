package expression;


import java.lang.reflect.Method;

public class ExpressionDiscovery implements ExpressionParse {

    private String packageName;

    private String className;

    private String methodName;

    private String argsName;

    private String returnName;

    private String[] args;

    public ExpressionDiscovery(String expression) {

        parse(expression);
    }


    private void parse(String expression) {
    }


    @Override
    public Boolean match(Method method) {
        return true;
    }
}
