package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterIntercept extends AbstractAspectjInterceptor implements MethodIntercept {


    public AfterIntercept(Method method, PointCut cut) {
        super(method,cut);
    }

    public Object invoke(MethodInvocation invocation) throws InvocationTargetException, IllegalAccessException {
        try {
            return invocation.proceed();
        } finally {
            invokeAdviceMethod();
        }
    }

}
