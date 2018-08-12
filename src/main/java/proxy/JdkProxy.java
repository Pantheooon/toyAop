package proxy;

import interceptor.MethodIntercept;

import java.util.List;

public class JdkProxy<T> implements AopProxy<T> {


    private Class[] interfaces;

    private List<MethodIntercept> intercepts;

    private T target;

    public JdkProxy(List<MethodIntercept> interceptors, T target, Class[] interfaces) {
        this.intercepts = interceptors;
        this.target = target;
        this.interfaces = interfaces;
    }

    public T get(T t) {
        return null;
    }
}
