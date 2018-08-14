package proxy;

public interface ProxyFactory<T> {


    T getProxy(T t);


    void addAspect(Object aspectClass);
}
