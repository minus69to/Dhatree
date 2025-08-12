package therap.dhatree.UserService.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import therap.dhatree.UserService.entity.Partner;
import therap.dhatree.UserService.entity.Pregnant;
import therap.dhatree.UserService.entity.User;
import therap.dhatree.UserService.repository.PartnerRepository;
import therap.dhatree.UserService.repository.PregnantRepository;
import therap.dhatree.UserService.repository.UserRepository;
import therap.dhatree.UserService.web.dto.AuthDtos;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PregnantRepository pregnantRepository;
    private final PartnerRepository partnerRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository,
            PregnantRepository pregnantRepository,
            PartnerRepository partnerRepository,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.pregnantRepository = pregnantRepository;
        this.partnerRepository = partnerRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest req) {
        validateRegisterRequest(req);

        String email = req.email.trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("EMAIL_EXISTS");
        }

        User user = new User();
        user.setEmail(email);
        user.setUserType(mapUserType(req.userType));
        user.setAuthProvider("local");
        user.setEmailVerified(false);
        user.setIsActive(true);
        user.setAccountStatus("pending");
        user.setPasswordHash(passwordEncoder.encode(req.password));
        user = userRepository.save(user);

        switch (user.getUserType()) {
            case "patient" ->
                createPregnantProfile(user, req.profile);
            case "partner" ->
                createPartnerProfile(user, req.profile);
            case "doctor" -> {
                /* No profile row in SQL linked to users */ }
            default ->
                throw new BadRequestException("INVALID_USER_TYPE");
        }

        AuthDtos.UserView view = toView(user);
        AuthDtos.AuthResponse resp = new AuthDtos.AuthResponse();
        resp.user = view;
        resp.accessToken = jwtService.generateAccessToken(
                user.getId().toString(), user.getEmail(), user.getUserType(), user.getAuthProvider(), 900);
        resp.refreshToken = jwtService.generateAccessToken(
                user.getId().toString(), user.getEmail(), user.getUserType(), user.getAuthProvider(), 604800);
        return resp;
    }

    private String mapUserType(String input) {
        if (input == null) {
            return null;
        }
        String v = input.trim().toLowerCase();
        if (v.equals("pregnant")) {
            return "patient"; // align to API term
        }
        return v;
    }

    private void validateRegisterRequest(AuthDtos.RegisterRequest req) {
        if (req == null) {
            throw new BadRequestException("INVALID_INPUT");
        }
        if (req.email == null || req.email.trim().isEmpty()) {
            throw new BadRequestException("INVALID_INPUT: email");
        }
        if (req.password == null || req.password.trim().isEmpty()) {
            throw new BadRequestException("INVALID_INPUT: password");
        }
        if (req.userType == null || req.userType.trim().isEmpty()) {
            throw new BadRequestException("INVALID_INPUT: userType");
        }
        String t = req.userType.toLowerCase();
        if (!(t.equals("patient") || t.equals("partner") || t.equals("doctor") || t.equals("pregnant"))) {
            throw new BadRequestException("INVALID_USER_TYPE");
        }
        if (!t.equals("doctor")) {
            if (req.profile == null) {
                throw new BadRequestException("INVALID_INPUT: profile");
            }
        }
        if (t.equals("patient") || t.equals("pregnant")) {
            if (req.profile.first_name == null || req.profile.first_name.trim().isEmpty()
                    || req.profile.last_name == null || req.profile.last_name.trim().isEmpty()) {
                throw new BadRequestException("INVALID_INPUT: first_name/last_name");
            }
        }
        if (t.equals("partner")) {
            if (req.profile.first_name == null || req.profile.first_name.trim().isEmpty()) {
                throw new BadRequestException("INVALID_INPUT: first_name");
            }
            if (req.profile.partner_id == null || req.profile.partner_id.trim().isEmpty()) {
                throw new BadRequestException("INVALID_INPUT: partner_id");
            }
        }
    }

    private void createPregnantProfile(User user, AuthDtos.Profile profile) {
        Pregnant p = new Pregnant();
        p.setUser(user);
        p.setFirstName(profile.first_name);
        p.setLastName(profile.last_name);
        p.setPhone(profile.phone);
        p.setDateOfBirth(profile.date_of_birth);
        p.setProfileImageUrl(profile.profile_image_url);
        p.setAddress(profile.address);
        p.setEmergencyContactName(profile.emergency_contact_name);
        p.setEmergencyContactPhone(profile.emergency_contact_phone);
        p.setBloodGroup(profile.blood_group);
        p.setHeight(profile.height);
        p.setPrePregnancyWeight(profile.pre_pregnancy_weight);
        pregnantRepository.save(p);
    }

    private void createPartnerProfile(User user, AuthDtos.Profile profile) {
        Partner p = new Partner();
        p.setUser(user);
        p.setFirstName(profile.first_name);
        p.setLastName(profile.last_name);
        p.setPhone(profile.phone);
        p.setDateOfBirth(profile.date_of_birth);
        p.setRelationshipToMother(profile.relationship_to_mother);

        // Find the pregnant record by user ID (since that's what we get from registration)
        UUID userId = UUID.fromString(profile.partner_id);
        Pregnant pregnant = pregnantRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("PARTNER_NOT_FOUND"));
        p.setPregnant(pregnant);
        partnerRepository.save(p);
    }

    private AuthDtos.UserView toView(User u) {
        AuthDtos.UserView v = new AuthDtos.UserView();
        v.id = u.getId() != null ? u.getId().toString() : null;
        v.email = u.getEmail();
        v.userType = u.getUserType();
        v.authProvider = u.getAuthProvider();
        v.emailVerified = Boolean.TRUE.equals(u.getEmailVerified());
        v.isActive = Boolean.TRUE.equals(u.getIsActive());
        v.accountStatus = u.getAccountStatus();
        v.lastLogin = u.getLastLogin() != null ? u.getLastLogin().toString() : null;
        return v;
    }

    public static class BadRequestException extends RuntimeException {

        public BadRequestException(String m) {
            super(m);
        }
    }

    public static class NotFoundException extends RuntimeException {

        public NotFoundException(String m) {
            super(m);
        }
    }
}
