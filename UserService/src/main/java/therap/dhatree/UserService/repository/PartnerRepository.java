package therap.dhatree.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therap.dhatree.UserService.entity.Partner;
import therap.dhatree.UserService.entity.Pregnant;
import therap.dhatree.UserService.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID> {
    
    // Find by user
    Optional<Partner> findByUser(User user);
    
    // Find by user ID
    Optional<Partner> findByUserId(UUID userId);
    
    // Find by pregnant woman
    List<Partner> findByPregnant(Pregnant pregnant);
    
    // Find by pregnant woman ID
    List<Partner> findByPregnantId(UUID pregnantId);
    
    // Find by first name and last name
    List<Partner> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Find by first name (partial match)
    List<Partner> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find by last name (partial match)
    List<Partner> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find by phone number
    Optional<Partner> findByPhone(String phone);
    
    // Find by relationship to mother
    List<Partner> findByRelationshipToMother(String relationship);
    
    // Find by relationship to mother (partial match)
    List<Partner> findByRelationshipToMotherContainingIgnoreCase(String relationship);
    
    // Find by date of birth range
    List<Partner> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    
    // Find by age range (calculated from date of birth)
    @Query("SELECT p FROM Partner p WHERE p.dateOfBirth BETWEEN :endDate AND :startDate")
    List<Partner> findByAgeRange(
        @Param("startDate") LocalDate startDate, // Older age
        @Param("endDate") LocalDate endDate      // Younger age
    );
    
    // Find partners by pregnant woman's blood group
    @Query("SELECT p FROM Partner p WHERE p.pregnant.bloodGroup = :bloodGroup")
    List<Partner> findByPregnantBloodGroup(@Param("bloodGroup") String bloodGroup);
    
    // Find partners by pregnant woman's city/area
    @Query("SELECT p FROM Partner p WHERE p.pregnant.address LIKE %:city% OR p.pregnant.address LIKE %:area%")
    List<Partner> findByPregnantCityOrArea(@Param("city") String city, @Param("area") String area);
    
    // Find partners with specific relationship type
    List<Partner> findByRelationshipToMotherIn(List<String> relationships);
    
    // Find partners by full name search
    @Query("SELECT p FROM Partner p WHERE " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Partner> findByFullNameSearch(@Param("searchTerm") String searchTerm);
    
    // Find partners by pregnant woman's name search
    @Query("SELECT p FROM Partner p WHERE " +
           "LOWER(p.pregnant.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.pregnant.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Partner> findByPregnantNameSearch(@Param("searchTerm") String searchTerm);
    
    // Count by relationship type
    long countByRelationshipToMother(String relationship);
    
    // Find partners created in date range
    @Query("SELECT p FROM Partner p WHERE p.user.createdAt BETWEEN :startDate AND :endDate")
    List<Partner> findByUserCreationDateRange(
        @Param("startDate") java.time.ZonedDateTime startDate,
        @Param("endDate") java.time.ZonedDateTime endDate
    );
    
    // Find partners by pregnant woman's emergency contact
    @Query("SELECT p FROM Partner p WHERE p.pregnant.emergencyContactName LIKE %:contactName%")
    List<Partner> findByPregnantEmergencyContact(@Param("contactName") String contactName);
}
