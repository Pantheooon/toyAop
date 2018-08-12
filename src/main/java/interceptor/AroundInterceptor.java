package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.Method;

public class AroundInterceptor extends AbstractAspectjInterceptor implements MethodInterceptor {


    public AroundInterceptor(Method method, PointCut cut) {
        super(method,cut);
    }

    public Object invoke(MethodInvocation invocation) {
        return null;
    }
}
