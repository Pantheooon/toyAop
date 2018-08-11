package proxy;

import java.lang.reflect.Method;
import java.util.Set;

public class JdkProxy<T> extends AbstractAopProxy<T> {


    private Class[] interfaces;

    public JdkProxy(Set<Method> methods, T target, Class[] interfaces) {
        super(methods, target);
        this.interfaces = interfaces;
    }

    public T get(T o) {
        return null;
    }
}
