package edash.config;

import edash.utils.EnvConfig;
import redis.clients.jedis.Jedis;

public class RedisConnection {
	private static Jedis jedis;
	
	// Singleton pattern to ensure a single Redis Connection
	public static Jedis getConnection() {
		if (jedis == null || !jedis.isConnected()) {
			String redisHost = EnvConfig.get("REDIS_HOST");
			int redisPort = Integer.parseInt(EnvConfig.get("REDIS_PORT"));
			
			jedis = new Jedis(redisHost, redisPort);
			System.out.println("Connected to Redis!");
		}
		return jedis;
	}
	
	public static void closeConnection() {
		if (jedis != null) {
			jedis.close();
			System.out.println("Redis connection closed");
		}
	}
}
