package interceptor;

import advice.Advice;

public class AbstractAspectjInterceptor<T> {

    private Advice advice;

    private String targetName;

    private Class<T> clazz;

    private Object[] parameters;


    public Object invokeAdviceMethod() {
        return null;
    }
}
