package expression;

import annotation.After;
import annotation.AfterThrowing;
import annotation.Before;

import java.lang.reflect.Method;

public class MetaPointCut {

    private Method method;

    private Object adviceObject;


    private String expression;

    private AdviceType adviceType;


    public MetaPointCut(Method method, Object adviceObject) {
        this.method = method;
        this.adviceObject = adviceObject;
        parse();
    }


    public String getExpression() {
        return this.expression;
    }

    public AdviceType getAdviceType() {
        return this.adviceType;
    }

    public Method getMethod() {
        return this.method;
    }

    public Object getAdviceObject() {
        return this.adviceObject;
    }

    private void parse() {
        Before before = method.getAnnotation(Before.class);
        if (before != null) {
            adviceType = AdviceType.BEFORE;
            expression = before.value();
            return;
        }
        AfterThrowing around = method.getAnnotation(AfterThrowing.class);
        if (around != null) {
            adviceType = AdviceType.AFTERTHROWING;
            expression = around.value();
            return;
        }
        After after = method.getAnnotation(After.class);
        if (after != null) {
            adviceType = AdviceType.AFTER;
            expression = after.value();
            return;
        }
    }
}
