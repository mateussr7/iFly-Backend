package com.ifly.api.controllers;


import com.ifly.services.RotaServices;
import com.ifly.utils.OrigDestRota;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rota")
public class RotaController {

    RotaServices rotaServices = new RotaServices();

    @GetMapping("/getOrigDest")
    public ResponseEntity<OrigDestRota> getOrigDestByRotaId(@RequestParam Long id){
        OrigDestRota origDestRota = new OrigDestRota();
        origDestRota = rotaServices.getOrigDestByRotaId(id);
        return ResponseEntity.ok(origDestRota);
    }
    @GetMapping("/getId")
    public ResponseEntity<Long> getIdRota(@RequestParam int idOrigin, @RequestParam int idDestiny){
        return ResponseEntity.ok(rotaServices.getIdRotaByOriginAndDestiny(idOrigin, idDestiny));
    }
}
