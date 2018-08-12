package proxy;

import expression.PointCut;
import interceptor.MethodInterceptor;

import java.util.List;
import java.util.Set;

public class CglibProxy<T> extends AbstractAopProxy<T> {

    public CglibProxy(List<MethodInterceptor> interceptors, T target) {
        super(interceptors, target);
    }

    public T get(T o) {
        return null;
    }
}
