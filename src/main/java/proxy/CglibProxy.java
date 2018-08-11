package proxy;

import java.lang.reflect.Method;
import java.util.Set;

public class CglibProxy<T> extends AbstractAopProxy<T> {
    public CglibProxy(Set<Method> methods, T target) {
        super(methods, target);
    }

    public T get(T o) {
        return null;
    }
}
