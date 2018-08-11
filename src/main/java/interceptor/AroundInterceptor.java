package interceptor;

import advice.MethodInvocation;

import java.lang.reflect.Method;

public class AroundInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public AroundInterceptor(Method method) {
        super(method);
    }

    public Object invoke(MethodInvocation invocation) {
        return null;
    }
}
