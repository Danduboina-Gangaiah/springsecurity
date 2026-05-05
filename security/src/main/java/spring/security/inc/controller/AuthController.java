package spring.security.inc.controller;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import spring.security.inc.dto.RegisterRequest;
import spring.security.inc.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.security.inc.dto.AuthResponse;
import spring.security.inc.dto.LoginRequest;
import spring.security.inc.dto.RegisterRequest;
import spring.security.inc.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
	private final AuthService authService;
	
	public AuthController(AuthService authService)
	{
		this.authService=authService;
	}

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
	
}
