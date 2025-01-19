package edash;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisTest {
	private Jedis jedis;

    public RedisTest() {
        try {
            this.jedis = new Jedis("127.0.0.1", 6379); // Use correct port
            System.out.println("Connected to Redis!");
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }

    public void testKeyValueOps() {
        try {
            System.out.println("Testing Key-Value Operations...");
            jedis.set("key1", "value1");
            System.out.println("GET key1: " + jedis.get("key1"));
            jedis.del("key1");
            System.out.println("After DELETE, GET key1: " + jedis.get("key1"));
        } catch (Exception e) {
            System.err.println("Operation error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void testListOps() {
    	System.out.println("Testing List Operations...");
    	jedis.lpush("list1", "value1", "value2", "value3");
    	System.out.println("List Contents: " + jedis.lrange("list1", 0, -1));
    	jedis.rpush("list1", "item4");
    	System.out.println("After RPUSH, List Contents: " + jedis.lrange("list1", 0, -1));
    	jedis.del("list1");
    }
    
    public void testHashOps() {
    	System.out.println("Testing Hash Operations....");
    	Map<String, String> hash = new HashMap<>();
    	hash.put("field1", "value1");
    	hash.put("field2", "value2");
    	jedis.hset("hash1", hash);
    	System.out.println("Hash Contents: " + jedis.hgetAll("hash1"));
    	jedis.hdel("hash1", "field1");
    	System.out.println("After DELETE field1, Hash Contents: " + jedis.hgetAll("hash1"));
    }
    
    public void testSetOps() {
    	System.out.println("Testing Set Operations...");
    	jedis.sadd("set1", "number1", "number2", "number3");
    	System.out.println("Set Members: " + jedis.smembers("set1"));
    	jedis.srem("set1", "member2");
    	System.out.println("After REMOVE member2, Set Members: " + jedis.smembers("set1"));
    }
    
    public void testSortedSetOps() {
    	System.out.println("Testing Set Operations...");
    	jedis.sadd("set1", "member1", "member2", "member3");
    	System.out.println("Set Members: " + jedis.smembers("set1"));
    	jedis.srem("set1", "member2");
    	System.out.println("After REMOVE member2, Set Members: " + jedis.smembers("set1"));
    }
    
    public void testExpirationOps() throws InterruptedException {
    	System.err.println("Testing Expiration and TTL...");
    	jedis.set("temp_key", "temporary value");
    	jedis.expire("temp_key", 5);
    	System.out.println("TTL for temp_key: " + jedis.ttl("temp_key"));
    	Thread.sleep(6000);
    	System.out.println("After Expiration, GET temp_key: " + jedis.get("temp_key "));
    }
    
    public void close() {
        if (jedis != null) {
            jedis.close();
            System.out.println("Redis connection closed.");
        }
    }
}
