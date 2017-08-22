/**
 * Created by lc on 2017/7/18.
 */

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo1 {

    @Test
    public void demo1(){
        Jedis jedis = new Jedis("119.23.221.39",6379);
        jedis.set("name","ez");
        String value = jedis.get("name");
        System.out.println(value);
        jedis.close();
    }


    @Test
    public void demo2(){
        //获取连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        //最大空闲连接数
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config,"119.23.221.39",6379);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set("name","张三");
            String name = jedis.get("name");
            System.out.println(name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
            }
            if (jedisPool!=null){
                jedisPool.close();
            }
        }
    }
}
