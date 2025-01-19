package edash.repositories;

import edash.models.Message;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class MessageRepository {
	
	public void save(Message message) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(message);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Message> findBySenderId(Long senderId){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Message> messages = entityManager.createQuery(
				"SELECT m FROM Messages WHERE m.senderId = :senderId", Message.class)
				.setParameter("senderId", senderId)
				.getResultList();
		entityManager.close();
		return messages;
	}
	
	public List<Message> findByRecipientId(Long recipientId){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Message> messages = entityManager.createQuery(
				"SELECT m FROM Messages WHERE m.recipientId = :recipientId", Message.class)
				.setParameter("recipientId", recipientId)
				.getResultList();
		entityManager.close();
		return messages;
	}
	
	public List<Message> findAll(){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<Message> messages = entityManager.createQuery("SELCT m FROM Messages m", Message.class).getResultList();
		entityManager.close();
		return messages;
	}
}
