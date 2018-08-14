package expression;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParseChain implements ExpressionParse {

    private static List<ExpressionParse> expressionParseList = new ArrayList<>();


    public ExpressionParseChain(String expression) {
        initChain(expression);
    }

    private void initChain(String expression) {
        expressionParseList.add(new ExpressionDiscovery(expression));
    }

    @Override
    public Boolean match(Method method) {
        for (ExpressionParse expressionParse : expressionParseList) {
            if (!expressionParse.match(method)) {
                return false;
            }
        }
        return true;
    }
}
