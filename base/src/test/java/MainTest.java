import com.example.base.BaseApplication;
import com.example.base.javautil.concurrent.exchange.ThreadA;
import com.example.base.javautil.concurrent.exchange.ThreadB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Exchanger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class MainTest {

    @Before
    public void setUp(){

    }

    @Test
    public void testExchanger(){
        Exchanger<String> exchanger = new Exchanger<>();
        Thread threadA = new Thread(new ThreadA(exchanger));
        threadA.start();
        Thread threadB = new Thread(new ThreadB(exchanger));
        threadB.start();
        System.out.println("main end...");
    }

}
