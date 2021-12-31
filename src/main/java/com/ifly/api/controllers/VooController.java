package com.ifly.api.controllers;

import com.ifly.dto.VooDTO;
import com.ifly.services.VooServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/voo")
public class VooController {
    VooServices vooServices = new VooServices();

    @GetMapping()
    public ResponseEntity<List<VooDTO>> getVoos(@RequestParam String origin,
                                                @RequestParam String destiny,
                                                @RequestParam Timestamp date){
        return ResponseEntity.ok(vooServices.getVoos(origin, destiny, date));
    }

}
