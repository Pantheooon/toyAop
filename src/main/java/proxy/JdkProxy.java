package proxy;

import expression.PointCut;
import interceptor.MethodInterceptor;

import java.util.List;
import java.util.Set;

public class JdkProxy<T> extends AbstractAopProxy<T> {


    private Class[] interfaces;

    public JdkProxy(List<MethodInterceptor> interceptors, T target, Class[] interfaces) {
        super(interceptors, target);
        this.interfaces = interfaces;
    }

    public T get(T o) {
        return null;
    }
}
