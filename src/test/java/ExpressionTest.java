import advice.Advice;
import interceptor.AroundInterceptor;
import org.junit.Test;

public class ExpressionTest {


    @Test
    public void test1() {
        AroundInterceptor aroundInterceptor = new AroundInterceptor(null);
        Class<?> superclass = aroundInterceptor.getClass().getSuperclass();
        Class<?>[] interfaces = aroundInterceptor.getClass().getInterfaces();
        System.out.println(aroundInterceptor);
        }
    }

