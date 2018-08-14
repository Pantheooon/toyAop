package interceptor;

import advice.Advice;
import expression.PointCut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AbstractAspectjInterceptor<T> implements Advice {

    private PointCut pointCut;

    private Method method;

    protected AbstractAspectjInterceptor(Method method, PointCut cut) {
        this.pointCut = cut;
        this.method = method;
    }


    public Object invokeAdviceMethod() throws InvocationTargetException, IllegalAccessException {

        return pointCut.invoke(null);
    }
}
