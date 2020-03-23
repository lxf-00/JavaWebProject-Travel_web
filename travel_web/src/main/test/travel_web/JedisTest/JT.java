package travel_web.JedisTest;

import com.travelInfo.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JT {
    @Test
    public void test1(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("a", "b");
    }
}
