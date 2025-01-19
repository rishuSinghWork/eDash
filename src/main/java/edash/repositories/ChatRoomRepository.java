package edash.repositories;

import edash.models.ChatRoom;
import edash.config.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ChatRoomRepository {
	
	public void save(ChatRoom chatRoom) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.persist(chatRoom);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public ChatRoom findById(Long id) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		ChatRoom chatRoom = entityManager.createQuery(
				"SELECT cr FROM ChatRoom WHERE cr.id = :id", ChatRoom.class)
				.setParameter("id", id)
				.getSingleResult();
		entityManager.close();
		return chatRoom;
	}
	
	public ChatRoom findByName(String name) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		ChatRoom chatRoom = entityManager.createQuery(
				"SELECT cr FROM ChatRoom WHERE cr.name = :name", ChatRoom.class)
				.setParameter("name", name)
				.getSingleResult();
		entityManager.close();
		return chatRoom;
	}
	
	public List<ChatRoom> findAll(){
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		List<ChatRoom> chatRooms = entityManager.createQuery("SELCT cr FROM ChatRom cr", ChatRoom.class).getResultList();
		entityManager.close();
		return chatRooms;
	}
	
	public void delete(ChatRoom chatRoom) {
		EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			entityManager.remove(entityManager.merge(chatRoom));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}
