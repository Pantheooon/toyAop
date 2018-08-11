package proxy;

import java.lang.reflect.Method;
import java.util.Set;

public abstract class AbstractAopProxy<T> implements AopProxy<T> {

    private Set<Method> methodSet;

    private T target;

    public AbstractAopProxy(Set<Method> methods,T target) {
        this.methodSet = methods;
        this.target = target;
    }

}
