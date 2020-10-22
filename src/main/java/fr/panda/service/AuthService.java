/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.service;

import fr.panda.dto.LoginRequest;
import fr.panda.dto.RegisterRequest;
import fr.panda.entities.User;
import fr.panda.repository.UserRepository;
import fr.panda.security.JwtProvider;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gtu
 */
@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword( encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        System.out.println("AuthService -> signup : ");
        System.out.println(user);
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        System.out.println("AuthService -> encodePassword : ");
        System.out.println(passwordEncoder.encode(password));
        return passwordEncoder.encode(password);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
