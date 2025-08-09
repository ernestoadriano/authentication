package com.eddy.authentication.controller;

import com.eddy.authentication.dto.AuthenticationDTO;
import com.eddy.authentication.dto.AuthenticationResponse;
import com.eddy.authentication.dto.RegisterDTO;
import com.eddy.authentication.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationDTO dto) {
        return authenticationService.login(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        return authenticationService.register(dto);
    }
}
