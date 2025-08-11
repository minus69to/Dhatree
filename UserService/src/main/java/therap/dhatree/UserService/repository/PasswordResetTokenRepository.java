package therap.dhatree.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therap.dhatree.UserService.entity.PasswordResetToken;
import therap.dhatree.UserService.entity.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
    
    // Find by token
    Optional<PasswordResetToken> findByToken(String token);
    
    // Find by user
    List<PasswordResetToken> findByUser(User user);
    
    // Find by user ID
    List<PasswordResetToken> findByUserId(UUID userId);
    
    // Find by token and user
    Optional<PasswordResetToken> findByTokenAndUser(String token, User user);
    
    // Find by token and user ID
    Optional<PasswordResetToken> findByTokenAndUserId(String token, UUID userId);
    
    // Find valid tokens (not expired and not used)
    @Query("SELECT t FROM PasswordResetToken t WHERE t.expiresAt > :now AND t.isUsed = false")
    List<PasswordResetToken> findValidTokens(@Param("now") ZonedDateTime now);
    
    // Find expired tokens
    @Query("SELECT t FROM PasswordResetToken t WHERE t.expiresAt <= :now")
    List<PasswordResetToken> findExpiredTokens(@Param("now") ZonedDateTime now);
    
    // Find used tokens
    List<PasswordResetToken> findByIsUsedTrue();
    
    // Find unused tokens
    List<PasswordResetToken> findByIsUsedFalse();
    
    // Find tokens by expiration date range
    List<PasswordResetToken> findByExpiresAtBetween(ZonedDateTime startDate, ZonedDateTime endDate);
    
    // Find tokens expiring soon (within specified time)
    @Query("SELECT t FROM PasswordResetToken t WHERE t.expiresAt BETWEEN :now AND :expiryTime")
    List<PasswordResetToken> findTokensExpiringSoon(
        @Param("now") ZonedDateTime now,
        @Param("expiryTime") ZonedDateTime expiryTime
    );
    
    // Find tokens by creation date range
    @Query("SELECT t FROM PasswordResetToken t WHERE t.user.createdAt BETWEEN :startDate AND :endDate")
    List<PasswordResetToken> findByUserCreationDateRange(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );
    
    // Find tokens by used date range
    List<PasswordResetToken> findByUsedAtBetween(ZonedDateTime startDate, ZonedDateTime endDate);
    
    // Count valid tokens for a user
    @Query("SELECT COUNT(t) FROM PasswordResetToken t WHERE t.user = :user AND t.expiresAt > :now AND t.isUsed = false")
    long countValidTokensByUser(@Param("user") User user, @Param("now") ZonedDateTime now);
    
    // Count expired tokens for a user
    @Query("SELECT COUNT(t) FROM PasswordResetToken t WHERE t.user = :user AND t.expiresAt <= :now")
    long countExpiredTokensByUser(@Param("user") User user, @Param("now") ZonedDateTime now);
    
    // Count used tokens for a user
    long countByUserAndIsUsedTrue(User user);
    
    // Count unused tokens for a user
    long countByUserAndIsUsedFalse(User user);
    
    // Delete expired tokens
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.expiresAt <= :now")
    void deleteExpiredTokens(@Param("now") ZonedDateTime now);
    
    // Delete used tokens
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.isUsed = true")
    void deleteUsedTokens();
    
    // Delete tokens by user
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.user = :user")
    void deleteByUser(@Param("user") User user);
    
    // Delete tokens by user ID
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.user.id = :userId")
    void deleteByUserId(@Param("userId") UUID userId);
    
    // Find tokens created in date range
    @Query("SELECT t FROM PasswordResetToken t WHERE t.user.createdAt BETWEEN :startDate AND :endDate")
    List<PasswordResetToken> findByCreationDateRange(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );
    
    // Find active tokens (not expired, not used, not too old)
    @Query("SELECT t FROM PasswordResetToken t WHERE t.expiresAt > :now AND t.isUsed = false AND t.user.createdAt >= :minCreationDate")
    List<PasswordResetToken> findActiveTokens(
        @Param("now") ZonedDateTime now,
        @Param("minCreationDate") ZonedDateTime minCreationDate
    );
}
