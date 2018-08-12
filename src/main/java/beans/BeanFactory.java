package beans;

public interface BeanFactory<T> {

    /**
     * 根据bean的class获取bean实例
     * 因为没有实现容器,此处获取直接通过反射获取一个新的bean对象
     *
     * @param t
     * @return
     */
    T getBean(Class<T> t);


    void addBean(T t);


    void addBean(Class<T> tClass, T t);
}
