package edash.utils;

import edash.models.*;
import edash.repositories.*;

public class DatabaseSeeder {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;

    public DatabaseSeeder(ProductRepository productRepository,
                          UserRepository userRepository,
                          SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.supplierRepository = supplierRepository;
    }

    public void seedDatabase() {
        seedSuppliers();
        seedProducts();
        seedUsers();
    }

    private void seedSuppliers() {
        if (!supplierRepository.findAll().isEmpty()) return;

        Supplier supplier1 = new Supplier();
        supplier1.setName("ABC Electronics");
        supplier1.setContact("123-456-7890");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Home Furniture Co.");
        supplier2.setContact("987-654-3210");

        supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);

        System.out.println("Suppliers seeded successfully.");
    }

    private void seedProducts() {
        if (!productRepository.findAll().isEmpty()) return;

        // Fetch suppliers from database
        Supplier supplier1 = supplierRepository.findByName("ABC Electronics");
        Supplier supplier2 = supplierRepository.findByName("Home Furniture Co.");

        if (supplier1 == null || supplier2 == null) {
            throw new IllegalStateException("Suppliers must be seeded before products.");
        }

        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setCategory("Electronics");
        product1.setStock(10);
        product1.setPrice(1200.00);
        product1.setSupplierId(supplier1.getId());

        Product product2 = new Product();
        product2.setName("Phone");
        product2.setCategory("Electronics");
        product2.setStock(20);
        product2.setPrice(800.00);
        product2.setSupplierId(supplier1.getId());

        Product product3 = new Product();
        product3.setName("Desk Chair");
        product3.setCategory("Furniture");
        product3.setStock(15);
        product3.setPrice(150.00);
        product3.setSupplierId(supplier2.getId());

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        System.out.println("Products seeded successfully.");
    }

    private void seedUsers() {
        if (!userRepository.findAll().isEmpty()) return;

        User user1 = new User();
        user1.setUsername("admin");
        user1.setRole("ADMIN");

        User user2 = new User();
        user2.setUsername("john_doe");
        user2.setRole("USER");

        User user3 = new User();
        user3.setUsername("jane_doe");
        user3.setRole("USER");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        System.out.println("Users seeded successfully.");
    }
}
