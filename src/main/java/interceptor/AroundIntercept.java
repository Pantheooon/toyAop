package interceptor;

import advice.MethodInvocation;
import expression.PointCut;

import java.lang.reflect.Method;

public class AroundIntercept extends AbstractAspectjInterceptor implements MethodIntercept {


    public AroundIntercept(Method method, PointCut cut) {
        super(method,cut);
    }

    public Object invoke(MethodInvocation invocation) {
        return null;
    }
}
