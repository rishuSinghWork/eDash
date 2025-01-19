package edash.repositories;

import edash.models.Order;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class OrderRepository {
	
	public void save(Order order) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(order);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public Order findById(Long id) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		Order order = entityManager.createQuery(
				"SELECT o FROM Order WHERE o.id = :id", Order.class)
				.setParameter("id", id)
				.getSingleResult();
		entityManager.close();
		return order;
	}
	
	public List<Order> findAll(){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Order> orders = entityManager.createQuery("SELECT o FROM Order s", Order.class).getResultList();
		entityManager.close();
		return orders;
	}
	
	public void delete(Order order) {
		// This would make more sense if cancellation is done in a different way in which we just change the status and stop the processing on the order
		// lets revisit this again for then 
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.remove(entityManager.merge(order));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}
