package interceptor;

import advice.JoinPoint;
import advice.MethodInvocation;
import expression.TargetObject;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectiveMethodInvocation implements MethodInvocation, JoinPoint {


    private List<MethodIntercept> chain;

    private TargetObject targetObject;

    public ReflectiveMethodInvocation(List<MethodIntercept> chain, TargetObject object) {
        this.chain = chain;
        this.targetObject = object;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {

        int invokedSize = 0;

        if (invokedSize == chain.size()) {
            return invokeJoinPoint();
        }

        for (MethodIntercept methodIntercept : chain) {
            methodIntercept.invoke(this);
        }
        return null;
    }

    private Object invokeJoinPoint() throws InvocationTargetException, IllegalAccessException {
        return targetObject.invoke();
    }

    public Method getMethod() {
        return null;
    }
}
