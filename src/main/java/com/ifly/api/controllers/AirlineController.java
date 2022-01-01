package com.ifly.api.controllers;

import com.ifly.dto.EmpresaAereaDTO;
import com.ifly.services.AirlineServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

    AirlineServices airlineServices = new AirlineServices();

    @PutMapping
    public ResponseEntity<EmpresaAereaDTO> updateEmpresaAerea(@RequestBody EmpresaAereaDTO empresaAereaDTO){
        return ResponseEntity.ok(airlineServices.updateEmpresaAerea(empresaAereaDTO));
    }

    @PostMapping
    public ResponseEntity<EmpresaAereaDTO> insertEmpresaAerea(@RequestBody EmpresaAereaDTO empresaAereaDTO){
        return ResponseEntity.ok(airlineServices.insertEmpresaAerea(empresaAereaDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmpresaAereaDTO>> getAll(){
        return ResponseEntity.ok(airlineServices.getAllAirlines());
    }
}
