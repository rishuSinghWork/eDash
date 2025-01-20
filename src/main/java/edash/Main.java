package edash;

import edash.config.EntityManagerFactoryProvider;
import edash.utils.DatabaseSeeder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

//import redis.clients.jedis.Jedis;

public class Main {
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getEntityManagerFactory();
		EntityManager entityManager = null;
		
		try {
			entityManager = entityManagerFactory.createEntityManager();
			DatabaseSeeder databaseSeeder = new DatabaseSeeder(entityManager);
			databaseSeeder.seedDatabase();
			System.out.println("database seeded successfully. Application is ready to start.");
		} catch (Exception e) {
			System.err.println("Error occured: "+ e.getMessage());
			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
			if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
				entityManagerFactory.close();
			}
		}
		
//		RedisTest redisTest = new RedisTest();
//		redisTest.testKeyValueOps();
//		redisTest.testListOps();
//		redisTest.testHashOps();
//		redisTest.testSetOps();
//		redisTest.testSortedSetOps();
//		
//		try {
//			redisTest.testExpirationOps();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		redisTest.close();
		 
	}
}
