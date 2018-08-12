package beans;

import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory<T> implements BeanFactory<T> {


    private Map<Class<T>, T> beans = new HashMap();


    public T getBean(Class<T> t) {
        T bean = beans.get(t);
        if (bean == null) {
            try {
                bean = t.newInstance();
                addBean(t, bean);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return bean;
    }


    public void addBean(T t) {
        addBean((Class<T>) t.getClass(), t);
    }

    public void addBean(Class<T> tClass, T t) {
        beans.put(tClass, t);
    }
}
