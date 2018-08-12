package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public AfterInterceptor(Method method, PointCut cut) {
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
