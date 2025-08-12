package therap.dhatree.UserService.securityconfig;

import therap.dhatree.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints - no authentication required
                        .requestMatchers("/api/v1/user-service/auth/**").permitAll()
                        .requestMatchers("/api/v1/user-service/health/**").permitAll()
                        .requestMatchers("/api/v1/user-service/test/**").permitAll()
                        
                        // Admin endpoints - ADMIN role required
                        .requestMatchers("/api/v1/user-service/admin/**").hasRole("ADMIN")
                        
                        // User management endpoints - USER or ADMIN role required
                        .requestMatchers("/api/v1/user-service/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/user-service/pregnant/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/user-service/partner/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/user-service/doctor/**").hasAnyRole("USER", "ADMIN")
                        
                        // Password reset endpoints - public
                        .requestMatchers("/api/v1/user-service/password-reset/**").permitAll()
                        
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Allowed origins
        config.setAllowedOrigins(List.of(
            "http://localhost:3000", 
            "http://localhost:8080",
            "http://localhost:8081",
            "http://74.225.246.127:3000"
        ));
        
        // Allowed methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        // Allowed headers
        config.setAllowedHeaders(List.of("*"));
        
        // Allow credentials
        config.setAllowCredentials(true);
        
        // Expose headers
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));
        
        // Max age for preflight requests
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
