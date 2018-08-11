package interceptor;

import advice.JoinPoint;
import advice.MethodInvocation;

public interface MethodInterceptor {


    Object invoke(MethodInvocation invocation);
}
