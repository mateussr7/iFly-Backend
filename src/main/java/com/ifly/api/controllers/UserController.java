package com.ifly.api.controllers;

import com.ifly.domain.Administrador;

import com.ifly.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("TESTE");
    }

    @PostMapping("/new")
    public ResponseEntity<String> criaUsuario(){
        Administrador filixi = new Administrador();
        filixi.setNome("Filixi");
        filixi.setLogin("adm");
        filixi.setSenha("senha");
        filixi.setCpf("33333333333");
        administradorRepository.save(filixi);
        return ResponseEntity.ok("Adicionado");
    }
}
