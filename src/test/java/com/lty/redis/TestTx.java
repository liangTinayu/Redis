package com.lty.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTx {
    public boolean trancationMethod() throws Exception{
        Jedis jedis = new Jedis("192.168.11.205",6379);
        //开启观测
        jedis.watch("balance");
        int consume = 10;
        Thread.sleep(10000);
        if( consume>Integer.parseInt(jedis.get("balance"))){
            jedis.unwatch();
            System.out.println("余额不足！！");
            return false;
        }else{
            //开启事物
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",consume);
            transaction.incrBy("debt",consume);
            transaction.exec();
            System.out.println("当前可用金额  需要还款");
            System.out.println(jedis.get("balance")+"          "+jedis.get("debt"));
            return true;
        }

    }


    public static void main(String[] args) throws Exception{
        TestTx tx = new TestTx();
        Boolean flag = tx.trancationMethod();
        System.out.println("此次操作：" +  (flag ? true :"失败"));

    }
}
