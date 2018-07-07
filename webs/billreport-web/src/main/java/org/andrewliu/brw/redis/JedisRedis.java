package org.andrewliu.brw.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  测试redis基于jedis的操作
 * 1、通过jedis直连redis指定实例操作redis
 * 2、通过jedis连接池操作redis
 * 3、用jedis操作redis集群
 * @Date: Created in 2018/7/5 16:14
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class JedisRedis {


    public static void main(String[] args){
        JedisRedis jedisRedis = new JedisRedis();
        jedisRedis.singleJedis();
        jedisRedis.jedisPool();
        jedisRedis.jedisCluster();

    }

    /**
     * <p>测试连接单个redis使用</p>
     */
    private  void  singleJedis(){
        //创建jedis对象
        Jedis jedis = new Jedis("172.16.94.224", 7001);
        //调用jedis对象的方法，方法名称和redis 的命令一致
        jedis.set("singleKey", "singleKeyValue");
        String string = jedis.get("singleKey");
        System.out.println(string);
        //关闭jedis
        jedis.close();

    }

    /**
     * <p>测试使用jedisPool来操作redis</p>
     */
    private  void  jedisPool(){
        JedisPool jedisPool = new JedisPool("172.16.94.224", 7001);
        //获得jedis 连接对象
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("singleKey"));
        jedis.close();

    }

    /**
     * <p>测试通过jedis使用redis集群来操作redis</p>
     */
    private void  jedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.16.94.224", 7001));
        nodes.add(new HostAndPort("172.16.94.224", 7002));
        nodes.add(new HostAndPort("172.16.94.224", 7003));
        nodes.add(new HostAndPort("172.16.94.224", 7004));
        nodes.add(new HostAndPort("172.16.94.224", 7005));
        nodes.add(new HostAndPort("172.16.94.224", 7006));

        nodes.add(new HostAndPort("172.16.94.225", 7007));
        nodes.add(new HostAndPort("172.16.94.225", 7008));
        nodes.add(new HostAndPort("172.16.94.225", 7009));

        JedisCluster cluster = new JedisCluster(nodes );
        cluster.set("clusterKey", "1000");
        System.out.println(cluster.get("clusterKey"));
        System.out.println(cluster.get("name"));


    }

}
