package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterThrowingIntercept extends AbstractAspectjInterceptor implements MethodIntercept {


    public AfterThrowingIntercept(Method method, PointCut cut) {
        super(method, cut);
    }

    public Object invoke(MethodInvocation invocation) throws InvocationTargetException, IllegalAccessException {
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            invokeAdviceMethod();
            throw ex;
        }
    }
}
