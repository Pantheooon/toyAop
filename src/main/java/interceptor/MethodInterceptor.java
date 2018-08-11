package interceptor;

import advice.JoinPoint;
import advice.MethodInvocation;

import java.lang.reflect.InvocationTargetException;

public interface MethodInterceptor {


    Object invoke(MethodInvocation invocation) throws InvocationTargetException, IllegalAccessException;
}
