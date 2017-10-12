package persistencedemo.biz.redis;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Service
public class RedisDemo {

    public static void testMget() {
        try {
            Jedis jedis = new Jedis();
            Set<String> keys = jedis.keys("cvfeedBackHandl_*");
            List<String> result = Lists.newArrayList();
            long t1 = System.currentTimeMillis();
            for (String key : keys) {
                result.add(jedis.get(key));
            }
            for (String src : result) {
                System.out.println(src);
            }
            System.out.println(System.currentTimeMillis() - t1);
        } finally {
            // TODO: handle finally clause
        }
    }

    public static void testPipline() {
        try {

            Jedis jedis = new Jedis();
            // Jedis jedis = RedisCacheClient.getInstrance().getClient();
            Set<String> keys = jedis.keys("cvfeedBackHandl_*");
            List<Object> result = Lists.newArrayList();
            Pipeline pipelined = jedis.pipelined();
            long t1 = System.currentTimeMillis();
            for (String key : keys) {
                pipelined.get("testabcd");
            }
            result = pipelined.syncAndReturnAll();
            for (Object src : result) {
                System.out.println(src);
            }
            System.out.println(System.currentTimeMillis() - t1);
        } finally {}
    }
}
