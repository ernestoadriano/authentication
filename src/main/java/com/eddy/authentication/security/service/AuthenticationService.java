package com.eddy.authentication.security.service;

import com.eddy.authentication.dto.AuthenticationDTO;
import com.eddy.authentication.dto.AuthenticationResponse;
import com.eddy.authentication.dto.RegisterDTO;
import com.eddy.authentication.exception.NotFoundEntityException;
import com.eddy.authentication.models.User;
import com.eddy.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<AuthenticationResponse> login(AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    public ResponseEntity<String> register(RegisterDTO dto) {
        if (userRepository.findByUsername(dto.username()) != null)
            throw new NotFoundEntityException("User already exists");

        User user = new User();

        String encryptedPAssword = new BCryptPasswordEncoder().encode(dto.password());
        user.setUsername(dto.username());
        user.setPassword(encryptedPAssword);
        user.setRole(dto.role());
        userRepository.save(user);

        return ResponseEntity.ok().body("User client registered successfully!");
    }
}
