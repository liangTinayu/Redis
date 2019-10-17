package com.lty.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

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
    }

}
