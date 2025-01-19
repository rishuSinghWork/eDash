package edash;

//import redis.clients.jedis.Jedis;

public class Main {
	public static void main(String[] args) {
		RedisTest redisTest = new RedisTest();
		redisTest.testKeyValueOps();
		redisTest.testListOps();
		redisTest.testHashOps();
		redisTest.testSetOps();
		redisTest.testSortedSetOps();
		
		try {
			redisTest.testExpirationOps();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		redisTest.close();
		 
	}
}
