package interceptor;

import advice.MethodInvocation;

import java.util.List;

public class ReflectiveMethodInvocation implements MethodInterceptor {


    List<MethodInterceptor> chain;


    public Object invoke(MethodInvocation invocation) {
        return null;
    }
}
