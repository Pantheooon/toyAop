package interceptor;

import advice.MethodInvocation;

import java.lang.reflect.Method;

public class BeforeInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public BeforeInterceptor(Method method) {
        super(method);
    }

    public Object invoke(MethodInvocation invocation) {

        return invocation.proceed();
    }
}
