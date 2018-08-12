package expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TargetObject {

    private Object target;

    private Object[] params;

    private Method method;

    public TargetObject(Object target, Object[] params, Method method) {
        this.target = target;
        this.params = params;
        this.method = method;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }

    public Object[] getParams() {
        return this.params;
    }

    public int getParamSize() {
        return params.length;
    }
}
