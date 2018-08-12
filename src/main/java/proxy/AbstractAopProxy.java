package proxy;

import expression.PointCut;
import interceptor.MethodInterceptor;

import java.util.List;
import java.util.Set;

public abstract class AbstractAopProxy<T> implements AopProxy<T> {

    private List<MethodInterceptor> interceptors;

    private T target;

    public AbstractAopProxy(List<MethodInterceptor> interceptors, T target) {
        this.interceptors = interceptors;
        this.target = target;
    }

}
