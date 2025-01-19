package edash.repositories;

import edash.models.User;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class UserRepository {
	public void save(User user) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public User findById(Long id) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		User user = entityManager.createQuery(
				"SELECT u FROM User WHERE u.id = :id", User.class)
				.setParameter("id", id)
				.getSingleResult();
		entityManager.close();
		return user;
	}
	
	public User findByUsername(String name) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		User user = entityManager.createQuery(
				"SELECT u FROM User WHERE u.name = : name", User.class)
				.setParameter("name", name)
				.getSingleResult();
		entityManager.close();
		return user;
	}
	
	public List<User> findAll(){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
		entityManager.close();
		return users;
	}
	
	public void delete(User user) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.remove(entityManager.merge(user));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}
