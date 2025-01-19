package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username is mandatory")
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank(message = "Role is mandatory")
	@Column(nullable = false)
	private String role;
	
	// Getter and Setters
	public Long getId() { return id; }
	public String getUsername() { return username; }
	public String getRole() { return role; }
	
	public void setId(Long id) { this.id = id; }
	public void setName(String username) { this.username = username; }
	public void sertRole(String role) { this.role = role; }
}
