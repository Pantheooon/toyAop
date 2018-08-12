package advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface JoinPoint {


    Object proceed() throws InvocationTargetException, IllegalAccessException;

}
