package proxy;

import annotation.After;
import annotation.Around;
import annotation.Aspect;
import annotation.Before;
import beans.BeanFactory;
import beans.DefaultBeanFactory;
import expression.AdviceType;
import expression.AspectjExpressionPointCut;
import expression.MetaPointCut;
import expression.PointCut;
import interceptor.AfterInterceptor;
import interceptor.AroundInterceptor;
import interceptor.BeforeInterceptor;
import interceptor.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.*;

public class AopProxyFactory<T> implements ProxyFactory<T> {


    private List<Class> parsedAspect = new ArrayList<Class>();

    private Set<PointCut> pointCut = new HashSet<PointCut>();

    private BeanFactory beanFactory = new DefaultBeanFactory();

    public T get(T t) {
        beanFactory.addBean(t);
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
        findAdviceMethod(declaredMethods, beanFactory.getBean(aspectClass));
        parsedAspect.add(aspectClass);
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
            return new CglibProxy(findCanApplyAdvice4Class(t.getClass()), t);
        }
        return new JdkProxy(findCanApplyAdvice4Class(t.getClass()), t, interfaces);
    }


    private List<MethodInterceptor> findCanApplyAdvice4Class(Class c) {
        List<MethodInterceptor> methodInterceptors = new ArrayList<MethodInterceptor>();
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            methodInterceptors.addAll(findCanApplyAdvice4Method(declaredMethod));
        }
        return methodInterceptors;
    }

    private List<MethodInterceptor> findCanApplyAdvice4Method(Method declaredMethod) {
        List<MethodInterceptor> methodInterceptors = new ArrayList<MethodInterceptor>();
        if (getPointCut().size() == 0) {
            return methodInterceptors;
        }
        for (PointCut cut : pointCut) {
            if (cut.match(declaredMethod)) {
                AdviceType adviceType = cut.getAdviceType();
                switch (adviceType) {
                    case AFTER:
                        methodInterceptors.add(new AfterInterceptor(declaredMethod, cut));
                        break;
                    case AROUND:
                        methodInterceptors.add(new BeforeInterceptor(declaredMethod, cut));
                        break;
                    case BEFORE:
                        methodInterceptors.add(new AroundInterceptor(declaredMethod, cut));
                        break;
                }
            }
        }
        return methodInterceptors;
    }

    private Set<PointCut> getPointCut() {
        return this.pointCut;
    }
}
