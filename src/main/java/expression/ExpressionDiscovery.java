package expression;

import java.lang.reflect.Method;

public interface ExpressionDiscovery {


    Boolean match(Method method);
}
