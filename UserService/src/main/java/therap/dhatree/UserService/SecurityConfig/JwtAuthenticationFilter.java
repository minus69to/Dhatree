package therap.dhatree.UserService.securityconfig;

import therap.dhatree.UserService.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        // If no Authorization header or doesn't start with "Bearer ", continue filter chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract token from "Bearer <token>"
            final String token = authHeader.substring(7);

            // Validate token
            if (jwtUtil.validateToken(token) && !jwtUtil.isTokenExpired(token)) {
                
                // Extract claims from token
                Claims claims = jwtUtil.extractAllClaims(token);
                String email = claims.get("email", String.class);
                String userType = claims.get("userType", String.class);
                String accountStatus = claims.get("accountStatus", String.class);

                // Check if account is active
                if ("active".equals(accountStatus)) {
                    
                    // Spring Security expects role format like: ROLE_ADMIN, ROLE_USER
                    String role = "ROLE_" + userType.toUpperCase();
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                    // Create authentication token
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(email, null, List.of(authority));

                    // Set additional details
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // Set authentication in Security Context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                    // Log successful authentication (optional)
                    logger.debug("JWT authentication successful for user: " + email + " with role: " + role);
                } else {
                    logger.warn("JWT authentication failed: Account status is not active for user: " + email);
                }
            } else {
                logger.warn("JWT authentication failed: Invalid or expired token");
            }
            
        } catch (Exception e) {
            logger.error("JWT authentication error: " + e.getMessage(), e);
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        
        // Skip JWT filtering for public endpoints
        return path.startsWith("/api/v1/user-service/auth/") ||
               path.startsWith("/api/v1/user-service/health/") ||
               path.startsWith("/api/v1/user-service/test/") ||
               path.startsWith("/api/v1/user-service/password-reset/");
    }
}
