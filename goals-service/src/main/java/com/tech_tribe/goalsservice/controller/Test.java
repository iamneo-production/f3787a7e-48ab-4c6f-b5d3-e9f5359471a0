package com.tech_tribe.goalsservice.controller;

import com.tech_tribe.goalsservice.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping
    @PreAuthorize("#user.role.equals(T(com.tech_tribe.goalsservice.utility.UserRole).Admin)")
    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("Goal service Test 123! "+user.getRole());
    }
}
