package interceptor;

import advice.MethodInvocation;

public class AfterInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {




    public Object invoke(MethodInvocation invocation) {
        try {
            return invocation.proceed();
        } finally {
            invokeAdviceMethod();
        }
    }

}
