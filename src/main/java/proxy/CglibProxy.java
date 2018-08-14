package proxy;

import advice.JoinPoint;
import advice.MethodInvocation;
import expression.TargetObject;
import interceptor.MethodIntercept;
import interceptor.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CglibProxy<T> implements AopProxy<T>, MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    private List<MethodIntercept> intercepts;

    private T target;

    public CglibProxy(List<MethodIntercept> interceptors, T target) {
        this.intercepts = interceptors;
        this.target = target;
    }

    public T get(T o) {
        enhancer.setSuperclass(o.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws InvocationTargetException, IllegalAccessException {
        JoinPoint invocation = new ReflectiveMethodInvocation(intercepts, new TargetObject(target, args, method));
        return invocation.proceed();
    }

}
