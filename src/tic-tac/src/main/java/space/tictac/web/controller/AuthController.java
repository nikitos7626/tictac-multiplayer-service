package space.tictac.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.tictac.domain.service.AuthControl;
import space.tictac.web.model.SignUpRequest;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthControl authService;
    public AuthController(AuthControl authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || authHeader.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Отсутствует заголовок Authorization");
        }
        UUID uuid = authService.auth(authHeader);
        if (uuid != null) {
            return ResponseEntity.ok(uuid);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный логин или пароль");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?>registration(@RequestBody SignUpRequest request){
        boolean success = authService.register(request);
        if(success){
            return ResponseEntity.ok("Регистрация успешна");
        }
        return ResponseEntity.badRequest().body("Логин занят");
    }
}
