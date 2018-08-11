package expression;

import java.beans.Expression;
import java.lang.reflect.Method;

public class AspectjExpression implements PointCut {


    private String expression;

    private ExpressionDiscovery expressionDiscovery;






    public AspectjExpression(String expression){
        this.expression = expression;

    }

    public String getExpression() {
        return expression;
    }

    public Boolean match(Method method) {
        return null;
    }
}
