package therap.dhatree.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import therap.dhatree.UserService.entity.Doctor;
import therap.dhatree.UserService.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    
    // Find by user
    Optional<Doctor> findByUser(User user);
    
    // Find by user ID
    Optional<Doctor> findByUserId(UUID userId);
    
    // Find by first name and last name
    List<Doctor> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Find by first name (partial match)
    List<Doctor> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find by last name (partial match)
    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find by phone number
    Optional<Doctor> findByPhone(String phone);
    
    // Find by license number
    Optional<Doctor> findByLicenseNumber(String licenseNumber);
    
    // Find by specialization
    List<Doctor> findBySpecialization(String specialization);
    
    // Find by specialization (partial match)
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);
    
    // Find by experience years range
    List<Doctor> findByExperienceYearsBetween(Integer minYears, Integer maxYears);
    
    // Find by experience years (minimum)
    List<Doctor> findByExperienceYearsGreaterThanEqual(Integer minYears);
    
    // Find by consultation fee range
    List<Doctor> findByConsultationFeeBetween(BigDecimal minFee, BigDecimal maxFee);
    
    // Find by consultation fee (maximum)
    List<Doctor> findByConsultationFeeLessThanEqual(BigDecimal maxFee);
    
    // Find by hospital affiliation
    List<Doctor> findByHospitalAffiliationContainingIgnoreCase(String hospital);
    
    // Find by date of birth range
    List<Doctor> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    
    // Find by age range (calculated from date of birth)
    @Query("SELECT d FROM Doctor d WHERE d.dateOfBirth BETWEEN :endDate AND :startDate")
    List<Doctor> findByAgeRange(
        @Param("startDate") LocalDate startDate, // Older age
        @Param("endDate") LocalDate endDate      // Younger age
    );
    
    // Find doctors with profile images
    List<Doctor> findByProfileImageUrlIsNotNull();
    
    // Find doctors without profile images
    List<Doctor> findByProfileImageUrlIsNull();
    
    // Find by city/area (from address)
    @Query("SELECT d FROM Doctor d WHERE d.address LIKE %:city% OR d.address LIKE %:area%")
    List<Doctor> findByCityOrArea(@Param("city") String city, @Param("area") String area);
    
    // Find doctors by qualification
    @Query("SELECT d FROM Doctor d WHERE :qualification = ANY(d.qualification)")
    List<Doctor> findByQualification(@Param("qualification") String qualification);
    
    // Find doctors by multiple qualifications
    @Query("SELECT d FROM Doctor d WHERE EXISTS (SELECT 1 FROM d.qualification q WHERE q IN :qualifications)")
    List<Doctor> findByQualificationsIn(@Param("qualifications") List<String> qualifications);
    
    // Find doctors by full name search
    @Query("SELECT d FROM Doctor d WHERE " +
           "LOWER(d.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(d.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(CONCAT(d.firstName, ' ', d.lastName)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Doctor> findByFullNameSearch(@Param("searchTerm") String searchTerm);
    
    // Find doctors by specialization and experience
    List<Doctor> findBySpecializationAndExperienceYearsGreaterThanEqual(String specialization, Integer minYears);
    
    // Find doctors by specialization and consultation fee range
    List<Doctor> findBySpecializationAndConsultationFeeBetween(String specialization, BigDecimal minFee, BigDecimal maxFee);
    
    // Count by specialization
    long countBySpecialization(String specialization);
    
    // Count by experience range
    long countByExperienceYearsBetween(Integer minYears, Integer maxYears);
    
    // Find doctors created in date range
    @Query("SELECT d FROM Doctor d WHERE d.user.createdAt BETWEEN :startDate AND :endDate")
    List<Doctor> findByUserCreationDateRange(
        @Param("startDate") java.time.ZonedDateTime startDate,
        @Param("endDate") java.time.ZonedDateTime endDate
    );
    
    // Find top doctors by experience
    List<Doctor> findTop10ByOrderByExperienceYearsDesc();
    
    // Find doctors by consultation fee (ascending order)
    List<Doctor> findTop20ByOrderByConsultationFeeAsc();
}
