package therap.dhatree.UserService.model;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(unique = true, nullable = false)
    private String token;
    
    @Column(name = "expires_at", nullable = false)
    private ZonedDateTime expiresAt;
    
    @Column(name = "is_used")
    private Boolean isUsed = false;
    
    @Column(name = "used_at")
    private ZonedDateTime usedAt;
    
    // Constructors
    public PasswordResetToken() {}
    
    public PasswordResetToken(User user, String token, ZonedDateTime expiresAt) {
        this.user = user;
        this.token = token;
        this.expiresAt = expiresAt;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public Boolean getIsUsed() {
        return isUsed;
    }
    
    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
    
    public ZonedDateTime getUsedAt() {
        return usedAt;
    }
    
    public void setUsedAt(ZonedDateTime usedAt) {
        this.usedAt = usedAt;
    }
    
    // Helper methods
    public boolean isExpired() {
        return ZonedDateTime.now().isAfter(expiresAt);
    }
    
    public boolean isValid() {
        return !isExpired() && !isUsed;
    }
    
    public void markAsUsed() {
        this.isUsed = true;
        this.usedAt = ZonedDateTime.now();
    }
}
