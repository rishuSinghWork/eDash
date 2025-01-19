package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "chatroom_participants", joinColumns = @JoinColumn(name = "chat_room_id"))
	@Column(name = "user_id")
	private Set<Long> participants;
	
	// Getter and Setter 
	public Long setId() { return id; }
	public String getName() { return name; }
	public Set<Long> getParticipants() { return participants; }
	
	public void setId(Long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setParticipants(Set<Long> participants) { this.participants = participants; }
}
