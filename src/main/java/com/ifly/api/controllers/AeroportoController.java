package com.ifly.api.controllers;

import com.ifly.dto.AeroportoDTO;
import com.ifly.services.AeroportoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AeroportoController {

    private AeroportoServices aeroportoServices = new AeroportoServices();

    @GetMapping
    public ResponseEntity<List<AeroportoDTO>> getAll(){
        return ResponseEntity.ok(aeroportoServices.getAllAirports());
    }

}
