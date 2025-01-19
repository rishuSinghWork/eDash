package edash.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Set;

public class RedisUtils {
	
	private static Jedis jedis;
	
	// Initialize the Redis connection  
	static {
		try {
			jedis = new Jedis("localhost", 6379);
			System.out.println("Connected to Redis");
		} catch (Exception e){
			System.err.println("Falied to connect to Redis: "  + e.getMessage());
		}
	}
	
	// Catch data with an expiration time
	public static void cacheData(String key, String value, int expirationSeconds) {
		jedis.setex(key, expirationSeconds, value);
	}
	
	// Retrieve data from cache
	public static String getCachedData(String key) {
		return jedis.get(key);
	}
	
	// Publish message to a Redis channel
	public static void publish(String channel, String message) {
		jedis.publish(channel, message);
	}
	
	// Subscribe to a Redis channel 
	public static void subscribe(String channel) {
		JedisPubSub pubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("Message recieved: " + message);
			}
		};
		new Thread(() -> jedis.subscribe(pubSub, channel)).start();
	}
	
	// Get all keys in the Redis -> for debugging 
	public static Set<String> getAllKeys() {
		return jedis.keys("*");
	}
	
	// Close Redis connection
	public static void closeConnection() {
		if (jedis != null) {
			jedis.close();
		}
	}
	
}
