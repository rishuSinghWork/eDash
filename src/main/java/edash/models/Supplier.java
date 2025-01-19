package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "supplier")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Supplier name is mandatory")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Contact can't be mandatory")
	@Column(nullable = false)
	private String contact;
	
	// Getter and Setter
	public Long getId() { return id; }
	public String getName() { return name; }
	public String getContact() { return contact; }
	
	public void setId(Long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setContact(String contact) { this.contact = contact; }
}
