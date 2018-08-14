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

    int invokedSize = 0;

    public Object proceed() throws InvocationTargetException, IllegalAccessException {



        if (invokedSize == chain.size()) {
            return invokeJoinPoint();
        }

        MethodIntercept methodIntercept = chain.get(invokedSize);
        invokedSize ++;
        return methodIntercept.invoke(this);
    }

    private Object invokeJoinPoint() throws InvocationTargetException, IllegalAccessException {
        return targetObject.invoke();
    }

}
