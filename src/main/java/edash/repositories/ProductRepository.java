package edash.repositories;

import edash.models.Product;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

// We generally in best practice should have a DAO here thus not directly exposing the DB transactions
public class ProductRepository {
	
	public void save(Product product) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(product);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public Product findById(Long id) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		Product product = entityManager.find(Product.class, id);
		entityManager.close();
		return product;
	}
	
	public List<Product> findAll() {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		entityManager.close();
		return products;
	}
	
	public void delete(Product product) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.remove(entityManager.merge(product));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
}
