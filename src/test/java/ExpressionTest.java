import interceptor.AroundIntercept;
import org.junit.Test;

public class ExpressionTest {


    @Test
    public void test1() {
        AroundIntercept aroundInterceptor = new AroundIntercept(null);
        Class<?> superclass = aroundInterceptor.getClass().getSuperclass();
        Class<?>[] interfaces = aroundInterceptor.getClass().getInterfaces();
        System.out.println(aroundInterceptor);
        }
    }

