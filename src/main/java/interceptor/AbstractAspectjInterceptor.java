package interceptor;

import advice.Advice;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AbstractAspectjInterceptor<T> implements Advice {

    private Advice advice;

    private String targetName;

    private Class<T> clazz;

    private Object[] parameters;


    protected AbstractAspectjInterceptor(Method method) {

    }


    public Object invokeAdviceMethod() {
        return null;
    }
}
