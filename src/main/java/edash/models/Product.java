package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Product name is mandatory")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Category is mandatory")
	@Column
	private String category;
	
	@Min(value = 0, message = "Stock cannot be negative")
	@Column(nullable = false)
	private int stock;
	
	@Min(value = 0, message = "Price cannot be negative")
	@Column(nullable = false)
	private double price;
	
	@NotNull
	@Column(name = "supplier_id", nullable = false)
	private Long supplierId;
	
	// Getter and Setter 
	public Long getId() { return id; }
	public String getName() { return name; }
	public String getCategory() { return category; }
	public int getStock() { return stock; }
	public double getPrice() { return price; }
	public Long getSupplierId() { return supplierId; }
	
	
	public void setId(Long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setCategory(String category) { this.category = category; }
	public void setStock(int stock) { this.stock = stock; }
	public void setPrice(double price) { this.price = price; }
	public void setSupplier(Long supplierId) {this.supplierId = supplierId; }
}
