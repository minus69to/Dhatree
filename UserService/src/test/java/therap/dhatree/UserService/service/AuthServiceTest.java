package therap.dhatree.UserService.service;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import therap.dhatree.UserService.entity.Pregnant;
import therap.dhatree.UserService.entity.User;
import therap.dhatree.UserService.repository.DoctorRepository;
import therap.dhatree.UserService.repository.PartnerRepository;
import therap.dhatree.UserService.repository.PregnantRepository;
import therap.dhatree.UserService.repository.UserRepository;
import therap.dhatree.UserService.web.dto.AuthDtos;

public class AuthServiceTest {

    private UserRepository userRepository;
    private PregnantRepository pregnantRepository;
    private PartnerRepository partnerRepository;
    private DoctorRepository doctorRepository;
    private JwtService jwtService;

    private AuthService authService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        pregnantRepository = mock(PregnantRepository.class);
        partnerRepository = mock(PartnerRepository.class);
        doctorRepository = mock(DoctorRepository.class);
        jwtService = mock(JwtService.class);

        authService = new AuthService(userRepository, pregnantRepository, partnerRepository, jwtService);
    }

    @Test
    void register_patient_success() {
        AuthDtos.RegisterRequest req = new AuthDtos.RegisterRequest();
        req.email = "test@example.com";
        req.password = "Passw0rd!";
        req.userType = "patient";
        req.profile = new AuthDtos.Profile();
        req.profile.first_name = "Jane";
        req.profile.last_name = "Doe";

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(jwtService.generateAccessToken(any(), any(), any(), any(), anyLong())).thenReturn("jwt");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(UUID.randomUUID());
            return u;
        });

        AuthDtos.AuthResponse resp = authService.register(req);

        assertNotNull(resp);
        assertNotNull(resp.user);
        assertEquals("test@example.com", resp.user.email);
        assertEquals("patient", resp.user.userType);
        assertEquals("jwt", resp.accessToken);
        assertEquals("jwt", resp.refreshToken);

        verify(pregnantRepository, times(1)).save(any(Pregnant.class));
    }

    @Test
    void register_duplicate_email_conflict() {
        AuthDtos.RegisterRequest req = new AuthDtos.RegisterRequest();
        req.email = "dup@example.com";
        req.password = "Passw0rd!";
        req.userType = "patient";
        req.profile = new AuthDtos.Profile();
        req.profile.first_name = "Jane";
        req.profile.last_name = "Doe";

        when(userRepository.existsByEmail("dup@example.com")).thenReturn(true);

        AuthService.BadRequestException ex = assertThrows(AuthService.BadRequestException.class, () -> authService.register(req));
        assertEquals("EMAIL_EXISTS", ex.getMessage());
        verify(userRepository, never()).save(any());
    }
}
