package expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface PointCut {


    String getExpression();


    Boolean match(Method method);


    AdviceType getAdviceType();

    Object invoke(Object[] args) throws InvocationTargetException, IllegalAccessException;

}
