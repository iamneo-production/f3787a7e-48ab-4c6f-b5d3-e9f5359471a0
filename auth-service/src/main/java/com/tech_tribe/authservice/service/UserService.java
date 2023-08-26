package com.tech_tribe.authservice.service;

import com.tech_tribe.authservice.dto.SignupDTO;
import com.tech_tribe.authservice.dto.UserDTO;
import com.tech_tribe.authservice.entity.User;
import com.tech_tribe.authservice.exceptions.ResourceDuplicationException;
import com.tech_tribe.authservice.exceptions.ResourceNotFoundException;
import com.tech_tribe.authservice.repository.UserRepository;
import com.tech_tribe.authservice.security.KeyUtils;
import com.tech_tribe.authservice.utility.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyUtils keyUtils;

    public void registerUser(SignupDTO signupDTO) {
        User newUser = new User(
                signupDTO.getUsername(),
                signupDTO.getFirstname(),
                signupDTO.getLastname(),
                signupDTO.getAge(),
                signupDTO.getAddress(),
                signupDTO.getContactNumber(),
                signupDTO.getNic(),
                signupDTO.getEmail(),
                UserRole.User,
                signupDTO.getPassword());

        if (userDetailsManager.userExists(newUser.getUsername()) || userRepository.existsByEmail(signupDTO.getEmail()) || userRepository.existsByNic(signupDTO.getEmail())) {
            throw new ResourceDuplicationException(String.format("User email %s or username %s is already exists", signupDTO.getEmail(), signupDTO.getUsername()));
        }
        userDetailsManager.createUser(newUser);
    }

    public void registerFinancialAdvisor(SignupDTO signupDTO) {
        User newUser = new User(
                signupDTO.getUsername(),
                signupDTO.getFirstname(),
                signupDTO.getLastname(),
                signupDTO.getAge(),
                signupDTO.getAddress(),
                signupDTO.getContactNumber(),
                signupDTO.getNic(),
                signupDTO.getEmail(),
                UserRole.Financial_Advisor,
                signupDTO.getPassword());

        if (userDetailsManager.userExists(newUser.getUsername()) || userRepository.existsByEmail(signupDTO.getEmail()) || userRepository.existsByNic(signupDTO.getEmail())) {
            throw new ResourceDuplicationException(String.format("User email %s or username %s is already exists", signupDTO.getEmail(), signupDTO.getUsername()));
        }
        userDetailsManager.createUser(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserByUsername(String username) {
        return UserDTO.from((User) userDetailsManager.loadUserByUsername(username));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        Optional<User> getUser = userRepository.findByUsername(userDTO.getUsername());

        if (getUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User %s not found", userDTO.getUsername()));
        }

        User user = getUser.get();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        userDetailsManager.updateUser(user);

        return UserDTO.from(user);

    }

    public boolean deleteUser(String username) {
        Optional<User> getUser = userRepository.findByUsername(username);
        if (getUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User %s not found", username));
        }
        userRepository.delete(getUser.get());
        return true;
    }

    public String base64PublicKey() {
        try {
            return keyUtils.getBase64PublicKey();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
