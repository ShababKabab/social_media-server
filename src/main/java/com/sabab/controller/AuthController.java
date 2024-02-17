package com.sabab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabab.config.JwtProvider;
import com.sabab.models.User;
import com.sabab.repository.UserRepository;
import com.sabab.request.LoginRequest;
import com.sabab.response.AuthResponse;
import com.sabab.service.CustomerUserDetailsService;
import com.sabab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;

	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		if(userRepository.findByEmail(user.getEmail())!=null) {
			throw new Exception("This email is already used with another account");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res= new AuthResponse(token,"Register Success");
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) throws Exception {
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res= new AuthResponse(token,"Login Success");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password wrong");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
