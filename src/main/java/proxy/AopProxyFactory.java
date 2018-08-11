package proxy;

import advice.Advice;
import annotation.After;
import annotation.Around;
import annotation.Aspect;
import annotation.Before;
import interceptor.AfterInterceptor;
import interceptor.AroundInterceptor;
import interceptor.BeforeInterceptor;

import java.lang.reflect.Method;
import java.util.*;

public class AopProxyFactory<T> implements ProxyFactory<T> {


    private List<Class> parsedAspect = new ArrayList<Class>();

    private Set<Method> adviceList = new HashSet<Method>();


    public T get(T t) {
        return (T) createProxy(t).get(null);
    }

    public void addAspect(Class aspectClass) {
        if (validate(aspectClass)) {
            throw new IllegalArgumentException("切面类缺少aspect注解");
        }
        if (parsedAspect.contains(aspectClass)) {
            return;
        }
        Method[] declaredMethods = aspectClass.getDeclaredMethods();
        if (declaredMethods == null || declaredMethods.length == 0) {
            return;
        }
        findAdviceMethod(declaredMethods);
        parsedAspect.add(aspectClass);
    }

    /**
     * 查找切面方法中可作为增强的方法
     *
     * @param methods
     */
    private void findAdviceMethod(Method[] methods) {
        if (methods == null || methods.length == 0) {
            return;
        }
        for (Method method : methods) {
            if (isAdvice(method)) {
                adviceList.add(method);
            }
        }
    }

    /**
     * 判断该方法是不是增强的方法
     *
     * @param method
     * @return
     */
    private boolean isAdvice(Method method) {
        return method.getAnnotation(Before.class) != null
                || method.getAnnotation(After.class) != null
                || method.getAnnotation(Around.class) != null;
    }

    /**
     * 判断该类是不是切面
     *
     * @param a
     * @return
     */
    private Boolean validate(Class a) {
        if (a == null) {
            return false;
        }
        if (!a.isAnnotationPresent(Aspect.class)) {
            return false;
        }

        return true;
    }

    public AopProxy createProxy(T t) {
        Class<?>[] interfaces = t.getClass().getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            return new CglibProxy(adviceList, t);
        }
        return new JdkProxy(adviceList, t, interfaces);
    }

}
