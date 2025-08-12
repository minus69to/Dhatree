package therap.dhatree.UserService.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import therap.dhatree.UserService.service.AuthService;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthService.BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(AuthService.BadRequestException ex) {
        HttpStatus status = ex.getMessage() != null && ex.getMessage().equals("EMAIL_EXISTS")
                ? HttpStatus.CONFLICT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(Map.of(
                "error", Map.of(
                        "code", ex.getMessage() == null ? "BAD_REQUEST" : ex.getMessage(),
                        "message", ex.getMessage()
                )
        ));
    }

    @ExceptionHandler(AuthService.NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(AuthService.NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", Map.of(
                        "code", ex.getMessage() == null ? "NOT_FOUND" : ex.getMessage(),
                        "message", ex.getMessage()
                )
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", Map.of(
                        "code", "INTERNAL_ERROR",
                        "message", ex.getMessage()
                )
        ));
    }
}
