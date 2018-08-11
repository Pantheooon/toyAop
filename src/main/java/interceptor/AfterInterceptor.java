package interceptor;

import advice.MethodInvocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public AfterInterceptor(Method method) {
        super(method);
    }

    public Object invoke(MethodInvocation invocation) throws InvocationTargetException, IllegalAccessException {
        try {
            return invocation.proceed();
        } finally {
            invokeAdviceMethod();
        }
    }

}
