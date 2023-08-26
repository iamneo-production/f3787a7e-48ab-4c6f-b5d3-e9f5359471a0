package com.tech_tribe.authservice.controller;

import com.tech_tribe.authservice.dto.LoginDTO;
import com.tech_tribe.authservice.dto.SignupDTO;
import com.tech_tribe.authservice.exceptions.ResourceDuplicationException;
import com.tech_tribe.authservice.security.TokenGenerator;
import com.tech_tribe.authservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    @Qualifier("jwtAuthenticationProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody SignupDTO signupDTO) {
        try {
            userService.registerUser(signupDTO);
            return ResponseEntity.ok("User saved successfully");
        } catch (ResourceDuplicationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/publickey")
    public ResponseEntity<?> publicKey() {
        return ResponseEntity.ok(userService.base64PublicKey());
    }

}
