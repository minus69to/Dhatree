package therap.dhatree.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therap.dhatree.UserService.model.Pregnant;
import therap.dhatree.UserService.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PregnantRepository extends JpaRepository<Pregnant, UUID> {
    
    // Find by user
    Optional<Pregnant> findByUser(User user);
    
    // Find by user ID
    Optional<Pregnant> findByUserId(UUID userId);
    
    // Find by first name and last name
    List<Pregnant> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Find by first name (partial match)
    List<Pregnant> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find by last name (partial match)
    List<Pregnant> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find by phone number
    Optional<Pregnant> findByPhone(String phone);
    
    // Find by blood group
    List<Pregnant> findByBloodGroup(String bloodGroup);
    
    // Find by date of birth range
    List<Pregnant> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    
    // Find by age range (calculated from date of birth)
    @Query("SELECT p FROM Pregnant p WHERE p.dateOfBirth BETWEEN :endDate AND :startDate")
    List<Pregnant> findByAgeRange(
        @Param("startDate") LocalDate startDate, // Older age
        @Param("endDate") LocalDate endDate      // Younger age
    );
    
    // Find by height range
    List<Pregnant> findByHeightBetween(BigDecimal minHeight, BigDecimal maxHeight);
    
    // Find by pre-pregnancy weight range
    List<Pregnant> findByPrePregnancyWeightBetween(BigDecimal minWeight, BigDecimal maxWeight);
    
    // Find by city/area (from address)
    @Query("SELECT p FROM Pregnant p WHERE p.address LIKE %:city% OR p.address LIKE %:area%")
    List<Pregnant> findByCityOrArea(@Param("city") String city, @Param("area") String area);
    
    // Find by emergency contact name
    List<Pregnant> findByEmergencyContactNameContainingIgnoreCase(String contactName);
    
    // Find by emergency contact phone
    Optional<Pregnant> findByEmergencyContactPhone(String contactPhone);
    
    // Find pregnant women with profile images
    List<Pregnant> findByProfileImageUrlIsNotNull();
    
    // Find pregnant women without profile images
    List<Pregnant> findByProfileImageUrlIsNull();
    
    // Count by blood group
    long countByBloodGroup(String bloodGroup);
    
    // Find by full name search
    @Query("SELECT p FROM Pregnant p WHERE " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Pregnant> findByFullNameSearch(@Param("searchTerm") String searchTerm);
    
    // Find pregnant women created in date range
    @Query("SELECT p FROM Pregnant p WHERE p.user.createdAt BETWEEN :startDate AND :endDate")
    List<Pregnant> findByUserCreationDateRange(
        @Param("startDate") java.time.ZonedDateTime startDate,
        @Param("endDate") java.time.ZonedDateTime endDate
    );
}
