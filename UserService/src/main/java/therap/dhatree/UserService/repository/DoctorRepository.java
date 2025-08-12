package therap.dhatree.UserService.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import therap.dhatree.UserService.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    List<Doctor> findByConsultationFeeBetween(BigDecimal minFee, BigDecimal maxFee);
}
