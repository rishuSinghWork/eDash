package edash.utils;

import edash.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class DatabaseSeeder {

    private final EntityManager entityManager;

    public DatabaseSeeder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void seedDatabase() {
        seedSuppliers();
        seedProducts();
        seedUsers();
        seedChatRooms();
        seedMessages();
    }

    private void seedSuppliers() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Check if suppliers exist
            List<Supplier> existingSuppliers = entityManager.createQuery("SELECT s FROM Supplier s", Supplier.class)
                    .getResultList();
            if (!existingSuppliers.isEmpty()) {
                transaction.commit();
                return;
            }

            // Seed suppliers
            Supplier supplier1 = new Supplier();
            supplier1.setName("ABC Electronics");
            supplier1.setContact("123-456-7890");

            Supplier supplier2 = new Supplier();
            supplier2.setName("Home Furniture Co.");
            supplier2.setContact("987-654-3210");

            entityManager.persist(supplier1);
            entityManager.persist(supplier2);

            transaction.commit();
            System.out.println("Suppliers seeded successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error seeding suppliers: " + e.getMessage());
        }
    }

    private void seedProducts() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Check if products exist
            List<Product> existingProducts = entityManager.createQuery("SELECT p FROM Product p", Product.class)
                    .getResultList();
            if (!existingProducts.isEmpty()) {
                transaction.commit();
                return;
            }

            // Fetch suppliers for association
            Supplier supplier1 = entityManager.createQuery("SELECT s FROM Supplier s WHERE s.name = :name", Supplier.class)
                    .setParameter("name", "ABC Electronics")
                    .getSingleResult();

            Supplier supplier2 = entityManager.createQuery("SELECT s FROM Supplier s WHERE s.name = :name", Supplier.class)
                    .setParameter("name", "Home Furniture Co.")
                    .getSingleResult();

            // Seed products
            Product product1 = new Product();
            product1.setName("Smartphone");
            product1.setCategory("Electronics");
            product1.setStock(50);
            product1.setPrice(699.99);
            product1.setSupplier(supplier1.getId());

            Product product2 = new Product();
            product2.setName("Office Chair");
            product2.setCategory("Furniture");
            product2.setStock(20);
            product2.setPrice(89.99);
            product2.setSupplier(supplier2.getId());

            entityManager.persist(product1);
            entityManager.persist(product2);

            transaction.commit();
            System.out.println("Products seeded successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error seeding products: " + e.getMessage());
        }
    }

    private void seedUsers() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Check if users exist
            List<User> existingUsers = entityManager.createQuery("SELECT u FROM User u", User.class)
                    .getResultList();
            if (!existingUsers.isEmpty()) {
                transaction.commit();
                return;
            }

            // Seed users
            User admin = new User();
            admin.setUsername("admin");
            admin.setRole("Admin");

            User user1 = new User();
            user1.setUsername("john_doe");
            user1.setRole("Staff");

            User user2 = new User();
            user2.setUsername("jane_doe");
            user2.setRole("Manager");

            entityManager.persist(admin);
            entityManager.persist(user1);
            entityManager.persist(user2);

            transaction.commit();
            System.out.println("Users seeded successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error seeding users: " + e.getMessage());
        }
    }

    private void seedChatRooms() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Check if chat rooms exist
            List<ChatRoom> existingChatRooms = entityManager.createQuery("SELECT c FROM ChatRoom c", ChatRoom.class)
                    .getResultList();
            if (!existingChatRooms.isEmpty()) {
                transaction.commit();
                return;
            }

            // Fetch users for association
            User admin = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "admin")
                    .getSingleResult();

            User user1 = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "john_doe")
                    .getSingleResult();

            User user2 = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "jane_doe")
                    .getSingleResult();

            // Seed chat rooms
            ChatRoom generalRoom = new ChatRoom();
            generalRoom.setName("General");
            generalRoom.setParticipants(Set.of(admin.getId(), user1.getId(), user2.getId()));

            ChatRoom supportRoom = new ChatRoom();
            supportRoom.setName("Support");
            supportRoom.setParticipants(Set.of(admin.getId(), user2.getId()));

            entityManager.persist(generalRoom);
            entityManager.persist(supportRoom);

            transaction.commit();
            System.out.println("ChatRooms seeded successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error seeding chat rooms: " + e.getMessage());
        }
    }

    private void seedMessages() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Check if messages exist
            List<Message> existingMessages = entityManager.createQuery("SELECT m FROM Message m", Message.class)
                    .getResultList();
            if (!existingMessages.isEmpty()) {
                transaction.commit();
                return;
            }

            // Fetch users for association
            User admin = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "admin")
                    .getSingleResult();

            User user1 = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", "john_doe")
                    .getSingleResult();

            // Seed messages
            Message message1 = new Message();
            message1.setContent("Welcome to the system!");
            message1.setSenderId(admin.getId());
            message1.setReciepientId(user1.getId());
            //message1.setTimestamp(new Timestamp(System.currentTimeMillis()));

            entityManager.persist(message1);

            transaction.commit();
            System.out.println("Messages seeded successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error seeding messages: " + e.getMessage());
        }
    }
}
