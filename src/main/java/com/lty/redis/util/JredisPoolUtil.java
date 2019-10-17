package com.lty.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//Jedis连接池
public class JredisPoolUtil {


    private static volatile JedisPool jredisPool= null;

    //单例模式 私有化构造方法
    public  JredisPoolUtil(){}

    public  static  JedisPool getJredisPoolInstance(){
            if(null == jredisPool){
                synchronized (JredisPoolUtil.class){
                    if( null == jredisPool){
                        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                        jedisPoolConfig.setMaxActive(1000);
                        jedisPoolConfig.setMaxIdle(50);
                        jedisPoolConfig.setMaxWait(100*1000);
                        jredisPool = new JedisPool(jedisPoolConfig,"192.168.11.205",6379);
                        return jredisPool;
                    }
                }
            }
            return jredisPool;
    }

    public static  void release(JedisPool jredisPool , Jedis jedis){
        if(null != jedis){
            jredisPool.returnResourceObject(jedis);
        }

    }


}
