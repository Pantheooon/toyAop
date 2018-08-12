package expression;

import java.lang.reflect.Method;

public class AspectjExpressionPointCut implements PointCut {

    /**
     * pointcut的表达式
     */
    private String expression;

    /**
     * 增强的方法
     */
    private Method method;

    /**
     * 切面对象
     */
    private Object object;

    /**
     * 增强的类型
     */
    private AdviceType adviceType;

    /**
     * 处理表达式的解析器
     */
    private ExpressionDiscovery expressionDiscovery;


    public AspectjExpressionPointCut(MetaPointCut mpc) {
        expression = mpc.getExpression();
        method = mpc.getMethod();
        object = mpc.getAdviceType();
        adviceType = mpc.getAdviceType();
    }

    public String getExpression() {
        return expression;
    }

    public Boolean match(Method method) {
        return expressionDiscovery.match(method);
    }

    public AdviceType getAdviceType() {
        return this.adviceType;
    }
}
