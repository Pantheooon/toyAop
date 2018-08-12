package interceptor;

import advice.Advice;
import expression.PointCut;

import java.lang.reflect.Method;

public class AbstractAspectjInterceptor<T> implements Advice {

    private Advice advice;

    private String targetName;

    private Class<T> clazz;

    private Object[] parameters;


    protected AbstractAspectjInterceptor(Method method, PointCut cut) {

    }


    public Object invokeAdviceMethod() {
        return null;
    }
}
