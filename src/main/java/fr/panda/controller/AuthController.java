/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.controller;


import fr.panda.dto.LoginRequest;
import fr.panda.dto.RegisterRequest;
import fr.panda.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gtu
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        System.out.println("AuthController -> login : " + loginRequest.getUsername() + " " +loginRequest.getPassword());
        return authService.login(loginRequest);
    }
    
}
