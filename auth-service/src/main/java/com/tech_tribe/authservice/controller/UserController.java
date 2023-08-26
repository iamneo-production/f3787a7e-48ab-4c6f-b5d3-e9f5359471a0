package com.tech_tribe.authservice.controller;

import com.tech_tribe.authservice.dto.SignupDTO;
import com.tech_tribe.authservice.exceptions.ResourceDuplicationException;
import com.tech_tribe.authservice.entity.User;
import com.tech_tribe.authservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @PreAuthorize("#user.role.equals(T(com.tech_tribe.authservice.utility.UserRole).Admin)")
    public ResponseEntity<?> registerFinancialAdvisor(@AuthenticationPrincipal User user, @Valid @RequestBody SignupDTO signupDTO) {
        try {
            userService.registerFinancialAdvisor(signupDTO);
            return ResponseEntity.ok("User saved successfully");
        } catch (ResourceDuplicationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping
    @PreAuthorize("#user.role.equals(T(com.tech_tribe.authservice.utility.UserRole).Admin) || #user.role.equals(T(com.tech_tribe.authservice.utility.UserRole).Financial_Advisor)")
    public ResponseEntity<List<User>> getAllUsers(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    @PreAuthorize("#user.username == #username || #user.role.equals(T(com.tech_tribe.authservice.utility.UserRole).Financial_Advisor)")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User user, @PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

}
