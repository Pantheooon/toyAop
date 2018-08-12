package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.Method;

public class BeforeInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public BeforeInterceptor(Method method, PointCut cut) {
        super(method, cut);
    }

    public Object invoke(MethodInvocation invocation) {

        return invocation.proceed();
    }
}
