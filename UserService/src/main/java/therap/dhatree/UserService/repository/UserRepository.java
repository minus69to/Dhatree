package therap.dhatree.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therap.dhatree.UserService.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    // Find by email
    Optional<User> findByEmail(String email);
    
    // Find by email and isActive
    Optional<User> findByEmailAndIsActiveTrue(String email);
    
    // Find by user type
    List<User> findByUserType(String userType);
    
    // Find by account status
    List<User> findByAccountStatus(String accountStatus);
    
    // Find by auth provider
    List<User> findByAuthProvider(String authProvider);
    
    // Find by Google ID
    Optional<User> findByGoogleId(String googleId);
    
    // Find active users
    List<User> findByIsActiveTrue();
    
    // Find users by email verification status
    List<User> findByEmailVerified(Boolean emailVerified);
    
    // Find users by user type and account status
    List<User> findByUserTypeAndAccountStatus(String userType, String accountStatus);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Check if Google ID exists
    boolean existsByGoogleId(String googleId);
    
    // Find users by last login (recent activity)
    @Query("SELECT u FROM User u WHERE u.lastLogin >= :since ORDER BY u.lastLogin DESC")
    List<User> findUsersWithRecentActivity(@Param("since") java.time.ZonedDateTime since);
    
    // Find users by creation date range
    @Query("SELECT u FROM User u WHERE u.createdAt BETWEEN :startDate AND :endDate")
    List<User> findUsersByCreationDateRange(
        @Param("startDate") java.time.ZonedDateTime startDate,
        @Param("endDate") java.time.ZonedDateTime endDate
    );
    
    // Count users by user type
    long countByUserType(String userType);
    
    // Count active users
    long countByIsActiveTrue();
    
    // Find users by email pattern (for search)
    @Query("SELECT u FROM User u WHERE u.email LIKE %:emailPattern%")
    List<User> findUsersByEmailPattern(@Param("emailPattern") String emailPattern);
}
