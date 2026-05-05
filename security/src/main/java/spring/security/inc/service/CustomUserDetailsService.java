package spring.security.inc.service;


import lombok.RequiredArgsConstructor;
import spring.security.inc.repository.UserRepository;
import spring.security.inc.entity.*;
import spring.security.inc.entity.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	 public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	 private final UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException
	    {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User Not Found"));

	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                List.of(new SimpleGrantedAuthority(user.getRole()))
	        );
	    }
}