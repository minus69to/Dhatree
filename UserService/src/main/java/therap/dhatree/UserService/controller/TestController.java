package therap.dhatree.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import therap.dhatree.UserService.model.Doctor;
import therap.dhatree.UserService.model.User;
import therap.dhatree.UserService.repository.DoctorRepository;
import therap.dhatree.UserService.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create-doctor-profile/{userId}")
    public ResponseEntity<?> createDoctorProfile(@PathVariable String userId) {
        try {
            UUID userUuid = UUID.fromString(userId);
            User user = userRepository.findById(userUuid).orElse(null);
            
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            Doctor doctor = new Doctor();
            doctor.setUser(user);
            doctor.setFirstName("Test");
            doctor.setLastName("Doctor");
            doctor.setPhone("+1234567890");
            doctor.setSpecialization("General Medicine");

            Doctor saved = doctorRepository.save(doctor);
            
            return ResponseEntity.ok("Doctor profile created with ID: " + saved.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/users/doctors")
    public ResponseEntity<List<User>> getDoctorUsers() {
        List<User> doctorUsers = userRepository.findByUserType("doctor");
        return ResponseEntity.ok(doctorUsers);
    }
}
