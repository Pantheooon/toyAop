package proxy;

import annotation.After;
import annotation.AfterThrowing;
import annotation.Aspect;
import annotation.Before;
import expression.AdviceType;
import expression.AspectjExpressionPointCut;
import expression.MetaPointCut;
import expression.PointCut;
import interceptor.AfterIntercept;
import interceptor.AfterThrowingIntercept;
import interceptor.BeforeIntercept;
import interceptor.MethodIntercept;

import java.lang.reflect.Method;
import java.util.*;

public class AopProxyFactory<T> implements ProxyFactory<T> {


    private List<Class> parsedAspect = new ArrayList<Class>();

    private Set<PointCut> pointCut = new HashSet<PointCut>();


    public T getProxy(T t) {
        return (T) createProxy(t).get(t);
    }

    public void addAspect(Object aspect) {
        Class<?> aClass = aspect.getClass();
        if (!validate(aClass)) {
            throw new IllegalArgumentException("切面类缺少aspect注解");
        }
        if (parsedAspect.contains(aClass)) {
            return;
        }
        Method[] declaredMethods = aClass.getDeclaredMethods();
        if (declaredMethods == null || declaredMethods.length == 0) {
            return;
        }
        findAdviceMethod(declaredMethods, aspect);
        parsedAspect.add(aClass);
    }

    /**
     * 查找切面方法中可作为增强的方法
     *
     * @param methods
     */
    private void findAdviceMethod(Method[] methods, Object adviceObject) {
        if (methods == null || methods.length == 0) {
            return;
        }
        for (Method method : methods) {
            if (isAdvice(method)) {
                MetaPointCut mpc = new MetaPointCut(method, adviceObject);
                pointCut.add(new AspectjExpressionPointCut(mpc));
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
                || method.getAnnotation(AfterThrowing.class) != null;
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
            return new CglibProxy(findCanApplyAdvice4Class(t.getClass()), t);
        }
        return new JdkProxy(findCanApplyAdvice4Class(t.getClass()), t, interfaces);
    }


    private List<MethodIntercept> findCanApplyAdvice4Class(Class c) {
        List<MethodIntercept> methodIntercepts = new ArrayList<MethodIntercept>();
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            methodIntercepts.addAll(findCanApplyAdvice4Method(declaredMethod));
        }
        return methodIntercepts;
    }

    private List<MethodIntercept> findCanApplyAdvice4Method(Method declaredMethod) {
        List<MethodIntercept> methodIntercepts = new ArrayList<MethodIntercept>();
        if (getPointCut().size() == 0) {
            return methodIntercepts;
        }
        for (PointCut cut : pointCut) {
            if (cut.match(declaredMethod)) {
                AdviceType adviceType = cut.getAdviceType();
                switch (adviceType) {
                    case AFTER:
                        methodIntercepts.add(new AfterIntercept(declaredMethod, cut));
                        break;
                    case BEFORE:
                        methodIntercepts.add(new BeforeIntercept(declaredMethod, cut));
                        break;
                    case AFTERTHROWING:
                        methodIntercepts.add(new AfterThrowingIntercept(declaredMethod, cut));
                        break;
                }
            }
        }
        return methodIntercepts;
    }

    private Set<PointCut> getPointCut() {
        return this.pointCut;
    }
}
