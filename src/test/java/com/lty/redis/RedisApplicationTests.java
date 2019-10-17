package com.lty.redis;

import com.lty.redis.util.JredisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void connect(){

        Jedis jedis = new Jedis("192.168.11.205",6379);
        jedis.connect();
        System.out.println("jedis.ping():" +jedis.ping());
   //String

        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        System.out.println(jedis.get("k3"));
        jedis.append("k1","append this value");
        jedis.select(1);
        jedis.set("k1","0");
        jedis.incr("k1");
        System.out.println("加后的结果为："+jedis.get("k1"));

    }

    @Test
    public  void testPool(){
        JedisPool jedisPool = JredisPoolUtil.getJredisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        jedis.set("k11","v11");
        System.out.println(jedis.get("k11"));
        JredisPoolUtil.release(jedisPool,jedis);
    }

}
