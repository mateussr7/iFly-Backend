package com.ifly.api.controllers;

import com.ifly.services.RotaServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rota")
public class RotaController {

    RotaServices rotaServices = new RotaServices();

    @GetMapping("/getId")
    public ResponseEntity<Long> getIdRota(@RequestParam int idOrigin, @RequestParam int idDestiny){
        return ResponseEntity.ok(rotaServices.getIdRotaByOriginAndDestiny(idOrigin, idDestiny));
    }
}
