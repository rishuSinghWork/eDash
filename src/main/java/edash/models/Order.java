package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Product name cannot be null")
	@Column(name = "product_id", nullable = false)
	private Long productId;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	@Column(nullable = false)
	private int quantity;
	
	@NotBlank(message = "Status is mandatory")
	@Column(nullable = false)
	private String status;
	
	@Column(name = "created_id", nullable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	// Getter and Setter 
	public Long getId() { return id; }
	public Long getProductId() { return productId; }
	public int getQuantity() { return quantity; }
	public String getStatus() { return status; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	
	public void setId(Long id) { this.id = id; }
	public void setProductId(Long productId) { this.productId = productId; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public void setStatus(String status) { this.status = status; }
}
