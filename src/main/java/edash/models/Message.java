package edash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "sender_id", nullable = false)
	private Long senderId;
	
	@Column(name = "recipient_id ")
	private Long recipientId ;
	
	@NotBlank(message = "Message content is mandatory")
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private LocalDateTime timestamp;
	
	@PrePersist
	protected void onCreate() {
		this.timestamp = LocalDateTime.now();
	}
	
	// Getter and Setter
	public Long getId() { return id; }
	public Long getSenderId() { return senderId; }
	public Long getReciepientId() { return recipientId; }
	public String getContent() { return content; }
	public LocalDateTime getTimeStamp() { return timestamp; }
	
	public void setId(Long id) { this.id = id; }
	public void setSenderId(Long senderId) { this.senderId = senderId; }
	public void setReciepientId(long recipientId) { this.recipientId = recipientId; }
	public void setContent(String content) { this.content = content; }
}
