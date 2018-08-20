import annotation.After;
import annotation.Aspect;
import annotation.Before;
import org.junit.Test;
import proxy.AopProxyFactory;

public class ProxyTest {


    @Test
    public void getProxy() {
        AopProxyFactory aopProxyFactory = new AopProxyFactory();
        aopProxyFactory.addAspect(new AspectTest());
        Haha haha = new Haha();
        Haha proxy = (Haha) aopProxyFactory.getProxy(haha);
        proxy.test();
    }

    @Aspect
    public class AspectTest {

        public AspectTest() {
        }


        @After("excution(* *.*.*(..))")
        public void after() {
            System.out.println(456);
        }

        @Before("excution(* *.*.*(..))")
        public void test() {
            System.out.println(123);
        }
    }

}
