package com.ifly.api.controllers;

import com.ifly.dto.CredentialsDTO;
import com.ifly.dto.LoginDTO;
import com.ifly.dto.PassageiroDTO;
import com.ifly.services.UserServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServices userServices = new UserServices();


    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody  CredentialsDTO credentials) throws IllegalAccessException {
        LoginDTO login = userServices.login(credentials);
        return login.getUser() != null ? ResponseEntity.ok(login) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/new-passenger")
    public ResponseEntity<PassageiroDTO> insert(@RequestBody PassageiroDTO dto){
        PassageiroDTO passageiro = userServices.registerNewPassenger(dto);
        return passageiro != null ? ResponseEntity.ok(passageiro) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<PassageiroDTO>> passengerRanking(){
        List<PassageiroDTO> passageiros = userServices.getPassengerRanking();
        return ResponseEntity.ok(passageiros);
    }

}
