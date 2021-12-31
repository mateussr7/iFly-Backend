package com.ifly.api.controllers;

import com.ifly.domain.Administrador;

import com.ifly.dto.CredentialsDTO;
import com.ifly.dto.LoginDTO;
import com.ifly.dto.UsuarioDTO;
import com.ifly.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository = new UserRepository();

    @GetMapping
    public ResponseEntity<Long> teste(){
        userRepository.openConnection();
        return ResponseEntity.ok(userRepository.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginService(@RequestBody  CredentialsDTO credentials){
        userRepository.openConnection();
        LoginDTO login = userRepository.loginService(credentials);
        return login.getUser() != null ? ResponseEntity.ok(login) : ResponseEntity.badRequest().build();
    }


}
