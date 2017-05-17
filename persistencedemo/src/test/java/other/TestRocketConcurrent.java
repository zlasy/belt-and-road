package other;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dang.rocket.core.util.ConvertUtil;
import com.dang.rocket.core.util.HttpUtil;

public class TestRocketConcurrent {

    Logger logger = LoggerFactory.getLogger(TestRocketConcurrent.class);
    
    static String url1 = "http://10.255.242.67:8100/check_is_enterprise_user.php?custid=50100551&result_format=json&platform=php";
    static String url2 = "http://10.3.253.111:8000/shopinner/store/getStoreGeneral?info_type=shopType&shop_id=489";
    
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
                public synchronized void run() {
                    long start = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + "|start|" + start);
                    HttpUtil.getPageHTML(url1, Charset.forName(ConvertUtil.UTF8), true, 5000);
                    long spend = System.currentTimeMillis() - start;
                    System.out.println(Thread.currentThread().getName() + "|end|" + spend);
                }
            });
        }
    }

}
