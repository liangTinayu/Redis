package com.lty.redis;

import redis.clients.jedis.Jedis;

/**
 * 主从复制
 *
 */
public class TestMS {

    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("192.168.11.205",6379);
        Jedis jedis_S = new Jedis("192.168.11.205",6380);
        //设置成从数据库
        jedis_S.slaveof("192.168.11.205",6379);

        jedis_M.set("key10","test10");
        //jvm运行是非常快的 所以有可能还没有同步到附属数据库上
        //这样可通过隔段时间再次查询获得本次数据
        String result = jedis_S.get("key10");
        System.out.println("结果是："+result);
    }
}
