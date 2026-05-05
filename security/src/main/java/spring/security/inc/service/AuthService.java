package spring.security.inc.service;

import spring.security.inc.dto.*;
import  spring.security.inc.entity.User;
import  spring.security.inc.jwt.JwtService;
import  spring.security.inc.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
	 private  UserRepository userRepository;
	 private  PasswordEncoder passwordEncoder;
	 private  AuthenticationManager authenticationManager;
	 private  JwtService jwtService;
	 private  CustomUserDetailsService userDetailsService;

    public UserRepository getUserRepository() {
		return userRepository;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public JwtService getJwtService() {
		return jwtService;
	}

	public CustomUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService,
			CustomUserDetailsService userDetailsService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}


    public String register(RegisterRequest request)
    {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public AuthResponse login(LoginRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtService.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }
}