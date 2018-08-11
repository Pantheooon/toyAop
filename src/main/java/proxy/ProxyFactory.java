package proxy;

public interface ProxyFactory<T> {


    T get(T t);


    void addAspect(Class aspectClass);
}
