package edash.repositories;

import edash.models.Supplier;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class SupplierRepository {
	
	public void save(Supplier supplier) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(supplier);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public Supplier findByName(String name) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		Supplier supplier = entityManager.createQuery(
				"SELECT s FROM Supplier WHERE s.name = :name", Supplier.class)
				.setParameter("name", name)
				.getSingleResult();
		entityManager.close();
		return supplier;
	}
	
	public List<Supplier> findAll(){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Supplier> suppliers = entityManager.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList();
		entityManager.close();
		return suppliers;
	}
	
	public void delete(Supplier supplier) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.remove(entityManager.merge(supplier));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}
