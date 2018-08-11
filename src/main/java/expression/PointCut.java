package expression;

import java.lang.reflect.Method;

public interface PointCut {


    String getExpression();


    Boolean match(Method method);
}
