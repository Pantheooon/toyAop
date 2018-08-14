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
        if (expression.startsWith("excution")) {
            String substring = expression.substring(9, expression.length() - 1);
            String[] split = substring.split(" ");
            returnName = split[0];
            String left = split[1];
            int i = left.lastIndexOf("(");
            int i1 = left.lastIndexOf(")");
            argsName = left.substring(i + 1, i1);
            parseArgsName(argsName);
            String names = left.substring(0, i);
            int i2 = names.lastIndexOf(".");
            methodName = names.substring(i2 + 1, names.length());
        }
    }

    private void parseArgsName(String argsName) {
        if (argsName.equals("..")) {
            argsName = "*";
            return;
        }
        args = argsName.split(",");
    }

    @Override
    public Boolean match(Method method) {
        return true;
    }
}
